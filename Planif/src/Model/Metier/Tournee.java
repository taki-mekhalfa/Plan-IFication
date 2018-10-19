package Model.Metier;

import java.util.List;
import java.util.Map;

public class Tournee {
    private final List<Chemin> chemins;
    private final Map<Livraison, Temps> heuresDeLivraison;

    public Tournee(List<Chemin> chemins, Map<Livraison, Temps> heuresDeLivraison) {
        this.chemins = chemins;
        this.heuresDeLivraison = heuresDeLivraison;
        
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public Map<Livraison, Temps> getHeuresDeLivraison() {
        return heuresDeLivraison;
    }
}
