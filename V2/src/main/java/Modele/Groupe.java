package Modele;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author cidmo
 * Classe permettant de regrouper les éléments qui seront affichés sur le dessin
 */
public class Groupe extends Element {
    List<Element> contenant;
    
    public Groupe()                                     
    {
        this.contenant = new ArrayList<Element>();
    }
    public Groupe(List<Element> g)
    {
        this.contenant = g;
    }
    
    
    public void add(Element e) {                                    // Ajout d'élément à un groupe
        if (e.groupe != this) {
            if (e.groupe != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.getContenant().add(e);
            e.setGroupe(this);
        }
    }
    
    public void remove(Element e) {                                   // Retirer un élément d'un groupe en adaptant que 
        if (e.groupe != this) {                                       // si on enlève un mur on enlève ses coins de début fin, de même
            throw new Error("la figure n'est pas dans le groupe");    // pour les coins
        }
        else if(e instanceof Mur)
        {
        this.getContenant().remove(e);
        this.getContenant().remove(((Mur) e).beg);
        this.getContenant().remove(((Mur) e).end);
        e.setGroupe(null);
        ((Mur) e).beg.setGroupe(null);
        ((Mur) e).end.setGroupe(null);
        } 
        else if(e instanceof Coins &&(((Coins) e).inWall(this.contenant))) 
        {                
                
                this.getContenant().remove(((Coins) e).InWallGet(contenant).beg);
                this.getContenant().remove(((Coins) e).InWallGet(contenant).end);
                this.getContenant().remove(((Coins) e).InWallGet(contenant));
                                
        
        } else {
            this.getContenant().remove(e);
        e.setGroupe(null);
        }
    }
    
    public void removeAll(List<Element> lE) {
        for (Element e : lE) {
            this.remove(e);
        }
    }
        public void clear() {
        List<Element> toRemove = new ArrayList<>(this.getContenant());
        this.removeAll(toRemove);
    }
           
    public Element nearest(Coins p, double dmax) {      // Retourne l'élement le plus proche du clique dans un certains rayon
        if (this.getContenant().isEmpty()) {
            return null;
        } else {
            Element fmin = this.getContenant().get(0);
            double min = fmin.distToPoint(p);
            for (int i = 1; i < this.getContenant().size(); i++) {
                Element fcur = this.getContenant().get(i);
                double cur = fcur.distToPoint(p);
                if (cur < min) {
                    min = cur;
                    fmin = fcur;
                }
            }
            if (min <= dmax) {
                return fmin;
            } else {
                return null;
            }
        }
    }

  public void reassignationCoinsMur(List<Element> e) // Réassigne les coins de début et de fin des murs  
  {                                                  // Ces-derniers se mélangent parfois à la sauvegarde à cause de la supression d'autre élément
      boolean t1,t2;
      t1=false;
      t2=false;
    for(Element a:e){
      if(a instanceof Mur)
      {
          for(Element a1:e)
          {
              if(a1 instanceof Coins)
              {
                  if(((Mur) a).beg.cx==((Coins) a1).cx &&((Mur) a).beg.cy==((Coins) a1).cy)
                  {
                      ((Mur) a).beg.idC=this.contenant.indexOf(a1);
                      
                      t1=true;
                      if(t1){break;}
                  }
              }  
          } 
      } 
    }
          for(Element a:e){
      if(a instanceof Mur)
      {
          for(Element a1:e)
          {
              if(a1 instanceof Coins)
              {
                  if(((Mur) a).end.cx==((Coins) a1).cx &&((Mur) a).end.cy==((Coins) a1).cy)
                  {
                      ((Mur) a).end.idC=this.contenant.indexOf(a1);
                      
                      t2=true;
                      if(t2){break;}
                  }
              }  
          } 
      } 
    }
      
  }
    
      
    
    
// Méthodes héritées (voir superclasse) :
    @Override
    public void dessin(GraphicsContext context) {
        for (Element e: this.getContenant())
        {
            e.dessin(context);
        }
    }

    
    

    @Override
    public void drawselction(GraphicsContext context) {
        for(Element e: this.getContenant())
        {
            e.drawselction(context);
        }
    }

    @Override
    public double distToPoint(Coins c) {
        if (this.getContenant().isEmpty()) {
            return new Coins(0, 0).distToPoint(c);
        } else {
            double dist = this.getContenant().get(0).distToPoint(c);
            for (int i = 1; i < this.getContenant().size(); i++) {
                double cur = this.getContenant().get(i).distToPoint(c);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
   }
    @Override
    public void drawpiecesel(GraphicsContext context) {
    }

    
    
    // Getteurs et Setteurs :
    
    public List<Element> getContenant() {
        return contenant;
    }


  
    
}
