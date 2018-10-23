package Model.Calcules;

import Model.Metier.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Calculateur {
    private static int CLUSTERING_MAX_ITER = 64;
    private static int TEMPS_LIMITE = 0;
    private Plan plan;


    public Calculateur(Plan plan) {
        this.plan = plan;
    }

    public List<Tournee> getTournees(DemandeLivraisons demandeLivraisons, int nombreLivreurs) {
        //--------     _  _ _  _     ---------------//
        //---------     \(°.°)/    -----------------//
        //                \:/
        //                | |

        List<List<Livraison>> clusters = new KMeansClusterer().getClusters(demandeLivraisons.getPointsDeLivraisons(), nombreLivreurs, CLUSTERING_MAX_ITER);
        List<Tournee> lesTournees = new LinkedList<>();

        for (List<Livraison> cluster : clusters) {
            Livraison livraisonVirtuelle = new Livraison(demandeLivraisons.getEntrepot(), 0);
            cluster.add(0, livraisonVirtuelle);
            Map<Livraison, Map<Livraison, Chemin>> grapheCompletPlusCourtsChemins = new HashMap<>();
            
            for (Livraison livraison : cluster){
                Map<Livraison,Chemin> plusCourtsChemins = new Djikstra().getPlusCourtsChemins(livraison.getNoeud(), cluster, plan);
                grapheCompletPlusCourtsChemins.put(livraison,plusCourtsChemins);
            }

            Tournee tournee = new TSPDeBase().getTournee(cluster, grapheCompletPlusCourtsChemins, TEMPS_LIMITE, demandeLivraisons.getHeureDeDepart());

            lesTournees.add(tournee);
        }

        return lesTournees;
    }

}
