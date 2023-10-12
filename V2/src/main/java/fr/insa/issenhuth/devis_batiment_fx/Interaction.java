package fr.insa.issenhuth.devis_batiment_fx;

import Modele.Coins;
import Modele.Element;
import Modele.Groupe;
import Modele.Maison;
import Modele.Mur;
import Modele.Piece;
import Modele.Revetement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author cidmo
 */
public class Interaction {
    
    Dessin_2D view;    
    int state ;    
    List<Element> selectionToSupr;
    Coins coinPourMur;
    Mur murEnCours;
    Coins coinDebutMur;
    Coins coinsAlligne;
    List<Coins> topiece;
    Piece Pselc;
    
    
/* state :     1) Création de coins
               2) Selection pour supprimer     
               3) Création Mur  
                    31: Pose du 2ème coins
               4) Création de pièce
               5) Application des revêtement
                    51: au plafond  
                    52: au sol
                    53: au mur 1
                    54: au mur 2
                    55: au mur 3
                    56: au mur 4
*/
    public Interaction(Dessin_2D view)
    {
       this.view = view;
       this.selectionToSupr= new ArrayList<>();
       this.topiece = new ArrayList<>();
    }
        
    public void statechanger(int newstate)      // Changement d'état selon le bouton utilisée et action en conséquence:
    {
        if(newstate==1||newstate==3)
        {
            this.view.getDelete().setDisable(true);
            this.view.Creer.setDisable(true);
            this.view.Detruire.setDisable(true);
            this.murEnCours = null;
            
        } else if(this.state==2)                
                {
                    this.view.Creer.setDisable(true);
                    this.view.Detruire.setDisable(true);
                }       
            this.state=newstate;                   
    }
    
