package Controleur;

import java.io.File;

public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{

	@Override
    public void init(){
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
		Controleur.planification.calculerTournees(nombreLivreurs);
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
}
