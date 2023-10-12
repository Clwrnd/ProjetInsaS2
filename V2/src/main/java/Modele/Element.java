package Modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 *
 * @author cidmo
 */
public abstract class Element {      

    Color couleurSelection = Color.CORAL;
    Color couleurSelectPourPiece = Color.DEEPPINK;    
    Groupe groupe;
    
    public abstract void dessin(GraphicsContext context); // Dessine l'élement 
    public abstract double distToPoint(Coins c); // Retourne la distance de l'élement par rapport à un coins

    public abstract void drawselction(GraphicsContext context); // Dessine les élement qui sont dans une sélection
    public abstract void drawpiecesel(GraphicsContext context); // Dessine les élément qui sont dans une sélection pour créer une pièce 

    
// Getteur et Setteurs:
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

   
    
  
    
    
    
}