    void Coins(ActionEvent a)
    {
        this.statechanger(1);
    }    
    void Selectionner(ActionEvent a)
    {
        this.statechanger(2);
    }
    void Mur(ActionEvent a)
    {
        this.statechanger(3);                      
    }
    void Piece (ActionEvent a)
    {
        this.statechanger(4);
    }    
    void btPlafond() {
        this.statechanger(51);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier();
        this.view.RevInfo.getItems().clear();
        if(this.Pselc!=null && (!this.Pselc.getP().getRevetement().isEmpty()))
        { 
            for(Revetement rev: this.Pselc.getP().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        } else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    void btSol() {
        this.statechanger(52);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier();
        this.view.RevInfo.getItems().clear();
                if(this.Pselc!=null && (!this.Pselc.getS().getRevetement().isEmpty()))
        { 
            for(Revetement rev: this.Pselc.getS().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        }else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    void Mur1() {
        this.statechanger(53);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier();
        this.view.RevInfo.getItems().clear();
                if(this.Pselc!=null && (!this.Pselc.getM1().getRevetement().isEmpty()))
        { 
            for(Revetement rev: this.Pselc.getM1().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        }else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    void Mur2() {
        this.statechanger(54);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier();
        this.view.RevInfo.getItems().clear();
                if(this.Pselc!=null && (!this.Pselc.getM2().getRevetement().isEmpty()))
        {
            for(Revetement rev: this.Pselc.getM2().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        }else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    void Mur3() {
        this.statechanger(55);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier(); 
        this.view.RevInfo.getItems().clear();
                if(this.Pselc!=null && (!this.Pselc.getM3().getRevetement().isEmpty()))
        {
            for(Revetement rev: this.Pselc.getM3().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        }else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    void Mur4() {
        this.statechanger(56);
        this.ActiveBoutonAppliquer();
        this.ActiveBoutonRetier(); 
        this.view.RevInfo.getItems().clear();
                if(this.Pselc!=null && (!this.Pselc.getM4().getRevetement().isEmpty()))
        { 
            for(Revetement rev: this.Pselc.getM4().getRevetement())
            {
                this.view.RevInfo.getItems().add(new RecapRevPiece(rev.getDesignation()));
            }
        }else
        {
                this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
        }
    }
    
    // Action quand un clic dans la zone de dessin à lieu
    void hitindrawzone(MouseEvent e) {
        if(this.state==1){
        double px = e.getX();
        double py = e.getY();        
        Groupe model= this.view.getModel();
        if(new Coins(px,py,model.getContenant().size()).testCoinsEnDouble(model.getContenant()))        
                {
                   model.add(new Coins(px,py,model.getContenant().size()));  
                   this.view.redrawAll();
                }        
        } else if(this.state==3)
            {    
                if(this.coinPourMur!=null)                                      // Regarde si il y a un point proche pour le prendre comme apuie
             {
                 this.coinDebutMur = this.coinPourMur;
               }
                    else{
                       this.coinDebutMur = this.posInModel(e.getX(), e.getY());  
                     }                          
        this.murEnCours = new Mur(this.coinDebutMur,
        new Coins(this.coinDebutMur) );       
        this.statechanger(31);
        
        } 
        else if (this.state==31)
        {
            Coins p=null;               // Contrôle pour avoir uniquement des murs droits et possibilités de terminer le mur avec un coins existant ou 
                                        // de le positionner alligné avec un autre points
            if(Math.abs(e.getY()-(this.murEnCours.getBeg().getCy()))< Math.abs(e.getX()-(this.murEnCours.getBeg().getCx())))
            { 
                if(this.coinsAlligne!=null){
                 p = this.posInModel(this.coinsAlligne.getCx(),this.coinDebutMur.getCy() );
                              } else {
                     p = this.posInModel(e.getX(),this.coinDebutMur.getCy() );
                }                
                Coins pclic =new Coins(p.getCx(),p.getCy(),this.view.model.getContenant().size());
                Coins pclic0 =new Coins(this.coinDebutMur.getCx(),this.coinDebutMur.getCy(),this.view.model.getContenant().size());               
                  
                this.view.model.add(pclic); 
                this.view.model.add(pclic0);       
                Mur ns = new Mur(pclic0, pclic);
                this.view.getModel().add(ns);
                this.murEnCours = null;
            }
            else
            {
                if(this.coinsAlligne!=null)
                {
                     p = this.posInModel(this.murEnCours.getBeg().getCx(),this.coinsAlligne.getCy() );
                }else {
                    p = this.posInModel(this.murEnCours.getBeg().getCx(),e.getY() ); 
                }                
                Coins pclic =new Coins(p.getCx(),p.getCy(),this.view.model.getContenant().size());
                Coins pclic0 =new Coins(this.coinDebutMur.getCx(),this.coinDebutMur.getCy(),this.view.model.getContenant().size());               
                 
                this.view.model.add(pclic);
                this.view.model.add(pclic0);       
                Mur ns = new Mur(pclic0, pclic);
                this.view.getModel().add(ns);
                this.murEnCours = null;
            }            
            this.view.redrawAll();
            this.coinsAlligne=null;
            this.statechanger(3);
            
        } else if(this.state==2)
        {
            Coins pclic = new Coins(e.getX(), e.getY());
            Element proche = this.view.getModel().nearest(pclic, Double.MAX_VALUE);
               if (proche != null) {
                if (e.isShiftDown()&&(!this.selectionToSupr.contains(proche))) {
                    this.selectionToSupr.add(proche);
                } else if (e.isControlDown()) {
                    if (this.selectionToSupr.contains(proche)) {
                        this.selectionToSupr.remove(proche);
                    } else {
                        this.selectionToSupr.add(proche);
                    }
                } else {
                    this.selectionToSupr.clear();
                    this.selectionToSupr.add(proche);
                }
                this.ActiveBoutonSupprimer();
                this.view.redrawAll();
                System.out.println(selectionToSupr);               
            }
        }
        else if(this.state==4)
        {            
            Coins proche = this.coinPourMur;         
               if (proche != null) {
                if (e.isShiftDown()) {
                    this.topiece.add(proche);
                } else if (e.isControlDown()) {
                    if (this.topiece.contains(proche)) {
                        this.topiece.remove(proche);
                    } else {
                        this.topiece.add(proche);
                    }
                } else {
                    this.topiece.clear();
                    this.topiece.add(proche);
                }
                this.ActiveBoutonDetruire();
                this.ActiveBoutonCreer();
                this.view.redrawAll();

            } else{this.topiece.clear();this.view.redrawAll();}
   
        } else if(this.state==5||this.state==51||this.state==52||this.state==53||this.state==54||this.state==55||this.state==56)
        {
        this.Pselc=null;
        this.view.redrawAll();

            for(Piece p: this.view.lPiece)
                if(p.mouseInPiece(e.getX(), e.getY()))
                {
                    this.Pselc=p;
                    this.view.redrawAll();
                }   
            this.statechanger(5);
            this.ActiveBoutonAppliquer();
            this.ActiveBoutonRetier();
            this.view.Plafond.getToggleGroup().selectToggle(null);           
            this.view.RevInfo.getItems().clear();
            this.view.RevInfo.getItems().add(new RecapRevPiece("Selectionner un élément"));
            
        }
    }
    
   void mouseMovedDansZoneDessin(MouseEvent t) {            // Action quand souris bougée dans la zone de dessin
        Label test = new Label();
        Coins psouris = new Coins(t.getX(), t.getY());
         if (this.state == 31) {
           // attente deuxieme point segment
           if(Math.abs(t.getY()-(this.murEnCours.getBeg().getCy()))< Math.abs(t.getX()-(this.murEnCours.getBeg().getCx())))
           {    
              this.murEnCours.setEnd(this.posInModel(t.getX(), this.murEnCours.getBeg().getCy()));
              this.coinsAlligne=psouris.pointAlligneX(this.view.model.getContenant());
              this.view.redrawAll();                
              
              DoubleProperty td = new SimpleDoubleProperty( (double)Math.round( Math.abs( ((this.murEnCours.getBeg().getCx()-t.getX())*this.view.batimentActif.getLongueurBase())/995 )*100 )/100 );
              test.textProperty().bind(td.asString());
              this.view.setBottom(test);                                
            }
            else
            {                
              this.murEnCours.setEnd(this.posInModel(this.murEnCours.getBeg().getCx(), t.getY()));
              this.coinsAlligne=psouris.pointAlligneY(this.view.model.getContenant());
              this.view.redrawAll();

              DoubleProperty td = new SimpleDoubleProperty( (double)Math.round( Math.abs( ((this.murEnCours.getBeg().getCy()-t.getY())*this.view.batimentActif.getLargeurBase())/645 )*100 )/100);
              test.textProperty().bind(td.asString());
              this.view.setBottom(test);
            } 
         }
         
        if(this.state==3||this.state==31||this.state==4)
         { Coins proche= null;
            double dist =1000;
                          
                    for(Element e : this.view.model.getContenant())
                        {
                            if(e instanceof Coins){
                             Double dist2 =((Coins)e).distToPoint(psouris);
                             if (dist2 < dist&& dist2<20) {
                    dist2 = dist;
                    proche = (Coins)e;
                } 
           }          
       }
                this.coinPourMur= (Coins)proche ;
                this.view.redrawAll();

         }
              
    }
    // action lors de l'appuie des différents boutons
   
    public void Effacer(ActionEvent a)
    {
        if (this.state == 2 && this.selectionToSupr.size() >= 1)
        {
            
            this.view.getModel().removeAll(selectionToSupr);
            this.selectionToSupr.clear();
            this.ActiveBoutonSupprimer();
            this.view.redrawAll();                          
            System.out.println(view.getModel());
        }
        
    }
    void creer(ActionEvent e) {

    Piece piece = new Piece(this.topiece.get(0),this.topiece.get(1),this.topiece.get(2),this.topiece.get(3),this.view.lPiece.size()); 
       this.view.lPiece.add(piece);         
        piece.getC1().getInPiece().add(piece);
        piece.getC2().getInPiece().add(piece);
        piece.getC3().getInPiece().add(piece);
        piece.getC4().getInPiece().add(piece);
 
       this.topiece.clear();
       this.ActiveBoutonCreer();
       this.view.redrawAll();      
    }

    

    void Bdetruire(ActionEvent e) {
        for(Element el: this.topiece)
        {
            if(el instanceof Coins&& !((Coins)el).getInPiece().isEmpty())
            {               
                    this.view.lPiece.removeAll(((Coins)el).getInPiece());
            } 
        }
    this.topiece.clear();
    this.ActiveBoutonDetruire();
    this.ActiveBoutonCreer();
    this.view.redrawAll();      
        }

    void Revetement() {
        this.view.getChildren().removeAll(this.view.getDessinVbD(), this.view.getDessinHbG());
        this.view.setLeft(this.view.getRevetementHbG());
        this.statechanger(5);
                this.selectionToSupr.clear();
                this.Pselc = null;
                this.topiece.clear();  
                this.view.redrawAll();
                this.view.Retirer.setDisable(true);
                this.view.Appliquer.setDisable(true);             
    }

    void Creation() {                       
                this.view.getChildren().remove(this.view.getRevetementHbG());
                this.view.setLeft(this.view.getDessinHbG());
                this.view.setRight(this.view.getDessinVbD());
                this.statechanger(2);     
                this.selectionToSupr.clear();
                this.Pselc = null;
                this.topiece.clear();  
                this.view.redrawAll();
    }

    void appRevet() {
       if(this.state==51 && this.Pselc!=null)
       {
          this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getP().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
          this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getP());
          this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       } else if(this.state==52 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getS().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
           this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getS());
           this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       }else if(this.state==53 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM1().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
           this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM1());
           this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       }else if(this.state==54 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM2().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
           this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM2());
           this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       }else if(this.state==55 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM3().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
           this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM3());
           this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       }else if(this.state==56 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM4().getRevetement().add(this.view.associateurRev.get(this.view.choixRev.getValue())) ;
           this.view.associateurRev.get(this.view.choixRev.getValue()).getApplique().add(this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM4());
           this.view.RevInfo.getItems().add(new RecapRevPiece(String.valueOf(this.view.choixRev.getValue())));
       }
    }

    void RetRev() {
        if(this.state==51 && this.Pselc!=null)
       {
          this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getP().getRevetement().clear();
          this.view.RevInfo.getItems().clear();
          this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       } else if(this.state==52 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getS().getRevetement().clear() ;
           this.view.RevInfo.getItems().clear();
           this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       }else if(this.state==53 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM1().getRevetement().clear() ;
           this.view.RevInfo.getItems().clear();
           this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       }else if(this.state==54 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM2().getRevetement().clear() ;
           this.view.RevInfo.getItems().clear();
           this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       }else if(this.state==55 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM3().getRevetement().clear() ;
           this.view.RevInfo.getItems().clear();
           this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       }else if(this.state==56 && this.Pselc!=null)
       {
           this.view.lPiece.get(this.view.lPiece.indexOf(this.Pselc)).getM4().getRevetement().clear() ;
           this.view.RevInfo.getItems().clear();
           this.view.RevInfo.getItems().add(new RecapRevPiece("Aucun revêtement"));
       }
    }
    
        void switchNiv(Event e) {
            this.view.niveauModel.get(this.view.indexNiveauActif).clear();
                  for(Element el :this.view.model.getContenant())
             {
                  
                  this.view.niveauModel.get(this.view.indexNiveauActif).add(el);
             }
             this.view.model.clear();
             
             this.view.pieceNiveau.get(this.view.indexNiveauActif).clear();
             this.view.pieceNiveau.get(this.view.indexNiveauActif).addAll(this.view.lPiece);
             this.view.lPiece.clear();
                          
             this.view.indexNiveauActif= this.view.choixNiv.getValue();
             for(Element el :this.view.niveauModel.get(this.view.choixNiv.getValue()))
             {
                  this.view.model.add(el);
             }
             this.view.lPiece.addAll(this.view.pieceNiveau.get(this.view.choixNiv.getValue()));
             this.selectionToSupr.clear();
             this.topiece.clear();
             this.ActiveBoutonCreer();
             this.ActiveBoutonDetruire();
             this.view.redrawAll();
          
    }
    
        void BclearNiveau(ActionEvent e) {
        this.view.model.clear();
        this.view.lPiece.clear();
        this.view.model.add(new Coins(5,5,0));this.view.model.add(new Coins(5,645,1));this.view.model.add(new Coins(995,5,2));this.view.model.add(new Coins(995,645,3));

        this.view.redrawAll();
    }    
    
    
        
    void ActiveBoutonCreer()                // Conditions d'activations des différents boutons;
    {
        if(this.topiece.size()!=4)  {
            this.view.Creer.setDisable(true);
        } else {
            this.view.Creer.setDisable(false);
        }
    }
    void ActiveBoutonDetruire() 
    { 
        if(this.view.lPiece.size()<1||this.topiece.size()!=1)  {
            this.view.Detruire.setDisable(true);
        } else {
            this.view.Detruire.setDisable(false);
        }
    }
    
    void ActiveBoutonAppliquer()
    {        
        if(this.state==51 && this.view.associateurRev.get(this.view.choixRev.getValue()).ispPlafond())
        {
            this.view.Appliquer.setDisable(false);
        } else if(this.state==52 && this.view.associateurRev.get(this.view.choixRev.getValue()).ispSol())
        {
           this.view.Appliquer.setDisable(false); 
        } else if(((this.state==53)||(this.state==54)||(this.state==55)||(this.state==56) )&& (this.view.associateurRev.get(this.view.choixRev.getValue()).ispMur()))
        {
           this.view.Appliquer.setDisable(false); 
        } 
                else  {
            this.view.Appliquer.setDisable(true);
        }
        
    }
    
    void ActiveBoutonRetier()
    {
        if(this.Pselc==null)
        {
            this.view.Retirer.setDisable(true);
        } else
        {
            this.view.Retirer.setDisable(false);
        }
    }
    
    void ActiveBoutonSupprimer() 
    {
        if(this.selectionToSupr.size()<1)  {
            this.view.getDelete().setDisable(true);
        } else {
            this.view.getDelete().setDisable(false);
        }
    }

     public Coins posInModel(double xVue, double yVue) {                // A voir
        Transform modelVersVue = this.view.Dessin.getTransform();
        Point2D ptrans;
        try {
            ptrans = modelVersVue.inverseTransform(xVue, yVue);
        } catch (NonInvertibleTransformException ex) {
            throw new Error(ex);
        }
        Coins pclic = new Coins(ptrans.getX(), ptrans.getY());
        return pclic;
    }
    
     
     // Enregistrement des données avec ssave(enregistrer sous) et save enregistrer

    void ssave(Stage alpha) {  
    this.view.niveauModel.get(this.view.indexNiveauActif).clear();
                  for(Element el :this.view.model.getContenant())
             {  
                  this.view.niveauModel.get(this.view.indexNiveauActif).add(el);
             }
             this.view.pieceNiveau.get(this.view.indexNiveauActif).clear();
             this.view.pieceNiveau.get(this.view.indexNiveauActif).addAll(this.view.lPiece);
        
        FileChooser fc = new FileChooser();         
        fc.setTitle("Enregistrer sous");
            File selectedFile = fc.showSaveDialog(alpha);
            this.view.fichierActif= selectedFile;
            if(selectedFile!=null)
            {
        try {
         FileWriter writer = new FileWriter(selectedFile);  //pour écrire dans un fichier
        BufferedWriter bw = new BufferedWriter(writer);  
        
        if(this.view.batimentActif instanceof Maison)
        {
            bw.write("Batiment;"+"Maison;"+this.view.batimentActif.getNomBatiment()+
                     ";"+String.valueOf(this.view.batimentActif.getNombreNivEta())+
                    ";"+String.valueOf(this.view.batimentActif.getHauteurSP())+
                    ";"+String.valueOf(this.view.batimentActif.getLongueurBase())+
                    ";"+String.valueOf(this.view.batimentActif.getLargeurBase())+
                     ";"+this.view.batimentActif.getIsolant().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getIsolant().getPrix())+
                    ";"+this.view.batimentActif.getRevExt().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getRevExt().getPrix())
                    );
        } else 
        {
                     bw.write("Batiment;"+"Immeuble;"+this.view.batimentActif.getNomBatiment()+
                     ";"+String.valueOf(this.view.batimentActif.getNombreNivEta())+
                    ";"+String.valueOf(this.view.batimentActif.getHauteurSP())+
                    ";"+String.valueOf(this.view.batimentActif.getLongueurBase())+
                    ";"+String.valueOf(this.view.batimentActif.getLargeurBase())+
                    ";"+this.view.batimentActif.getIsolant().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getIsolant().getPrix())+
                    ";"+this.view.batimentActif.getRevExt().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getRevExt().getPrix())
                    );   
        }
        bw.newLine();
            
        for(Revetement rev: this.view.listRev)
        {
            bw.write("Revetement;"+String.valueOf(rev.getId())+
                    ";"+rev.getDesignation()+
                    ";"+rev.ispMur()+
                    ";"+rev.ispSol()+
                    ";"+rev.ispPlafond()+
                    ";"+String.valueOf(rev.getPrix())
            );
           bw.newLine();
        }
   
      for(ArrayList<Element> niveau : this.view.niveauModel)
      {   int i= this.view.niveauModel.indexOf(niveau);
          Groupe nivgrp = new Groupe(niveau);
          nivgrp.reassignationCoinsMur(niveau);
          bw.write("Niveau;");
          bw.newLine();
        for(Element e: nivgrp.getContenant())
            {
                if(e  instanceof Coins){
                  bw.write("Coin;"+String.valueOf(nivgrp.getContenant().indexOf(e))+
                          ";"+String.valueOf(((Coins) e).getCx())+
                          ";"+String.valueOf(((Coins) e).getCy()));         
                  bw.newLine();  

                }  else if(e instanceof Mur) 
                {
                    bw.write("Mur;"+String.valueOf(nivgrp.getContenant().indexOf(e))+
                            ";"+String.valueOf((((Mur) e).getBeg().getIdC())+
                                    ";"+String.valueOf((((Mur) e).getEnd().getIdC()))));
                    bw.newLine();
                  
                }
                               
            }
        for(Piece p : this.view.pieceNiveau.get(i))
        {
             bw.write("Piece;"+String.valueOf(nivgrp.getContenant().indexOf(p.getC1()))+
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC2()))+
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC3()))+   
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC4()))+
                          ";"+String.valueOf(this.view.pieceNiveau.get(i).indexOf(p)));
             bw.newLine();
             bw.write("Plafond;");
             if(p.getP().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getP().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Sol;");
             if(p.getS().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getS().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur1;");
             if(p.getM1().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM1().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur2;");
             if(p.getM2().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM2().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur3;");
             if(p.getM3().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM3().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

                          bw.newLine();
             bw.write("Mur4;");
             if(p.getM4().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM4().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }
             
                  bw.newLine();
        }
        
      }
        
         bw.close();
        writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
        this.view.menu.enregistrer.setDisable(false);
            }
    }

    void save(Stage alpha) {
    this.view.niveauModel.get(this.view.indexNiveauActif).clear();
                  for(Element el :this.view.model.getContenant())
             {  
                  this.view.niveauModel.get(this.view.indexNiveauActif).add(el);
             }
             this.view.pieceNiveau.get(this.view.indexNiveauActif).clear();
             this.view.pieceNiveau.get(this.view.indexNiveauActif).addAll(this.view.lPiece);
        
        if(this.view.fichierActif!=null){
            File selectedFile = this.view.fichierActif;
            
     try {
         FileWriter writer = new FileWriter(selectedFile);  //pour écrire dans un fichier
        BufferedWriter bw = new BufferedWriter(writer);  
        
        if(this.view.batimentActif instanceof Maison)
        {
            bw.write("Batiment;"+"Maison;"+this.view.batimentActif.getNomBatiment()+
                     ";"+String.valueOf(this.view.batimentActif.getNombreNivEta())+
                    ";"+String.valueOf(this.view.batimentActif.getHauteurSP())+
                    ";"+String.valueOf(this.view.batimentActif.getLongueurBase())+
                    ";"+String.valueOf(this.view.batimentActif.getLargeurBase())+
                    ";"+this.view.batimentActif.getIsolant().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getIsolant().getPrix())+
                    ";"+this.view.batimentActif.getRevExt().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getRevExt().getPrix())
                    );
        } else 
        {
                     bw.write("Batiment;"+"Immeuble;"+this.view.batimentActif.getNomBatiment()+
                     ";"+String.valueOf(this.view.batimentActif.getNombreNivEta())+
                    ";"+String.valueOf(this.view.batimentActif.getHauteurSP())+
                    ";"+String.valueOf(this.view.batimentActif.getLongueurBase())+
                    ";"+String.valueOf(this.view.batimentActif.getLargeurBase())+
                    ";"+this.view.batimentActif.getIsolant().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getIsolant().getPrix())+
                    ";"+this.view.batimentActif.getRevExt().getDesignation()+
                    ";"+String.valueOf(this.view.batimentActif.getRevExt().getPrix())
                    );   
        }
        bw.newLine();
            
        for(Revetement rev: this.view.listRev)
        {
            bw.write("Revetement;"+String.valueOf(rev.getId())+
                    ";"+rev.getDesignation()+
                    ";"+rev.ispMur()+
                    ";"+rev.ispSol()+
                    ";"+rev.ispPlafond()+
                    ";"+String.valueOf(rev.getPrix())
            );
           bw.newLine();
        }
   
      for(ArrayList<Element> niveau : this.view.niveauModel)
      {   int i= this.view.niveauModel.indexOf(niveau);
          Groupe nivgrp = new Groupe(niveau);
          nivgrp.reassignationCoinsMur(niveau);
          bw.write("Niveau;");
          bw.newLine();
        for(Element e: nivgrp.getContenant())
            {
                if(e  instanceof Coins){
                  bw.write("Coin;"+String.valueOf(nivgrp.getContenant().indexOf(e))+
                          ";"+String.valueOf(((Coins) e).getCx())+
                          ";"+String.valueOf(((Coins) e).getCy()));         
                  bw.newLine();  

                }  else if(e instanceof Mur) 
                {
                    bw.write("Mur;"+String.valueOf(nivgrp.getContenant().indexOf(e))+
                            ";"+String.valueOf((((Mur) e).getBeg().getIdC())+
                                    ";"+String.valueOf((((Mur) e).getEnd().getIdC()))));
                    bw.newLine();
                  
                }
                               
            }
        for(Piece p : this.view.pieceNiveau.get(i))
        {
             bw.write("Piece;"+String.valueOf(nivgrp.getContenant().indexOf(p.getC1()))+
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC2()))+
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC3()))+   
                          ";"+String.valueOf(nivgrp.getContenant().indexOf(p.getC4()))+
                          ";"+String.valueOf(this.view.pieceNiveau.get(i).indexOf(p)));
             bw.newLine();
             bw.write("Plafond;");
             if(p.getP().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getP().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Sol;");
             if(p.getS().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getS().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur1;");
             if(p.getM1().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM1().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur2;");
             if(p.getM2().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM2().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

             bw.newLine();
             bw.write("Mur3;");
             if(p.getM3().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM3().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }

                          bw.newLine();
             bw.write("Mur4;");
             if(p.getM4().getRevetement().isEmpty())
             {
                 bw.write("none");
             } else{
                  bw.write("existe;");
             for(Revetement rev: p.getM4().getRevetement())
             {
                 bw.write(String.valueOf(rev.getId())+";");
             }
             }
             
                  bw.newLine();
        }
        
      }
        
         bw.close();
        writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        }
    }
    
}
