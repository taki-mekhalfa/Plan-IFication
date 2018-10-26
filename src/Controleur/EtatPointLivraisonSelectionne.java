package controleur;

import java.io.File;

import Model.Metier.Livraison;
import Model.Metier.Tournee;

public class EtatPointLivraisonSelectionne extends EtatDefaut {
	
	@Override
	public void boutonChargerPlan(File fichier){
		Controleur.planification.chargerPlan(fichier);
		Controleur.setEtatCourant(Controleur.etatPlanCharge);
	}
	@Override
	public void definirNombreLivreur(int nbLivreurs){
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	@Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
		Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
	}
	@Override
    public void selectionnerPointLivraison(Livraison livraison){
		Controleur.setEtatCourant(Controleur.etatPointLivraisonSelectionne);
    }
	@Override
	public void deselectionnerPointLivraison(){
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
    @Override
	public void boutonSupprimerPointLivraison(Tournee tournee, Livraison livraison){
    	Controleur.setEtatCourant(Controleur.etatSupprimerPointLivraison);
	}
    @Override
	public void boutonChangerPointLivraison(Tournee tournee, Livraison livraison){
    	Controleur.setEtatCourant(Controleur.etatChangerPointLivraison);
	}
    @Override
	public void boutonAjouterPointLivraison(Tournee tournee, Livraison livraison){
    	Controleur.setEtatCourant(Controleur.etatAjouterPointLivraison);
	}
}
