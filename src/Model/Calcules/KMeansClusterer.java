package Model.Calcules;

import Model.Metier.Livraison;
import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class KMeansClusterer implements Clusterer {

    private List<Livraison> livraisons;
    private List<VirtualCluster> means;
    private List<ConcreteCluster> clusters;
    private int nombreClusters;
    private int maxiter;

    @Override
    public List<List<Livraison>> getClusters(String entrpot,List<Livraison> livraisons, int nombreClusters, int maxiter) {
        this.livraisons = livraisons;
        this.nombreClusters = nombreClusters;
        this.maxiter = maxiter;
        //-----------------------------------

        calculerMeans();
        ConstruireClusters();
        List<List<Livraison>> resultat = new LinkedList<>();
        for (ConcreteCluster cluster : clusters) resultat.add(cluster.getCluster());

        return resultat;
    }

    private void initialiserMeans() {
        means = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < nombreClusters; i++) {
            Noeud noeud = NoeudFactory.getNoeudParId(livraisons.get(random.nextInt(livraisons.size())).getNoeud());
            means.add(new VirtualCluster(noeud.getLongitude(), noeud.getLatitude()));
        }
    }
    
    private void initialiserClusters(){
        clusters = new LinkedList<>();
        for (VirtualCluster virtualCluster:means){
            clusters.add(new ConcreteCluster(virtualCluster.getMeanX(),virtualCluster.getMeanY()));
        }
    }

    private void calculerMeans() {
        initialiserMeans();
        int limitSize = livraisons.size() / nombreClusters;
        for (int i = 0; i < maxiter; i++) {
            List<VirtualCluster> clustersDisponibles = new LinkedList<>(means);
            for (Livraison livraison: livraisons) {
                String idNoeud = livraison.getNoeud();
                VirtualCluster bestCluster = null;
                double bestDistance = Double.POSITIVE_INFINITY;
                for (VirtualCluster virtualCluster : clustersDisponibles) {
                    Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                    double distance = distance(noeud.getLongitude(), noeud.getLatitude(), virtualCluster.getMeanX(), virtualCluster.getMeanY());
                    if (distance < bestDistance){
                        bestDistance = distance;
                        bestCluster = virtualCluster;
                    }
                }

                bestCluster.addLivraison(livraison);
                if (bestCluster.getSize() >= limitSize) clustersDisponibles.remove(bestCluster);
                if (clustersDisponibles.isEmpty()) clustersDisponibles = new LinkedList<>(means);
            }
            // Mettre à jour les means:

            List<VirtualCluster> newMeans = new LinkedList<>();
            for (VirtualCluster virtualCluster : means) newMeans.add(new VirtualCluster(virtualCluster.getNouveauMeanX(),virtualCluster.getNouveauMeanY()));
            means = newMeans;

        }
    }

    private void ConstruireClusters(){
        initialiserClusters();
        int limitSize = livraisons.size() / nombreClusters;
            List<ConcreteCluster> clustersDisponibles = new LinkedList<>(clusters);
            for (Livraison livraison : livraisons) {
                String idNoeud = livraison.getNoeud();
                ConcreteCluster bestCluster = null;
                double bestDistance = Double.POSITIVE_INFINITY;
                for (ConcreteCluster concreteCluster : clustersDisponibles) {
                    Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                    double distance = distance(noeud.getLongitude(), noeud.getLatitude(), concreteCluster.getMeanX(), concreteCluster.getMeanY());
                    if (distance < bestDistance){
                        bestDistance = distance;
                        bestCluster = concreteCluster;
                    }
                }

                bestCluster.addLivraison(livraison);
                if (bestCluster.getSize() >= limitSize) clustersDisponibles.remove(bestCluster);
                if (clustersDisponibles.isEmpty()) clustersDisponibles = new LinkedList<>(clusters);
            }
            // Mettre à jour les means:


        }

    
    private double distance(double p1X, double p1Y, double p2X, double p2Y) {
        double xDis = 100 * (p1X - p2X);
        double yDis = 100 * (p1Y - p2Y);

        return Math.sqrt(xDis*xDis + yDis*yDis);
    }

    private class VirtualCluster {
        double meanX;
        double meanY;
        double sumX = 0;
        double sumY = 0;
        protected int size = 0;
        
        public VirtualCluster(double meanX, double meanY) {
            this.meanX = meanX;
            this.meanY = meanY;
        }

        public void addLivraison(Livraison livraison) {
            Noeud noeud = NoeudFactory.getNoeudParId(livraison.getNoeud());
            sumX += noeud.getLongitude();
            sumY += noeud.getLatitude();
            size++;
        }

        public int getSize() {
            return size;
        }


        public double getNouveauMeanX() {
            return sumX / size;
        }

        public double getNouveauMeanY() {
            return sumY / size;
        }

        public double getMeanX() {
            return meanX;
        }

        public double getMeanY() {
            return meanY;
        }
    }
    
    private class ConcreteCluster extends VirtualCluster{
        private List<Livraison> cluster;
        public ConcreteCluster(double meanX, double meanY){
            super(meanX,meanY);
            cluster = new LinkedList<>();

        }
        
        @Override
        public void addLivraison(Livraison livraison){
            Noeud noeud = NoeudFactory.getNoeudParId(livraison.getNoeud());
            sumX += noeud.getLongitude();
            sumY += noeud.getLatitude();
            cluster.add(livraison);
            size++;
        }
        
        public List<Livraison> getCluster(){
            return cluster;
        }


    }
}
