package Model.Calcules;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Plan;

import java.util.List;
import java.util.Map;

/**
 * Classe correspondant à l'interface PCC pour la classe Djikstra
 * @author H4104
 * @see Model.Calcules.Djikstra
 */
public interface PCC {
    Map<Livraison, Chemin> getPlusCourtsChemins(String idSource, List<Livraison> pointsDestination, Plan plan);
}
