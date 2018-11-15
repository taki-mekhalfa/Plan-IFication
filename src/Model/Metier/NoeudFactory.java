package Model.Metier;

import java.util.HashMap;
import java.util.Map;

public class NoeudFactory {
    private static final Map<String, Noeud> noeuds = new HashMap<>();

    /**
     * contructeur par defaut
     */
    private NoeudFactory() {
    }

    /**
     * creer un noeud
     * @param idNoeud id d'un noeud
     * @param latitude latitude
     * @param longitude longitude
     * @return noeud
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
     * recuperer un noeud par son id
     * @param idNoeud id de ce noeud
     * @return noeud
     */
    public static Noeud getNoeudParId(String idNoeud) {
        return noeuds.get(idNoeud);
    }
}
