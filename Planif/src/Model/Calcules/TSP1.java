package Model.Calcules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Model.Metier.Chemin;
import Model.Metier.Livraison;

public class TSP1 extends TemplateTSP {

	@Override
	protected Iterator<Livraison> iterator(Livraison sommetCrt, List<Livraison> nonVus, Map<Livraison , Map<Livraison, Chemin>> plusCourtsChemins) {
		return null;
	}

	@Override
	protected int bound(Livraison sommetCourant, List<Livraison> nonVus,Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins) {
		return 0;
	}
}
