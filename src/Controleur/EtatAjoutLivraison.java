package Controleur;

import Model.Metier.Livraison;

public class EtatAjoutLivraison extends EtatDefaut {
    private Livraison livraison1;
    private Livraison livraison2;
    private String idNoeud;

    @Override
    public void init() {
        this.livraison1 = null;
        this.livraison2 = null;
        this.idNoeud = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        if (livraison1 != null && livraison2 != null) return false;
        if (livraison1 == null) livraison1 = livraison;
        else livraison2 = livraison;
        if (livraison1 != null && livraison2 != null) {
            boolean rt = Controleur.planification.livraisonsConsecutives(livraison1, livraison2);
            if (!rt) livraison2 = null;
            else if (idNoeud != null) Controleur.interfaceGUI.activerBoutonValider();
            return rt;
        }
        return true;
    }

    @Override
    public void livraisonDeselectionnee(Livraison livraison) {
        if (livraison.equals(livraison2)) livraison2 = null;
        if (livraison.equals(livraison1)) {
            livraison1 = livraison2;
            livraison2 = null;
        }
        Controleur.interfaceGUI.desactiverBoutonValider();
    }

    @Override
    public boolean noeudSelectionne(String idNoeud) {
        if (this.idNoeud == null) {
            this.idNoeud = idNoeud;
            if (livraison1 != null && livraison2 != null) {
                Controleur.interfaceGUI.activerBoutonValider();
            }
            return true;
        } else return false;
    }

    @Override
    public void noeudDeselectionne(String idNoeud) {
        this.idNoeud = null;
        Controleur.interfaceGUI.desactiverBoutonValider();
    }

    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
        CommandeAjout com = new CommandeAjout(Controleur.planification.getTournees(), livraison1);
    	listeCommandes.add(com);
    	Livraison livAjoute = Controleur.planification.ajouterPointDeLivraison(idNoeud, 500, livraison1, livraison2);
    	Controleur.vueGraphique.annulerModification();
        com.sauvegardesApres(livAjoute);
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

    @Override
    public void boutonAnnuler() {
        Controleur.vueGraphique.annulerModification();
        Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
    }

}
