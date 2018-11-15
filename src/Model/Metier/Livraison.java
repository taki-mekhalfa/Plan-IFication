package Model.Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Livraison {

    private SimpleStringProperty idNoeud;
    private SimpleStringProperty duree;
    private SimpleStringProperty heureDeLivraison;


    /**
     * constructeur
     * @param idNoeud id d'un noeud
     * @param duree duree d'un noeud
     */
    public Livraison(String idNoeud, int duree) {
        this.idNoeud = new SimpleStringProperty(idNoeud);
        this.duree = new SimpleStringProperty(Integer.toString(duree));
        heureDeLivraison = null;
    }

    /**
     * constructeur par defaut
     */
    public Livraison () {
        idNoeud = null;
        duree = null;
    }


    /**
     * getter id d'un noeud
     * @return id de ce noeud
     */
    public String getNoeud() {
        return idNoeud.get();
    }

    /**
     * getter de la duree d'un noeud
     * @return duree de ce noeud
     */
    public int getDuree() {
        return Integer.parseInt(duree.get());
    }

    /**
     * @return idNoeud
     */
    public SimpleStringProperty getNoeudProperty() {
        return idNoeud;
    }

    /**
     * @return duree
     */
    public SimpleStringProperty getDureeProperty() {
        if (duree.get().equals("")) return duree;
        return new SimpleStringProperty(Integer.toString(Integer.parseInt(duree.get()) / 60));
    }

    /**
     * @param d duree
     */
    public void setDureeProperty(SimpleStringProperty d){
        duree = d;
    }
    /**
     * @return heureDeLivraison
     */
    public SimpleStringProperty getHeureDeLivraisonProperty() {
        return heureDeLivraison;
    }

    /**
     * @param h heureDeLivraison
     */
    public void setHorraireProperty(SimpleStringProperty h)
    {
        heureDeLivraison = h;
    }

    /**
     * @param str id noeud
     */
    public void setNoeud (SimpleStringProperty str)
    {
        idNoeud = str;
    }
    @Override
    public String toString() {
        return "Livraison{" +
                ", idNoeud='" + idNoeud + '\'' +
                ", duree=" + duree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return idNoeud.equals(livraison.idNoeud);
    }

    @Override
    public int hashCode() {
        return idNoeud.hashCode();
    }
}