package fr.insa.issenhuth.devis_batiment_fx;

import Modele.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;


/**
 *
 * @author chloi
 */
public class Menus extends MenuBar {   
    Menu fichier;
    Menu options;
    Menu outil;
    Menu revetement;
    Menu devis;
    Menu langue;
    
    MenuItem Fermer;
    MenuItem Quitter;
    MenuItem ouvrir;
    MenuItem enregistrer;
    MenuItem sous;
    MenuItem creer;
    
    MenuItem aide;  
    MenuItem afficherd;
    MenuItem imprimer;
    MenuItem sousd;
    MenuItem fr;
    
    Label RevL;
    Label OutL;
    
    Info_bat infoBatStage;    

    FileChooser fileChooser;
    Dessin_2D view;
    
    public Menus(Stage alpha) {         // Création de la menubar
        
        this.fichier = new Menu("Fichier");         // Assemblage des différents composants
        this.outil = new Menu();
        this.options = new Menu("Paramètres & Options");
        this.revetement = new Menu();
        this.devis = new Menu("Devis");
        this.setUseSystemMenuBar(true);
        this.getMenus().addAll(fichier,outil,revetement,devis,options);
                
        this.ouvrir = new MenuItem("Ouvrir");               
        this.enregistrer = new MenuItem("Enregistrer");
        this.sous = new MenuItem("Enregistrer sous");
        this.creer = new MenuItem("Créer");
        this.Quitter = new MenuItem("Quitter");
        this.Fermer = new MenuItem("Fermer");
        fichier.getItems().addAll(ouvrir,new SeparatorMenuItem(),enregistrer,new SeparatorMenuItem(),sous,new SeparatorMenuItem(),creer,new SeparatorMenuItem(),Fermer,new SeparatorMenuItem(),Quitter);//attribution des items        
        this.langue = new Menu("Langue");
        this.fr=new MenuItem("Français");
        langue.getItems().add(fr);
        this.aide = new MenuItem("Aides");
        options.getItems().addAll(langue,new SeparatorMenuItem(),aide);
        this.afficherd = new MenuItem("Afficher");
        this.imprimer = new MenuItem ("Imprimer");
        this.sousd = new MenuItem("Enregistrer sous");
        devis.getItems().addAll(afficherd,new SeparatorMenuItem(),imprimer,new SeparatorMenuItem(),sousd);
        
        this.setStyle("-fx-background-color: #5e6e6e;   -fx-border-color: #000000;");
        
                                                            // Contrôle des interactions utilisateurs                                                   
        ouvrir.setOnAction(event -> {
            this.fileChooser = new FileChooser();
            fileChooser.setTitle("Sélection d'un fichier");
            File selectedFile = fileChooser.showOpenDialog(alpha);
            if(selectedFile!=null){
            String[] lean;
            HashMap<String,Revetement> associateur = new HashMap();
            ArrayList<ArrayList<Element>> niveau = new ArrayList();
            ArrayList<ArrayList<Piece>> pieceniveau =new ArrayList();
            ArrayList<Revetement> lrev = new ArrayList();
            Batiment batiment = null;
            int i;
            int niv=-1;
            try { 
            BufferedReader lire = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile),"UTF-8"));
            String ligne = lire.readLine();  
            
            lean=ligne.split(";");
            if(lean[1].equals("Maison"))
            {
                batiment = new Maison( lean[2],Integer.parseInt(lean[3]),Double.parseDouble(lean[4]),
                Double.parseDouble(lean[5]),Double.parseDouble(lean[6]),
                new Revetement(lean[7],Double.parseDouble(lean[8]) ),new Revetement(lean[9],Double.parseDouble(lean[10]) ) );
            } else {
                batiment = new Immeuble( lean[2],Integer.parseInt(lean[3]),Double.parseDouble(lean[4]),
                Double.parseDouble(lean[5]),Double.parseDouble(lean[6]),
                new Revetement(lean[7],Double.parseDouble(lean[8]) ),new Revetement(lean[9],Double.parseDouble(lean[10]) ) );
            }
            ligne = lire.readLine();
            
            while(ligne!=null)  
            {
                lean=ligne.split(";");
                switch(lean[0])
                {   
                    case "Niveau" : niv=niv+1;
                                    niveau.add(new ArrayList<>());
                                    pieceniveau.add(new ArrayList<>());
                    break;
                    case "Revetement" : Revetement rev =new Revetement(Integer.parseInt(lean[1]),lean[2],Boolean.parseBoolean(lean[3]),Boolean.parseBoolean(lean[4]),Boolean.parseBoolean(lean[5]),Double.parseDouble(lean[6]));
                    lrev.add(rev);
                    associateur.put(String.valueOf(rev.getId()), rev);
                    break;    
                    case "Coin": niveau.get(niv).add(new Coins(Double.parseDouble(lean[2]),Double.parseDouble(lean[3]),Integer.parseInt(lean[1])));
                    break;
                    case  "Mur": niveau.get(niv).add(new Mur((Coins)niveau.get(niv).get(Integer.parseInt(lean[2])),(Coins)niveau.get(niv).get(Integer.parseInt(lean[3])),Integer.parseInt(lean[1])));
                    break;                    
                    case "Piece": Piece piece = new Piece((Coins)niveau.get(niv).get(Integer.parseInt(lean[1])),
                    (Coins)niveau.get(niv).get(Integer.parseInt(lean[2])),
                    (Coins)niveau.get(niv).get(Integer.parseInt(lean[3])),
                    (Coins)niveau.get(niv).get(Integer.parseInt(lean[4])),
                    (Integer.parseInt(lean[5]))
                    );
                    ((Coins)niveau.get(niv).get(niveau.get(niv).indexOf(piece.getC1()))).getInPiece().add(piece);
                    ((Coins)niveau.get(niv).get(niveau.get(niv).indexOf(piece.getC2()))).getInPiece().add(piece);
                    ((Coins)niveau.get(niv).get(niveau.get(niv).indexOf(piece.getC3()))).getInPiece().add(piece);
                    ((Coins)niveau.get(niv).get(niveau.get(niv).indexOf(piece.getC4()))).getInPiece().add(piece);
                    
                    ligne = lire.readLine();
                    lean=ligne.split(";");
                    if(lean[1].equals("none"))
                    {
                        ligne = lire.readLine();
                        lean=ligne.split(";");
                    } else {
                        for(i=2;i<lean.length;i++){
                           piece.getP().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getP());
                        }
                        ligne = lire.readLine();
                        lean=ligne.split(";");   
                    }
                    
                     if(lean[1].equals("none"))
                    {
                        ligne = lire.readLine();
                        lean=ligne.split(";");
                    } else {
                        for(i=2;i<lean.length;i++){
                           piece.getS().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getS());
                        }
                        ligne = lire.readLine();
                        lean=ligne.split(";");   
                    }
                     
                      if(lean[1].equals("none"))
                    {
                        ligne = lire.readLine();
                        lean=ligne.split(";");
                    } else {
                        for(i=2;i<lean.length;i++){
                           piece.getM1().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getM1());
                           
                        }
                        ligne = lire.readLine();
                        lean=ligne.split(";");   
                    }
                      
                       if(lean[1].equals("none"))
                    {
                        ligne = lire.readLine();
                        lean=ligne.split(";");
                    } else {
                        for(i=2;i<lean.length;i++){
                           piece.getM2().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getM2());
                        }
                        ligne = lire.readLine();
                        lean=ligne.split(";");   
                    }
                       
