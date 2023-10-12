package Modele;

import java.util.ArrayList;

/**
 *
 * @author chloi
 */
public  class Revetement {
    double prix;
    int id;
    String designation;
    boolean pMur;
    boolean pSol;
    boolean pPlafond;
    ArrayList<Object> Applique;
    
    public Revetement(int id,String design,boolean pM,boolean pS,boolean pP, double prixU)
    {
        this.Applique= new ArrayList<Object>();
        this.id= id;
        this.designation=design;
        this.pMur =pM;
        this.pSol=pS;
        this.pPlafond=pP;
        this.prix=prixU;
    }
    
        public Revetement(String design, double prixU)
    {
        this.designation=design;
        this.prix=prixU;
    }
  
  public static ArrayList<Revetement> revDeBase()  // Créer les revetements de base lors de la création pour la 1er fois du batiment
  {
      ArrayList<Revetement> rBase= new ArrayList<Revetement>();
      rBase.add( new Revetement(1,"Peinture *",true,false,true,10.95));
      rBase.add( new Revetement(2,"Peinture **",true,false,true,29.90));
      rBase.add( new Revetement(3,"Peinture ***",true,false,true,77.30));
      rBase.add( new Revetement(4,"Carrelage *",true,true,false,49.75));
      rBase.add( new Revetement(5,"Carrelage **",true,true,false,89.45));
      rBase.add( new Revetement(6,"Lambris *",true,true,false,42.50));
      rBase.add( new Revetement(7,"Lambris **",true,true,true,50.60));
      rBase.add( new Revetement(8,"Marbre",true,true,false,97.85));
      rBase.add( new Revetement(9,"Crêpi",true,false,false,67.80));
      rBase.add( new Revetement(10,"Papier peint",true,false,false,32.90));  
      rBase.add( new Revetement(11,"Plaquettes de parement",true,false,false,15.20));
      rBase.add( new Revetement(12,"Liège *",true,false,false,25.40));
      rBase.add( new Revetement(13,"Liège **",false,true,false,33.90));
      rBase.add( new Revetement(14,"Parquet",false,true,false,46.36));
      rBase.add( new Revetement(15,"Vynile Lino",false,true,false,23.55));
      rBase.add( new Revetement(16,"Moquette",false,true,false,48.10));
      rBase.add( new Revetement(17,"Stratifié",false,true,false,17.95));  
      rBase.add( new Revetement(18,"Gazon",false,true,false,17.95));
      
      
      return rBase;
  }

  // Getteurs et Setteurs :

    public String getDesignation() {
        return designation;
    }

    public boolean ispMur() {
        return pMur;
    }


    public boolean ispSol() {
        return pSol;
    }


    public boolean ispPlafond() {
        return pPlafond;
    }


    public int getId() {
        return id;
    }


    public double getPrix() {
        return prix;
    }

    public ArrayList<Object> getApplique() {
        return Applique;
    }
}
