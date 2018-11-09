package Model.Metier;

public class Livraison {

    private final String idNoeud;
    private final int duree;

    public Livraison(String idNoeud, int duree) {
        this.idNoeud = idNoeud;
        this.duree = duree;
    }


    public String getNoeud() {
        return idNoeud;
    }

    public int getDuree() {
        return duree;
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
