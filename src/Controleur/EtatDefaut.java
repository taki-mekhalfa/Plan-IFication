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
	
    @Override
    public void init() {

    }

    @Override
    public void boutonChargerPlan(File fichierXML) {

    }

    @Override
    public void definirNombreLivreur(int nbLivreurs) {

    }

    @Override
    public void boutonChargerDemandeLivraison(File fichierXML) {

    }

    @Override
    public void boutonCalculerTournees(int nombreLivreurs) {

    }

    @Override
    public void boutonSuprimmerLivraison() {

    }

    @Override
    public void boutonDeplacerLivraison() {

    }

    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        return false;
    }

    @Override
    public void boutonValider(ListeCommandes listeCommandes) {

    }

    @Override
    public void boutonAnnuler() {

    }

    @Override
    public void boutonAjouterLivraison() {

    }

    @Override
    public void livraisonDeselectionnee(Livraison livraison) {

    }

    @Override
    public boolean noeudSelectionne(String idNoeud) {
        return false;
    }

    @Override
    public void noeudDeselectionne(String idNoeud) {

    }
    
    @Override
    public void undo(ListeCommandes l){
    	
    }
    
    @Override
    public void redo(ListeCommandes l){
    	
    }

    @Override
    public void saisieNombreLivreurs() {

    }

    @Override
    public void saisieDuree(int duree) {

    }
}
