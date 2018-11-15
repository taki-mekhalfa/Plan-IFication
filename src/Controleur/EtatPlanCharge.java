package Controleur;

import java.io.File;

public class EtatPlanCharge extends EtatDefaut{

    @Override
    public void init(){
    	message = "Cliquez sur le bouton Charger livraisons pour charger le fichier xml" + 
    			'\n' + "de demande de livraison." + 
    			'\n' + "Vous pouvez également définir le nombre de livreurs.";
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
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
			Controleur.messageErreurDemandeLivraisonXML();
		}
	}
}
