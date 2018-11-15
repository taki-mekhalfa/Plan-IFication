package Model.Calcules;

import Model.Metier.Livraison;

import java.util.List;

/**
 * Classe correspondant à l'interface Clusterer pour la classe KMeansClusterer
 * @author H4104
 * @see Model.Calcules.KMeansClusterer
 */
public interface Clusterer {
    List<List<Livraison>> getClusters(String idEntrepot,List<Livraison> livraisons, int nombreClusters, int maxiter);

}
