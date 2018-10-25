package Vue;

import Controleur.Controleur;
import Model.Planification;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InterfaceGUI extends Application {


    private Button boutonChargerPlan;
    private Button boutonChargerDemandeLivraison;
    private Button boutonCaluculerTournees;
    private ToolBar menuBar;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Planification planification = new Planification();
        Controleur.planification = planification;
        VueGraphique vueGraphique = new VueGraphique(planification);
        BorderPane borderPane = new BorderPane();
        createMenuBar();

        borderPane.setTop(menuBar);
        borderPane.setCenter(vueGraphique);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createMenuBar() {
        boutonChargerPlan = new Button("Charger un plan");
        boutonChargerDemandeLivraison = new Button("Charger livraisons");
        boutonCaluculerTournees = new Button("Calculer tournees");


        menuBar = new ToolBar(boutonChargerPlan, boutonChargerDemandeLivraison, boutonCaluculerTournees);

        boutonChargerPlan.setOnAction(event -> {
            File fichierXML = choisirFichier();
            if (fichierXML != null) {
                Controleur.boutonChargerPlan(fichierXML);
            }
        });

        boutonChargerDemandeLivraison.setOnAction(event -> {
            File fichierXML = choisirFichier();
            if (fichierXML != null) {
                Controleur.boutonChargerDemandeLivraison(fichierXML);
            }
        });

        boutonCaluculerTournees.setOnAction(event -> {

            Controleur.boutonCalculerTournees(3);

        });

    }

    private File choisirFichier() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(primaryStage);
    }



}
