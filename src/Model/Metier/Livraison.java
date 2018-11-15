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
     * @param idNoeud correspondant au noeud indiquant la position de la livraison � effectuer
     * @param duree correspondant � un entier indiquant la dur�e n�c�ssaire pour effectuer la livraison
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
     * M�thode d'obtention de l'identifiant du noeud correspondant � la livraison.
     * @return idNoeud correspondant � cet identifiant
     */
    public String getNoeud() {
        return idNoeud.get();
    }

    /**
     * M�thode d'obtention de la dur�e associ�e � la livraison.
     * @return duree correspondant � cet entier
     */
    public int getDuree() {
        return Integer.parseInt(duree.get());
    }

    /**
     * M�thode d'obtention du noeud correspondant � la livraison sous forme de SimpleStringProperty.
     * @return idNoeud correspondant � cette SimpleStringProperty
     */
    public SimpleStringProperty getNoeudProperty() {
        return idNoeud;
    }

    /**
     * M�thode d'obtention de la dur�e associ�e � cette livraison sous forme de SimpleStringProperty.
     * @return duree correspondant � cette SimpleStringProperty
     */
    public SimpleStringProperty getDureeProperty() {
        if (duree.get().equals("")) return duree;
        return new SimpleStringProperty(Integer.toString(Integer.parseInt(duree.get()) / 60));
    }

    /**
     * M�thode d'affectation de la SimpleStringProperty de la dur�e de cette livraison.
     * @param d correspondant � la SimpleStringProperty � affecter
     */
    public void setDureeProperty(SimpleStringProperty d){
        duree = d;
    }
    
    /**
     * M�thode d'obtention de l'heure de livraison.
     * @return heureDeLivraison correspondant � cette heure sous la forme d'une SimpleStringProperty
     */
    public SimpleStringProperty getHeureDeLivraisonProperty() {
        return heureDeLivraison;
    }

    /**
    * M�thode d'affectation de la SimpleStringProperty de l'horaire de cette livraison.
    * @param h correspondant � la SimpleStringProperty � affecter
    */
    public void setHorraireProperty(SimpleStringProperty h)
    {
        heureDeLivraison = h;
    }

    /**
     * M�thode d'affectation de la SimpleStringProperty du noeud de cette livraison.
     * @param str correspondant � la SimpleStringProperty � affecter
     */
    public void setNoeud (SimpleStringProperty str)
    {
        idNoeud = str;
    }
    
    /**
     * M�thode de conversion de la livraison en string.
     * @return Livraison correspondant � l'ensemble des composants de cette livraisons sous forme de string
     */
    @Override
    public String toString() {
        return "Livraison{" +
                ", idNoeud='" + idNoeud + '\'' +
                ", duree=" + duree +
                '}';
    }

    /**
     * M�thode de test pour l'�galit� de deux livraisons.
     * @return boolean correspondant au r�sultat de ce test
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return idNoeud.get().equals(livraison.idNoeud.get());
    }

    /**
     * M�thode d'obtention du code de hachage.
     * @return hashCode correspondant au code de hachage pour la livraison.
     */
    @Override
    public int hashCode() {
        return idNoeud.get().hashCode();
    }
}