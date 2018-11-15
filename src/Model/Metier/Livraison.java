package Model.Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe de gestion de livraison.
 * @author H4104
 */
public class Livraison {

    private SimpleStringProperty idNoeud;
    private SimpleStringProperty duree;
    private SimpleStringProperty heureDeLivraison;

    /**
     * Constructeur de la classe Livraison
     * @param idNoeud correspondant au noeud indiquant la position de la livraison à effectuer
     * @param duree correspondant à un entier indiquant la durée nécéssaire pour effectuer la livraison
     * @see Model.Metier.Noeud
     */
    public Livraison(String idNoeud, int duree) {
        this.idNoeud = new SimpleStringProperty(idNoeud);
        this.duree = new SimpleStringProperty(Integer.toString(duree));
        heureDeLivraison = null;
    }

    /**
     * Constructeur par defaut de la classe Livraison.
     */
    public Livraison () {
        idNoeud = null;
        duree = null;
    }

    /**
     * Méthode d'obtention de l'identifiant du noeud correspondant à la livraison.
     * @return idNoeud correspondant à cet identifiant
     */
    public String getNoeud() {
        return idNoeud.get();
    }

    /**
     * Méthode d'obtention de la durée associée à la livraison.
     * @return duree correspondant à cet entier
     */
    public int getDuree() {
        return Integer.parseInt(duree.get());
    }

    /**
     * Méthode d'obtention du noeud correspondant à la livraison sous forme de SimpleStringProperty.
     * @return idNoeud correspondant à cette SimpleStringProperty
     */
    public SimpleStringProperty getNoeudProperty() {
        return idNoeud;
    }

    /**
     * Méthode d'obtention de la durée associée à cette livraison sous forme de SimpleStringProperty.
     * @return duree correspondant à cette SimpleStringProperty
     */
    public SimpleStringProperty getDureeProperty() {
        if (duree.get().equals("")) return duree;
        return new SimpleStringProperty(Integer.toString(Integer.parseInt(duree.get()) / 60));
    }

    /**
     * Méthode d'affectation de la SimpleStringProperty de la durée de cette livraison.
     * @param d correspondant à la SimpleStringProperty à affecter
     */
    public void setDureeProperty(SimpleStringProperty d){
        duree = d;
    }
    
    /**
     * Méthode d'obtention de l'heure de livraison.
     * @return heureDeLivraison correspondant à cette heure sous la forme d'une SimpleStringProperty
     */
    public SimpleStringProperty getHeureDeLivraisonProperty() {
        return heureDeLivraison;
    }

    /**
    * Méthode d'affectation de la SimpleStringProperty de l'horaire de cette livraison.
    * @param h correspondant à la SimpleStringProperty à affecter
    */
    public void setHorraireProperty(SimpleStringProperty h)
    {
        heureDeLivraison = h;
    }

    /**
     * Méthode d'affectation de la SimpleStringProperty du noeud de cette livraison.
     * @param str correspondant à la SimpleStringProperty à affecter
     */
    public void setNoeud (SimpleStringProperty str)
    {
        idNoeud = str;
    }
    
    /**
     * Méthode de conversion de la livraison en string.
     * @return Livraison correspondant à l'ensemble des composants de cette livraisons sous forme de string
     */
    @Override
    public String toString() {
        return "Livraison{" +
                ", idNoeud='" + idNoeud + '\'' +
                ", duree=" + duree +
                '}';
    }

    /**
     * Méthode de test pour l'égalité de deux livraisons.
     * @return boolean correspondant au résultat de ce test
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return idNoeud.equals(livraison.idNoeud);
    }

    /**
     * Méthode d'obtention du code de hachage.
     * @return hashCode correspondant au code de hachage pour la livraison.
     */
    @Override
    public int hashCode() {
        return idNoeud.hashCode();
    }
}