package fr.insa.issenhuth.devis_batiment_fx;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    
    @Override
    public void start(Stage stage) throws IOException {
        Accueil accueil = new Accueil(stage);       
        Scene scene = new Scene(accueil,700,600);
        //stage.getIcons().add(new Image("file:C:\\Users\\cidmo\\Documents\\NetBeansProjects\\Devis_batimentS2\\V2\\src\\main\\java\\fr\\insa\\issenhuth\\devis_batiment_fx\\logo.png"));
        stage.setScene(scene);
        stage.setTitle("2I-BuildingEstimator");
        stage.show();       
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Première utilisation ? Consultez l'aide dans 'Paramètres & Options'");
        alert.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}