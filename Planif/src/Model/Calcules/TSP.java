package Model.Calcules;

import Model.Metier.Tournee;

import java.util.Map;

import Model.Metier.Chemin;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Livraison;
import Model.Metier.Temps;

public interface TSP {
	public Tournee getTournee(DemandeLivraisons demandeLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite, Temps topDepart);
}
