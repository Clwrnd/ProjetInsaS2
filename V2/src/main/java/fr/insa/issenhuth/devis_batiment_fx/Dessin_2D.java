package fr.insa.issenhuth.devis_batiment_fx;

import Modele.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 *
 * @author cidmo
 */
public class Dessin_2D extends BorderPane {
    
         Interaction controlleur;
         
         ToggleButton SelectToSupr; 
         ToggleButton Coin ;
         ToggleButton Mur;
         ToggleButton Piece;
         ToggleButton SelectToRevetement;
         
         RadioButton Plafond;
         RadioButton Sol;
         RadioButton Mur1;
         RadioButton Mur2;
         RadioButton Mur3;
         RadioButton Mur4;
         
         
         Button Retirer;
         Button Appliquer;
         Button Detruire;
         Button Creer;
         Button Delete;
         Button ClearNiveau;
                      
         VBox DessinVbD ; 
         VBox DessinHbG ;
         VBox RevetementHbG;
         
         ChoiceBox choixRev;
         ChoiceBox<Integer> choixNiv;
         TableView RevInfo;
         
         Setcanvas Dessin;
         
         File fichierActif;
         
         HashMap<String,Revetement> associateurRev;
         
         ArrayList<Revetement> listRev;
         
         Groupe model;
         ArrayList<Piece> lPiece;
         
         Batiment batimentActif;     
         ArrayList<ArrayList<Piece>> pieceNiveau;
         int indexNiveauActif;
         ArrayList<ArrayList<Element>> niveauModel;   

