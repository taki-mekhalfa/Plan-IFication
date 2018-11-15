package Model.Metier;

/**
 * Classe de gestion des noeuds du graphe.
 * @author H4104
 * @see Model.Metier.NoeudFactory
 */
public class Noeud {
    private final String id;
    private final double longitude;
    private final double latitude;

    /**
     * Constructeur de la classe Noeud.
     * @param id correspondant à l'identifiant du noeud à créer
     * @param latitude correspondant la position en latitude du noeud
     * @param longitude correspondant la position en longitude du noeud
     */
    public Noeud(String id, double latitude, double longitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Méthode d'obtention de l'identifiant du noeud.
     * @return id correspondant à l'identifiant recherché
     */
    public String getId() {
        return id;
    }

    /**
     * Méthode d'obtention de la longitude du noeud.
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Méthode d'obtention de la latitude du noeud.
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Méthode de test pour l'égalité de deux noeuds.
     * @return boolean correspondant au résultat de ce test
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return this.id.equals(noeud.id);
    }

    /**
     * Méthode d'obtention du code de hachage.
     * @return hashCode correspondant au code de hachage pour le noeud.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * Méthode de conversion en string du noeud.
     * @return Noeud correspondant à un string contenant les informations relatives au noeud
     */
    @Override
    public String toString() {
        return "Noeud{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
