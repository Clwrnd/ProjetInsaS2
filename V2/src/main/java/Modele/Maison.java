package Modele;
/**
 *
 * @author cidmo
 */
public class Maison extends Batiment {
     
     public Maison(String nom, int nbN,double h,double longue,double larg,Revetement isolant,Revetement revExt)
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
