package Controleur;

import java.io.File;

public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{

	@Override
    public void init(){
		message = "Cliquez sur le bouton Calculer tournees une fois le nombre de livreurs d�fini." + 
    			'\n' + "Par d�faut, le nombre de livreurs est �gal � 3.";
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
			Controleur.messageErreurPlanXML();
		}
	}

	@Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		if (Controleur.planification.chargerDemandesDeLivraisons(fichierXML)){
			Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
		}
		else{
			Controleur.planification.supprimerDemandesLivraisons();
			Controleur.planification.MAJAffichage();
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
			Controleur.messageErreurDemandeLivraisonXML();
		}
	}

	@Override
	public void boutonCalculerTournees(int nombreLivreurs){
		Controleur.planification.calculerTournees(nombreLivreurs);
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
}
