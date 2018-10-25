package Controleur;

import java.io.File;

public interface Etat {
	public void boutonChargerPlan(File fichierXML);
	public void definirNombreLivreur(int nbLivreurs);
	public void boutonChargerDemandeLivraison(File fichierXML);
	public void boutonCalculerTournees(int nbLivreurs);
}
