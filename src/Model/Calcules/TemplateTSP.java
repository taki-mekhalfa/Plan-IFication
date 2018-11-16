package Model.Calcules;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;

import java.util.*;

/**
 * Classe de fonctionnement du TSP.
 * @author H4104
 * @see Model.Calcules.TSP
 */
public abstract class TemplateTSP implements TSP {
    private List<Livraison> meilleureSolution;
    private double coutMeilleureSolution;
    private List<Livraison> listeLivraisons;
    private int tempsLimite;

    /**
     * Méthode d'obtention des tournées.
     * @param listeLivraisons correspondant à la liste de livraisons à effectuer
     * @param plusCourtsChemins correspondant à une map de livraisons associées à des map de livraisons et de chemins, représenant les trajets à effectuer
     * @param tempsLimite correspondant à la durée maximale pour le fonctionnement de l'algorithme sous forme entière
     * @param topDepart correspondant à l'heure de départ de la tournée de type Temps
     * @return Tournee correspondant à la tournée ainsi calculée
     * @see Model.Metier.Livraison
     * @see Model.Metier.Temps
     */
    @Override
    public Tournee getTournee(List<Livraison> listeLivraisons, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins, int tempsLimite, Temps topDepart) {

        this.coutMeilleureSolution = Double.POSITIVE_INFINITY;
        this.listeLivraisons = listeLivraisons;
        this.tempsLimite = tempsLimite;

        List<Livraison> nonVus = new LinkedList<>(this.listeLivraisons);
        List<Livraison> vus = new LinkedList<>();
        Livraison livraisonEntrepot = listeLivraisons.get(0);
        vus.add(livraisonEntrepot);
        nonVus.remove(0);
        branchAndBound(livraisonEntrepot, nonVus, vus, 0, plusCourtsChemins, System.currentTimeMillis(), tempsLimite);

        //------------------------------------------------------------------
        List<Chemin> listeChemins = new LinkedList<>();
        Map<Livraison, Temps> heuresDeLivraison = new HashMap<>();

        meilleureSolution = new ArrayList<>(meilleureSolution);
        Temps tempsCumule = topDepart;
        Livraison livraisonPrecedente = livraisonEntrepot;

        for (int i = 1; i < meilleureSolution.size(); i++) {
            Livraison livraisonCourant = meilleureSolution.get(i);
            Chemin chemin = plusCourtsChemins.get(livraisonPrecedente).get(livraisonCourant);
            listeChemins.add(chemin);
            tempsCumule = Temps.addConvert(tempsCumule, livraisonPrecedente.getDuree() + chemin.getCout() / 4.17);
            // \\
            heuresDeLivraison.put(livraisonCourant, tempsCumule);
            livraisonPrecedente = livraisonCourant;
        }

        listeChemins.add(plusCourtsChemins.get(livraisonPrecedente).get(livraisonEntrepot));
        heuresDeLivraison.put(livraisonEntrepot,topDepart);
        return new Tournee(listeChemins, heuresDeLivraison);
    }

    /**
     * Méthode d'obtention du cout de la meilleur solution.
     * @return coutMeilleurSolution correspondant à ce résultat sous forme d'un double
     */
    private double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    protected abstract int bound(Livraison livraisonCourante, List<Livraison> nonVus, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins);


    protected abstract Iterator<Livraison> iterator(Livraison livraisonCourante, List<Livraison> nonVus, Map<Livraison, Map<Livraison, Chemin>> plusCourtsChemins);

    /**
     * Méthode branchAndBound pour parcourir le graphique et determiner le trajets à effectuer.
     * @param livraisonCourante correspondat à la livraison courante de l'algorithme
     * @param nonVus correspondant àla liste des livraisons qu'il reste à effectuer
     * @param vus correspondant à la liste des livraisons déjà effectuée
     * @param coutVus correspondant au coût total courant de toutes les livraisons effectuées
     * @param cout correspondant à la map de livraisons associées aux map de livraisons et chemins représentant les trajets
     * @param tpsDebut correspondant à l'heure de départ de l'algorithme
     * @param tpsLimite correspondant au temps maximum autorisé pour le fonctionnement de l'algorithme sous forme entière
     * @see Model.Metier.Livraison
     */
    private void branchAndBound(Livraison livraisonCourante, List<Livraison> nonVus, List<Livraison> vus, double coutVus, Map<Livraison, Map<Livraison, Chemin>> cout, long tpsDebut, int tpsLimite) {
        if(System.currentTimeMillis() - tpsDebut > tpsLimite){
        	return;
        }
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

