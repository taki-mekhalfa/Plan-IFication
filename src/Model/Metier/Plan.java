package Model.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe de gestion du plan contenant les éléments obtenus dans le fichier XML chargé.
 * @author H4104
 */
public class Plan {

    private final Map<String, List<Troncon>> plan;

    /**
     * Constructeur de la classe Plan.
     * @param plan correspondant au même plan mais sous forme d'une map de strign associés avec des listes de troncons
     */
    public Plan(Map<String, List<Troncon>> plan) {
        this.plan = plan;
    }

    /**
     * Méthode d'obtention des successeurs associés à un noeud précis.
     * @param idNoeud correspondant  à l'identifiant du noeud selectionné
     * @return List<Troncon> correspondant à cette liste de successeurs
     */
    public List<Troncon> getSuccesseurs(String idNoeud) {
        return plan.get(idNoeud);
    }

    /**
     * Méthode d'obtention des noeuds du plan.
     * @return List<String> correspondant à cette série de noeuds
     */
    public List<String> getNoeuds() {
        return new ArrayList<>(plan.keySet());
    }

    /**
     * Méthode d'obtention d'un nom de rue.
     * @param idSource correspondant à l'identifiant du noeud d'origine de la rue
     * @param idDes correspondant à l'identifiant du noeud de destination de la rue
     * @return String correspondant au nom de la rue recherchée
     */
    public String getNomDeLaRue(String idSource, String idDes){
        for (Troncon troncon : plan.get(idSource)){
            if (troncon.getDestination().equals(idDes)) return troncon.nomDeLaRue;
        }

        return "Pas du Nom";
    }

    /**
     * Méthode d'obtention de la distance associée à une rue
     * @param idSource correspondant à l'identifiant du noeud d'origine de la rue
     * @param idDes correspondant à l'identifiant du noeud de destination de la rue
     * @return double correspondant à la distance associée à la rue recherchée
     */
    public double getDistance(String idSource, String idDestination) {
        List<Troncon> troncons = plan.get(idSource);
        for (Troncon troncon : troncons) {
            if (troncon.getDestination().equals(idDestination)) return troncon.longueur;
        }

        return Double.POSITIVE_INFINITY;
    }

    /**
     * Méthode de conversion du plan sous forme de string.
     * @String correspondant à la description du plan sous forme d'une simple string
     */
    @Override
    public String toString() {
        return "Plan{" +
                "plan=" + plan +
                '}';
    }

    /**
     * Classe de gestion des tronçons du plan.
     * @author H4104
     */
    public static class Troncon {
        private final String idOrigine;
        private final String idDestination;
        private final double longueur;
        private final String nomDeLaRue;

        /**
         * Constructeur de la classe Troncon.
         * @param idOrigine correspondant à l'identifiant du noeud d'origine de ce tronçon
         * @param idDestination correspondant à l'identifiant du noeud de destination de ce tronçon
         * @param longueur correspondant à la distance associée au tronçon en question
         * @param nomDeLaRue correspondant au nom de rue associé à ce tronçon
         */
        public Troncon(String idOrigine, String idDestination, double longueur, String nomDeLaRue) {
            this.idOrigine = idOrigine;
            this.idDestination = idDestination;
            this.longueur = longueur;
            this.nomDeLaRue = nomDeLaRue;
        }

        /**
         * Méthode d'obtention de la distance.
         * @return longueur correspondant à un double quantifiant la distance parcourue par ce tronçon
         */
        public double getLongueur() {
            return longueur;
        }

        /**
         * Méthode d'obtention du noeud d'origine.
         * @return idOrigine correspondant à l'identifiant du noeud de départ du tronçon
         */
        public String getOrigine() {
            return idOrigine;
        }

        /**
         * Méthode d'obtention du noeud de destination.
         * @return idDestination correspondant à l'identifiant du noeud d'arrivée du tronçon
         */
        public String getDestination() {
            return idDestination;
        }

        /**
         * Méthode d'obtention du nom de la rue.
         * @return nomDeLaRue correspondant à une string contenant le nom de la rue associée à ce tronçon
         */
        public String getNomDeLaRue() {
            return nomDeLaRue;
        }

        /**
         * Méthode de conversion du tronçon sous forme de string.
         * @String correspondant à la description du tronçon sous forme d'une simple string
         */
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
