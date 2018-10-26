package controleur;

import java.io.File;

import Model.Metier.Livraison;
import Model.Metier.Tournee;

public class EtatDefaut implements Etat{

    @Override
	public void boutonChargerPlan(File fichierXML){
		
	}
    @Override
	public void definirNombreLivreur(int nbLivreurs){
		
	}
    @Override
	public void boutonChargerDemandeLivraison(File fichierXML){
		
	}
    @Override
	public void boutonCalculerTournees(int nombreLivreurs){
		
	}
    @Override
    public void selectionnerPointLivraison(Livraison livraison){
    	
    }
    @Override
	public void deselectionnerPointLivraison(){
		
	}
    @Override
	public void boutonAjouterPointLivraison(Tournee tournee, Livraison livraison){
		
	}
    @Override
	public void boutonSupprimerPointLivraison(Tournee tournee, Livraison livraison){
		
	}
    @Override
	public void boutonChangerPointLivraison(Tournee tournee, Livraison livraison){
		
	}
    @Override
	public void validerOperation(){
		
	}
    @Override
    public void annulerOperation(){
    	
    }
}