         Menus menu;
          
public Dessin_2D(Stage stage, ArrayList<Revetement> revLis,Batiment bat)
{
    this(new ArrayList<ArrayList<Element>>(),new ArrayList<ArrayList<Piece>>() ,stage,revLis,bat);
}        
  
                        
public Dessin_2D(ArrayList<ArrayList<Element>> model,ArrayList<ArrayList<Piece>> lpiece ,Stage stage, ArrayList<Revetement> revListe,Batiment batiment)
{
   // Récupération des données pour le dessin
    int i;
    this.batimentActif = batiment;
    this.pieceNiveau = new  ArrayList<ArrayList<Piece>>();
    this.niveauModel = new ArrayList<ArrayList<Element>>();
    this.model= new Groupe();     
    this.lPiece = new ArrayList<Piece>();
    
    this.choixNiv = new ChoiceBox();
    this.RevInfo = new TableView<RecapRevPiece>();
    
    for(i=0;i<this.batimentActif.getNombreNivEta();i++)             
    {
        this.choixNiv.getItems().add(i);
    }    
        if(!model.isEmpty())
         {
            this.niveauModel.addAll(model);
         } 
        else
           {
             for(i=0;i<this.batimentActif.getNombreNivEta();i++)
             { 
                 ArrayList<Element> bordure = new ArrayList<Element>();
                 bordure.add(new Coins(5,5,0));bordure.add(new Coins(5,645,1));bordure.add(new Coins(995,5,2));bordure.add(new Coins(995,645,3));
                 this.niveauModel.add(new ArrayList<>());
                 this.niveauModel.get(i).addAll(bordure);
             }    
          }       
    
        if(!lpiece.isEmpty())
        {
       this.pieceNiveau.addAll(lpiece);
         } 
           else
                {
                for(i=0;i<this.batimentActif.getNombreNivEta();i++)
                 {
                  this.pieceNiveau.add(new ArrayList<>());
                 }    
                }

     if(!this.niveauModel.get(0).isEmpty())             // Ajout du premier niveau dans le dessin2D
        {
            for(Element e: this.niveauModel.get(0))
            {
                this.model.add(e);
            }
        }
     if(!this.pieceNiveau.get(0).isEmpty())
       {
         this.lPiece.addAll(this.pieceNiveau.get(0));
       }
       
    this.indexNiveauActif=0;
    this.choixNiv.getSelectionModel().selectFirst();

    
    
    this.choixRev = new ChoiceBox();                        // Association du choix de la choixbox au revêtement correspondant
    this.associateurRev = new HashMap<>(); 
    this.listRev= revListe;
        for(Revetement rev: this.listRev)
    {
        
        this.choixRev.getItems().add(rev.getDesignation());
        this.associateurRev.put(rev.getDesignation(), rev);        
    }
    
    this.choixRev.getSelectionModel().selectFirst();  
        
    this.menu = new Menus(stage);                                   // Création de l'interface (bouton etc)
    this.menu.setUseSystemMenuBar(true);  
    
    this.setTop(this.menu);
       
    this.controlleur = new Interaction(this);
    
    Text t2=new Text("Outils de création : ");
    t2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
    TableColumn<RecapRevPiece,String> col1=new TableColumn<RecapRevPiece,String>("Revêtements actuels de la surface : ");  
    this.ClearNiveau = new Button("Réinitialiser le niveau");
    this.SelectToSupr = new ToggleButton("Selectionner");
    this.SelectToSupr.setPrefSize(100, 20);
    this.Coin = new ToggleButton("Coin"); 
    this.Coin.setPrefSize(60, 20);    
    this.Mur = new ToggleButton("Mur");
    this.Mur.setPrefSize(60, 20);
    this.Piece = new ToggleButton("Piece");
    this.Piece.setPrefSize(60, 20);
    this.Creer= new Button("Creer");
    this.Creer.setPrefSize(45,15 );
    this.Detruire= new Button("Détruire");
    this.Detruire.setPrefSize(60,15 );
    this.SelectToRevetement = new ToggleButton("Selectionner");
    this.SelectToRevetement.setPrefSize(100, 20);
    this.Appliquer = new Button("Appliquer");
    this.Retirer = new Button("Retirer");
    this.Appliquer.setPrefSize(80, 20);
    this.Retirer.setPrefSize(60, 20);
    this.Mur1 = new RadioButton("Mur du haut  ");
    this.Mur2 = new RadioButton("Mur du bas");
    this.Mur3 = new RadioButton("Mur de gauche  ");
    this.Mur4 = new RadioButton("Mur de droite");
    this.Plafond = new RadioButton("Plafond  ");
    this.Sol = new RadioButton("Sol");
    this.Plafond.setStyle("-fx-background-color: #6d7f7f;");
    this.Sol.setStyle("-fx-background-color: #6d7f7f;");
    this.Mur1.setStyle("-fx-background-color: #6d7f7f;");
    this.Mur2.setStyle("-fx-background-color: #6d7f7f;");
    this.Mur3.setStyle("-fx-background-color: #6d7f7f;");
    this.Mur4.setStyle("-fx-background-color: #6d7f7f;");
    this.Delete = new Button("Supprimer");
    Text nivc = new Text("Niveaux :");
    Text revc = new Text("Revêtement :");
    revc.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
    nivc.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 10));
    
    HBox hmur1 = new HBox(Mur1,Mur2);
    HBox hmur2 = new HBox(Mur3,Mur4);
    HBox type = new HBox(this.Plafond,this.Sol);
    this.RevetementHbG = new VBox(revc,this.choixRev,new Separator(),type,hmur1,hmur2,new Separator(),this.Appliquer,this.Retirer,new Separator(),this.RevInfo);
    this.RevetementHbG.setSpacing(10);
    this.RevetementHbG.setStyle("-fx-background-color: #6d7f7f;-fx-border-color: #000000;"); 
    this.RevetementHbG.setSpacing(10);
    this.RevetementHbG.setPadding(new Insets(15, 12, 15, 12));
    HBox btpiece = new HBox(Creer,Detruire);   
    this.DessinHbG= new VBox(t2,new Separator(),this.getSelectToSupr(),this.getDelete(),new Separator(),this.getCoin(),this.getMur(),new Separator(),this.Piece,btpiece,new Separator(),this.ClearNiveau);
    DessinHbG.setPadding(new Insets(15, 12, 15, 12));
    DessinHbG.setSpacing(10);
    DessinHbG.setStyle("-fx-background-color: #6d7f7f;");
    this.RevInfo.getColumns().add(col1);
    this.RevInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    col1.setCellValueFactory(new PropertyValueFactory<RecapRevPiece,String>("Revetementp"));   


    ToggleGroup choixr = new ToggleGroup();
    Sol.setToggleGroup(choixr); 
    Plafond.setToggleGroup(choixr);
    Mur1.setToggleGroup(choixr);
    Mur2.setToggleGroup(choixr);
    Mur3.setToggleGroup(choixr);
    Mur4.setToggleGroup(choixr);
  
    ToggleGroup choix = new ToggleGroup();
    this.SelectToSupr.setToggleGroup(choix);
    this.Coin.setToggleGroup(choix);
    this.Mur.setToggleGroup(choix);
    this.Piece.setToggleGroup(choix);

    this.setLeft(DessinHbG);
    
    this.Dessin = new Setcanvas(this);
    this.setCenter(Dessin);
    this.controlleur.statechanger(1);
 
    Text t1=new Text(this.batimentActif.getNomBatiment());
    t1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 10));
    
    HBox Niveau = new HBox(nivc,this.choixNiv);
    Niveau.setSpacing(10);
    this.DessinVbD= new VBox(t1,new Separator(),Niveau);
    DessinVbD.setPadding(new Insets(15, 12, 15, 12));
    DessinVbD.setSpacing(10);
    DessinVbD.setStyle("-fx-background-color: #6d7f7f;");    
    this.setRight(DessinVbD);
    
           
    this.SelectToSupr.setOnAction((e)->{                                    // Partie appel au controlleur
        this.controlleur.Selectionner(e);    
    } );
    
    this.Coin.setOnAction((e)->{
        this.controlleur.Coins(e);    
    } );
    
    this.Mur.setOnAction((e)->{
    this.controlleur.Mur(e);    
    } );
    
    this.Piece.setOnAction((e)->{
        this.controlleur.Piece(e);
    });
      
    this.Delete.setOnAction((e)->{
        this.controlleur.Effacer(e);
    });

    this.menu.sous.setOnAction((e)->{
        this.controlleur.ssave(stage);
    });

    this.menu.enregistrer.setOnAction((e)->{
    this.controlleur.save(stage);
        }   );
    
    this.Creer.setOnAction((e)->{
        this.controlleur.creer(e);
            });
 
    this.Detruire.setOnAction((e)->{
        this.controlleur.Bdetruire(e);
    });
    
    this.menu.RevL.setOnMouseClicked((e)->{
        this.controlleur.Revetement();
    });
    
    this.menu.OutL.setOnMouseClicked((e)->{
        this.controlleur.Creation();
    });
    
    this.Retirer.setOnAction((e)->{
        this.controlleur.RetRev();
    });
    
    this.Appliquer.setOnAction((e)->{
           this.controlleur.appRevet();  
    });
    
    this.Plafond.setOnAction((e)->{
        this.controlleur.btPlafond();
        
    });
    
    this.Sol.setOnAction((e)->{
        this.controlleur.btSol();
    });
    
    this.choixRev.setOnAction((e)->{
        this.controlleur.ActiveBoutonAppliquer();
        this.controlleur.ActiveBoutonRetier();
    });
    
    this.Mur1.setOnAction((e)->{
        this.controlleur.Mur1();
    });
    this.Mur2.setOnAction((e)->{
        this.controlleur.Mur2();
    });
    
    this.Mur3.setOnAction((e)->{
        this.controlleur.Mur3();
    });
    
    this.Mur4.setOnAction((e)->{
        this.controlleur.Mur4();
    });
    
    this.choixNiv.setOnAction((e)->{
        this.controlleur.switchNiv(e);
    });
    
    this.ClearNiveau.setOnAction((e)->{
        this.controlleur.BclearNiveau(e);
    });
    
    this.menu.afficherd.setOnAction(event ->{       
        this.niveauModel.get(this.indexNiveauActif).clear();
                  for(Element el :this.model.getContenant())
             {  
                  this.niveauModel.get(this.indexNiveauActif).add(el);
             }
             this.pieceNiveau.get(this.indexNiveauActif).clear();
             this.pieceNiveau.get(this.indexNiveauActif).addAll(this.lPiece);
        
            Devis devisStage=new Devis(this.batimentActif,this.listRev,this.pieceNiveau);
            devisStage.show();                        
        });
    
    
}


 public void redrawAll()        // Redessinage après chaque action;
 {
     this.Dessin.drawVraiCanvas();
 }


 // Getteurs et Setteurs
 
    public Groupe getModel() {
        return model;
    }
    
    public Interaction getControlleur() {
        return controlleur;
    }

    public Button getDelete() {
        return Delete;
    }

    public void setDelete(Button Delete) {
        this.Delete = Delete;
    }

    public VBox getDessinVbD() {
        return DessinVbD;
    }

    public VBox getDessinHbG() {
        return DessinHbG;
    }

 
    public VBox getRevetementHbG() {
        return RevetementHbG;
    }

    public ToggleButton getSelectToSupr() {
        return SelectToSupr;
    }

    public ToggleButton getCoin() {
        return Coin;
    }

    public ToggleButton getMur() {
        return Mur;
    }

    public Setcanvas getDessin() {
        return Dessin;
    }
    
}