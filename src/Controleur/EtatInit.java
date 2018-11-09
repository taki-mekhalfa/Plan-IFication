package Controleur;
import java.io.File;

public class EtatInit extends EtatDefaut{

    @Override
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
}
