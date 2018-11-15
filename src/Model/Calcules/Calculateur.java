package Model.Calcules;

import Model.Metier.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe de gestion des calcules dans le cadre de la planification des livraisons.
 * @author H4104
 * @see Model.Planification
 * @see Model.Metier.Tournee
 * @see Model.Metier.Plan
 * @see Model.Metier.Livraison
 */
public class Calculateur {
    private Plan plan;

    /**
     * Constructeur de la classe Calculateur.
     * @param plan correspondant au graphe associé à cette instance de calcul
     */
    public Calculateur(Plan plan) {
        this.plan = plan;
    }

    /**
     * Méthode d'obtention des tournées
     * @param demandeLivraisons correspondant à la série de de livraisons à effectuer
     * @param nombreLivreurs correspondant au nombre de livreurs voulus pour effectuer ces livraisons
     * @return lesTournees correspondant à la liste des tournéé à effectuer
     */
    public List<Tournee> getTournees(DemandeLivraisons demandeLivraisons, int nombreLivreurs) {
        //--------     _  _ _  _     ---------------//
        //---------     \(Â°.Â°)/    -----------------//
        //                \:/
        //                | |

        int CLUSTERING_MAX_ITER = 64;
        List<List<Livraison>> clusters = new PetalClustring().getClusters(demandeLivraisons.getEntrepot(),demandeLivraisons.getPointsDeLivraisons(), nombreLivreurs, CLUSTERING_MAX_ITER);
        List<Tournee> lesTournees = new LinkedList<>();

        for (List<Livraison> cluster : clusters) {
            Livraison livraisonVirtuelle = new Livraison(demandeLivraisons.getEntrepot(), 0);
            cluster.add(0, livraisonVirtuelle);
            Map<Livraison, Map<Livraison, Chemin>> grapheCompletPlusCourtsChemins = new HashMap<>();
            
            for (Livraison livraison : cluster){
                Map<Livraison,Chemin> plusCourtsChemins = new Djikstra().getPlusCourtsChemins(livraison.getNoeud(), cluster, plan);
                grapheCompletPlusCourtsChemins.put(livraison,plusCourtsChemins);
            }

            int TEMPS_LIMITE = 0;
            Tournee tournee = new TSPDeBase().getTournee(cluster, grapheCompletPlusCourtsChemins, TEMPS_LIMITE, demandeLivraisons.getHeureDeDepart());

            lesTournees.add(tournee);
        }

        return lesTournees;
    }

}
