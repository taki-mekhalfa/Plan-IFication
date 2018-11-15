package Controleur;

import Model.Metier.Livraison;
import Model.Planification;
import Vue.InterfaceGUI;
import Vue.VueGraphique;
import Vue.VueTextuelle;

import java.io.File;

/**
 * Classe controleur, qui gere les changements d'etat et leurs actions associees sur le modele et les vues.
 * @author H4104
 * @see Model.Planification
 * @see Model.Metier.Livraison
 * @see Model.Metier.Tournee
 * @see Model.Metier.Temps
 * @see Model.Metier.Chemin
 * @see Controleur.EtatInit
 * @see Controleur.EtatPlanCharge
 * @see Controleur.EtatPlanEtDemandeLivraisonCharges
 * @see Controleur.EtatTourneesCalculees
 * @see Controleur.EtatSupressionLivraison
 * @see Controleur.EtatAjoutLivraison
 * @see Controleur.EtatDeplacementLivraison
 * @see Controleur.ListeCommandes
 * @see Controleur.Etat
 * @see Vue.InterfaceGUI
 * @see Vue.VueGraphique
 * @see Vue.VueTextuelle
 */
public class Controleur {

    static final EtatInit etatInit = new EtatInit();
    static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
    static final EtatPlanEtDemandeLivraisonCharges etatPlanEtDemandeLivraisonCharges = new EtatPlanEtDemandeLivraisonCharges();
    static final EtatTourneesCalculees etatTourneesCalculees = new EtatTourneesCalculees();
    static final EtatSupressionLivraison etatSupressionLivraison = new EtatSupressionLivraison();
    static final EtatAjoutLivraison etatAjoutLivraison = new EtatAjoutLivraison();
    static final EtatDeplacementLivraison etatDeplacementLivraison = new EtatDeplacementLivraison();
    private static Etat etatCourant = etatInit;

    public static Planification planification;
    public static InterfaceGUI interfaceGUI;
    public static VueGraphique vueGraphique;
    public static VueTextuelle vueTextuelle;

    public static ListeCommandes listeCommandes = new ListeCommandes();

    /**
     * Declenchement des actions liees au clic sur le bouton Charger un plan selon l'etat courant
     * @param fichierXML le fichier xml contenant le plan
     */
    public static void boutonChargerPlan(File fichierXML) {
        etatCourant.boutonChargerPlan(fichierXML);
    }

    /**
     * Declenchement des actions liees a l'entree du nombre de livreur dans le champ prevu selon l'etat courant
     * @param nbLivreurs le nombre de livreurs indique
     */
    public static void definirNombreLivreur(int nbLivreurs) {
        etatCourant.definirNombreLivreur(nbLivreurs);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Charger livraisons selon l'etat courant
     * @param fichierXML le fichier xml contenant la demande de livraison
     */
    public static void boutonChargerDemandeLivraison(File fichierXML) {
        etatCourant.boutonChargerDemandeLivraison(fichierXML);
    }

    /**
    * Declenchement des actions liees au clic sur le bouton calculer tournees selon l'etat courant
    * @param nombreLivreurs le nombre de livreurs pour le calcul
    */
    public static void boutonCalculerTournees(int nombreLivreurs) {
        etatCourant.boutonCalculerTournees(nombreLivreurs);
    }

    /**
     * Changement d'etat, appel des methodes d'entree dans l'etat
     * @param nouvelEtat le nouvel etat
     */
    static void setEtatCourant(Etat nouvelEtat) {
    	vueTextuelle.clearZoneDialogue();
        etatCourant = nouvelEtat;
        etatCourant.init();
        vueTextuelle.addZoneDialogue(etatCourant.getMessage(), 0);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Supprimer livraison selon l'etat courant
     */
    public static void boutonSuprimmerLivraison() {
        etatCourant.boutonSuprimmerLivraison();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Valider selon l'etat courant
     */
    public static void boutonValider() {
        etatCourant.boutonValider(listeCommandes);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Annuler selon l'etat courant
     */
    public static void boutonAnnuler() {
        etatCourant.boutonAnnuler();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Ajouter livraison selon l'etat courant
     */
    public static void boutonAjouterLivraison() {
        etatCourant.boutonAjouterLivraison();
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Deplacer livraison selon l'etat courant
     */
    public static void boutonDeplacerLivraison() {
        etatCourant.boutonDeplacerLivraison();
    }

    /**
     * Declenchement des actions liees a la selection d'une livraison selon l'etat courant
     * @return true si la livraison est effectivement selectionnee, false sinon.
     */
    public static boolean livraisonSelectionne(Livraison livraison) {
        return etatCourant.livraisonSelectionne(livraison);
    }

    /**
     * Declenchement des actions liees a la selection d'un noeud selon l'etat courant
     * @return true si le noeud est effectivement selectionnee, false sinon.
     */
    public static boolean noeudSelectionne(String idNoeud) {
        return etatCourant.noeudSelectionne(idNoeud);
    }

    /**
     * Declenchement des actions liees a la deselection d'un noeud selon l'etat courant
     */
    public static void noeudDeselectionne(String idNoeud) {
        etatCourant.noeudDeselectionne(idNoeud);
    }

    /**
     * Declenchement des actions liees a la deselection d'une livraison selon l'etat courant
     */
    public static void livraisonDeselectionnee(Livraison livraison) {
        etatCourant.livraisonDeselectionnee(livraison);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton undo selon l'etat courant
     */
    public static void undo() {
        etatCourant.undo(listeCommandes);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton redo selon l'etat courant
     */
    public static void redo() {
        etatCourant.redo(listeCommandes);
    }

    /**
     * Declenchement des actions liees a la saisie du nombre de livreurs selon l'etat courant
     */
    public static void saisieNombreLivreurs() {
        etatCourant.saisieNombreLivreurs();
    }

    /**
     * Declenchement des actions liees a la saisie de la duree d'une livraison selon l'etat courant
     */
    public static void saisieDuree(int duree){
        etatCourant.saisieDuree(duree);
    }
    
    /**
     * Message d'erreur pour le fichier XML du plan
     */
    public static void messageErreurPlanXML(){
        vueTextuelle.addZoneDialogue("Erreur dans le fichier xml du plan", 1);
    }
    
    /**
     * Message d'erreur pour le fichier XML de la demande de livraison
     */
    public static void messageErreurDemandeLivraisonXML(){
        vueTextuelle.addZoneDialogue("Erreur dans le fichier xml de la demande de livraison", 1);
    }
    
    /**
     * Message d'erreur pour une tentative de suppression de l'entrepot
     */
    public static void messageErreurSuppressionEntrepot(){
        vueTextuelle.addZoneDialogue("Vous ne pouvez pas supprimer l'entrepot.", 1);
    }
    
    /**
     * Message d'erreur pour une tentative de deplacement de l'entrepot
     */
    public static void messageErreurDeplacementEntrepot(){
        vueTextuelle.addZoneDialogue("Vous ne pouvez pas deplacer l'entrepot.", 1);
    }
    
    /**
     * Message d'alerte pour un nombre de livreur trop eleve par rapport au nombre de points de livraison
     */
    public static void messageAlerteNbLivreur(){
        vueTextuelle.addZoneDialogue("Attention, le nombre de livreurs que vous avez entre est superieur au nombre de points de livraison, il y a donc des livreurs inutilises.", 2);
    }
    
    /**
     * Message d'alerte pour un nombre de livreur nul
     */
    public static void messageAlerteNbLivreurNul(){
        vueTextuelle.addZoneDialogue("Attention, vous n'avez pas de livreur pour cette demande de livraison", 2);
    }
}

