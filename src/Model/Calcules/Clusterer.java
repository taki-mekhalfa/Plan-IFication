package Model.Calcules;

import Model.Metier.Livraison;

import java.util.List;

public interface Clusterer {
    List<List<Livraison>> getClusters(String idEntrepot,List<Livraison> livraisons, int nombreClusters, int maxiter);

}
