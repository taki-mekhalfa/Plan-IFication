package Controleur;

import Model.Metier.Livraison;

/**
 * Classe EtatDeplacementLivraison
 * @author H4104
 * @see Controleur.EtatDefaut
 * @see Model.Metier.Livraison
 * @see Controleur.Controleur
 */
public class EtatDeplacementLivraison extends EtatDefaut {

    Livraison livraisonADeplacer;
    Livraison livraison1;
    Livraison livraison2;

    /**
     * Initialisation de l'etat
     */
    @Override
    public void init(){
    	message = "Selectionnez une intersection a deplacer ainsi que les points de livraison" + 
    			'\n' + "precedant et suivant dans la nouvelle tournee." + 
    			'\n' + "Une fois la selection effectuee, cliquez sur Valider ou Annuler.";
        livraisonADeplacer = null;
        livraison1 = null;
        livraison2 = null;
        Controleur.interfaceGUI.activerBoutonAnnuler();
    }

    /**
     * Actions liees a la selection d'une livraison
     * @return true si la livraison est effectivement selectionnee, false sinon.
     */
    @Override
    public boolean livraisonSelectionne(Livraison livraison) {
        if (livraisonADeplacer == null) {
        	if (!Controleur.planification.isEntrepot(livraison)){
        		livraisonADeplacer = livraison;
	            return true;
        	}
        	else{
        		Controleur.messageErreurDeplacementEntrepot();
        		return false;
        	}
	            
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

    /**
     * Actions liees a la deselection d'une livraison
     */
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

    /**
     * Declenchement des actions liees au clic sur le bouton Valider
     */
    @Override
    public void boutonValider(ListeCommandes listeCommandes) {
    	CommandeDeplacement com;
    	if (!Controleur.planification.isEntrepot(livraison1)){
    		com = new CommandeDeplacement(Controleur.planification.getTournees(), livraisonADeplacer, livraison1);
    	}
    	else{
    		com = new CommandeDeplacement(Controleur.planification.getTournees(), livraisonADeplacer, livraison2);
    	}
    	listeCommandes.add(com);
    	Controleur.planification.deplacerLivraison(livraisonADeplacer, livraison1, livraison2);
    	Controleur.vueGraphique.annulerModification();
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
