package fr.insa.issenhuth.devis_batiment_fx;

import Modele.Coins;
import Modele.Element;
import Modele.Groupe;
import Modele.Mur;
import Modele.Piece;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;

/**
 *
 * @author cidmo
 */
public class Setcanvas extends Pane {
    
    Canvas vraiCanvas;
    Dessin_2D main; 
    
    public Setcanvas(Dessin_2D dessin)
    {
        this.main=dessin;
        this.vraiCanvas= new Canvas(this.getWidth(),this.getHeight());       // Mise en dépendance des dimensions de la zone de dessin  
        this.getChildren().add(vraiCanvas);                                  // avec les dimensions de la fenêtre   
        this.vraiCanvas.heightProperty().bind(this.heightProperty());
        this.vraiCanvas.heightProperty().addListener((o)->{
        this.drawVraiCanvas();
    });
        
        this.vraiCanvas.widthProperty().bind(this.widthProperty());
        this.vraiCanvas.widthProperty().addListener((o)->{
        this.drawVraiCanvas();
    });
          this.vraiCanvas.setOnMouseClicked((e)->{                      // Appel au controleur lorsque la souris est déplacée ou lors d'un clic
            Interaction control= this.main.getControlleur();
            control.hitindrawzone(e);
        });
        this.vraiCanvas.setOnMouseMoved((t) -> {
            this.main.getControlleur().mouseMovedDansZoneDessin(t);
        });
       
    }
            
    public void drawVraiCanvas()            // Dessin et spécification de comment dessiner chaque élement 
    {       
        GraphicsContext context = this.vraiCanvas.getGraphicsContext2D();
        Groupe model = main.getModel();
        context.clearRect(0, 0, this.vraiCanvas.getWidth(), this.vraiCanvas.getHeight());
        context.setFill(Color.valueOf("#d5f0f0"));
        context.fillRect(0, 0,this.vraiCanvas.getWidth(), this.vraiCanvas.getHeight());
        model.dessin(context);
        List<Element> select = this.main.controlleur.selectionToSupr;
        if(! select.isEmpty() )
        {
            for(Element e: select)
            {
                e.drawselction(context);
            }
        }
         Mur enCOurs = this.main.controlleur.murEnCours;
        if (enCOurs != null) {
            context.setLineDashes(1, 1);
            enCOurs.dessin(context);
            context.setLineDashes(null);
        }
        Coins tom = this.main.controlleur.coinPourMur;
        if(tom!=null)
        {
            tom.drawtomur(context);
        }
        Element call = this.main.controlleur.coinsAlligne;
        if(call!= null && call instanceof Coins)
        { 
            
                ((Coins)call).drawtomur(context);
            
        }
        
        List<Coins> tp = this.main.controlleur.topiece;
         if(! tp.isEmpty() )
        {
            for(Element e: tp)
            {                
                e.drawpiecesel(context);                
            }
        }
         ArrayList<Piece> lpiece = this.main.lPiece;
         if(!lpiece.isEmpty())
         {
        for(Piece p: lpiece)
        {
            p.indiquePiece(context);
        }
         }  
         Piece Psel = this.main.controlleur.Pselc;
         if(Psel!=null)
         {
             Psel.indiquePieceRev(context);
         }
             
}
    
// Getteurs et Setteurs :
    
        public Transform getTransform() {
        return this.vraiCanvas.getGraphicsContext2D().getTransform();
    }
}