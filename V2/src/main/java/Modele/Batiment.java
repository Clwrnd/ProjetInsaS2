package Modele;

/**
 *
 * @author cidmo
 */
public abstract class Batiment {
     String nomBatiment;
     int nombreNivEta;
     double hauteurSP;
     double longueurBase;
     double largeurBase;
     Revetement isolant;
     Revetement revExt;
     
     public double PrixExt() // Calcul du coût du revêtement et isolant extérieur 
     {
         double surfcLar = (this.largeurBase*this.hauteurSP);
         double surfcLon = (this.longueurBase*this.hauteurSP);
         double surftot = (2*(surfcLar+surfcLon))*this.nombreNivEta ;
         double prixTot = surftot*(this.isolant.getPrix()+this.revExt.getPrix());
         return prixTot;
         
     }
      
// Getteurs et Setteurs:    
 
    public String getNomBatiment() {
        return nomBatiment;
    }

    public int getNombreNivEta() {
        return nombreNivEta;
    }

    public double getHauteurSP() {
        return hauteurSP;
    }

    public double getLongueurBase() {
        return longueurBase;
    }

    public double getLargeurBase() {
        return largeurBase;
    }

    public Revetement getIsolant() {
        return isolant;
    }

    public Revetement getRevExt() {
        return revExt;
    }
          
}
