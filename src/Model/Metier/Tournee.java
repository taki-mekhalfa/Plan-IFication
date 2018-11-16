package Model.Metier;

import Model.Calcules.Djikstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Classe de gestion des tournees pour l'application.
 * @author H4104
 */
public class Tournee {
    private List<Chemin> chemins;
    private Map<Livraison, Temps> heuresDeLivraison;

    /**
     * Constructeur de la classe Tournee.
     * @param chemins correspondant e la liste de chemins associee e cette tournee
     * @param heuresDeLivraison correspondant e une map de livraisons associees e des temps de passage
     */
    public Tournee(List<Chemin> chemins, Map<Livraison, Temps> heuresDeLivraison) {
        this.chemins = chemins;
        this.heuresDeLivraison = heuresDeLivraison;
    }

    /**
     * Methode d'ajout de livraison e la tournee.
     * @param livraison correspondant e la livraison e ajouter e la tournee 
     * @param livraison1 correspondant e la livraison precedant la livraison que l'on veut ajouter
     * @param livraison2 correspondant e la livraison precedant la livraison que l'on veut ajouter
     * @param plan correspondant au plan associe e la tournee en cours
     */
    public void ajouterLivraison(Livraison livraison, Livraison livraison1, Livraison livraison2, Plan plan) {
        Livraison livraisonPrecedente,livraisonSuivante;
        int idx1 = chercherChemin(livraison1.getNoeud());
        int idx2 = chercherChemin(livraison2.getNoeud());

        if (idx1 == 0 && idx2 + 1 == chemins.size()){
            livraisonPrecedente = livraison2;
            livraisonSuivante = livraison1;
        }
        else if (idx1 + 1 == chemins.size() && idx2 == 0 || idx1 <= idx2){
            livraisonPrecedente = livraison1;
            livraisonSuivante = livraison2;
        }
        else
        {
            livraisonPrecedente = livraison2;
            livraisonSuivante = livraison1;
        }

        int idxLivraisonPrecedente = chercherChemin(livraisonPrecedente.getNoeud());
        Chemin premierChemin = new Djikstra().getPlusCourtsChemins(livraisonPrecedente.getNoeud(), Collections.singletonList(livraison), plan).get(livraison);
        Chemin deuxiemeChemin = new Djikstra().getPlusCourtsChemins(livraison.getNoeud(), Collections.singletonList(livraisonSuivante), plan).get(livraisonSuivante);
        chemins.remove(idxLivraisonPrecedente);
        chemins.addAll(idxLivraisonPrecedente, Arrays.asList(premierChemin, deuxiemeChemin));
        heuresDeLivraison.put(livraison, null);
        Temps tempsCumule = heuresDeLivraison.get(livraisonPrecedente);
        for (Chemin chemin : chemins.subList(idxLivraisonPrecedente + 1, chemins.size())) {
            Livraison livraisonDepartDuChemin = chercherLivraison(chemin);
            tempsCumule = Temps.addConvert(tempsCumule, premierChemin.getCout() / 4.17 + livraisonPrecedente.getDuree());
            heuresDeLivraison.put(livraisonDepartDuChemin, tempsCumule);
            livraisonPrecedente = livraisonDepartDuChemin;
            premierChemin = chemin;
        }

    }

    /**
     * Methode de recherche de livraison parmis celle associees e la tournee.
     * @param chemin correspondant au chemin auquel la livraison que l'on recherche est associee
     * @return livraison correspondant e cet elements que l'on cherchait
     */
    private Livraison chercherLivraison(Chemin chemin) {
        for (Livraison livraison : heuresDeLivraison.keySet()) {
            if (livraison.getNoeud().equals(chemin.getDepart())) {
                return livraison;
            }
        }
        return null;
    }

    /**
     * Methode de recherche de chemin parmis ceux associees e la tournee.
     * @param idNoeud correspondant e l'identifiant du noeud auquel le chemin que l'on recherche est associe
     * @return int correspondant e l'index du chemin que l'on cherchait
     */
    private int chercherChemin(String idNoeud) {
        Chemin dummyChemin = new Chemin(Collections.singletonList(idNoeud), 0);
        int tmp = chemins.indexOf(dummyChemin);
        return chemins.indexOf(dummyChemin);
    }

