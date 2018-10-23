package controleur;

import java.io.File;

public class EtatTourneesCalculees extends EtatDefaut{
	
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	
	public void definirNombreLivreur(int nbLivreurs){
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
}
