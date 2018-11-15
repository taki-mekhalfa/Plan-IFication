package Model.Calcules;

import Model.Metier.Livraison;
import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de gestion globale des clusters.
 * @author H4104
 * @see Model.Calcules.Clusterer
 */
public class PetalClustring implements Clusterer {
    private List<Livraison> livraisons;
    private List<List<Livraison>> clusters;

    /**
     * Méthode d'obtention des clusters associés à la liste de livraisons.
     * @param idEntrepot correspondant à l'identifiant de l'entrepôt pour cette serie de livraisons
     * @param livraisons correspondant à la liste de livraisons à traiter
     * @param nombreClusters correspondant au nombre de clusters que l'on veut obtenir
     * @param maxiter correspondant au nombre maximum d'itérations que l'on autorise
     * @return clusters correspondant à une liste de liste correspondant aux clusters ainsi formés
     */
    @Override
    public List<List<Livraison>> getClusters(String idEntrepot, List<Livraison> livraisons, int nombreClusters, int maxiter) {
        clusters = new LinkedList<>();
        this.livraisons = new ArrayList<>(livraisons);
        trierLivraisons(idEntrepot);
        creerClusters(nombreClusters);
        return clusters;
    }

    /**
     * Méthode de création des clusters.
     * @param nombreClusters correspondant au nombre de clusters voulus
     */
    private void creerClusters(int nombreClusters) {
        int q = livraisons.size() / nombreClusters;
        int r = livraisons.size() - q * nombreClusters;

        int idx = 0;
        while (idx < livraisons.size()) {
            List<Livraison> cluster = new LinkedList<>();
            while (cluster.size() < q) {
                cluster.add(livraisons.get(idx++));
            }
            if (r != 0) {
                r--;
                cluster.add(livraisons.get(idx++));
            }

            clusters.add(cluster);
        }
    }

    /**
     * Méthode de trie de livraisons en fonction de leur position sur le plan.
     * @param idEntrepot correspondant à l'identifiant de l'entrepôt que l'on considère
     */
    private void trierLivraisons(String idEntrepot) {
        Noeud entrepot = NoeudFactory.getNoeudParId(idEntrepot);

        livraisons.sort((o1, o2) -> {
            Noeud noeud1 = NoeudFactory.getNoeudParId(o1.getNoeud());
            Noeud noeud2 = NoeudFactory.getNoeudParId(o2.getNoeud());
            double angle1 = Math.atan2(noeud1.getLatitude() - entrepot.getLatitude(), noeud1.getLongitude() - entrepot.getLongitude());
            double angle2 = Math.atan2(noeud2.getLatitude() - entrepot.getLatitude(), noeud2.getLongitude() - entrepot.getLongitude());
            return Double.compare(angle1, angle2);
        });

    }
}
