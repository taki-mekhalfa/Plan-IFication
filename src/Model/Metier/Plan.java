package Model.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plan {

    private final Map<String, List<Trancon>> plan;

    public Plan(Map<String, List<Trancon>> plan) {
        this.plan = plan;
    }

    public List<Trancon> getSuccesseurs(String idNoeud) {
        return plan.get(idNoeud);
    }

    public List<String> getNoeuds() {
        return new ArrayList<>(plan.keySet());
    }

    public String getNomDeLaRue(String idSource, String idDes){
        for (Trancon trancon : plan.get(idSource)){
            if (trancon.getDestination().equals(idDes)) return trancon.nomDeLaRue;
        }

        return "Pas du Nom";
    }

    public double getDistance(String idSource, String idDestination) {
        List<Trancon> trancons = plan.get(idSource);
        for (Trancon trancon : trancons) {
            if (trancon.getDestination().equals(idDestination)) return trancon.longueur;
        }

        return Double.POSITIVE_INFINITY;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "plan=" + plan +
                '}';
    }

    public static class Trancon {
        private final String idOrigine;
        private final String idDestination;
        private final double longueur;
        private final String nomDeLaRue;

        public Trancon(String idOrigine, String idDestination, double longueur, String nomDeLaRue) {
            this.idOrigine = idOrigine;
            this.idDestination = idDestination;
            this.longueur = longueur;
            this.nomDeLaRue = nomDeLaRue;
        }

        public double getLongueur() {
            return longueur;
        }

        public String getOrigine() {
            return idOrigine;
        }

        public String getDestination() {
            return idDestination;
        }

        public String getNomDeLaRue() {
            return nomDeLaRue;
        }

        @Override
        public String toString() {
            return "Trancon{" +
                    "idOrigine='" + idOrigine + '\'' +
                    ", idDestination='" + idDestination + '\'' +
                    ", longueur=" + longueur +
                    ", nomDeLaRue='" + nomDeLaRue + '\'' +
                    '}';
        }
    }
}
