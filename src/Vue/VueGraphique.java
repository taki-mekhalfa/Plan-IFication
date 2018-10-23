package Vue;

import Model.Metier.*;
import Model.Planification;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Random;


public class VueGraphique extends Application {
    private double minLongitude = Double.POSITIVE_INFINITY, maxLatitude = Double.NEGATIVE_INFINITY;
    private double maxLongitude = Double.NEGATIVE_INFINITY, minLatitude = Double.POSITIVE_INFINITY;
    private int WITDH = 800, HEIGHT = 600;
    private Plan plan;
    private DemandeLivraisons demandeLivraisons;
    private List<Tournee> tournees;
    private Planification planification = new Planification();
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane stackPane = new StackPane();

        planification.chargerPlan(new File("./XMLFiles/moyenPlan.xml"));
        planification.chargerDemandesDeLivraisons(new File("./XMLFiles/dl-moyen-12.xml"));
        planification.calculerTournees(3);
        tournees = planification.getTournees();
        plan = planification.getPlan();
        demandeLivraisons = planification.getDemandeLivraisons();
        calculerCoins();
        Group group = new Group();
        dessinerPlan(group.getChildren());
        stackPane.getChildren().add(group);
        dessinerDemandeDeLivraisons(group.getChildren());
        dessinerTournees(group.getChildren());
        Scene scene = new Scene(stackPane, WITDH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void dessinerPlan(ObservableList<Node> observableList) {
        for (String idNoeud : plan.getNoeuds()) {
            for (Model.Metier.Plan.Trancon trancon : plan.getSuccesseurs(idNoeud)) {
                Model.Metier.Noeud n1 = Model.Metier.NoeudFactory.getNoeudParId(idNoeud);
                Model.Metier.Noeud n2 = Model.Metier.NoeudFactory.getNoeudParId(trancon.getDestination());
                Line line = new Line(trX(n1.getLongitude()), trY(n1.getLatitude()), trX(n2.getLongitude()), trY(n2.getLatitude()));
                observableList.add(line);
            }
        }
    }

    private void dessinerDemandeDeLivraisons(ObservableList<Node> observableList) {
        Noeud entrepot = NoeudFactory.getNoeudParId(demandeLivraisons.getEntrepot());

        Circle circleEntrepot = new Circle(trX(entrepot.getLongitude()), trY(entrepot.getLatitude()), 8, Color.RED);
        observableList.add(circleEntrepot);

        for (Livraison livraison : demandeLivraisons.getPointsDeLivraisons()) {
            Noeud pointLivr = NoeudFactory.getNoeudParId(livraison.getNoeud());
            Circle circleLivr = new Circle(trX(pointLivr.getLongitude()), trY(pointLivr.getLatitude()), 8, Color.BLUE);
            observableList.add(circleLivr);
        }
    }

    private void dessinerTournees(ObservableList<Node> observableList) {
        Random random = new Random();
        for (Tournee tournee : tournees) {
            Color color = Color.color(random.nextInt(100) / 100.0, random.nextInt(100) / 100.0, random.nextInt(100) / 100.0);
            List<Chemin> chemins = tournee.getChemins();
            for (Chemin chemin : chemins) {
                Noeud premierNoeud = NoeudFactory.getNoeudParId(chemin.getDepart());
                Line line = new Line();
                line.setStartX(trX(premierNoeud.getLongitude()));
                line.setStartY(trY(premierNoeud.getLatitude()));

                for (String idNoeud : chemin.getChemin()) {
                    Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                    line.setEndX(trX(noeud.getLongitude()));
                    line.setEndY(trY(noeud.getLatitude()));
                    line.setStroke(color);
                    line.setStrokeWidth(4);
                    observableList.add(line);

                    line = new Line();
                    line.setStartX(trX(noeud.getLongitude()));
                    line.setStartY(trY(noeud.getLatitude()));
                }
            }
        }
    }

    private double trX(double longitude) {
        //double echeleHor = (WITDH / (maxLongitude - minLongitude));
        return  16384 * (longitude - minLongitude );
    }

    private double trY(double latitude) {
        //double echeleVer = (HEIGHT / (maxLatitude - minLatitude));
        return  16384 * (maxLatitude - latitude);
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
}


