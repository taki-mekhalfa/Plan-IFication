package controleur;

import java.io.File;

import Model.Metier.Livraison;
import Model.Metier.Tournee;

public interface Etat {
	public void boutonChargerPlan(File fichierXML);
	public void definirNombreLivreur(int nbLivreurs);
	public void boutonChargerDemandeLivraison(File fichierXML);
	public void boutonCalculerTournees(int nbLivreurs);
	public void selectionnerPointLivraison(Livraison livraison);
	public void deselectionnerPointLivraison();
	public void boutonAjouterPointLivraison(Tournee tournee, Livraison livraison);
	public void boutonSupprimerPointLivraison(Tournee tournee, Livraison livraison);
	public void boutonChangerPointLivraison(Tournee tournee, Livraison livraison);
	public void validerOperation();
	public void annulerOperation();
}
