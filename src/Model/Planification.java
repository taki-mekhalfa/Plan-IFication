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
 * Classe permettant la manipulation des éléments du model.
 * Elle gère l'appel des méthodes des autres classes afin d'obtenir les résultats voulus par le Controleur.
 * @author H4104
 */
public class Planification extends Observable {
    private Plan plan;
    private DemandeLivraisons demandeLivraisons;
    private Calculateur calculateur;
    private List<Tournee> tournees;

    /**
     * Méthode pour le chargement du plan.
     * @see Model.XMLHelpers.PlanXMLHelperDom4J
     * @param fichier le fichier XML du plan que l'on veut charger
     * @return boolean correspondant au résultat de l'opération
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
     * Méthode pour le chargement des demandes de livraisons.
     * @see Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J
     * @param fichierXML contenant les demandes de livraisons que l'on veut charger
     * @return booleen indiquant true pour la reussite de l'operation et false pour l'echec
     */
    public boolean chargerDemandesDeLivraisons(File fichierXML) {
        demandeLivraisons = new DemandeLivraisonsXMLHelperDom4J().getDemandeLivraisons(fichierXML);
        if (demandeLivraisons == null){
        	return false;
        }
        
        if(!plan.getNoeuds().contains(demandeLivraisons.getEntrepot())){
    		return false;
    	}
        for(Livraison l : demandeLivraisons.getPointsDeLivraisons()){
        	if(!plan.getNoeuds().contains(l.getNoeud())){
        		return false;
        	}
        }
        notifierAbonnes("livraisons");
    	return true;
        
    }

    /**
     * Méthode de calcul des tournées.
     * @param nombreLivreurs correspondant à la valeur voulue par l'utilisateur.
     * Elle a une valeur par défaut de 3
     */
    public void calculerTournees(int nombreLivreurs) {
        tournees = calculateur.getTournees(demandeLivraisons, nombreLivreurs);
        notifierAbonnes("tournees");
    }

    /**
     * Méthode d'ajout d'un point de livraison.
     * @param idPointLivraison correspondant à la livraison à ajouter
     * @param duree correspondant à la durée de la nouvelle livraison
     * @param livraison1 correspondant à la livraison précédent l'ajout
     * @param livraison2 correspondant à la livraison suivant l'ajout
     * @return livraison qui est la livraison que l'on vient d'ajouter à la tournée
     * @see Model.Metier.Tournee
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
     * Méthode de déplacement d'une livraison de sa position originale
     * vers un nouvelle, située entre deux autres livraisons.
     * @param livraisonADeplacer correspondant à la livraison dont on veut modifier le placement
     * @param livraison1 correspondant à la livraison précédant la position-cible
     * @param livraison2 correspondant à la livraison suivant la position-cible
     * @see Model.Metier.Tournee
     */
    public void deplacerLivraison(Livraison livraisonADeplacer, Livraison livraison1, Livraison livraison2){
    	boolean supprimee = false;
        boolean ajoutee = false;
        for (Tournee tournee : tournees){
        	if (tournee.contientLivraison(livraisonADeplacer)){
                tournee.supprimerLivraison(livraisonADeplacer,plan);
                supprimee = true;
            }
        	if (tournee.consecutives(livraison1,livraison2)) {
        			tournee.ajouterLivraison(livraisonADeplacer, livraison1, livraison2, plan);
                    ajoutee = true;
            }
            

            if (supprimee && ajoutee) break;
        }

        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }

    /**
     * Méthode de suppression d'une livraison.
     * @param livraison corespondant à la livraison à supprimer au cours de l'opération
     * @see Model.Metier.Tournee
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
     * Méthode de notification afin de permettre une mise à jour de l'affichage.
     */
    public void MAJAffichage(){
    	notifierAbonnes("plan");
    	notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }
    
    /**
     * Méthode de suppression afin de supprimer le plan et les demandes de livraisons associées.
     */
    public void supprimerPlan(){
    	supprimerDemandesLivraisons();
    	plan = null;
    }
    
    /**
     * Méthode de suppression afin de supprimer les demandes de livraisons,
     * c'est à dire remettre à 0 les tournées et les demandes de livraisons.
     */
    public void supprimerDemandesLivraisons(){
    	tournees = null;
    	demandeLivraisons = null;
    }

    /**
     * Méthode d'obtention du plan.
     * @return plan correspondant au plan en cours de manipulation
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * Méthode d'obtention des demandes de livraisons.
     * @return demandeLivraisons
     */
    public DemandeLivraisons getDemandeLivraisons() {
        return demandeLivraisons;
    }

    /**
     * Méthode d'obtention des tournées.
     * @return tournees
     */
    public List<Tournee> getTournees() {
        return tournees;
    }

    /**
     * Méthode de choix d'une nouvelle liste de tournées.
     * @param t la nouvelle liste de tournées à considérer
     */
    public void setTournees(List<Tournee> t) {
        tournees = t;
    }

    /**
     * Méthode de notification des observeurs.
     * @param quoi 
     */
    private void notifierAbonnes(String quoi) {
        setChanged();
        notifyObservers(quoi);
    }

    /**
     * Méthode de test pour savoir si deux livraisons sont consécutives.
     * @param livraison1 premiere livraison
     * @param livraison2 deuxieme livraison
     * @return boolean correspondant à la réponse voulue
     * @see Model.Metier.Tournee
     */
    public boolean livraisonsConsecutives(Livraison livraison1, Livraison livraison2){
        for (Tournee tournee : tournees){
            if (tournee.consecutives(livraison1,livraison2)) return true;
        }

        return false;
    }

    /**
     * Méthode d'obtention du nom de la rue.
     * @param idOrigine correspondant au noeud dont la rue recherchée part
     * @param idDestination correspondant au noeud où la rue voulue arrive
     * @return nom de la rue
     */
    public String getNomDeLaRue(String idOrigine, String idDestination) {
        List<Plan.Troncon> tronconList = plan.getSuccesseurs(idOrigine);
        for (Plan.Troncon troncon : tronconList)
            if (troncon.getDestination().equals(idDestination)) return troncon.getNomDeLaRue();
        return "Pas Du Nom";
    }
    
    /**
     * Méthode de test pour savoir si la livraison correspond en fait à l'entrepôt.
     * @param livraison correspondant à l'élement à tester
     * @return boolean correspondant au résultat de ce test
     * @see Model.Metier.Livraison
     */
    public boolean isEntrepot(Livraison livraison){
    		return (livraison.getNoeud().equals(demandeLivraisons.getEntrepot()));
    }
}
