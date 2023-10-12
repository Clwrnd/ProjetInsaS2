package Modele;

import java.util.ArrayList;

/**
 *
 * @author chloi
 */
public class Plafond  {
 ArrayList<Revetement> revetement;
 Piece piece; 
    
    public Plafond(Piece piece)
    {
        this.revetement = new ArrayList<Revetement>();
        this.piece=piece;
    }
    
    public double surface(double scaleX,double scaleY)
    {
        return (this.piece.getM1().taille()*scaleX)*(this.piece.getM3().taille()*scaleY);
    }


    // Getteurs et Setteurs :
    
    public ArrayList<Revetement> getRevetement() {
        return revetement;
    }
}
