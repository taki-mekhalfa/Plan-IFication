package Controleur;

import Model.Metier.Livraison;

public class EtatSupressionLivraison extends EtatDefaut {
    private Livraison livraison;

    @Override
    public void init(){
        this.livraison = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        if (this.livraison == null) {
            this.livraison = livraison;
            Controleur.interfaceGUI.activerBoutonValider();
            return true;
        } else return false;
    }
    @Override
    public void livraisonDeselectionnee(Livraison livraison){
        Controleur.interfaceGUI.desactiverBoutonValider();
        this.livraison = null;
    }

    @Override
    public void boutonValider() {
        Controleur.planification.supprimerPointDeLivraison(livraison);
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

    @Override
    public void boutonAnnuler() {
        Controleur.vueGraphique.annulerModification();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }
}
