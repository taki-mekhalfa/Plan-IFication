package controleur;
import Model.Planification;

public class Controleur {
	protected static Etat etatCourant;
	
	protected static final EtatInit etatInit = new EtatInit();
	protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
	protected static final EtatPlanEtDemandeLivraisonCharges etatPlanEtDemandeLivraisonCharges = new EtatPlanEtDemandeLivraisonCharges();
	protected static final EtatTourneesCalculees etatTourneesCalculees = new EtatTourneesCalculees();
	
	protected static Planification planification;
	
	public void boutonChargerPlan(){
		etatCourant.boutonChargerPlan();
	}
	
	public void definirNombreLivreur(int nbLivreurs){
		etatCourant.definirNombreLivreur(nbLivreurs);
	}
	
	public void boutonChargerDemandeLivraison(){
		etatCourant.boutonChargerDemandeLivraison();
	}
	
	public void boutonCalculerTournees(){
		etatCourant.boutonCalculerTournees();
	}
	
	protected static void setEtatCourant(Etat nouvelEtat){
		etatCourant = nouvelEtat;
	}
}
