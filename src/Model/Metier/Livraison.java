package Model.Metier;

public class Livraison {
    private static int incId = 0;

    private final int id;
    private final String idNoeud;
    private final int duree;

    public Livraison(String idNoeud, int duree){
        this.id = ++incId;
        this.idNoeud = idNoeud;
        this.duree = duree;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", idNoeud='" + idNoeud + '\'' +
                ", duree=" + duree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livraison livraison = (Livraison) o;
        return id == livraison.id;
    }

    @Override
    public int hashCode() {
        return idNoeud.hashCode();
    }
}
