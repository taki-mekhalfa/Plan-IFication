package Controleur;

import java.io.File;

/**
 * Classe EtatTourneesCalculees
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Controleur.Controleur
 */
public class EtatTourneesCalculees extends EtatDefaut {

	/**
     * Initialisation de l'etat
     */
    @Override
    public void init(){
    	message = "Tournees calculees." + 
    			'\n' + "Vous pouvez modifier le resultat en cliquant sur les boutons de modification (Ajouter, supprimer et deplacer)";
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.activerBoutonCalculerTournees();
        Controleur.interfaceGUI.activerSaisieLivreurs();
        Controleur.interfaceGUI.activerBoutonAjouterLivraison();
        Controleur.interfaceGUI.activerBoutonSuprimmerLivraison();
        Controleur.interfaceGUI.activerBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonValider();
        Controleur.interfaceGUI.desactiverBoutonAnnuler();
        Controleur.interfaceGUI.desactiveSaisieDureeLivraison();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger un plan
     * @param fichierXML le fichier xml contenant le plan
     */
    @Override
    public void boutonChargerPlan(File fichier) {
        if (Controleur.planification.chargerPlan(fichier)){
            Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
            Controleur.interfaceGUI.desactiverSaisieLivreurs();
        	Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
	        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
	        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
	        Controleur.interfaceGUI.desactiverBoutonUndo();
	        Controleur.interfaceGUI.desactiverBoutonRedo();
	        Controleur.listeCommandes.init();
	
	        Controleur.setEtatCourant(Controleur.etatPlanCharge);
        }
        
        else{
        	Controleur.planification.supprimerPlan();
			Controleur.planification.MAJAffichage();
        	Controleur.setEtatCourant(Controleur.etatInit);
        }
        
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger livraisons
     * @param fichierXML le fichier xml contenant la demande de livraison
     */
    @Override
    public void boutonChargerDemandeLivraison(File fichierXML) {
        if (Controleur.planification.chargerDemandesDeLivraisons(fichierXML)){
        	Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
	        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
	        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
	        Controleur.interfaceGUI.desactiverBoutonUndo();
	        Controleur.interfaceGUI.desactiverBoutonRedo();
	        Controleur.listeCommandes.init();
	        Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
        }
        else{
        	Controleur.planification.supprimerDemandesLivraisons();
			Controleur.planification.MAJAffichage();
        	Controleur.setEtatCourant(Controleur.etatPlanCharge);
        }
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Supprimer livraison
     */
    @Override
    public void boutonSuprimmerLivraison() {
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverSaisieLivreurs();
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatSupressionLivraison);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Ajouter livraison
     */
    @Override
    public void boutonAjouterLivraison() {
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverSaisieLivreurs();
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatAjoutLivraison);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Deplacer livraison
     */
    @Override
    public void boutonDeplacerLivraison(){
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverSaisieLivreurs();
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatDeplacementLivraison);
    }
    
    /**
     * Declenchement des actions liees au clic sur le bouton undo
     */
    @Override
    public void undo(ListeCommandes l){
    	l.undo();
    }
    
    /**
     * Declenchement des actions liees au clic sur le bouton redo
     */
    @Override
    public void redo(ListeCommandes l){
    	l.redo();
    }

    /**
     * Declenchement des actions liees a la saisie du nombre de livreurs
     */
    @Override
    public void saisieNombreLivreurs(){
        Controleur.interfaceGUI.activerBoutonCalculerTournees();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton calculer tournees
     * @param nombreLivreurs le nombre de livreurs pour le calcul
     */
    @Override
    public void boutonCalculerTournees(int nombreLivreurs){
        int nbLivraisons = Controleur.planification.getDemandeLivraisons().getPointsDeLivraisons().size();
        if (nombreLivreurs > 0){
            Controleur.planification.calculerTournees(nombreLivreurs);
            Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
            Controleur.listeCommandes.init();
            if (nombreLivreurs > nbLivraisons){
                Controleur.messageAlerteNbLivreur();
            }
        }
        else{
            Controleur.messageAlerteNbLivreurNul();
        }
    }
}
