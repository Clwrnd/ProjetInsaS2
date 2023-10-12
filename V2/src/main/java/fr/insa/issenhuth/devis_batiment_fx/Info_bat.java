package fr.insa.issenhuth.devis_batiment_fx;

import Modele.Batiment;
import Modele.Immeuble;
import Modele.Maison;
import Modele.Revetement;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author chloi
 */
public class Info_bat extends Stage{

    Batiment batcree;
    Button Fermer;
   
    public Info_bat(){  // Création de la fenêtre de demande d'information sur le nouveau batiment
        
        // Création de l'interface (boutton etc ...)
         Text erreur0 = new Text("   Nom invalide");
         Text erreur1 = new Text("   Valeur invalide");
         Text erreur2 = new Text("   Valeur invalide");
         Text erreur3 = new Text("   Valeur invalide");
         Text erreur4 = new Text("   Valeur invalide");
         Text erreur5 = new Text("   Veuillez choisir un type de bâtiment");
         Text erreur6 = new Text("   Désignation invalide");
         Text erreur7 = new Text("   Valeur invalide");
         Text erreur8 = new Text("    Désignation invalide");
         Text erreur9 = new Text("   Valeur invalide");

         
         erreur0.setFill(Color.RED);
         erreur1.setFill(Color.RED);
         erreur2.setFill(Color.RED);
         erreur3.setFill(Color.RED);
         erreur4.setFill(Color.RED);
         erreur5.setFill(Color.RED);
         erreur6.setFill(Color.RED);
         erreur7.setFill(Color.RED);
         erreur8.setFill(Color.RED);
         erreur9.setFill(Color.RED);
         
         
         erreur0.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur5.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10));
         erreur6.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur7.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur8.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
         erreur9.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10)); 
        
            TextField NombrE_N = new TextField();
            TextField NomBat=new TextField();
            TextField Long=new TextField();
            TextField Larg = new TextField();
            TextField HauteurSP=new TextField();
            TextField designT = new TextField();
            TextField prixUT = new TextField();
            TextField designR = new TextField();
            TextField prixUR = new TextField();
            
            RadioButton maison=new RadioButton("Maison  ");
            RadioButton immeuble=new RadioButton("Immeuble");
            Button Annuler = new Button("Annuler");
            this.Fermer = new Button("Terminer");
            Button Ok = new Button("Ok");
            Fermer.setPrefSize(100, 20 );
            
            Label label0=new Label();
            Label nbr_e_n=new Label("Nombre de niveau (RDC inclus) :  ");
            Label nom_bat=new Label("Nom du bâtiment (20 carac. max) :  ");
            Label longeur=new Label("Longueur de la base :  ");
            Label largeur=new Label("Largeur de la base (<Longeur):  ");
            Label hauteur=new Label("Hauteur sous plafond :  ");
            Label isolant = new Label("Isolant extérieur:   ");
            Label design = new Label("Désignation :  ");
            Label prixU = new Label("Prix au m² :  ");
            Label designRev = new Label("Désignation :  ");
            Label prixUrev = new Label("Prix au m² :"  );
            Label revExt = new Label("Revêtement extérieur  :  ");
            
            Label uni1=new Label("  m");
            Label uni2=new Label("  m");
            Label uni3=new Label("  m");
            Label uni4=new Label("  €");
            Label uni5=new Label("  €");
            
            
            HBox hbox0=new HBox(label0);
            HBox hbox1=new HBox(nom_bat,NomBat,erreur0);
            HBox hbox5=new HBox(maison,immeuble,erreur5);
            HBox hbox2=new HBox(nbr_e_n,NombrE_N,erreur1);
            HBox hbox3=new HBox(hauteur,HauteurSP,uni1,erreur2);
            HBox hbox4=new HBox(longeur,Long,uni2,erreur3);
            HBox exit = new HBox(10,Ok,Annuler);
            HBox dim = new HBox(largeur,Larg, uni3,erreur4);
            HBox iso1 = new HBox(design,designT,erreur6);
            HBox iso2 = new HBox(prixU,prixUT,uni4,erreur7);
            HBox iso3 = new HBox(designRev,designR,erreur8);
            HBox iso4 = new HBox(prixUrev,prixUR,uni5,erreur9);
            VBox isoV = new VBox(isolant,iso1,iso2);
            VBox isoV2 = new VBox(revExt,iso3,iso4);
            isoV.setSpacing(5);
            isoV2.setSpacing(5);
            HBox isoH = new HBox(isoV,new Separator(Orientation.VERTICAL),isoV2);
            isoH.setSpacing(10);
            VBox vbox1=new VBox(hbox0,hbox1,hbox5,hbox2,hbox3,hbox4,dim,new Separator(),isoH,new Separator(),exit);
            vbox1.setPadding(new Insets(2, 12, 15, 12));
            vbox1.setSpacing(5);
            
            BorderPane layout2=new BorderPane();
                       
            ToggleGroup gr2=new ToggleGroup();
            maison.setToggleGroup(gr2);
            immeuble.setToggleGroup(gr2);
            
            Scene scene2=new Scene(layout2,750,350);
            layout2.setTop(vbox1);
            layout2.setStyle("-fx-background-color: #ceceb0;");
            this.setScene(scene2);
            this.setTitle("Informations générales");
            
            erreur0.setVisible(false);
            erreur1.setVisible(false);
            erreur2.setVisible(false);
            erreur3.setVisible(false);
            erreur4.setVisible(false);
            erreur5.setVisible(false);
            erreur6.setVisible(false);
            erreur7.setVisible(false);
            erreur8.setVisible(false);
            erreur9.setVisible(false);
           
           
           
           
        Annuler.setOnAction((e)->{            // Annulation de la création et controle de saisie des informations rentrées
        this.close();
        });
        
        Ok.setOnAction((e)->{
            if(NomBat.getText().length()>20 ||NomBat.getText().length()==0 )
            {
                erreur0.setVisible(true);
            }else
            {
                erreur0.setVisible(false);
            }
            
            if(designT.getText().length()==0 )
            {
                erreur6.setVisible(true);
            }else
            {
                erreur6.setVisible(false);
            }
            
            if(designR.getText().length()==0 )
            {
                erreur8.setVisible(true);
            }else
            {
                erreur8.setVisible(false);
            }
                

            try{
                if(Integer.parseInt(NombrE_N.getText())>0)
                {                
                  erreur1.setVisible(false);
                } else
                {
                  erreur1.setVisible(true);
                }
                
            }
                catch(Exception ex){
                    erreur1.setVisible(true);
                }
            try{
                if(Double.parseDouble(HauteurSP.getText())>0)
                {
                erreur2.setVisible(false);
                } else 
                {
                erreur2.setVisible(true); 
                }   
                

            }
                catch(Exception ex){
                    erreur2.setVisible(true);
                }
            
            try{
                if(Double.parseDouble(Long.getText())>0)
                {
                    erreur3.setVisible(false);
                } else
                {
                    erreur3.setVisible(true);
                }
                

            }
                catch(Exception ex){
                    erreur3.setVisible(true);
                }
            
            try{
                if(Double.parseDouble(Larg.getText())>0)
                {
                  erreur4.setVisible(false);
                } else
                {
                erreur4.setVisible(true);
                }
                
                
            }
                catch(Exception ex){
                    erreur4.setVisible(true);
                }
            if(gr2.getSelectedToggle()==null)
            {
                erreur5.setVisible(true);
            } else{
                erreur5.setVisible(false);
            }
            
            try {
            if((Long.getText()!=null)&&(Larg.getText()!=null)&&(Double.parseDouble(Larg.getText())>Double.parseDouble(Long.getText()))){
                erreur3.setVisible(true);
                erreur4.setVisible(true);
            }
            }
            catch(Exception ex)
            {
                erreur3.setVisible(true);
                erreur4.setVisible(true);
            }
              try{
                Double.parseDouble(prixUT.getText());
                erreur7.setVisible(false);
                
            }
                catch(Exception ex){
                    erreur7.setVisible(true);
                }
                            try{
                Double.parseDouble(prixUR.getText());
                erreur9.setVisible(false);
                
            }
                catch(Exception ex){
                    erreur9.setVisible(true);
                }
            
            if(!(erreur1.isVisible()||erreur2.isVisible()||erreur3.isVisible()||erreur4.isVisible()||erreur5.isVisible()||erreur0.isVisible()||erreur6.isVisible()||erreur7.isVisible()||erreur8.isVisible()||erreur9.isVisible()))
            {
               Revetement isobat = new Revetement(designT.getText(),Double.parseDouble(prixUT.getText()));
               Revetement revextbat = new Revetement(designR.getText(),Double.parseDouble(prixUR.getText()));
                if(gr2.getSelectedToggle()==maison)
                {
                    this.batcree= new Maison(NomBat.getText(),Integer.parseInt(NombrE_N.getText()),Double.parseDouble(HauteurSP.getText()),Double.parseDouble(Long.getText()),Double.parseDouble(Larg.getText()),isobat,revextbat);
                } else
                {
                    this.batcree= new Immeuble(NomBat.getText(),Integer.parseInt(NombrE_N.getText()),Double.parseDouble(HauteurSP.getText()),Double.parseDouble(Long.getText()),Double.parseDouble(Larg.getText()),isobat,revextbat);
                }

               layout2.getChildren().clear();               
               layout2.setCenter(Fermer);

            }
            
            
          
        
                
            
        });


    }

   
}
