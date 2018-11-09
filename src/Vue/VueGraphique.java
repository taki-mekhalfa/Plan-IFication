package Vue;

import Controleur.Controleur;
import Model.Metier.*;
import Model.Planification;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class VueGraphique extends Vue {
    private double minLongitude = Double.POSITIVE_INFINITY, maxLatitude = Double.NEGATIVE_INFINITY;
    private double maxLongitude = Double.NEGATIVE_INFINITY, minLatitude = Double.POSITIVE_INFINITY;
    private Group rootGroup = new Group();
    private Group planGroup;
    private Group tourneesGroup;
    private Group livraisonsGroup;
    private List<Color> colors;
    private List<CercleLivraison> cerclesLivraisonsSelectionnes = new LinkedList<>();
    private CercleIntersection cercleIntersection;

    public VueGraphique(Planification planification) {
        super(planification);
        this.getChildren().add(rootGroup);
        initCouleurs();
        planGroup = new Group();
        livraisonsGroup = new Group();
        tourneesGroup = new Group();
        rootGroup.getChildren().addAll(planGroup, tourneesGroup, livraisonsGroup);
        livraisonsGroup.setOnMouseClicked(event -> {
            CercleLivraison cercleLivraison = (CercleLivraison) event.getTarget();
            if (cercleLivraison.isSelectionne()) {
                cerclesLivraisonsSelectionnes.remove(cercleLivraison);
                Controleur.livraisonDeselectionnee(cercleLivraison.getLivraison());
                cercleLivraison.setSelectionne(false);
            } else if (Controleur.livraisonSelectionne(cercleLivraison.getLivraison())) {
                cerclesLivraisonsSelectionnes.add(cercleLivraison);
                cercleLivraison.setSelectionne(true);
            }
        });
        planGroup.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof CercleIntersection) {
                CercleIntersection intersection = (CercleIntersection) event.getTarget();
                if (intersection.isSelectionne()) {
                    cercleIntersection = null;
                    Controleur.noeudDeselectionne(intersection.getIdNoeud());
                    intersection.setSelectionne(false);
                } else if (Controleur.noeudSelectionne(intersection.getIdNoeud())) {
                    cercleIntersection = intersection;
                    intersection.setSelectionne(true);
                }
            }
        });

    }


    @Override
    void dessinerPlan() {
        tourneesGroup.getChildren().clear();
        livraisonsGroup.getChildren().clear();
        planGroup.getChildren().clear();
        if (plan != null) {
            calculerCoins();

            for (String idNoeud : plan.getNoeuds()) {
                Noeud n1 = NoeudFactory.getNoeudParId(idNoeud);
                CercleIntersection cercleIntersection = new CercleIntersection(trX(n1.getLongitude()), trY(n1.getLatitude()), 7, idNoeud);
                planGroup.getChildren().add(cercleIntersection);
                for (Plan.Troncon trancon : plan.getSuccesseurs(idNoeud)) {
                    Noeud n2 = NoeudFactory.getNoeudParId(trancon.getDestination());
                    LineModifiee line = new LineModifiee(trX(n1.getLongitude()), trY(n1.getLatitude()), trX(n2.getLongitude()), trY(n2.getLatitude()), trancon.getNomDeLaRue());
                    line.setDefaultColor(Color.BLACK);
                    line.setStrokeWidth(3);
                    planGroup.getChildren().add(line);
                }
            }

        }

    }

    @Override
    void dessinerDemandeDeLivraisons() {
        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();
        if (demandeLivraisons != null) {
            Noeud entrepot = NoeudFactory.getNoeudParId(demandeLivraisons.getEntrepot());

            CercleLivraison cercleLivraisonEntrepot = new CercleLivraison(trX(entrepot.getLongitude()), trY(entrepot.getLatitude()), 5, new Livraison(entrepot.getId(), 0));
            cercleLivraisonEntrepot.setDefaultColor(Color.RED);
            livraisonsGroup.getChildren().add(cercleLivraisonEntrepot);

            for (Livraison livraison : demandeLivraisons.getPointsDeLivraisons()) {
                Noeud pointLivr = NoeudFactory.getNoeudParId(livraison.getNoeud());
                CercleLivraison cercleLivraison = new CercleLivraison(trX(pointLivr.getLongitude()), trY(pointLivr.getLatitude()), 7, livraison);
                cercleLivraison.setCouleur(Color.BLUE);
                livraisonsGroup.getChildren().add(cercleLivraison);
            }

        }

    }

    @Override
    void dessinerTournees() {
        tourneesGroup.getChildren().clear();
        if (tournees != null) {
            int indexTournee = 0;
            for (Tournee tournee : tournees) {
                Color color = getColor(indexTournee);
                List<Chemin> chemins = tournee.getChemins();
                for (Chemin chemin : chemins) {
                    Noeud premierNoeud = NoeudFactory.getNoeudParId(chemin.getDepart());
                    LineModifiee line = new LineModifiee();
                    line.setStartX(trX(premierNoeud.getLongitude()));
                    line.setStartY(trY(premierNoeud.getLatitude()));
                    for (String idNoeud : chemin.getChemin().subList(1, chemin.getChemin().size())) {
                        Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                        line.setEndX(trX(noeud.getLongitude()));
                        line.setEndY(trY(noeud.getLatitude()));
                        line.setDefaultColor(color);
                        line.setNomDeLaRue(planification.getNomDeLaRue(premierNoeud.getId(), noeud.getId()));
                        line.setStrokeWidth(4);
                        tourneesGroup.getChildren().add(line);
                        line = new LineModifiee();
                        premierNoeud = noeud;
                        line.setStartX(trX(noeud.getLongitude()));
                        line.setStartY(trY(noeud.getLatitude()));
                    }
                }
                indexTournee = indexTournee + 1;
            }


        }

    }

    //Met en Orange le point selectionné
    void couleurPointFocus(String idNoeud){
        for (int i = 0; i < livraisonsGroup.getChildren().size(); i++){
            CercleLivraison cercle = (CercleLivraison) livraisonsGroup.getChildren().get(i);
            if(cercle.getLivraison().getNoeud().equals(idNoeud)){
                cercle.setCouleur(Color.ORANGE);
                break;
            }
        }
    }

    //Reset tous les points de livraison en bleu sauf l'entrepot (rouge)
    void resetCouleurs(){
        for (int i = 0; i < livraisonsGroup.getChildren().size(); i++){
            CercleLivraison cercle = (CercleLivraison) livraisonsGroup.getChildren().get(i);

            if(demandeLivraisons.getEntrepot().equals(cercle.getLivraison().getNoeud()))
                cercle.setCouleur(Color.RED);

            else
                cercle.setCouleur(Color.BLUE);

        }
    }

    private double trX(double longitude) {
        return 16384 * (longitude - minLongitude);
    }

    private double trY(double latitude) {
        return 16384 * (maxLatitude - latitude);
    }

    private void calculerCoins() {
        for (String idNoeud : plan.getNoeuds()) {
            Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
            if (noeud.getLongitude() < minLongitude) minLongitude = noeud.getLongitude();
            if (noeud.getLongitude() > maxLongitude) maxLongitude = noeud.getLongitude();
            if (noeud.getLatitude() < minLatitude) minLatitude = noeud.getLatitude();
            if (noeud.getLongitude() > maxLatitude) maxLatitude = noeud.getLatitude();
        }
    }

    private void initCouleurs() {
        colors = new LinkedList<>();
        colors.add(Color.BROWN);
        colors.add(Color.SEAGREEN);
        colors.add(Color.AQUA);
        colors.add(Color.YELLOWGREEN);
        colors.add(Color.CRIMSON);
        colors.add(Color.BLUEVIOLET);
        colors.add(Color.BLUEVIOLET);
        colors.add(Color.SPRINGGREEN);
        colors.add(Color.TOMATO);
        colors.add(Color.YELLOW);
        colors.add(Color.CORNFLOWERBLUE);
    }

    private Color getColor(int n) {
        if (n < colors.size()) {
            return colors.get(n);
        }
        Random random = new Random();
        Color newColor = Color.color(random.nextInt(100) / 100.0, random.nextInt(100) / 100.0, random.nextInt(100) / 100.0);
        colors.add(newColor);
        return newColor;
    }

    public void annulerModification() {
        for (CercleLivraison cercleLivraison : cerclesLivraisonsSelectionnes) cercleLivraison.setSelectionne(false);
        if (cercleIntersection != null) cercleIntersection.setSelectionne(false);
        cercleIntersection = null;
        cerclesLivraisonsSelectionnes.clear();
    }


}



