package Controleur;

import java.io.File;

/**
 * Classe EtatPlanCharge
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Controleur.Controleur
 */
public class EtatPlanCharge extends EtatDefaut{

	/**
     * Initialisation de l'etat
     */
    @Override
    public void init(){
    	message = "Cliquez sur le bouton Charger livraisons pour charger le fichier xml" + 
    			'\n' + "de demande de livraison." + 
    			'\n' + "Vous pouvez egalement definir le nombre de livreurs.";
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger un plan
     * @param fichierXML le fichier xml contenant le plan
     */
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

	/**
     * Declenchement des actions liees au clic sur le bouton Charger livraisons
     * @param fichierXML le fichier xml contenant la demande de livraison
     */
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
