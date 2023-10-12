package Modele;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @author cidmo
 */
public class Mur extends Element{

   
    int idM;
    Coins beg;
    Coins end;
    List<Piece> p;
    ArrayList<Revetement> revetement;
    double cx,cy;  
    
  public Mur(Coins deb,Coins fin,int i)         // Constructeur basique pour les murs affichées dnas le dessin 2D
  {
      idM=i;
      beg=deb;
      end=fin;
      this.cx= end.getCx()-deb.getCx();
      this.cy= end.getCy()-deb.getCy();
      this.p= new ArrayList<Piece>();
      this.revetement= new ArrayList<Revetement>();
  }
  
  public Mur(Coins deb,Coins fin)              // Constructeurs pour les murs spécifique à une pièce    
  {                                            // Pour ne pas compter des murs en 2 fois lors du devis
      beg=deb;
      end=fin;
      this.cx= end.getCx()-deb.getCx();
      this.cy= end.getCy()-deb.getCy();
       this.p= new ArrayList<Piece>();
       this.revetement= new ArrayList<Revetement>();
  }
 

  public double taille()            // Retourne la longueur d'un mur
  {
      return(Math.sqrt((cx*cx)+(cy*cy)));
  }
  
  public double surface(double Hauteur,double scaleX,double scaleY)
  {
      if(cx!=0)
      {
      return (this.taille()*scaleX)*Hauteur;
      } else
      {
         return (this.taille()*scaleY)*Hauteur; 
      }
  }
  

  
 // Méthodes héritées (voir Superclasse) :
      @Override
    public void dessin(GraphicsContext context) {
       context.setStroke(Color.BLACK);
       context.strokeLine(this.getBeg().getCx(), this.getBeg().getCy(), this.getEnd().getCx(), this.getEnd().getCy());
    }

    @Override
    public double distToPoint(Coins c) {
        double x1= this.getBeg().getCx();
        double y1= this.getBeg().getCy();
        double x2= this.getEnd().getCx();
        double y2= this.getEnd().getCy();
        double x3= c.getCx();
        double y3= c.getCy();
        double d = ((x3-x1) * (x2-x1) + (y3-y1) * (y2-y1))
                / (Math.pow(x2-x1, 2)+ Math.pow(y2- y1, 2));
        
        if(d<0) 
        {
            return this.getBeg().distToPoint(c);
        } else if(d>1)
        {
         return this.getEnd().distToPoint(c);
        } else 
        {
            Coins c4 = new Coins(x1+d*(x2-x1),y1+d*(y2-y1),0);
            return c4.distToPoint(c);
        }
        
    }

    @Override
    public void drawselction(GraphicsContext context) {
     context.setStroke(this.couleurSelection);
       context.strokeLine(this.getBeg().getCx(), this.getBeg().getCy(), this.getEnd().getCx(), this.getEnd().getCy());   
    }

    @Override
    public void drawpiecesel(GraphicsContext context) {
        context.setStroke(this.couleurSelectPourPiece);
        context.strokeLine(this.getBeg().getCx(), this.getBeg().getCy(), this.getEnd().getCx(), this.getEnd().getCy());
    }

    // Gettteurs et Setterurs :
    

    public int getIdM() {
        return idM;
    }


    public List<Piece> getP() {
        return p;
    }


    public ArrayList<Revetement> getRevetement() {
        return revetement;
    }
  

    public void setEnd(Coins end) {
        this.end = end;
    }


    public Coins getBeg() {
        return beg;
    }

    public Coins getEnd() {
        return end;
    } 
}













