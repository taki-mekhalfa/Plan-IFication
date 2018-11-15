package Model.Calcules;

import Model.Metier.*;


import java.util.List;
import java.util.Map;
/**
 * Classe correspondant à l'interface TSP pour la classe TemplateTSP
 * @author H4104
 * @see Model.Calcules.TemplateTSP
 */
public interface TSP {
    Tournee getTournee(List<Livraison> listLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite, Temps topDepart);
}
