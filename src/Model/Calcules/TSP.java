package Model.Calcules;

import Model.Metier.*;

import java.util.List;
import java.util.Map;

public interface TSP {
    Tournee getTournee(List<Livraison> listLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite, Temps topDepart);
}