    /**
     * Methode d'obtention de la liste de chemins associee e la tournee.
     * @return la liste de chemins de la tournee
     */
    public List<Chemin> getChemins() {
        return chemins;
    }

    /**
     * Methode d'affectation d'une liste de chemins e la tournee.
     * @param c correspondant e la liste de chemins e associer
     */
    public void setChemins(List<Chemin> c) {
        chemins = c;
    }

    /**
     * Methode d'obtention des heures de livraisons.
     * @return heuresDeLivraison correspondant e une map de livraisons avec leur heure de passage associee.
     */
    public Map<Livraison, Temps> getHeuresDeLivraison() {
        return heuresDeLivraison;
    }

    /**
     * Methode d'affectation des heures de livraisons de la tournee.
     * @param h correspondant e une map de livraisons associeees e leur heure de passage
     */
    public void setHeuresDeLivraison(Map<Livraison, Temps> h) {
    	heuresDeLivraison = h;
    }

    /**
     * Methode de suppression d'une livraison.
     * @param livraison correspondant e la livraison e supprimer de la tournee
     * @param plan correspondant au plan associe e la tournee
     */
    public void supprimerLivraison(Livraison livraison, Plan plan) {
        int idxLivraison = chercherChemin(livraison.getNoeud());
        Livraison livraisonPrecedente = chercherLivraison(chemins.get(idxLivraison - 1));
        Livraison livraisonSuivante = idxLivraison +1 == chemins.size() ? chercherLivraison((chemins.get(0))): chercherLivraison(chemins.get(idxLivraison + 1));

        Chemin nouveauChemin = new Djikstra().getPlusCourtsChemins(livraisonPrecedente.getNoeud(), Collections.singletonList(livraisonSuivante),plan).get(livraisonSuivante);

        chemins.remove(idxLivraison - 1);
        chemins.remove(idxLivraison - 1);
        chemins.add(idxLivraison - 1, nouveauChemin);
        heuresDeLivraison.remove(livraison);
        Temps tempsCumule = heuresDeLivraison.get(livraisonPrecedente);
        Chemin cheminPrecedent = nouveauChemin;
        for (Chemin chemin : chemins.subList(idxLivraison, chemins.size())) {
            Livraison livraisonDepartDuChemin = chercherLivraison(chemin);
            tempsCumule = Temps.addConvert(tempsCumule, cheminPrecedent.getCout() / 4.17 + livraisonPrecedente.getDuree());
            heuresDeLivraison.put(livraisonDepartDuChemin, tempsCumule);
            livraisonPrecedente = livraisonDepartDuChemin;
            cheminPrecedent =  chemin;
        }
    }

    /**
     * Methode de test pour savoir si la tournee contient une certaine livraison.
     * @param livraison correspondant e l'element dont on cherche e tester la presence
     * @return boolean correspondant au resultat de ce test
     */
    public boolean contientLivraison(Livraison livraison){
        return heuresDeLivraison.containsKey(livraison);
    }

    /**
     * Methode de conversion en string de la tournee.
     * @return Tournee correspondant e un string contenant les informations relatives e la tournee
     */
    @Override
    public String toString() {
        return "Tournee{" +
                "chemins=" + chemins +
                ", heuresDeLivraison=" + heuresDeLivraison +
                '}';
    }

    /**
     * Methode de test pour savboir si deux livraisons se suivent. 
     * @param livraison1 correspondant e la premiere livraison du test
     * @param livraison2 correspondant e la seconde livraison du test
     * @return boolean correspondant au resultat de ce test
     */
    public boolean consecutives(Livraison livraison1, Livraison livraison2){
        int idx1 = chercherChemin(livraison1.getNoeud());
        int idx2 = chercherChemin(livraison2.getNoeud());
        if (idx1 == -1 || idx2 == -1) return false;
        return idx1 == (idx2 + 1) % chemins.size() || idx2 == (idx1 + 1) % chemins.size();
    }

}
