package Vue;

import Controleur.Controleur;
import Model.Planification;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Classe de gestion pour la communication au sein de l'application. 
 * @author H4104
 * @see Controleur.Controleur
 * @see Model.Planification 
 */
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
    private TextField saisieDureeLivraison;
    private Stage primaryStage;
    
    /**
     * Méthode Main pour le fonctionnement de l'appliation.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Méthode de lancement de l'affichage.
     * @param primaryStage correspondant à l'état de départ
     * @see Vue.VueGraphique
     * @see Vue.VueTextuelle
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Planification planification = new Planification();
        VueGraphique vueGraphique = new VueGraphique(planification);
        VueTextuelle vueTextuelle = new VueTextuelle(planification);
        vueGraphique.setVueTextuelle(vueTextuelle);
        vueTextuelle.setVueGraph(vueGraphique);
        Controleur.planification = planification;
        Controleur.interfaceGUI = this;
        Controleur.vueGraphique = vueGraphique;
        Controleur.vueTextuelle = vueTextuelle;
        BorderPane borderPane = new BorderPane();
        createMenuBar();
        
        borderPane.setTop(menuBar);
        borderPane.setCenter(vueGraphique);
        borderPane.setRight(vueTextuelle);
        borderPane.setCenterShape(true);

        Scene scene = new Scene(borderPane, 1300, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Méthode de création de la bar de menu.
     */
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
        saisieDureeLivraison = new TextField();
        saisieLivreurs = new TextField();
        saisieDureeLivraison.setPromptText("Duree livraison: 0(min)");
        saisieLivreurs.setPromptText("Nombre de Livreurs: 3");

        menuBar = new ToolBar(boutonChargerPlan, boutonChargerDemandeLivraison, boutonCalculerTournees,
                saisieLivreurs, boutonAjouterLivraison, saisieDureeLivraison,
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
            if (saisieLivreurs.getText().equals("")) {
                nb = 3;
            } else {
                nb = Integer.parseInt((saisieLivreurs.getText()));
            }
            Controleur.boutonCalculerTournees(nb);
        });
        boutonSuprimmerLivraison.setOnAction(event -> Controleur.boutonSuprimmerLivraison());
        boutonValider.setOnAction(event -> {
            int duree = 0;
            if (!"".equals(saisieDureeLivraison.getText())) duree = Integer.parseInt(saisieDureeLivraison.getText());
            duree = duree * 60;
            Controleur.saisieDuree(duree);
            Controleur.boutonValider();
        });
        boutonAnnuler.setOnAction(event -> Controleur.boutonAnnuler());
        boutonAjouterLivraison.setOnAction(event -> Controleur.boutonAjouterLivraison());
        boutonDeplacerLivraison.setOnAction(event -> Controleur.boutonDeplacerLivraison());
        boutonUndo.setOnAction(event -> Controleur.undo());
        boutonRedo.setOnAction(event -> Controleur.redo());
        saisieLivreurs.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (!Character.isDigit(input)) {
                e.consume();
            }
            Controleur.saisieNombreLivreurs();

        });
        saisieDureeLivraison.setOnKeyTyped(e -> {
            char input = e.getCharacter().charAt(0);
            if (!Character.isDigit(input)) {
                e.consume();
            }
        });
        boutonChargerDemandeLivraison.setDisable(true);
        boutonCalculerTournees.setDisable(true);
        boutonSuprimmerLivraison.setDisable(true);
        boutonValider.setDisable(true);
        boutonAnnuler.setDisable(true);
        boutonAjouterLivraison.setDisable(true);
        boutonDeplacerLivraison.setDisable(true);
        boutonUndo.setDisable(true);
        boutonRedo.setDisable(true);
        saisieDureeLivraison.setDisable(true);
        saisieLivreurs.setDisable(false);

    }

    /**
     * Méthode de choix de fichier par l'utilisateur.
     * @return File correspondant au fichier choisit
     */
    private File choisirFichier() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * Méthode d'activation du bouton de chargement d'un plan.
     */
    public void activerBoutonChargerPlan() {
        boutonChargerPlan.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de chargement d'une demande de livraisons.
     */
    public void activerBoutonChargerDemandeLivraison() {
        boutonChargerDemandeLivraison.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de calcul des tournées.
     */
    public void activerBoutonCalculerTournees() {
        boutonCalculerTournees.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de suppression d'une livraison.
     */
    public void activerBoutonSuprimmerLivraison() {
        boutonSuprimmerLivraison.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton d'ajout de livraison.
     */
    public void activerBoutonAjouterLivraison() {
        boutonAjouterLivraison.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de retour en arrière.
     */
    public void activerBoutonAnnuler() {
        boutonAnnuler.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de validation.
     */
    public void activerBoutonValider() {
        boutonValider.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton d'annulation de la dernière action.
     */
    public void activerBoutonUndo() {
        boutonUndo.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de restauration de la dernière action annulée.
     */
    public void activerBoutonRedo() {
        boutonRedo.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de choix du nombre de livreurs.
     */
    public void activerSaisieLivreurs() {
        saisieLivreurs.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de saisie de la durée de livraison.
     */
    public void activeSaisieDureeLivraison() {
        saisieDureeLivraison.setDisable(false);
    }

    /**
     * Méthode d'activation du bouton de déplacement d'une livraison.
     */
    public void activerBoutonDeplacerLivraison() {
        boutonDeplacerLivraison.setDisable(false);
    }

    /**
     * Méthode de desactivation du bouton de calcul des tournées.
     */
    public void desactiverBoutonCalculerTournees() {
        boutonCalculerTournees.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de chargement d'une demande de livraisons.
     */
    public void desactiverBoutonChargerDemandeLivraison() {
        boutonChargerDemandeLivraison.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de chargement d'un plan.
     */
    public void desactiverBoutonChargerPlan() {
        boutonChargerPlan.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de suppression d'une livraison.
     */
    public void desactiverBoutonSuprimmerLivraison() {
        boutonSuprimmerLivraison.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de validation.
     */
    public void desactiverBoutonValider() {
        boutonValider.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton d'annulation de l'action en cours.
     */
    public void desactiverBoutonAnnuler() {
        boutonAnnuler.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton d'ajout de livraison.
     */
    public void desactiverBoutonAjouterLivraison() {
        boutonAjouterLivraison.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de deplacement d'une livraison.
     */
    public void desactiverBoutonDeplacerLivraison() {
        boutonDeplacerLivraison.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton d'annulation de la dernière action.
     */
    public void desactiverBoutonUndo() {
        boutonUndo.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de restauration de la dernière action annulée.
     */
    public void desactiverBoutonRedo() {
        boutonRedo.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de choix du nombre de livreurs.
     */
    public void desactiverSaisieLivreurs() {
        saisieLivreurs.setDisable(true);
    }

    /**
     * Méthode de desactivation du bouton de saisie de la durée de livraison.
     */
    public void desactiveSaisieDureeLivraison() {
        saisieDureeLivraison.setDisable(true);
    }
}
