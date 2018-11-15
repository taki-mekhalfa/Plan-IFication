package Model.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plan {

    private final Map<String, List<Troncon>> plan;

    /**
     * constructeur
     * @param plan map de plan
     */
    public Plan(Map<String, List<Troncon>> plan) {
        this.plan = plan;
    }

    /**
     * getter successeurs
     * @param idNoeud id d'un noeud
     * @return liste des troncons
     */
    public List<Troncon> getSuccesseurs(String idNoeud) {
        return plan.get(idNoeud);
    }

    /**
     * getter noeuds
     * @return liste des id des noeuds
     */
    public List<String> getNoeuds() {
        return new ArrayList<>(plan.keySet());
    }

    /**
     * recuperer le nom de la rue
     * @param idSource id de source
     * @param idDes id de destination
     * @return le nom de la rue
     */
    public String getNomDeLaRue(String idSource, String idDes){
        for (Troncon troncon : plan.get(idSource)){
            if (troncon.getDestination().equals(idDes)) return troncon.nomDeLaRue;
        }

        return "Pas du Nom";
    }

    /**
     * recuperer la distance entre le source et la destination  
     * @param idSource id de source
     * @param idDestination id de destination
     * @return la distance
     */
    public double getDistance(String idSource, String idDestination) {
        List<Troncon> troncons = plan.get(idSource);
        for (Troncon troncon : troncons) {
            if (troncon.getDestination().equals(idDestination)) return troncon.longueur;
        }

        return Double.POSITIVE_INFINITY;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "plan=" + plan +
                '}';
    }

    public static class Troncon {
        private final String idOrigine;
        private final String idDestination;
        private final double longueur;
        private final String nomDeLaRue;

        /**constructeur
         * @param idOrigine id de l'origine
         * @param idDestination id de la destination
         * @param longueur longueur
         * @param nomDeLaRue nom de la rue
         */
        public Troncon(String idOrigine, String idDestination, double longueur, String nomDeLaRue) {
            this.idOrigine = idOrigine;
            this.idDestination = idDestination;
            this.longueur = longueur;
            this.nomDeLaRue = nomDeLaRue;
        }

        /**
         * recuperer longueur
         * @return longueur
         */
        public double getLongueur() {
            return longueur;
        }

        /**
         * recuperer l'origine
         * @return id de l'origine
         */
        public String getOrigine() {
            return idOrigine;
        }

        /**
         * recuperer la destination
         * @return id de la destination
         */
        public String getDestination() {
            return idDestination;
        }

        /**
         * recuperer le nom de la rue
         * @return nom de la rue
         */
        public String getNomDeLaRue() {
            return nomDeLaRue;
        }

        @Override
        public String toString() {
            return "Troncon{" +
                    "idOrigine='" + idOrigine + '\'' +
                    ", idDestination='" + idDestination + '\'' +
                    ", longueur=" + longueur +
                    ", nomDeLaRue='" + nomDeLaRue + '\'' +
                    '}';
        }
    }
}
