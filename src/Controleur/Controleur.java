package controleur;
import Model.Planification;
import Model.Metier.Livraison;
import Model.Metier.Tournee;

import java.io.File;

public class Controleur {

    static final EtatInit etatInit = new EtatInit();
    static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
    static final EtatPlanEtDemandeLivraisonCharges etatPlanEtDemandeLivraisonCharges = new EtatPlanEtDemandeLivraisonCharges();
    static final EtatTourneesCalculees etatTourneesCalculees = new EtatTourneesCalculees();
    static final EtatPointLivraisonSelectionne etatPointLivraisonSelectionne = new EtatPointLivraisonSelectionne();
    static final EtatAjouterPointLivraison etatAjouterPointLivraison = new EtatAjouterPointLivraison();
    static final EtatSupprimerPointLivraison etatSupprimerPointLivraison = new EtatSupprimerPointLivraison();
    static final EtatChangerPointLivraison etatChangerPointLivraison = new EtatChangerPointLivraison();
    
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
	
	public static void selectionnerPointLivraison(Livraison livraison){
		etatCourant.selectionnerPointLivraison(livraison);
    }
    
	public static void deselectionnerPointLivraison(){
		etatCourant.deselectionnerPointLivraison();
	}
    
	public static void boutonAjouterPointLivraison(Tournee tournee, Livraison livraison){
		etatCourant.boutonAjouterPointLivraison(tournee, livraison);
	}
    
	public static void boutonSupprimerPointLivraison(Tournee tournee, Livraison livraison){
		etatCourant.boutonSupprimerPointLivraison(tournee, livraison);
	}
    
	public static void boutonChangerPointLivraison(Tournee tournee, Livraison livraison){
		etatCourant.boutonChangerPointLivraison(tournee, livraison);
	}
	
	public void validerOperation(){
		etatCourant.validerOperation();
	}
	
	public void annulerOperation(){
		etatCourant.annulerOperation();
    }
	
	protected static void setEtatCourant(Etat nouvelEtat){
		etatCourant = nouvelEtat;
	}
	
}
