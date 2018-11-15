package Model.Metier;

import Model.Calcules.Djikstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Classe de gestion des tournées pour l'application.
 * @author H4104
 */
public class Tournee {
    private List<Chemin> chemins;
    private Map<Livraison, Temps> heuresDeLivraison;

    /**
     * Constructeur de la classe Tournee.
     * @param chemins correspondant à la liste de chemins associée à cette tournée
     * @param heuresDeLivraison correspondant à une map de livraisons associées à des temps de passage
     */
    public Tournee(List<Chemin> chemins, Map<Livraison, Temps> heuresDeLivraison) {
        this.chemins = chemins;
        this.heuresDeLivraison = heuresDeLivraison;
    }

    /**
     * Méthode d'ajout de livraison à la tournée.
     * @param livraison correspondant à la livraison à ajouter à la tournée 
     * @param livraison1 correspondant à la livraison précédant la livraison que l'on veut ajouter
     * @param livraison2 correspondant à la livraison suivant la livraison que l'on veut ajouter
     * @param plan correspondant au plan associé à la tournée en cours
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
     * Méthode de recherche de livraison parmis celle associées à la tournée.
     * @param chemin correspondant au chemin auquel la livraison que l'on recherche est associée
     * @return livraison correspondant à cet éléments que l'on cherchait
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
     * Méthode de recherche de chemin parmis ceux associées à la tournée.
     * @param idNoeud correspondant à l'identifiant du noeud auquel le chemin que l'on recherche est associé
     * @return int correspondant à l'index du chemin que l'on cherchait
     */
    private int chercherChemin(String idNoeud) {
        Chemin dummyChemin = new Chemin(Collections.singletonList(idNoeud), 0);
        int tmp = chemins.indexOf(dummyChemin);
        return chemins.indexOf(dummyChemin);
    }

    /**
     * Méthode d'obtention de la liste de chemins associée à la tournée.
     * @param c correspondant à la liste de chemins de la tournée
     */
    public List<Chemin> getChemins() {
        return chemins;
    }

    /**
     * Méthode d'affectation d'une liste de chemins à la tournée.
     * @param c correspondant à la liste de chemins à associer
     */
    public void setChemins(List<Chemin> c) {
        chemins = c;
    }

    /**
     * Méthode d'obtention des heures de livraisons.
     * @return heuresDeLivraison correspondant à une map de livraisons avec leur heure de passage associée.
     */
    public Map<Livraison, Temps> getHeuresDeLivraison() {
        return heuresDeLivraison;
    }

    /**
     * Méthode d'affectation des heures de livraisons de la tournée.
     * @param h correspondant à une map de livraisons associéees à leur heure de passage
     */
    public void setHeuresDeLivraison(Map<Livraison, Temps> h) {
    	heuresDeLivraison = h;
    }

    /**
     * Méthode de suppression d'une livraison.
     * @param livraison correspondant à la livraison à supprimer de la tournée
     * @param plan correspondant au plan associé à la tournée
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
     * Méthode de test pour savoir si la tournée contient une certaine livraison.
     * @param livraison correspondant à l'élement dont on cherche à tester la présence
     * @return boolean correspondant au résultat de ce test
     */
    public boolean contientLivraison(Livraison livraison){
        return heuresDeLivraison.containsKey(livraison);
    }

    /**
     * Méthode de conversion en string de la tournée.
     * @return Tournee correspondant à un string contenant les informations relatives à la tournée
     */
    @Override
    public String toString() {
        return "Tournee{" +
                "chemins=" + chemins +
                ", heuresDeLivraison=" + heuresDeLivraison +
                '}';
    }

    /**
     * Méthode de test pour savboir si deux livraisons se suivent. 
     * @param livraison1 correspondant à la première livraison du test
     * @param livraison2 correspondant à la seconde livraison du test
     * @return boolean correspondant au rrésultat de ce test
     */
    public boolean consecutives(Livraison livraison1, Livraison livraison2){
        int idx1 = chercherChemin(livraison1.getNoeud());
        int idx2 = chercherChemin(livraison2.getNoeud());
        if (idx1 == -1 || idx2 == -1) return false;
        return idx1 == (idx2 + 1) % chemins.size() || idx2 == (idx1 + 1) % chemins.size();
    }

}
