package Model.Metier;

import Model.Calcules.Djikstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Classe de gestion des tourn�es pour l'application.
 * @author H4104
 */
public class Tournee {
    private List<Chemin> chemins;
    private Map<Livraison, Temps> heuresDeLivraison;

    /**
     * Constructeur de la classe Tournee.
     * @param chemins correspondant � la liste de chemins associ�e � cette tourn�e
     * @param heuresDeLivraison correspondant � une map de livraisons associ�es � des temps de passage
     */
    public Tournee(List<Chemin> chemins, Map<Livraison, Temps> heuresDeLivraison) {
        this.chemins = chemins;
        this.heuresDeLivraison = heuresDeLivraison;
    }

    /**
     * M�thode d'ajout de livraison � la tourn�e.
     * @param livraison correspondant � la livraison � ajouter � la tourn�e 
     * @param livraison1 correspondant � la livraison pr�c�dant la livraison que l'on veut ajouter
     * @param livraison2 correspondant � la livraison pr�c�dant la livraison que l'on veut ajouter
     * @param plan correspondant au plan associ� � la tourn�e en cours
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
     * M�thode de recherche de livraison parmis celle associ�es � la tourn�e.
     * @param chemin correspondant au chemin auquel la livraison que l'on recherche est associ�e
     * @return livraison correspondant � cet �l�ments que l'on cherchait
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
     * M�thode de recherche de chemin parmis ceux associ�es � la tourn�e.
     * @param idNoeud correspondant � l'identifiant du noeud auquel le chemin que l'on recherche est associ�
     * @return int correspondant � l'index du chemin que l'on cherchait
     */
    private int chercherChemin(String idNoeud) {
        Chemin dummyChemin = new Chemin(Collections.singletonList(idNoeud), 0);
        int tmp = chemins.indexOf(dummyChemin);
        return chemins.indexOf(dummyChemin);
    }

    /**
     * M�thode d'obtention de la liste de chemins associ�e � la tourn�e.
     * @param c correspondant � la liste de chemins de la tourn�e
     */
    public List<Chemin> getChemins() {
        return chemins;
    }

    /**
     * M�thode d'affectation d'une liste de chemins � la tourn�e.
     * @param c correspondant � la liste de chemins � associer
     */
    public void setChemins(List<Chemin> c) {
        chemins = c;
    }

    /**
     * M�thode d'obtention des heures de livraisons.
     * @return heuresDeLivraison correspondant � une map de livraisons avec leur heure de passage associ�e.
     */
    public Map<Livraison, Temps> getHeuresDeLivraison() {
        return heuresDeLivraison;
    }

    /**
     * M�thode d'affectation des heures de livraisons de la tourn�e.
     * @param h correspondant � une map de livraisons associ�ees � leur heure de passage
     */
    public void setHeuresDeLivraison(Map<Livraison, Temps> h) {
    	heuresDeLivraison = h;
    }

    /**
     * M�thode de suppression d'une livraison.
     * @param livraison correspondant � la livraison � supprimer de la tourn�e
     * @param plan correspondant au plan associ� � la tourn�e
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
     * M�thode de test pour savoir si la tourn�e contient une certaine livraison.
     * @param livraison correspondant � l'�lement dont on cherche � tester la pr�sence
     * @return boolean correspondant au r�sultat de ce test
     */
    public boolean contientLivraison(Livraison livraison){
        return heuresDeLivraison.containsKey(livraison);
    }

    /**
     * M�thode de conversion en string de la tourn�e.
     * @return Tournee correspondant � un string contenant les informations relatives � la tourn�e
     */
    @Override
    public String toString() {
        return "Tournee{" +
                "chemins=" + chemins +
                ", heuresDeLivraison=" + heuresDeLivraison +
                '}';
    }

    /**
     * M�thode de test pour savboir si deux livraisons se suivent. 
     * @param livraison1 correspondant � la premi�re livraison du test
     * @param livraison2 correspondant � la seconde livraison du test
     * @return boolean correspondant au rr�sultat de ce test
     */
    public boolean consecutives(Livraison livraison1, Livraison livraison2){
        int idx1 = chercherChemin(livraison1.getNoeud());
        int idx2 = chercherChemin(livraison2.getNoeud());
        if (idx1 == -1 || idx2 == -1) return false;
        return idx1 == (idx2 + 1) % chemins.size() || idx2 == (idx1 + 1) % chemins.size();
    }

}
