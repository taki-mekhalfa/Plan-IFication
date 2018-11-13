package Controleur;
import java.io.File;

public class EtatInit extends EtatDefaut{
	
	@Override
    public void init(){
		message = "Cliquez sur le bouton Charger un plan pour charger le fichier xml de plan." + '\n' + "Vous pouvez également définir le nombre de livreurs.";
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
