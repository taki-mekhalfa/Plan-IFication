package Model.Calcules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Model.Metier.Chemin;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;
import Model.Calcules.TSP;

public abstract class TemplateTSP implements TSP {
	private Map< Livraison,Map<Livraison,Chemin>> plusCourtsChemins;
	private List<Livraison> meilleureSolution;
	private double coutMeilleureSolution;
	private List<Livraison> listeLivraisons;
	private int tempsLimite;
	
	public Tournee getTournee(List<Livraison> listeLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite,Temps topDepart){
		this.plusCourtsChemins = plusCourtsChemins;
		this.coutMeilleureSolution=0;
		this.listeLivraisons = listeLivraisons;
		this.tempsLimite = tempsLimite;
		List<Livraison> nonVus = new LinkedList<>(this.listeLivraisons);
		List<Livraison> vus = new LinkedList<>();
		vus.add(listeLivraisons.get(0));
		nonVus.remove(0);
		branchAndBound(listeLivraisons.get(0), nonVus, vus, 0, plusCourtsChemins, System.currentTimeMillis(), tempsLimite);
		
		List<Chemin> listeChemins = new LinkedList<>();
		Map<Livraison,Temps> edt = new HashMap<>();
		
		Temps tpsCourant;
		tpsCourant = topDepart;
		Livraison livraisonPrecedente=meilleureSolution.get(0);
		
		for(int i=1;i<meilleureSolution.size();i++){
			Livraison livraisonCourant = meilleureSolution.get(i);
			Chemin chemin = plusCourtsChemins.get(livraisonPrecedente).get(livraisonCourant);
			listeChemins.add(chemin);
			tpsCourant.addConvert(livraisonPrecedente.getDuree()+(chemin.getCout()/4.17)) ;
			// \\
			edt.put(livraisonCourant,tpsCourant);
			livraisonPrecedente=livraisonCourant;
		}
		Tournee res = new Tournee(listeChemins,edt);
		
		return null;
	}
	
	
	
	public double getCoutMeilleureSolution(){
		return coutMeilleureSolution;
	}
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCourant
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return une borne inferieure du cout des permutations commencant par sommetCourant, 
	 * contenant chaque sommet de nonVus exactement une fois et terminant par le sommet 0
	 */
	protected abstract int bound(Livraison sommetCourant, List<Livraison> nonVus,Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins);
	
	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCrt
	 * @param nonVus : tableau des sommets restant a visiter
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @return un iterateur permettant d'iterer sur tous les sommets de nonVus
	 */
	protected abstract Iterator<Livraison> iterator(Livraison sommetCrt, List<Livraison> nonVus, Map<Livraison , Map<Livraison, Chemin>> plusCourtsChemins);
	/**
	 * Methode definissant le patron (template) d'une resolution par separation et evaluation (branch and bound) du TSP
	 * @param sommetCrt le dernier sommet visite
	 * @param nonVus la liste des sommets qui n'ont pas encore ete visites
	 * @param vus la liste des sommets visites (y compris sommetCrt)
	 * @param coutVus la somme des couts des arcs du chemin passant par tous les sommets de vus + la somme des duree des sommets de vus
	 * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i < nbSommets
	 * @param tpsDebut : moment ou la resolution a commence
	 * @param tpsLimite : limite de temps pour la resolution
	 */	
	 void branchAndBound(Livraison livraisonCourante, List<Livraison> nonVus, List<Livraison> vus, double coutVus, Map<Livraison, Map<Livraison,Chemin>> cout, long tpsDebut, int tpsLimite){
		 
	    if (nonVus.size() == 0){ // tous les sommets ont ete visites
	    	coutVus += cout.get(livraisonCourante).get(listeLivraisons.get(0)).getCout() / 4.17;
	    	if (coutVus < coutMeilleureSolution){ // on a trouve une solution meilleure que meilleureSolution
	    		meilleureSolution=vus;
	    		coutMeilleureSolution = coutVus;
	    	}
	    } else if (coutVus + bound(livraisonCourante, nonVus, cout) < coutMeilleureSolution){
	        Iterator<Livraison> it = iterator(livraisonCourante,nonVus, cout);
	        while (it.hasNext()){
	        	Livraison prochaineLivraison = it.next();
	        	vus.add(prochaineLivraison);
	        	nonVus.remove(prochaineLivraison);
	        	double nouveauCout = cout.get(livraisonCourante).get(prochaineLivraison).getCout()/4.17 + prochaineLivraison.getDuree();
	        	branchAndBound(prochaineLivraison, nonVus, vus, coutVus + nouveauCout, cout, tpsDebut, tpsLimite);
	        	vus.remove(prochaineLivraison);
	        	nonVus.add(prochaineLivraison);
	        }	    
	    }
	}
}

