package Controleur;
import java.io.File;

/**
 * Classe EtatInit
 * @author H4104
 * @see EtatDefaut
 * @see Controleur
 */
public class EtatInit extends EtatDefaut{
	
	/**
     * Initialisation de l'etat
     */
	@Override
    public void init(){
		message = "Cliquez sur le bouton Charger un plan pour charger le fichier xml de plan." + '\n' + "Vous pouvez egalement definir le nombre de livreurs.";
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonCalculerTournees();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonUndo();
        Controleur.interfaceGUI.desactiverBoutonRedo();
        Controleur.planification.MAJAffichage();
    }
	
	/**
     * Declenchement des actions liees au clic sur le bouton Charger un plan selon l'etat courant
     * @param fichier le fichier xml contenant le plan
     */
    @Override
	public void boutonChargerPlan(File fichier){
		if (Controleur.planification.chargerPlan(fichier)){
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
		}
		else{
			Controleur.messageErreurPlanXML();
		}
	}
    
    
}
