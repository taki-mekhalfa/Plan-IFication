package Controleur;

import Model.Metier.Livraison;

import java.io.File;

public interface Etat {

    void init();


    void boutonChargerPlan(File fichierXML);

    void definirNombreLivreur(int nbLivreurs);

    void boutonChargerDemandeLivraison(File fichierXML);

    void boutonCalculerTournees(int nbLivreurs);

    void boutonAjouterLivraison();

    void boutonSuprimmerLivraison();

    void boutonDeplacerLivraison();

    void boutonValider();

    void boutonAnnuler();

    boolean livraisonSelectionne(Livraison livraison);

    void livraisonDeselectionnee(Livraison livraison);

    boolean noeudSelectionne(String idNoeud);

    void noeudDeselectionne(String idNoeud);
}

