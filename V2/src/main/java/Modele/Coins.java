package Modele;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author cidmo
 */
public class Coins extends Element {

     int idC;
     double cx;
     double cy;
     int rayonDessin= 3;
     ArrayList<Piece> inPiece; // Pièces auquels le point appartient
    
     
    public Coins(double a,double b,int c)   // Constructeur de base pour les coins affichés sur le dessin 2D
    {
        this.cx=a;
        this.cy=b;
        idC=c;
        this.inPiece= new ArrayList<Piece>();
    }
    
    
    public Coins(double a,double b)   // Constructeur pour les coins permettant de sélectionner l'élément le plus proche
    {
        this.cx=a;
        this.cy=b;
    }
    public Coins(Coins modele) {    // Constructeur pour contruire le mur en cours de création
         
        this(modele.cx,modele.cx);
    }
               
    boolean inWall(List<Element> e)             // Retourne vrai si le coin considéré est le début ou fin d'un mur       
    {                                               
        boolean b = false;
        for(Element el:e ) 
        {  
            if(el instanceof Mur && (((Mur) el).beg==this||((Mur) el).end==this) )
            {                
                    b= true;
                
            } 
            
        }
        return b;
    }
    
    Mur InWallGet(List<Element> e)  // Retourne le mur le auquel le coins est le début ou la fin, liée à la méthode précèdente
    {
        Mur m=null;
        for(Element el:e ) 
        {
            if(el instanceof Mur &&(((Mur) el).beg==this||((Mur) el).end==this) )
            {
                
                    m=((Mur)el);
            }
            }
        return m;
    }
    
     
   public boolean testCoinsEnDouble(List<Element> e)        // test un coins ayant les mêmes coordoonées existe déja
   { boolean test=true;
       for(Element al: e)
       {
           if(al instanceof Coins && this.cx==((Coins)al).cx&& this.cy==((Coins)al).cy)
           {
               test= false;
           }
               
       }
       return test;
   }
   
   
   public Coins pointAlligneX( List<Element> e )    // Retourne le point alligné(avec une marge) le plus proche sur l'axe horizontal
   {   
       Coins nearest = null;
       ArrayList<Element> c = new ArrayList();
       for(Element al: e)
       {
           if(al instanceof Coins && Math.abs(this.cx-((Coins)al).cx ) < 15              )
           {
                c.add((Coins)al);
                Groupe g = new Groupe(c);
                nearest = (Coins)g.nearest(this, Double.MAX_VALUE);
           }
          
          
               
       }
       return nearest;
       
   }
   
      public Coins pointAlligneY( List<Element> e )  // Retourne le point alligné(avec une marge) le plus proche sur l'axe Vertical
   { 
       
       Coins nearest = null;
       ArrayList<Element> c = new ArrayList();
       for(Element al: e)
       {
           if(al instanceof Coins && Math.abs(this.cy-((Coins)al).cy ) < 15              )
           {
                c.add((Coins)al);
                Groupe g = new Groupe(c);
                nearest = (Coins)g.nearest(this, Double.MAX_VALUE);
           }
          
          
               
       }
       return nearest;
       
   }
   
      public static Coins findC(double x,double t,Coins a,Coins b,Coins c,Coins d)  // Associe un coins à un autre si ils ont les mêmes caractéristiques
      { Coins[] cn = new Coins[4];
        Coins coins= null;
        cn[0]=a;cn[1]=b;cn[2]=c;cn[3]=d;
        for(Coins C:cn)
        {
            if(C.cx==x&&C.cy==t)
            {
                coins=C;
            }
        }
          return coins;
      }
    
   // Méthode héritées (voir superclasse) :
      @Override
    public void dessin(GraphicsContext context) {      
       context.setFill(Color.BLACK); 
       context.fillOval(getCx()-getRayonDessin(), getCy()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
    }

    @Override
    public double distToPoint(Coins b) {
      return(Math.sqrt(((this.getCx()-b.getCx())*(this.getCx()-b.getCx()))+((this.getCy()-b.getCy())*(this.getCy()-b.getCy()))) );
    }

    @Override
    public void drawselction(GraphicsContext context) {
        context.setFill(this.couleurSelection);
        context.fillOval(getCx()-getRayonDessin(), getCy()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
    }
    
     public void drawtomur(GraphicsContext context) {
        context.setFill(Color.RED);
        context.fillOval(getCx()-getRayonDessin(), getCy()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
    }
    

    @Override
    public void drawpiecesel(GraphicsContext context) {
        context.setFill(this.couleurSelectPourPiece);
        context.fillOval(getCx()-getRayonDessin(), getCy()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
    }


    
    
   // Getteurs et Setteurs :
    
    public ArrayList<Piece> getInPiece() {
        return inPiece;
    }

        public int getRayonDessin() {
        return rayonDessin;
    }

   
    public double getCx() {
        return cx;
    }

 
    public double getCy() {
        return cy;
    }

        public int getIdC() {
        return idC;
    }
    
    
}
