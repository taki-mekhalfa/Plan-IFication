package Model.Metier;

import java.util.List;
import java.util.Objects;

public class Chemin {

    private final List<String> chemin;
    private final double cout;
    private String idDepart;

    public Chemin(List<String> chemin, double cout) {
        this.chemin = chemin;
        this.cout = cout;
        idDepart = chemin.get(0);
    }

    public List<String> getChemin() {
        return chemin;
    }

    public double getCout() {
        return cout;
    }

    public String getDepart() {
        return idDepart;
    }

    public String getArrivee() {
        return chemin.get(chemin.size() - 1);
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "chemin=" + chemin +
                ", cout=" + cout +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chemin chemin1 = (Chemin) o;
        return idDepart.equals(chemin1.getDepart());
    }


}
