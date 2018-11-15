package Controleur;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;

/**
 * Classe permettant le retour en arrière après une opération de deplacement de point de livraison
 * @author H4104
 * @see Controleur.Commande
 * @see Model.Metier.Livraison
 * @see Model.Metier.Tournee
 * @see Model.Metier.Temps
 * @see Model.Metier.Chemin
 * @see Controleur.Controleur
 */
public class CommandeDeplacement implements Commande{
	private Livraison livraisonDeplacee;
	
	private Tournee tourneePlus; //Les variables avec Plus dans le nom correspondent à la tournée qui a gagné une livraison
	private Tournee tourneeMoins; //Les variables avec Moins dans le nom correspondent à la tournée qui a perdu une livraison
	
	private Map<Livraison, Temps> sauvegardeLivraisonsPlusAvant; 	//Avant correspond à l'état de la tournée avant la modification
	private List<Chemin> sauvegardeCheminsPlusAvant;
	private Map<Livraison, Temps> sauvegardeLivraisonsPlusApres;
	private List<Chemin> sauvegardeCheminsPlusApres;
	
	private Map<Livraison, Temps> sauvegardeLivraisonsMoinsAvant;	//Après correspond à l'état de la tournée après la modification
	private List<Chemin> sauvegardeCheminsMoinsAvant;
	private Map<Livraison, Temps> sauvegardeLivraisonsMoinsApres;
	private List<Chemin> sauvegardeCheminsMoinsApres;	
	
	
	/**
	 * Constructeur de la classe CommandeDeplacement, sauvegarde de l'etat avant le deplacement
	 * @param t Liste des tournees existantes
	 * @param livDep Livraison deplacee
	 * @param liv1 Livraison precedant la livraison deplacee
	 */
	public CommandeDeplacement(List<Tournee> t, Livraison livDep, Livraison liv1){
		livraisonDeplacee = livDep;
		for(Tournee tourn : t){
			if(tourn.contientLivraison(livraisonDeplacee)){
				tourneeMoins = tourn;
				break;
			}
		}
		for(Tournee tourn : t){
			if(tourn.contientLivraison(liv1)){
				tourneePlus = tourn;
				break;
			}
		}
		sauvegardeLivraisonsPlusAvant = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tourneePlus.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsPlusAvant.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsPlusAvant = new LinkedList<>();
		for(Chemin chemin : tourneePlus.getChemins()){
			sauvegardeCheminsPlusAvant.add(chemin);
		}
		
		sauvegardeLivraisonsMoinsAvant = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tourneeMoins.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsMoinsAvant.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsMoinsAvant = new LinkedList<>();
		for(Chemin chemin : tourneeMoins.getChemins()){
			sauvegardeCheminsMoinsAvant.add(chemin);
		}
	}
	
	/**
	 * Sauvegarde de l'etat apres le deplacement
	 */
	public void sauvegardesApres(){
		sauvegardeLivraisonsPlusApres = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tourneePlus.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsPlusApres.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsPlusApres = new LinkedList<>();
		for(Chemin chemin : tourneePlus.getChemins()){
			sauvegardeCheminsPlusApres.add(chemin);
		}
		
		sauvegardeLivraisonsMoinsApres = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tourneeMoins.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsMoinsApres.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsMoinsApres = new LinkedList<>();
		for(Chemin chemin : tourneeMoins.getChemins()){
			sauvegardeCheminsMoinsApres.add(chemin);
		}
	}
	
	/**
	 * Restoration du deplacement de la livraison
	 */
	public void redoCommande(){
		//Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().add(livraisonDeplacee);
		tourneePlus.setHeuresDeLivraison(sauvegardeLivraisonsPlusApres);
		tourneePlus.setChemins(sauvegardeCheminsPlusApres);
		tourneeMoins.setHeuresDeLivraison(sauvegardeLivraisonsMoinsApres);
		tourneeMoins.setChemins(sauvegardeCheminsMoinsApres);
		Controleur.planification.MAJAffichage();
	}
	
	/**
	 * Annulation du deplacement de la livraison
	 */
	public void undoCommande(){
		//Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().remove(livraisonDeplacee);
		tourneePlus.setHeuresDeLivraison(sauvegardeLivraisonsPlusAvant);
		tourneePlus.setChemins(sauvegardeCheminsPlusAvant);
		tourneeMoins.setHeuresDeLivraison(sauvegardeLivraisonsMoinsAvant);
		tourneeMoins.setChemins(sauvegardeCheminsMoinsAvant);
		Controleur.planification.MAJAffichage();
		
	}
}
