package controleur;

import java.io.File;

public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{
	
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	
	public void definirNombreLivreur(int nbLivreurs){
		
	}
	
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	
	public void boutonCalculerTournees(int nombreLivreurs){
		Controleur.planification.calculerTournees(nombreLivreurs);
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
}
