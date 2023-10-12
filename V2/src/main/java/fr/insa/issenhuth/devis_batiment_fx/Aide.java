/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.issenhuth.devis_batiment_fx;
    
    import javafx.application.Application;
    import javafx.stage.Stage;
    import javafx.scene.Scene;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.scene.control.Hyperlink;
    import javafx.scene.control.ScrollPane;
    import javafx.scene.layout.VBox;


/**
 *
 * @author chloi
 */
public class Aide extends Stage{
    Text text1;
    Text text2;
    Text text3;
    Text text4;
    Text text5;
    VBox video2;
    VBox root;
    
    public Aide(){
        this.text1=new Text();
        text1.setFont(Font.font("Calibri (Corps)"));
        text1.setFont(Font.font("Calibri (Corps)",15));
        text1.setText("\n                                                 Aides au bon fonctionnement de l'application");
        /*text1.setX(200); 
        text1.setY(20);*/
        ScrollPane scrollPane = new ScrollPane();
        this.text2=new Text();
        Hyperlink creerSup = new Hyperlink("Créer et supprimer des éléments");
        Hyperlink ConstrBat = new Hyperlink("Construire son bâtiment");
        Hyperlink CreaPiece = new Hyperlink("Créer des pièces");
        Hyperlink ChgNiv = new Hyperlink("Changer de niveau");
        Hyperlink ApplRev = new Hyperlink("Appliquer des revêtements");
        Hyperlink devis = new Hyperlink("Le devis");
        this.video2 = new VBox(creerSup,ConstrBat,CreaPiece,ChgNiv);
        text2.setFont(Font.font("Calibri (Corps)"));
        text2.setText("\n    Bienvenue sur cette application. Voici quelques informations et astuces, accompagnées "
                + "de vidéos, afin de bien comprendre \n  son fonctionnement.\n\n\n        1- Créer un nouveau fichier/bâtiment\n\n    La première étape afin de "
                + "créer un bâtiment est de récupérer les informations importantes concernant ce dernier. Pour ce faire,\n  suivez les étapes suivantes :"
                + "\n  - Cliquer sur Fichier > Créer ; une nouvelle fenêtre s’ouvre.\n  - Remplir toutes les cases.\n  - Valider.\n\n  Attention, la hauteur sous-plafonds, la longueur ainsi que la largeur "
                + "du bâtiment doivent être complétés par des chiffres.\n\n\n        2- Construire son bâtiment, création de murs et de pièces\n\n    Après avoir"
                + " remplis les informations demandées, ou après avoir récupéré un fichier déjà enregistré, il faut maintenant\n  construire.\n    Lorsque vous "
                + "venez de donner les informations du bâtiments que vous créez, un nouvel interface apparaît. Vous trouverez sur\n  la partie gauche tous les "
                + "boutons nécessaires à la conception du plan. Au centre s’affiche la zone de dessin. Vous trouvez alors\n  quatres points. Ils représentent la surface "
                + "constructible. Pour relier ces 4 points et donc créer des murs, suivez les étapes\n  suivantes :\n  - Cliquer sur le bouton Mur.\n  - Cliquer sur un premier "
                + "point puis cliquer sur l’autre bout du mur, un second point, la distance s’affiche au bas de l’écran.\n  Attention, vous ne pouvez créer que des murs "
                + "perpendiculaires.\n\n    Faire cette même action autant de fois que vous le voudrez pour créer tous les murs qui composent une pièce.\n  Il n’est pas nécessaire de créer "
                + "des points avant de construire un mur.\n\n    Il est maintenant temps de créer une pièce. Suivez les étapes suivantes :\n  - Cliquez sur Pièce.\n  - Sélectionner avec la "
                + "touche « ctrl » du clavier les 4 points représentant les coins de la pièce.\n  - Cliquer sur « créer ». Un petit carré s’affiche dans la pièce.\n\n    Il est aussi possible "
                + "de détruire une pièce et de réinitialiser le niveau (effacer tout ce qui a été construit).\n  Pour changer de niveau, appuyer sur le menu à droite de la zone de dessin.\n  "
                + "Il est possible de créer des points, il peuvent servir de repère, mais ils ne sont pas nécessaires dans la construction du bâtiment.\n");
        this.text3=new Text();
        text3.setFont(Font.font("Calibri (Corps)"));
        text3.setText("\n\n        3- Sélectionner un revêtement\n\n    Maintenant que les murs sont construit, il est temps de choisir le revêtement à appliquer sur "
                + "chacun d’entre eux. Suivez les\n  étapes suivantes :\n  - Sélectionner l’onglet revêtement.\n  - Sélectionner la pièce, appuyer n’importe où dans une "
                + "pièce, le petit carré se colore en rouge.\n  - Sélectionner l’endroit où vous voulez appliquer un revêtement en cliquant sur un des boutons sur la gauche.\n"
                + "  - Sélectionner le revêtement que vous voulez appliquer avec le menu déroulant (les étoiles correspondent à la qualité du\n  produit).\n  - Appuyer sur Appliquer. Si ce bouton est grisé, cela signifie que l’on ne peut pas "
                + "appliquer le revêtement choisi à l’endroit choisi\n  (on ne peut pas appliquer le gazon sur un plafond !).\n\n    Pour voir quel revêtement a été appliqué, sélectionner la pièce puis "
                + "cliquer sur la surface concernée. Le revêtement s’affiche\n  dans le tableau.\n");
        this.text4=new Text();
        text4.setFont(Font.font("Calibri (Corps)"));
        text4.setText("\n\n        4- Récupération du devis\n\n    Une fois que vous avez finis d’appliquer tous les revêtements,\n  le devis est prêt ! Pour le trouver, "
                + "rien de plus simple :\n  - Cliquer sur l’onglet Devis.\n  - Cliquer sur Afficher.\n  Le devis s’affiche dans une nouvelle fenêtre.\n");
        this.text5=new Text();
        text5.setFont(Font.font("Calibri (Corps)"));
        text5.setText("\n\n        5- Autres fonctionnalités\n\n    Il est bien sûr possible d’enregistrer son fichier :\n  - Fichier > Enregistrer sous\n  - Fichier >"
                + " Enregistrer (Si vous avez déja enregistré ou ouvert votre fichier auparavant)\n\n   Mais aussi d'ouvrir un fichier :\n  - Fichier > Ouvrir \n");
        
        /*text2.setX(10);
        text2.setY(40);*/
        this.root=new VBox(text1,text2,video2,text3,ApplRev,text4,devis,text5);
        scrollPane.setContent(root);
        Application a = new Application() { 
            @Override  
            public void start(Stage stage){}};
            creerSup.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/57B1dxQWt6Q");
            });
            ConstrBat.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/2HOFzI7IPKs");
            });
            CreaPiece.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/Wle0Of8IY-8");
            });
            ChgNiv.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/B2gOZdOITPo");
            });
            ApplRev.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/_AltHilL-Ts");
            });
            devis.setOnAction((e)->{        
                a.getHostServices().showDocument("https://youtu.be/vVDbSQJvyIY");
            });
        Scene scene=new Scene(scrollPane,710,400);
        this.setScene(scene);
        this.setTitle("Aides");
        this.setResizable(false);
    }
    
}
/*text2.setUnderline(true);

text.setFill(Color.YELLOW);
text.setWrappingWidth(80);  //interligne*/