                        if(lean[1].equals("none"))
                    {
                        ligne = lire.readLine();
                        lean=ligne.split(";");
                    } else {
                        for(i=2;i<lean.length;i++){
                           piece.getM3().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getM3());
                        }
                        ligne = lire.readLine();
                        lean=ligne.split(";");   
                    }
                        
                             if(!lean[1].equals("none")) {
                        for(i=2;i<lean.length;i++){
                           piece.getM4().getRevetement().add(associateur.get(lean[i]));
                           associateur.get(lean[i]).getApplique().add(piece.getM4());
                        }  
                    }
                  
                  pieceniveau.get(niv).add(piece);  
                  break;
                  default : System.out.println("Error");
                  break;
                }
                ligne = lire.readLine();
                
                
            }
            lire.close(); 
        }   catch (IOException ex) {
            ex.printStackTrace();
        }
           this.view = new Dessin_2D(niveau,pieceniveau,alpha,lrev,batiment); 
            this.view.fichierActif= selectedFile;
            Scene scene3 = new Scene(this.view,600,500);
            alpha.setScene(scene3);
            alpha.setTitle("2I-BuildingEstimator");
            alpha.show();
            }
        });
        
        creer.setOnAction(event -> {
             this.infoBatStage=new Info_bat();
             this.infoBatStage.show(); 
             this.infoBatStage.setResizable(false);
             this.infoBatStage.Fermer.setOnAction((e)->{
             Batiment b=this.infoBatStage.batcree;
             this.infoBatStage.close();
             this.view= new Dessin_2D(alpha,Revetement.revDeBase(),b);
             this.view.menu.enregistrer.setDisable(true);
             Scene scene3 = new Scene(this.view,600,500);
             alpha.setScene(scene3);
             alpha.setTitle("2I-BuildingEstimator");
             alpha.show();
            });
            
         });
        
         Quitter.setOnAction(event -> {
           alpha.close();
        });
         
        Fermer.setOnAction(event -> {
          Accueil accueil = new Accueil(alpha);
          Scene scene = new Scene(accueil,700,600);
          alpha.setScene(scene);
          alpha.setTitle("2I-BuildingEstimator");
          alpha.show();
        });
            
        this.OutL = new Label("Outils de création");        // Fait en sorte que lorsqu'on appuie sur le menu une action se passe
        outil.setGraphic(OutL);                             // ce qui est impossible avec un simple menu
        
        this.RevL = new Label("Revêtement");
        revetement.setGraphic(RevL);

        
        imprimer.setOnAction(event ->{ 
        alert();
        });
        
        sousd.setOnAction(event -> {
        alert();
        });
        
        aide.setOnAction(event -> {
        Aide aide=new Aide();
        aide.show();
        
        });
        
    }
    
    private void alert(){                                  // Fonction non disponible pour le moment
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Fonctionnalité indisponible pour le moment");
        alert.show();
    }
        
}
