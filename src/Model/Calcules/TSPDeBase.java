package Model.Calcules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Model.Metier.Chemin;
import Model.Metier.Livraison;

public class TSPDeBase extends TemplateTSP {

	@Override
	protected Iterator<Livraison> iterator(Livraison livraisonCourante, List<Livraison> nonVus, Map<Livraison , Map<Livraison, Chemin>> plusCourtsChemins) {
		return new IteratorSeq(nonVus, livraisonCourante);
	}

	@Override
	protected int bound(Livraison sommetCourant, List<Livraison> nonVus,Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins) {
		return 0;
	}
}
