package Model.Metier;

import Model.Calcules.Djikstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Tournee {
    private final List<Chemin> chemins;


    private final Map<Livraison, Temps> heuresDeLivraison;


    public Tournee(List<Chemin> chemins, Map<Livraison, Temps> heuresDeLivraison) {
        this.chemins = chemins;
        this.heuresDeLivraison = heuresDeLivraison;
    }

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

    private Livraison chercherLivraison(Chemin chemin) {
        for (Livraison livraison : heuresDeLivraison.keySet()) {
            if (livraison.getNoeud().equals(chemin.getDepart())) {
                return livraison;
            }
        }
        return null;
    }

    private int chercherChemin(String idNoeud) {
        Chemin dummyChemin = new Chemin(Collections.singletonList(idNoeud), 0);
        int tmp = chemins.indexOf(dummyChemin);
        return chemins.indexOf(dummyChemin);
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public Map<Livraison, Temps> getHeuresDeLivraison() {
        return heuresDeLivraison;
    }

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

    public boolean contientLivraison(Livraison livraison){
        return heuresDeLivraison.containsKey(livraison);
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "chemins=" + chemins +
                ", heuresDeLivraison=" + heuresDeLivraison +
                '}';
    }

    public boolean consecutives(Livraison livraison1, Livraison livraison2){
        int idx1 = chercherChemin(livraison1.getNoeud());
        int idx2 = chercherChemin(livraison2.getNoeud());
        if (idx1 == -1 || idx2 == -1) return false;
        return idx1 == (idx2 + 1) % chemins.size() || idx2 == (idx1 + 1) % chemins.size();
    }

}
