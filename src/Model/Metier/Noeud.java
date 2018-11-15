package Model.Metier;

public class Noeud {
    private final String id;
    private final double longitude;
    private final double latitude;

    /**
     * constructeur
     * @param id id d'un noeud
     * @param latitude latitude
     * @param longitude longitude
     */
    public Noeud(String id, double latitude, double longitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * getter id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * getter longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * getter latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return this.id.equals(noeud.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "Noeud{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
