package Model.Metier;

import java.util.List;

public class Chemin {

    private final List<String> chemin;
    private final double cout;

    public Chemin(List<String> chemin, double cout) {
        this.chemin = chemin;
        this.cout = cout;
    }

    public List<String> getChemin() {
        return chemin;
    }

    public double getCout() {
        return cout;
    }

    public String getDepart(){
        return chemin.get(0);
    }

    public String getFin(){
        return chemin.get(chemin.size() - 1);
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "chemin=" + chemin +
                ", cout=" + cout +
                '}';
    }
}
