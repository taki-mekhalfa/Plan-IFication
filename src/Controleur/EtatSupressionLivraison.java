package Controleur;

import Model.Metier.Livraison;

/**
 * Classe EtatSuppressionLivraison
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Controleur.Controleur
 * @see Model.Metier.Livraison
 */
public class EtatSupressionLivraison extends EtatDefaut {
    private Livraison livraison;

    /**
     * Initialisation de l'etat
     */
    @Override
    public void init() {
    	message = "Selectionnez une intersection a supprimer. " + 
    			'\n' + "Une fois la selection effectuee, cliquez sur Valider ou Annuler.";
        this.livraison = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

    /**
     * Actions liees a la selection d'une livraison
     * @return true si la livraison est effectivement selectionnee, false sinon.
     */
    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        if (this.livraison == null) {
        	if (!Controleur.planification.isEntrepot(livraison)){
        		this.livraison = livraison;
	            Controleur.interfaceGUI.activerBoutonValider();
	            return true;
        	}
        	else{
        		Controleur.messageErreurSuppressionEntrepot();
        		return false;
        	}
        } else return false;
    }

    /**
     * Actions liees a la deselection d'une livraison
     */
    @Override
    public void livraisonDeselectionnee(Livraison livraison) {
        Controleur.interfaceGUI.desactiverBoutonValider();
        this.livraison = null;
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Valider
     */
    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
        CommandeSuppression com = new CommandeSuppression(Controleur.planification.getTournees(), livraison);
        listeCommandes.add(com);
        Controleur.planification.supprimerPointDeLivraison(livraison);
        com.sauvegardesApres();

        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

    /**
     * Declenchement des actions liees au clic sur le bouton Annuler
     */
    @Override
    public void boutonAnnuler() {
        Controleur.vueGraphique.annulerModification();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }
}
