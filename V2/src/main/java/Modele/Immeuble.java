package Modele;
/**
 *
 * @author cidmo
 */
public class Immeuble extends Batiment {
        
     public Immeuble(String nom, int nbN,double h,double longue,double larg,Revetement isolant,Revetement revExt)
             {
              this.nomBatiment=nom;
              this.nombreNivEta=nbN;
              this.hauteurSP=h;
              this.largeurBase=larg;
              this.longueurBase=longue;
              this.isolant=isolant;
              this.revExt=revExt;
             }    
}
