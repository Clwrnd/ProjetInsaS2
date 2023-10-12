package fr.insa.issenhuth.devis_batiment_fx;

/**
 *
 * @author cidmo
 * Classe permettant l'affichage du récapitulatif des données sur les revêtement dans le devis
 */
public class RecapGeneral {
    private String designation;
    private double prix;
    private double surface;
    private double total;
    
    public RecapGeneral(String designation, double prix, double surface, double total)
    {
        this.designation=designation;
        this.prix=prix;
        this.surface=surface;
        this.total=total;
    }

 // Getteurs et Setteurs :
    
    public String getDesignation() {
        return designation;
    }


    public double getPrix() {
        return prix;
    }


    public double getSurface() {
        return surface;
    }


    public double getTotal() {
        return total;
    }
    
}
