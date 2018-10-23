package Model.Calcules;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Plan;

import java.util.List;
import java.util.Map;

public interface PCC {
    Map<Livraison, Chemin> getPlusCourtsChemins(String idSource, List<Livraison> pointsDestination, Plan plan);
}
