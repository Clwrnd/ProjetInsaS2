package Modele;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author cidmo
 */
public class Piece {
     int idP;
     Mur m1,m4,m2,m3;
     Mur[] m = new Mur[4];
     Coins c1,c2,c3,c4;
     Coins[] c = new Coins[4];   
     Plafond p;
     Sol s;
    
    public Piece(Coins a,Coins b,Coins c,Coins d,int i)  
    { 
        ArrayList<Double> cxL = new ArrayList<Double>();
        ArrayList<Double> cyL = new ArrayList<Double>();
        cxL.add(a.cx); cxL.add(b.cx); cxL.add(c.cx); cxL.add(d.cx);
        cyL.add(a.cy); cyL.add(b.cy); cyL.add(c.cy); cyL.add(d.cy);
        
// Arrange les coins pour toujours avoirs la même positions de coins en c1,c2... peut importe
// la façon dont les coins sont sélectionner (facilite les calculs pour la suite)

        c1= Coins.findC(Collections.min(cxL),Collections.max(cyL), a, b, c, d); //coins haut gauche
        c2= Coins.findC(Collections.max(cxL),Collections.max(cyL), a, b, c, d); // coins haut droit
        c3= Coins.findC(Collections.min(cxL),Collections.min(cyL), a, b, c, d); // coins bas gauche
        c4= Coins.findC(Collections.max(cxL),Collections.min(cyL), a, b, c, d); // coins bas droit
        this.c[0]= this.c1; this.c[1]= this.c2; this.c[2]= this.c3; this.c[3]= this.c4;        
        this.m1= new Mur(c1,c2);this.m2= new Mur(c3,c4);this.m3= new Mur(c1,c3);this.m4= new Mur(c2,c4); // Création des murs propre à la pièce pour ne pas les compter en double
        this.m[0]=m1;this.m[1]=m2;this.m[2]=m3;this.m[3]=m4;
        
        this.idP=i;
        this.p = new Plafond(this);
        this.s = new Sol(this);
    }
           
       
    public void indiquePiece(GraphicsContext context)  // Indicateur graphique pour signaler qu'une pièce est présente
    { double x = (c1.cx+c2.cx)/2;
       double y = (c1.cy+c3.cy)/2;
     context.setFill(Color.BLACK);
     context.fillRect(x+5, y+5, 5, 5);
    }
    
    public void indiquePieceRev(GraphicsContext context) // Indicateur graphique signalant la sélection d'une pièce dans la partie Revetement
    {            
       double x = (c1.cx+c2.cx)/2;
       double y = (c1.cy+c3.cy)/2;
     context.setFill(Color.RED);
     context.fillRect(x+5, y+5, 5, 5);
    }
    
   
   public boolean mouseInPiece(double x,double y)       // Retourne vrai si le clique souris est dans une pièce
    { 
        if(x<c2.cx && x>c1.cx && y>c3.cy && y<c1.cy ) 
        {
         return true;   
        } else
        {
            return false;
        }
 }  
   
    public double devisP(double hauteur, double scalex, double scaley)
    {
        double prix=0;
                for(Revetement r:this.m1.getRevetement())
                {
                   prix= prix+ m1.surface(hauteur, scalex, scaley)*r.prix;
                }
                for(Revetement r:this.m2.getRevetement())
                {
                   prix= prix+ m2.surface(hauteur, scalex, scaley)*r.prix;
                }
                for(Revetement r:this.m3.getRevetement())
                {
                   prix= prix+ m3.surface(hauteur, scalex, scaley)*r.prix;
                }
               for(Revetement r:this.m4.getRevetement())
                {
                   prix= prix+ m4.surface(hauteur, scalex, scaley)*r.prix;
                }
               for(Revetement r:this.p.getRevetement())
                {
                   prix= prix+ p.surface(scalex, scaley)*r.prix;
                }               
               for(Revetement r:this.s.getRevetement())
                {
                   prix= prix+ s.surface( scalex, scaley)*r.prix;
                }  
               return prix;
               
             
    }
         
// Getteurs et Setteurs :
        
    public void setM1(Mur m1) {
        this.m1 = m1;
    }


    public void setM2(Mur m2) {
        this.m2 = m2;
    }


    public void setM3(Mur m3) {
        this.m3 = m3;
    }

    public void setM4(Mur m4) {
        this.m4 = m4;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public Mur getM1() {
        return m1;
    }

    public Mur getM2() {
        return m2;
    }

    public Mur getM3() {
        return m3;
    }


    public Mur getM4() {
        return m4;
    }


    public int getIdP() {
        return idP;
    }

 
    public Coins getC1() {
        return c1;
    }


    public Coins getC2() {
        return c2;
    }

 
    public Coins getC3() {
        return c3;
    }

    public Coins getC4() {
        return c4;
    }


    public Plafond getP() {
        return p;
    }


    public Sol getS() {
        return s;
    }
 
    
}