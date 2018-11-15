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
 * Classe permettant le retour en arrière après une opération de suppression de point de livraison
 * @author H4104
 * @see Controleur.Commande
 * @see Model.Metier.Livraison
 * @see Model.Metier.Tournee
 * @see Model.Metier.Temps
 * @see Model.Metier.Chemin
 * @see Controleur.Controleur
 */
public class CommandeSuppression implements Commande{
	private Livraison livraison;
	private Tournee tournee;
	private Map<Livraison, Temps> sauvegardeLivraisonsAvant;
	private List<Chemin> sauvegardeCheminsAvant;
	private Map<Livraison, Temps> sauvegardeLivraisonsApres;
	private List<Chemin> sauvegardeCheminsApres;
	
	/**
	 * Constructeur de la classe CommandeSuppression, sauvegarde de l'etat avant la suppression
	 * @param t Liste des tournees existantes
	 * @param liv1 Livraison supprimee
	 */
	public CommandeSuppression(List<Tournee> t, Livraison liv){
		livraison = liv;
		for(Tournee tourn : t){
			if(tourn.contientLivraison(liv)){
				tournee = tourn;
				break;
			}
		}
		sauvegardeLivraisonsAvant = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tournee.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsAvant.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsAvant = new LinkedList<>();
		for(Chemin chemin : tournee.getChemins()){
			sauvegardeCheminsAvant.add(chemin);
		}
	}
	
	/**
	 * Sauvegarde de l'etat apres la suppression
	 */
	public void sauvegardesApres(){
		sauvegardeLivraisonsApres = new HashMap<>();
		for(Entry<Livraison, Temps> paire : tournee.getHeuresDeLivraison().entrySet()){
			sauvegardeLivraisonsApres.put(paire.getKey(), paire.getValue());
		}
		sauvegardeCheminsApres = new LinkedList<>();
		for(Chemin chemin : tournee.getChemins()){
			sauvegardeCheminsApres.add(chemin);
		}
	}
	
	/**
	 * Annulation de la suppression de la livraison
	 */
	public void redoCommande(){
		Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().remove(livraison);
		tournee.setHeuresDeLivraison(sauvegardeLivraisonsApres);
		tournee.setChemins(sauvegardeCheminsApres);
		Controleur.planification.MAJAffichage();
	}
	
	/**
	 * Annulation de la suppression de la livraison
	 */
	public void undoCommande(){
		Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().add(livraison);
		tournee.setHeuresDeLivraison(sauvegardeLivraisonsAvant);
		tournee.setChemins(sauvegardeCheminsAvant);
		Controleur.planification.MAJAffichage();
		
	}
}
