package Controleur;

import java.io.File;

/**
 * Classe EtatPlanEtDemandeLivraisonCharges
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Controleur.Controleur
 */
public class EtatPlanEtDemandeLivraisonCharges extends EtatDefaut{

	/**
     * Initialisation de l'etat
     */
	@Override
    public void init(){
		message = "Cliquez sur le bouton Calculer tournees une fois le nombre de livreurs defini." +
    			'\n' + "Par defaut, le nombre de livreurs est egal a  3." +
    	    	'\n' + "Attention, si il y a plus de 10 livraisons par livreur, le calcul risque de durer quelques secondes.";
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.activerBoutonCalculerTournees();
        Controleur.interfaceGUI.activerSaisieLivreurs();
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
			Controleur.planification.supprimerDemandesLivraisons();
			Controleur.planification.MAJAffichage();
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
			Controleur.messageErreurDemandeLivraisonXML();
		}
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
			if (nombreLivreurs > nbLivraisons){
				Controleur.messageAlerteNbLivreur();
			}
		}
		else{
			Controleur.messageAlerteNbLivreurNul();
		}
	}
}
