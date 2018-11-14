package Controleur;

import java.io.File;

public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{

	@Override
    public void init(){

		message = "Cliquez sur le bouton Calculer tournees une fois le nombre de livreurs défini." + 
    			'\n' + "Par défaut, le nombre de livreurs est égal à 3." + 
    	    	'\n' + "Attention, si il y a plus de 10 livraisons par livreur, le calcul risque de durer quelques secondes.";

        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.activerBoutonCalculerTournees();
        Controleur.interfaceGUI.activerSaisieLivreurs();
    }
	@Override
	public void boutonChargerPlan(File fichier){
		if (Controleur.planification.chargerPlan(fichier)){
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
		}
		else{
			Controleur.planification.supprimerPlan();
			Controleur.planification.MAJAffichage();
			Controleur.setEtatCourant(Controleur.etatInit);
		}
	}

	@Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}

	@Override
	public void boutonCalculerTournees(int nombreLivreurs){
		int nbLivraisons = Controleur.planification.getDemandeLivraisons().getPointsDeLivraisons().size();
		if (nombreLivreurs > 0){
			Controleur.planification.calculerTournees(nombreLivreurs);
			Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
			if (nombreLivreurs > nbLivraisons){
				Controleur.messageAlerteNbLivreur();
			}
		}
		else{
			Controleur.messageAlerteNbLivreurNul();
		}
	}
}
