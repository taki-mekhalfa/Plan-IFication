package Controleur;

import Model.Metier.Livraison;

import java.io.File;

/**
 * Classe EtatDefaut, qui definit les actions à realiser par defaut pour tous les etats qui en heritent
 * @author H4104
 * 
 */
public class EtatDefaut implements Etat {
	String message;
	
	/**
	 * Obtention du message de l'etat
	 * @return le message de l'etat
	 */

	@Override
	public String getMessage(){
		return message;
	}
	
	/**
	 * Initialisation de l'etat
	 */
    @Override
    public void init() {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger un plan selon l'etat courant
     * @param fichierXML le fichier xml contenant le plan
     */
    @Override
    public void boutonChargerPlan(File fichierXML) {

    }

    /**
     * Declenchement des actions liees a l'entree du nombre de livreur dans le champ prevu selon l'etat courant
     * @param nbLivreurs le nombre de livreurs indique
     */
    @Override
    public void definirNombreLivreur(int nbLivreurs) {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger livraisons selon l'etat courant
     * @param fichierXML le fichier xml contenant la demande de livraison
     */
    @Override
    public void boutonChargerDemandeLivraison(File fichierXML) {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton calculer tournees selon l'etat courant
     * @param nombreLivreurs le nombre de livreurs pour le calcul
     */
    @Override
    public void boutonCalculerTournees(int nombreLivreurs) {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Supprimer livraison selon l'etat courant
     */
    @Override
    public void boutonSuprimmerLivraison() {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Deplacer livraison selon l'etat courant
     */
    @Override
    public void boutonDeplacerLivraison() {

    }

    /**
     * Declenchement des actions liees a la selection d'une livraison selon l'etat courant
     * @return true si la livraison est effectivement selectionnee, false sinon.
     */
    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        return false;
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Valider selon l'etat courant
     */
    @Override
    public void boutonValider(ListeCommandes listeCommandes) {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Annuler selon l'etat courant
     */
    @Override
    public void boutonAnnuler() {

    }

    /**
     * Declenchement des actions liees au clic sur le bouton Ajouter livraison selon l'etat courant
     */
    @Override
    public void boutonAjouterLivraison() {

    }

    /**
     * Declenchement des actions liees a la deselection d'une livraison selon l'etat courant
     */
    @Override
    public void livraisonDeselectionnee(Livraison livraison) {

    }

    /**
     * Declenchement des actions liees a la selection d'un noeud selon l'etat courant
     * @return true si le noeud est effectivement selectionne, false sinon.
     */
    @Override
    public boolean noeudSelectionne(String idNoeud) {
        return false;
    }

    /**
     * Declenchement des actions liees a la deselection d'un noeud selon l'etat courant
     */
    @Override
    public void noeudDeselectionne(String idNoeud) {

    }
    
    /**
     * Declenchement des actions liees au clic sur le bouton undo selon l'etat courant
     */
    @Override
    public void undo(ListeCommandes l){
    	
    }
    
    /**
     * Declenchement des actions liees au clic sur le bouton redo selon l'etat courant
     */
    @Override
    public void redo(ListeCommandes l){
    	
    }

    /**
     * Declenchement des actions liees a la saisie du nombre de livreurs selon l'etat courant
     */
    @Override
    public void saisieNombreLivreurs() {

    }

    /**
     * Declenchement des actions liees a la saisie de la duree d'une livraison selon l'etat courant
     */
    @Override
    public void saisieDuree(int duree) {

    }
}
