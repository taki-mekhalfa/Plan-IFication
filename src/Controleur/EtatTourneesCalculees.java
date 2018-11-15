package Controleur;

import java.io.File;

public class EtatTourneesCalculees extends EtatDefaut {

    @Override
    public void init(){
    	message = "Tournees calculees." + 
    			'\n' + "Vous pouvez modifier le resultat en cliquant sur les boutons" + 
    			'\n' + "de modification (Ajouter, supprimer et deplacer)";
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
    
    @Override
    public void undo(ListeCommandes l){
    	l.undo();
    }
    
    @Override
    public void redo(ListeCommandes l){
    	l.redo();
    }

    @Override
    public void saisieNombreLivreurs(){
        Controleur.interfaceGUI.activerBoutonCalculerTournees();
    }

    @Override
    public void boutonCalculerTournees(int nombreLivreurs){
        Controleur.planification.calculerTournees(nombreLivreurs);
        Controleur.listeCommandes.init();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }
}
