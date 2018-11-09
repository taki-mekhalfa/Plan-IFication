package Model.Calcules;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Plan;

import java.util.*;

public class Djikstra implements PCC {

    private String idSource;
    private Set<String> nonEvalues;
    private Set<String> evalues;
    private List<Livraison> pointsDestination;
    private Plan plan;
    private Map<String, MetaDonne> metaDonnes;
    private Map<Livraison, Chemin> resultat;


    @Override
    public Map<Livraison, Chemin> getPlusCourtsChemins(String idSource, List<Livraison> pointsDestination, Plan plan) {
        nonEvalues = new HashSet<>();
        evalues = new HashSet<>();
        metaDonnes = new HashMap<>();
        resultat = new HashMap<>();
        this.idSource = idSource;
        this.pointsDestination = new LinkedList<>(pointsDestination);
        this.plan = plan;
        initialiser();
        evaluer();
        return resultat;
    }

    private void evaluer() {
        while (!pointsDestination.isEmpty()) {
            String lePlusProche = getPlusProche();
            for (Plan.Troncon troncon : plan.getSuccesseurs(lePlusProche)) {
                String successeur = troncon.getDestination();
                if (!evalues.contains(successeur)) traiter(lePlusProche, successeur);
            }

            nonEvalues.remove(lePlusProche);
            evalues.add(lePlusProche);
            for (Iterator<Livraison> it = pointsDestination.iterator(); it.hasNext(); ) {
                Livraison livraison = it.next();
                if (livraison.getNoeud().equals(lePlusProche)) {
                    MetaDonne metaDonne = metaDonnes.get(lePlusProche);
                    metaDonne.predecesseurs.add(livraison.getNoeud());
                    Chemin chemin = new Chemin(metaDonne.predecesseurs, metaDonne.distance);
                    resultat.put(livraison, chemin);
                    it.remove();
                }
            }



        }
    }

    private void traiter(String source, String successeur) {
        nonEvalues.add(successeur);

        if (!metaDonnes.containsKey(successeur)) {
            metaDonnes.put(successeur, new MetaDonne());
        }

        double longueurTroncon = plan.getDistance(source, successeur);
        MetaDonne metaDonneSuccesseur = metaDonnes.get(successeur);
        MetaDonne metaDonneSource = metaDonnes.get(source);
        if (longueurTroncon + metaDonneSource.distance < metaDonneSuccesseur.distance) {
            metaDonneSuccesseur.distance = metaDonneSource.distance + longueurTroncon;
            metaDonneSuccesseur.predecesseurs = new LinkedList<>(metaDonneSource.predecesseurs);
            metaDonneSuccesseur.predecesseurs.add(source);
        }
    }

    private String getPlusProche() {
        String lePlusProche = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (String idNoeud : nonEvalues) {
            double distance = metaDonnes.get(idNoeud).distance;
            if (distance < minDist) {
                minDist = distance;
                lePlusProche = idNoeud;
            }
        }

        return lePlusProche;
    }

    private void initialiser() {
        nonEvalues.add(idSource);

        MetaDonne metaDonnesSource = new MetaDonne();
        metaDonnesSource.distance = 0;
        metaDonnesSource.predecesseurs = new LinkedList<>();
        metaDonnes.put(idSource, metaDonnesSource);
    }

    private class MetaDonne {
        double distance = Double.POSITIVE_INFINITY;
        List<String> predecesseurs;

        @Override
        public String toString() {
            return "MetaDonne{" +
                    "distance=" + distance +
                    ", predecesseurs=" + predecesseurs +
                    '}';
        }
    }


}

