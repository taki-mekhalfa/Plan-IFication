package controleur;
import java.io.File;

public class EtatInit extends EtatDefaut{
	
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	
	public void definirNombreLivreur(int nbLivreurs){
		
	}
	
}
