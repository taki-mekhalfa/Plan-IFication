package Model;

import Model.Calcules.Calculateur;
import Model.Calcules.Djikstra;
import Model.Calcules.PetalClustring;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Livraison;
import Model.Metier.Plan;
import Model.Metier.Tournee;
import Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J;
import Model.XMLHelpers.PlanXMLHelperDom4J;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Classe permettant la manipulation des �l�ments du model.
 * Elle g�re l'appel des m�thodes des autres classes afin d'obtenir les r�sultats voulus par le Controleur.
 * @author mleral
 */
public class Planification extends Observable {
    private Plan plan;
    private DemandeLivraisons demandeLivraisons;
    private Calculateur calculateur;
    private List<Tournee> tournees;

    /**
     * M�thode pour le chargement du plan.
     * @see Model.XMLHelpers.PlanXMLHelperDom4J
     * @param fichier le fichier XML du plan que l'on veut charger
     * @return boolean correspondant au r�sultat de l'op�ration
     */
    public boolean chargerPlan(File fichier) {
        plan = new PlanXMLHelperDom4J().getPlan(fichier);
        if (plan != null){
        	calculateur = new Calculateur(plan);
        	notifierAbonnes("plan");
        	return true;
        }
        return false;
    }
    /**
     * M�thode pour le chargement des demandes de livraisons.
     * @see Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J
     * @param fichierXML contenant les demandes de livraisons que l'on veut charger
     */
    public void chargerDemandesDeLivraisons(File fichierXML) {
        demandeLivraisons = new DemandeLivraisonsXMLHelperDom4J().getDemandeLivraisons(fichierXML);
        notifierAbonnes("livraisons");
    }
    /**
     * M�thode de calcul des tourn�es.
     * @param nombreLivreurs correspondant � la valeur voulue par l'utilisateur.
     * Elle a une valeur par d�faut de 3
     */
    public void calculerTournees(int nombreLivreurs) {
        tournees = calculateur.getTournees(demandeLivraisons, nombreLivreurs);
        notifierAbonnes("tournees");
    }

    /**
     * M�thode d'ajout d'un point de livraison.
     * @param idPointLivraison correspondant � la livraison � ajouter
     * @param duree correspondant � la dur�e de la nouvelle livraison
     * @param livraison1 correspondant � la livraison pr�c�dent l'ajout
     * @param livraison2 correspondant � la livraison suivant l'ajout
     * @return livraison qui est la livraison que l'on vient d'ajouter � la tourn�e
     */
    public Livraison ajouterPointDeLivraison(String idPointLivraison, int duree, Livraison livraison1, Livraison livraison2){
        Livraison livraison = null;
        for (Tournee tournee: tournees){
            if (tournee.consecutives(livraison1,livraison2)){
                livraison = new Livraison(idPointLivraison,duree);
                demandeLivraisons.getPointsDeLivraisons().add(livraison);
                tournee.ajouterLivraison(livraison, livraison1,livraison2,plan);
                break;
            }
        }
        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
        return livraison;
    }

    /**
     * M�thode de d�placement d'une livraison de sa position originale
     * vers un nouvelle, situ�e entre deux autres livraisons.
     * @param livraisonADeplacer correspondant � la livraison dont on veut modifier le placement
     * @param livraison1 correspondant � la livraison pr�c�dant la position-cible
     * @param livraison2 correspondant � la livraison suivant la position-cible
     */
    public void deplacerLivraison(Livraison livraisonADeplacer, Livraison livraison1, Livraison livraison2){
        boolean supprimee = false;
        boolean ajoutee = false;
        for (Tournee tournee : tournees){
            if (tournee.consecutives(livraison1,livraison2)) {
                tournee.ajouterLivraison(livraisonADeplacer, livraison1, livraison2, plan);
                ajoutee = true;
            }
            else if (tournee.contientLivraison(livraisonADeplacer)) {
                tournee.supprimerLivraison(livraisonADeplacer,plan);
                supprimee = true;
            }

            if (supprimee && ajoutee) break;
        }

        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }

    /**
     * M�thode de suppression d'une livraison.
     * @param livraison corespondant � la livraison � supprimer au cours de l'op�ration
     */
    public void supprimerPointDeLivraison(Livraison livraison){
		for (Tournee tournee: tournees){
            if (tournee.contientLivraison(livraison)){
                tournee.supprimerLivraison(livraison,plan);
                demandeLivraisons.getPointsDeLivraisons().remove(livraison);
                break;
            }
        }
        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }
    
    /**
     * M�thode de notification afin de permettre une mise � jour de l'affichage.
     */
    public void MAJAffichage(){
    	notifierAbonnes("plan");
    	notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }
    
    /**
     * M�thode de suppression afin de supprimer le plan et les demandes de livraisons associ�es.
     */
    public void supprimerPlan(){
    	supprimerDemandesLivraisons();
    	plan = null;
    }
    
    /**
     * M�thode de suppression afin de supprimer les demandes de livraisons,
     * c'est � dire remettre � 0 les tourn�es et les demandes de livraisons.
     */
    public void supprimerDemandesLivraisons(){
    	tournees = null;
    	demandeLivraisons = null;
    }
    
    /**
     * M�thode d'obtention du plan.
     * @return plan correspondant au plan en cours de manipulation
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * M�thode d'obtention des demandes de livraisons.
     * @return demandeLivraisons
     */
    public DemandeLivraisons getDemandeLivraisons() {
        return demandeLivraisons;
    }
    
    /**
     * M�thode d'obtention des tourn�es.
     * @return tournees
     */
    public List<Tournee> getTournees() {
        return tournees;
    }

    /**
     * M�thode de choix d'une nouvelle liste de tourn�es.
     * @param t la nouvelle liste de tourn�es � consid�rer
     */
    public void setTournees(List<Tournee> t) {
        tournees = t;
    }

    /**
     * M�thode de notification des observeurs.
     * @param quoi 
     */
    private void notifierAbonnes(String quoi) {
        setChanged();
        notifyObservers(quoi);
    }

    /**
     * M�thode de test pour savoir si
     * deux livraisons sont cons�cutives.
     * @param livraison1
     * @param livraison2
     * @return boolean correspondant � la r�ponse voulue
     */
    public boolean livraisonsConsecutives(Livraison livraison1, Livraison livraison2){
        for (Tournee tournee : tournees){
            if (tournee.consecutives(livraison1,livraison2)) return true;
        }

        return false;
    }

    /**
     * M�thode d'obtention du nom de la rue.
     * @param idOrigine correspondant au noeud dont la rue recherch�e part
     * @param idDestination correspondant au noeud o� la rue voulue arrive
     * @return
     */
    public String getNomDeLaRue(String idOrigine, String idDestination) {
        List<Plan.Troncon> tronconList = plan.getSuccesseurs(idOrigine);
        for (Plan.Troncon troncon : tronconList)
            if (troncon.getDestination().equals(idDestination)) return troncon.getNomDeLaRue();
        return "Pas Du Nom";
    }
    
    public boolean isEntrepot(Livraison livraison){
    		return (livraison.getNoeud().equals(demandeLivraisons.getEntrepot()));
    }
}
