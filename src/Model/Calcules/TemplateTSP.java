package Model.Calcules;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;

import java.util.*;

public abstract class TemplateTSP implements TSP {
    private List<Livraison> meilleureSolution;
    private double coutMeilleureSolution;
    private List<Livraison> listeLivraisons;
    private int tempsLimite;

    @Override
    public Tournee getTournee(List<Livraison> listeLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite, Temps topDepart) {

        this.coutMeilleureSolution = Double.POSITIVE_INFINITY;
        this.listeLivraisons = listeLivraisons;
        this.tempsLimite = tempsLimite;

        List<Livraison> nonVus = new LinkedList<>(this.listeLivraisons);
        List<Livraison> vus = new LinkedList<>();
        vus.add(listeLivraisons.get(0));
        nonVus.remove(0);
        branchAndBound(listeLivraisons.get(0), nonVus, vus, 0, plusCourtsChemins, System.currentTimeMillis(), tempsLimite);

        //---------(°.°)-----------------------------------------------------
        List<Chemin> listeChemins = new LinkedList<>();
        Map<Livraison, Temps> heuresDeLivraison = new HashMap<>();

        meilleureSolution = new ArrayList<>(meilleureSolution);
        Temps tempsCumule = topDepart;
        Livraison livraisonPrecedente = listeLivraisons.get(0);

        for (int i = 1; i < meilleureSolution.size(); i++) {
            Livraison livraisonCourant = meilleureSolution.get(i);
            Chemin chemin = plusCourtsChemins.get(livraisonPrecedente).get(livraisonCourant);
            listeChemins.add(chemin);
            tempsCumule = Temps.addConvert(tempsCumule, livraisonPrecedente.getDuree() + chemin.getCout() / 4.17);
            // \\
            heuresDeLivraison.put(livraisonCourant, tempsCumule);
            livraisonPrecedente = livraisonCourant;
        }

        listeChemins.add(plusCourtsChemins.get(livraisonPrecedente).get(listeLivraisons.get(0)));
        return new Tournee(listeChemins, heuresDeLivraison);
    }

    private double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    protected abstract int bound(Livraison livraisonCourante, List<Livraison> nonVus, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins);


    protected abstract Iterator<Livraison> iterator(Livraison livraisonCourante, List<Livraison> nonVus, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins);


    void branchAndBound(Livraison livraisonCourante, List<Livraison> nonVus, List<Livraison> vus, double coutVus, Map<Livraison, Map<Livraison, Chemin>> cout, long tpsDebut, int tpsLimite) {
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout.get(livraisonCourante).get(listeLivraisons.get(0)).getCout() / 4.17;
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                meilleureSolution = new LinkedList<>(vus);
                coutMeilleureSolution = coutVus;
            }
        } else if (coutVus + bound(livraisonCourante, nonVus, cout) < coutMeilleureSolution) {
            Iterator<Livraison> it = iterator(livraisonCourante, nonVus, cout);
            while (it.hasNext()) {
                Livraison prochaineLivraison = it.next();
                vus.add(prochaineLivraison);
                nonVus.remove(prochaineLivraison);
                double nouveauCout = cout.get(livraisonCourante).get(prochaineLivraison).getCout() / 4.17 + prochaineLivraison.getDuree();
                branchAndBound(prochaineLivraison, nonVus, vus, coutVus + nouveauCout, cout, tpsDebut, tpsLimite);
                vus.remove(prochaineLivraison);
                nonVus.add(prochaineLivraison);
            }
        }

    }
}

