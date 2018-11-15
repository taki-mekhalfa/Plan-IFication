package Model.Metier;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe pour l'usine de gestion de noeud.
 * @author H4104
 * @see Model.Metier.Noeud
 */
public class NoeudFactory {
    private static final Map<String, Noeud> noeuds = new HashMap<>();

    /**
     * Constructeur de la classe NoeudFactory.
     */
    private NoeudFactory() {
    }

    /**
     * Méthode de création de noeuds.
     * @param idNoeud correspondant à l'identifiant du noeud à créer
     * @param latitude correspondant la position en latitude du noeud
     * @param longitude correspondant la position en longitude du noeud
     * @return noeud correspondant au noeud ainsi créer
     */
    public static Noeud makeNoeud(String idNoeud, double latitude, double longitude) {
        Noeud noeud = noeuds.get(idNoeud);
        if (noeud == null) {
            noeud = new Noeud(idNoeud, latitude, longitude);
            noeuds.put(idNoeud, noeud);
        }

        return noeud;
    }

    /**
     * Méthode d'obtention de noeud.
     * @param idNoeud correspondant à l'identifiant du noeud que l'on veut obtenir
     * @return noeud correspondant au noeud selectionné par l'identifiant fournis
     */
    public static Noeud getNoeudParId(String idNoeud) {
        return noeuds.get(idNoeud);
    }
}
