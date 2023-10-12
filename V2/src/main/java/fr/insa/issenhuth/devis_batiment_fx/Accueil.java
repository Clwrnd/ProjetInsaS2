package fr.insa.issenhuth.devis_batiment_fx;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author cidmo
 */
public class Accueil extends BorderPane {
    
    Menus menubar;
    
    public Accueil(Stage stage) // Création de l'accueil
    {
    Text titre = new Text("2I-BuildingEstimator");
    titre.setFont(Font.font("Freestyle Script", FontWeight.THIN, FontPosture.ITALIC, 100));                
    this.menubar=new Menus(stage);
    menubar.setUseSystemMenuBar(true);   
    this.setTop(menubar);
    menubar.enregistrer.setDisable(true);
    menubar.Fermer.setDisable(true);
    menubar.sous.setDisable(true);
    menubar.outil.setDisable(true);
    menubar.revetement.setDisable(true);
    this.menubar.devis.setDisable(true);
    this.setStyle("-fx-background-color: #d5f0f0;");
    this.setCenter(titre);
        
    }
    
}
