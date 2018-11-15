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
 * Classe permettant le retour en arrière après une opération d'ajout de point de livraison
 * @author H4104
 * @see Controleur.Commande
 * @see Model.Metier.Livraison
 * @see Model.Metier.Tournee
 * @see Model.Metier.Temps
 * @see Model.Metier.Chemin
 * @see Controleur.Controleur
 */
public class CommandeAjout implements Commande{
	private Livraison livraisonAjoutee;
	private Tournee tournee;
	private Map<Livraison, Temps> sauvegardeLivraisonsAvant;
	private List<Chemin> sauvegardeCheminsAvant;
	private Map<Livraison, Temps> sauvegardeLivraisonsApres;
	private List<Chemin> sauvegardeCheminsApres;
	
	/**
	 * Constructeur de la classe CommandeAjout, sauvegarde de l'etat avant l'ajout
	 * @param t Liste des tournees existantes
	 * @param liv1 Livraison precedant la livraison ajoutee
	 */
	public CommandeAjout(List<Tournee> t, Livraison liv1, boolean entrepot){
		for(Tournee tourn : t){
			if(tourn.contientLivraison(liv1)){
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
	 * Sauvegarde de l'etat apres l'ajout
	 * @param liv Livraison ajoutee
	 */
	public void sauvegardesApres(Livraison liv){
		livraisonAjoutee = liv;
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
	 * Restoration de l'ajout de la livraison
	 */
	public void redoCommande(){
		Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().add(livraisonAjoutee);
		tournee.setHeuresDeLivraison(sauvegardeLivraisonsApres);
		tournee.setChemins(sauvegardeCheminsApres);
		Controleur.planification.MAJAffichage();
	}
	
	/**
	 * Annulation de l'ajout de la livraison
	 */
	public void undoCommande(){
		Controleur.vueGraphique.getDemandeLivraisons().getPointsDeLivraisons().remove(livraisonAjoutee);
		tournee.setHeuresDeLivraison(sauvegardeLivraisonsAvant);
		tournee.setChemins(sauvegardeCheminsAvant);
		Controleur.planification.MAJAffichage();
		
	}
}
