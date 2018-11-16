package Model.Metier;

import java.util.List;
import java.util.Objects;

/**
 * Classe métier pour la gestion de chemins.
 * @author H4104
 */
public class Chemin {

    private final List<String> chemin;
    private final double cout;
    private String idDepart;

    /**
     * Constructeur de la classe Chemin.
     * @param chemin correspondant à une liste de string contenant les noeuds parcourus
     * @param cout correspondant à la valeur de la distance associée
     */
    public Chemin(List<String> chemin, double cout) {
        this.chemin = chemin;
        this.cout = cout;
        idDepart = chemin.get(0);
    }

    /**
     * Méthode d'obtention de la liste de noeuds.
     * @return chemin correspondant à la liste de noeuds du chemins
     */
    public List<String> getChemin() {
        return chemin;
    }

    /**
     * Méthode d'obtention de la distance associée au chemin.
     * @return cout correspondant à cette distance
     */
    public double getCout() {
        return cout;
    }

    /**
     * Méthode d'obtention du noeud de départ du chemin.
     * @return idDepart correspondant à l'identifiant du noeud de départ.
     */
    public String getDepart() {
        return idDepart;
    }

    /**
     * Méthode d'obtention du noeud final du chemin.
     * @return idArrive correspondant à l'identifiant du noeud final.
     */
    public String getArrivee() {
        return chemin.get(chemin.size() - 1);
    }

    /**
     * Méthode de conversion en string du chemin.
     * @return Chemin correspondant à un string contenant le descriptif du chemin
     */
    @Override
    public String toString() {
        return "Chemin{" +
                "chemin=" + chemin +
                ", cout=" + cout +
                '}';
    }

    /**
     * Méthode de test pour l'égalité de deux chemins.
     * @return boolean correspondant au résultat de ce test.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chemin chemin1 = (Chemin) o;
        return idDepart.equals(chemin1.getDepart());
    }


}
