package Controleur;

import Model.Metier.Livraison;

public class EtatDeplacementLivraison extends EtatDefaut {

    Livraison livraisonADeplacer;
    Livraison livraison1;
    Livraison livraison2;

    @Override
    public void init(){
        livraisonADeplacer = null;
        livraison1 = null;
        livraison2 = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        if (livraisonADeplacer == null) {
            livraisonADeplacer = livraison;
            return true;
        }

        if (livraison1 != null && livraison2 != null) return false;
        if (livraison1 == null) livraison1 = livraison;
        else livraison2 = livraison;
        if (livraison1 != null && livraison2 != null) {
            boolean rt = Controleur.planification.livraisonsConsecutives(livraison1, livraison2);
            if (rt) Controleur.interfaceGUI.activerBoutonValider();
            else livraison2 = null;
            return rt;
        }
        return true;
    }

    @Override
    public void livraisonDeselectionnee(Livraison livraison){
        if (livraison.equals(livraisonADeplacer)){
            livraisonADeplacer = null;
            livraison1 = null;
            livraison2 = null;
            Controleur.vueGraphique.annulerModification();
        }else {
            if (livraison.equals(livraison2)) livraison2 = null;
            if (livraison.equals(livraison1)) {
                livraison1 = livraison2;
                livraison2 = null;
            }
        }

        Controleur.interfaceGUI.desactiverBoutonValider();
    }

    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
    	CommandeDeplacement com = new CommandeDeplacement(Controleur.planification.getTournees(), livraisonADeplacer, livraison1);
    	listeCommandes.add(com);
    	Controleur.planification.deplacerLivraison(livraisonADeplacer, livraison1, livraison2);
    	Controleur.vueGraphique.annulerModification();
        com.sauvegardesApres();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

    @Override
    public void boutonAnnuler() {
        Controleur.vueGraphique.annulerModification();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

}
