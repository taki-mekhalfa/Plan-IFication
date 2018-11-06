package Controleur;

import Model.Metier.Livraison;
import Model.Planification;
import Vue.InterfaceGUI;
import Vue.VueGraphique;

import java.io.File;

public class Controleur {

    static final EtatInit etatInit = new EtatInit();
    static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
    static final EtatPlanEtDemandeLivraisonCharges etatPlanEtDemandeLivraisonCharges = new EtatPlanEtDemandeLivraisonCharges();
    static final EtatTourneesCalculees etatTourneesCalculees = new EtatTourneesCalculees();
    static final EtatSupressionLivraison etatSupressionLivraison = new EtatSupressionLivraison();
    static final EtatAjoutLivraison etatAjoutLivraison = new EtatAjoutLivraison();
    static final EtatDeplacementLivraison etatDeplacementLivraison = new EtatDeplacementLivraison();
    private static Etat etatCourant = (Etat) etatInit;

    public static Planification planification;
    public static InterfaceGUI interfaceGUI;
    public static VueGraphique vueGraphique;
    
    private static ListeCommandes listeCommandes;
	
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
		etatCourant.init();
	}

    public static void boutonSuprimmerLivraison(){
	    etatCourant.boutonSuprimmerLivraison();
    }
    public static void boutonValider(){
	    etatCourant.boutonValider();
    }
    public static void boutonAnnuler() {
	    etatCourant.boutonAnnuler();
    }
    public static void boutonAjouterLivraison() {
        etatCourant.boutonAjouterLivraison();
    }
    public static void boutonDeplacerLivraison() {etatCourant.boutonDeplacerLivraison();}

    public static boolean livraisonSelectionne(Livraison livraison){
	    return etatCourant.livraisonSelectionne(livraison);
    }
    public static boolean noeudSelectionne(String idNoeud){
        return etatCourant.noeudSelectionne(idNoeud);
    }
    public static void noeudDeselectionne(String idNoeud){
        etatCourant.noeudDeselectionne(idNoeud);
    }
    public static void livraisonDeselectionnee(Livraison livraison) {
	    etatCourant.livraisonDeselectionnee(livraison);
    }
    public static void undo(){
    	etatCourant.undo(listeCommandes);
    }
    public static void redo(){
    	etatCourant.redo(listeCommandes);
    }
}

