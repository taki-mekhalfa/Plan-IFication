package Controleur;

import Model.Metier.Livraison;
import Model.Metier.Tournee;

public class EtatSupressionLivraison extends EtatDefaut {
    private Livraison livraisonPrec;
    private Livraison livraison;
    private Livraison livraisonSuiv;
    private Tournee tournee;

    @Override
    public void init() {
    	message = "Selectionnez une intersection a supprimer. " + 
    			'\n' + "Une fois la selection effectuee, cliquez sur Valider ou Annuler.";
        this.livraison = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

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

    @Override
    public void livraisonDeselectionnee(Livraison livraison) {
        Controleur.interfaceGUI.desactiverBoutonValider();
        this.livraison = null;
    }

    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
        CommandeSuppression com = new CommandeSuppression(Controleur.planification.getTournees(), livraison);
        listeCommandes.add(com);
        Controleur.planification.supprimerPointDeLivraison(livraison);
        com.sauvegardesApres();

        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

    @Override
    public void boutonAnnuler() {
        Controleur.vueGraphique.annulerModification();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }
}
