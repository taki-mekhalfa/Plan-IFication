package Controleur;

import java.io.File;

public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{
	@Override
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	@Override
	public void definirNombreLivreur(int nbLivreurs){
		
	}
	@Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	@Override
	public void boutonCalculerTournees(int nombreLivreurs){
		Controleur.planification.calculerTournees(nombreLivreurs);
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
}
