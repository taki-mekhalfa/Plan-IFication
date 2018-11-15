package Controleur;

import Model.Metier.Livraison;

/**
 * Classe EtatAjoutLivraison
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Model.Metier.Livraison
 * @see Controleur.Controleur
 */
public class EtatAjoutLivraison extends EtatDefaut {
    private Livraison livraison1;
    private Livraison livraison2;
    private String idNoeud;
    private int duree;

    /**
     * Initialisation de l'etat
     */
    @Override
    public void init() {
    	message = "Selectionnez une intersection a ajouter ainsi que les points de livraison" + 
    			'\n' + "precedant et suivant la nouvelle livraison." + 
    			'\n' + "Vous pouvez modifier la duree de la livraison (par defaut nulle)" + 
    			'\n' + "Une fois la selection effectuee, cliquez sur Valider ou Annuler.";
        this.livraison1 = null;
        this.livraison2 = null;
        this.idNoeud = null;
        this.duree = 0;
        Controleur.interfaceGUI.activerBoutonAnnuler();
        Controleur.interfaceGUI.activeSaisieDureeLivraison();
    }

    /**
     * Actions liees a la selection d'une livraison
     * @return true si la livraison est effectivement selectionnee, false sinon.
     */
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

    /**
     * Actions liees a la deselection d'une livraison
     */
    @Override
    public void livraisonDeselectionnee(Livraison livraison) {
        if (livraison.equals(livraison2)) livraison2 = null;
        if (livraison.equals(livraison1)) {
            livraison1 = livraison2;
            livraison2 = null;
        }
        Controleur.interfaceGUI.desactiverBoutonValider();
    }

    /**
     * Declenchement des actions liees a la selection d'un noeud
     * @return true si le noeud est effectivement selectionnee, false sinon.
     */
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

    /**
     * Declenchement des actions liees a la deselection d'un noeud
     */
    @Override
    public void noeudDeselectionne(String idNoeud) {
        this.idNoeud = null;
        Controleur.interfaceGUI.desactiverBoutonValider();
    }
    
    /**
     * Declenchement des actions liees au clic sur le bouton Valider
     */
    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
    	System.out.println(Controleur.planification.getTournees());
    	System.out.println(livraison1);
    	CommandeAjout com;
    	if (!Controleur.planification.isEntrepot(livraison1)){
    		com = new CommandeAjout(Controleur.planification.getTournees(), livraison1, false);
    	}
    	else{
    		com = new CommandeAjout(Controleur.planification.getTournees(), livraison2, true);
    	}
    	listeCommandes.add(com);
    	Livraison livAjoute = Controleur.planification.ajouterPointDeLivraison(idNoeud, duree, livraison1, livraison2);
    	Controleur.vueGraphique.annulerModification();
        com.sauvegardesApres(livAjoute);
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

    /**
     * Declenchement des actions liees a la saisie de la duree d'une livraison
     */
    @Override
    public void saisieDuree(int duree){
        this.duree = duree;
    }
}
