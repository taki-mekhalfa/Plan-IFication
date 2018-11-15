package Model.Metier;

import java.util.HashMap;
import java.util.Map;

public class NoeudFactory {
    private static final Map<String, Noeud> noeuds = new HashMap<>();

    private NoeudFactory() {
    }

    public static Noeud makeNoeud(String idNoeud, double latitude, double longitude) {
        Noeud noeud = noeuds.get(idNoeud);
        if (noeud == null) {
            noeud = new Noeud(idNoeud, latitude, longitude);
            noeuds.put(idNoeud, noeud);
        }

        return noeud;
    }

    public static Noeud getNoeudParId(String idNoeud) {
        return noeuds.get(idNoeud);
    }
}
