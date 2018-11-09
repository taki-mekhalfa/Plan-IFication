package Vue;

import Controleur.Controleur;
import Model.Planification;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InterfaceGUI extends Application {


    private Button boutonChargerPlan;
    private Button boutonChargerDemandeLivraison;
    private Button boutonCalculerTournees;
    private Button boutonSuprimmerLivraison;
    private Button boutonValider;
    private Button boutonAnnuler;
    private Button boutonAjouterLivraison;
    private Button boutonDeplacerLivraison;
    private Button boutonUndo;
    private Button boutonRedo;
    private ToolBar menuBar;
    private TextField saisieLivreurs;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Planification planification = new Planification();
        VueGraphique vueGraphique = new VueGraphique(planification);
        Controleur.planification = planification;
        Controleur.interfaceGUI = this;
        Controleur.vueGraphique = vueGraphique;
        BorderPane borderPane = new BorderPane();
        createMenuBar();

        borderPane.setTop(menuBar);
        borderPane.setCenter(vueGraphique);

        Scene scene = new Scene(borderPane, 1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createMenuBar() {
        boutonChargerPlan = new Button("Charger un plan");
        boutonChargerDemandeLivraison = new Button("Charger livraisons");
        boutonCalculerTournees = new Button("Calculer tournees");
        boutonSuprimmerLivraison = new Button("Supprimer Livraison");
        boutonValider = new Button("Valider");
        boutonAnnuler = new Button("Annuler");
        boutonAjouterLivraison = new Button("Ajouter Livraison");
        boutonDeplacerLivraison = new Button("Deplacer Livraison");
        boutonUndo = new Button("Undo");
        boutonRedo = new Button("Redo");

        saisieLivreurs = new TextField();
        saisieLivreurs.setPromptText("Nombre de Livreurs : 3");
        saisieLivreurs.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (!Character.isDigit(input)) {
                e.consume();
            }
            Controleur.saisieNombreLivreurs();

        });

        menuBar = new ToolBar(boutonChargerPlan, boutonChargerDemandeLivraison, boutonCalculerTournees,
                saisieLivreurs,boutonAjouterLivraison,
                boutonSuprimmerLivraison, boutonDeplacerLivraison, boutonValider, boutonAnnuler, boutonUndo, boutonRedo);

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

        boutonCalculerTournees.setOnAction(event -> {
            int nb;
            if(saisieLivreurs.getText().equals("")){
                nb = 3;
            }else{
                nb = Integer.parseInt((saisieLivreurs.getText()));
            }
            System.out.println(nb);
            Controleur.boutonCalculerTournees(nb);
            //Controleur.boutonCalculerTournees(3)
        });
        boutonSuprimmerLivraison.setOnAction(event -> Controleur.boutonSuprimmerLivraison());
        boutonValider.setOnAction(event -> Controleur.boutonValider());
        boutonAnnuler.setOnAction(event -> Controleur.boutonAnnuler());
        boutonAjouterLivraison.setOnAction(event -> Controleur.boutonAjouterLivraison());
        boutonDeplacerLivraison.setOnAction(event -> Controleur.boutonDeplacerLivraison());
        boutonUndo.setOnAction(event -> Controleur.undo());
        boutonRedo.setOnAction(event -> Controleur.redo());


        boutonChargerDemandeLivraison.setDisable(true);
        boutonCalculerTournees.setDisable(true);
        boutonSuprimmerLivraison.setDisable(true);
        boutonValider.setDisable(true);
        boutonAnnuler.setDisable(true);
        boutonAjouterLivraison.setDisable(true);
        boutonDeplacerLivraison.setDisable(true);
        boutonUndo.setDisable(true);
        boutonRedo.setDisable(true);

    }

    private File choisirFichier() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(primaryStage);
    }

    public void activerBoutonChargerPlan() {
        boutonChargerPlan.setDisable(false);
    }

    public void activerBoutonChargerDemandeLivraison() {
        boutonChargerDemandeLivraison.setDisable(false);
    }

    public void activerBoutonCalculerTournees() {
        boutonCalculerTournees.setDisable(false);
    }

    public void activerBoutonSuprimmerLivraison() {
        boutonSuprimmerLivraison.setDisable(false);
    }

    public void activerBoutonAjouterLivraison() {
        boutonAjouterLivraison.setDisable(false);
    }

    public void activerBoutonAnnuler() {
        boutonAnnuler.setDisable(false);
    }

    public void activerBoutonValider() {
        boutonValider.setDisable(false);
    }

    public void activerBoutonUndo() {boutonUndo.setDisable(false);}

    public void activerBoutonRedo() {boutonRedo.setDisable(false);}

    public void activerBoutonDeplacerLivraison(){boutonDeplacerLivraison.setDisable(false);}

    public void desactiverBoutonCalculerTournees() {
        boutonCalculerTournees.setDisable(true);
    }

    public void desactiverBoutonChargerDemandeLivraison() {
        boutonChargerDemandeLivraison.setDisable(true);
    }

    public void desactiverBoutonChargerPlan() {
        boutonChargerPlan.setDisable(true);
    }

    public void desactiverBoutonSuprimmerLivraison() {
        boutonSuprimmerLivraison.setDisable(true);
    }

    public void desactiverBoutonValider() {
        boutonValider.setDisable(true);
    }

    public void desactiverBoutonAnnuler() {
        boutonAnnuler.setDisable(true);
    }

    public void desactiverBoutonAjouterLivraison() {
        boutonAjouterLivraison.setDisable(true);
    }

    public void desactiverBoutonDeplacerLivraison() {boutonDeplacerLivraison.setDisable(true);}

    public void desactiverBoutonUndo(){boutonUndo.setDisable(true);}

    public void desactiverBoutonRedo(){boutonRedo.setDisable(true);}

}
