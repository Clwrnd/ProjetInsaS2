/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.issenhuth.devis_batiment_fx;

    import Modele.*;
    import java.util.ArrayList;
    import javafx.geometry.Insets;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.VBox;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

/**
 *
 * @author chloi
 * Affichage du devis dans une autre fenêtre
 */
public class Devis extends Stage{
    VBox vbox1;
    VBox vbox2;
    TableView tableRécap;

    
    public Devis(Batiment bat,ArrayList<Revetement> rev,ArrayList<ArrayList<Piece>> pieceL){
        
        this.tableRécap = new TableView<RecapGeneral>(); 
        TableColumn<RecapGeneral,String> col1=new TableColumn<RecapGeneral,String>("Revêtement");
        TableColumn<RecapGeneral,Double> col2=new TableColumn<RecapGeneral,Double>("Prix au m²");
        TableColumn<RecapGeneral,Double> col3=new TableColumn<RecapGeneral,Double>("Surface d'application m²");
        TableColumn<RecapGeneral,Double> col4=new TableColumn<RecapGeneral,Double>("Total €");
        tableRécap.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        col1.setCellValueFactory(new PropertyValueFactory<RecapGeneral,String>("designation"));
        col2.setCellValueFactory(new PropertyValueFactory<RecapGeneral,Double>("prix"));
        col3.setCellValueFactory(new PropertyValueFactory<RecapGeneral,Double>("surface"));
        col4.setCellValueFactory(new PropertyValueFactory<RecapGeneral,Double>("total"));
        tableRécap.getColumns().addAll(col1,col2,col3,col4);
        
         BorderPane layout=new BorderPane();
         Text text = new Text();
         text.setFont(Font.font("Arial"));     
         
         double scalex = bat.getLongueurBase()/995 ;
         double scaley= bat.getLargeurBase()/645 ;  
         double devistotal=0;
         
        for(ArrayList<Piece> lp :pieceL)    // Calcul devis total
        {
            for(Piece p:lp)
            {
                devistotal =devistotal + p.devisP(bat.getHauteurSP(), scalex, scaley);
            }
        }
        
            devistotal= devistotal+ bat.PrixExt()  ;
              
        if(bat instanceof Maison) // Affichage informations bâtiment :
        {
        text.setText("Devis pour bâtiment : "+bat.getNomBatiment()+"\n \nType de bâtiment : Maison\n \nNombre de niveau : "+
                bat.getNombreNivEta()+"\n \nHauteur sous plafond : "+bat.getHauteurSP()+"m"+"\n \nSurface constructible : "+
                (bat.getLargeurBase()*bat.getLongueurBase())+" m² "+
                "\n \nRevêtement extérieur : "+bat.getRevExt().getDesignation()+" : "+bat.getRevExt().getPrix()+" €/m² "+
                  "\n \nIsolant extérieur : "+bat.getIsolant().getDesignation()+" : "+bat.getIsolant().getPrix()+" €/m² "+
                "\n \nDevis total : "+(double)Math.round(devistotal*100)/100+" € "
                );
        }
        else
        {
        text.setText("Devis pour bâtiment : "+bat.getNomBatiment()+"\n \nType de bâtiment : Immeuble\n \nNombre de niveau : "+
                bat.getNombreNivEta()+"\n \nHauteur sous plafond : "+bat.getHauteurSP()+"m"+"\n \nSurface constructible : "+
                (bat.getLargeurBase()*bat.getLongueurBase())+" m² "+
                "\n \nRevêtement extérieur : "+bat.getRevExt().getDesignation()+" : "+bat.getRevExt().getPrix()+" €/m² "+
                  "\n \nIsolant extérieur : "+bat.getIsolant().getDesignation()+" : "+bat.getIsolant().getPrix()+" €/m² "+
                "\n \nDevis total : "+(double)Math.round(devistotal*100)/100+" € "
                );    
        }
        
        for(Revetement r : rev)
        {   double surface=0;
            double prix=0;
            
            for(Object obj: r.getApplique()) 
            {
                if((obj instanceof Plafond))
                {
                    surface= surface + ((Plafond) obj).surface(scalex,scaley);
                    prix = prix +((Plafond) obj).surface(scalex,scaley)*r.getPrix();
                } 
                else if ((obj instanceof Sol))
                {
                   surface= surface +  ((Sol) obj).surface(scalex,scaley);
                   prix = prix +((Sol) obj).surface(scalex,scaley)*r.getPrix();
                    
                }
                else if((obj instanceof Mur))
                {
                    surface= surface + ((Mur) obj).surface(bat.getHauteurSP(),bat.getLongueurBase()/995,bat.getLargeurBase()/645);
                    prix = prix +((Mur) obj).surface(bat.getHauteurSP(),bat.getLongueurBase()/995,bat.getLargeurBase()/645)*r.getPrix();
                }                    
            }
            this.tableRécap.getItems().add(new RecapGeneral(r.getDesignation(),r.getPrix(),(double)Math.round(surface*100)/100,(double)Math.round(prix*100)/100));
        }
        
        
        this.vbox1=new VBox(text);
        this.vbox2=new VBox(tableRécap);
        this.vbox1.setPadding(new Insets(10, 7, 7, 4));
              
        layout.setLeft(vbox1);
        layout.setCenter(vbox2);
        layout.setStyle("-fx-background-color: #ceceb0;");
        Scene scene=new Scene(layout,800,500);
        this.setScene(scene);
        this.setTitle("Devis");
        
    }
    
    
}