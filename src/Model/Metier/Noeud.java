package Model.Metier;

public class Noeud {
    private final String id;
    private final double longitude;
    private final double latitude;

    public Noeud(String id, double latitude, double longitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

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
