package Model.Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Livraison {

    private SimpleStringProperty idNoeud;
    private final SimpleIntegerProperty duree;
    private SimpleStringProperty heureDeLivraison;


    public Livraison(String idNoeud, int duree) {
        this.idNoeud = new SimpleStringProperty(idNoeud);
        this.duree = new SimpleIntegerProperty(duree);
        heureDeLivraison = null;
    }

    public Livraison () {
        idNoeud = null;
        duree = null;
    }


    public String getNoeud() {
        return idNoeud.get();
    }

    public int getDuree() {
        return duree.get();
    }

    public SimpleStringProperty getNoeudProperty() {
        return idNoeud;
    }

    public SimpleIntegerProperty getDureeProperty() {
        return duree;
    }

    public SimpleStringProperty getHeureDeLivraisonProperty() {
        return heureDeLivraison;
    }

    public void setHorraireProperty(SimpleStringProperty h)
    {
        heureDeLivraison = h;
    }

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