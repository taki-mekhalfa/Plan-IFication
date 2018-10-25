package Controleur;
import Model.Planification;

import java.io.File;

public class Controleur {

    static final EtatInit etatInit = new EtatInit();
    static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
    static final EtatPlanEtDemandeLivraisonCharges etatPlanEtDemandeLivraisonCharges = new EtatPlanEtDemandeLivraisonCharges();
    static final EtatTourneesCalculees etatTourneesCalculees = new EtatTourneesCalculees();

    private static Etat etatCourant = etatInit;

    public static Planification planification;
	
	public static void boutonChargerPlan(File fichierXML){

		etatCourant.boutonChargerPlan(fichierXML);
	}
	
	public static void definirNombreLivreur(int nbLivreurs){
		etatCourant.definirNombreLivreur(nbLivreurs);
	}
	
	public static void boutonChargerDemandeLivraison(File fichierXML){
		etatCourant.boutonChargerDemandeLivraison(fichierXML);
	}
	
	public static void boutonCalculerTournees(int nombreLivreurs){
		etatCourant.boutonCalculerTournees(nombreLivreurs);
	}
	
	static void setEtatCourant(Etat nouvelEtat){
		etatCourant = nouvelEtat;
	}
}
