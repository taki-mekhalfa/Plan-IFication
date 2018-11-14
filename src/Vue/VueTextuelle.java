package Vue;

import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;
import Model.Planification;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class VueTextuelle extends Vue {

    private VueGraphique vueGraph;
    private Group tourneesGroup;
    private Group livraisonsGroup;
    private Label labelNomDeLaRue;
    private ObservableList<Livraison> dataLivraison = FXCollections.observableArrayList();
    private ObservableList<Livraison> dataTournee = FXCollections.observableArrayList();

    private TableView<Livraison> tableDemandeLivraison = new TableView<>();
    private TableColumn<Livraison, String> idCol = new TableColumn<>("ID Livraison");
    private TableColumn<Livraison, String> dureeCol = new TableColumn<>("Duree");

    private TableView<Livraison> tableTournee = new TableView<>();
    private TableColumn<Livraison, String> livraisonCol = new TableColumn<>("ID Livraison");
    private TableColumn<Livraison, String> horraireCol = new TableColumn<>("Heure de Livraison");
    private TableColumn<Livraison, String> dureeLivraisonCol = new TableColumn<>("Duree");

    private TextFlow zoneDialogue = new TextFlow(new Text("Cliquez sur le bouton Charger un plan pour charger le fichier xml de plan." + '\n' + "Vous pouvez également définir le nombre de livreurs."));

    public VueTextuelle(Planification planification) {

        super(planification);
        livraisonsGroup = new Group();
        tourneesGroup = new Group();
        labelNomDeLaRue = new Label();
        labelNomDeLaRue.setWrapText(true);
        labelNomDeLaRue.setTextAlignment(TextAlignment.LEFT);
        labelNomDeLaRue.setMaxWidth(200);
        VBox vBox = new VBox();

        zoneDialogue.setMaxWidth(400);
        vBox.getChildren().addAll(labelNomDeLaRue, tourneesGroup, livraisonsGroup, zoneDialogue);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 0, 0));
        tableTournee.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableTournee.setPrefWidth(300);
        tableDemandeLivraison.setPrefWidth(300);
        tableDemandeLivraison.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDemandeLivraison.setPlaceholder(new Label("Demande de livraisons non chargee"));
        this.getChildren().add(vBox);
    }


    @Override
    void dessinerPlan() {

        labelNomDeLaRue.setText("");
        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();
        dataLivraison.clear();

        tableDemandeLivraison.setEditable(true);
        idCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
        dureeCol.setCellValueFactory(cellData -> cellData.getValue().getDureeProperty());
        tableDemandeLivraison.getColumns().setAll(idCol, dureeCol);
        tableDemandeLivraison.setItems(dataLivraison);
        livraisonsGroup.getChildren().add(tableDemandeLivraison);
    }

    @Override
    void dessinerDemandeDeLivraisons() {
        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();
        dataLivraison.clear();
        if (demandeLivraisons != null) {
            tableDemandeLivraison.setEditable(true);

            idCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
            dureeCol.setCellValueFactory(cellData -> cellData.getValue().getDureeProperty());


            dataLivraison.addAll(demandeLivraisons.getPointsDeLivraisons());
            tableDemandeLivraison.getColumns().setAll(idCol, dureeCol);
            tableDemandeLivraison.setItems(dataLivraison);
            livraisonsGroup.getChildren().add(tableDemandeLivraison);

            //Ecoute les clics de souris sur les lignes du tableau pour r�cup�rer l'ID de la livraison
            tableDemandeLivraison.setOnMouseClicked(event -> {
                vueGraph.resetCouleurs();
                try {
                    vueGraph.couleurPointFocus(tableDemandeLivraison.getSelectionModel().getSelectedItem().getNoeud());
                } catch (NullPointerException e) {

                }

            });
        }
    }

    @Override
    void dessinerTournees() {
        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();
        dataTournee.clear();
        livraisonCol.setSortable(false);
        horraireCol.setSortable(false);
        dureeLivraisonCol.setSortable(false);
        if (tournees != null) {
            tableTournee.setEditable(true);

            int i = 1;
            for (Tournee tournee : tournees) {

                Map<Livraison, Temps> distribution = tournee.getHeuresDeLivraison();
                SortedSet<Livraison> livraisonsOrdonnees = new TreeSet<>((o1, o2) -> distribution.get(o1).compareTo(distribution.get(o2)));
                livraisonsOrdonnees.addAll(distribution.keySet());

                livraisonCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
                horraireCol.setCellValueFactory(cellData -> cellData.getValue().getHeureDeLivraisonProperty());
                dureeLivraisonCol.setCellValueFactory(cellData -> cellData.getValue().getDureeProperty());

                if (tournee.getHeuresDeLivraison().size() != 1) {
                    Livraison livreur = new Livraison("Livreur :" + i, 0);
                    livreur.setDureeProperty(new SimpleStringProperty(""));
                    dataTournee.add(livreur);
                    i++;

                    for (Livraison livraison : livraisonsOrdonnees) {
                        if (livraison.getNoeud().equals(demandeLivraisons.getEntrepot())) {
                            Livraison lv = new Livraison("Entrepot", 0);
                            lv.setHorraireProperty(distribution.get(livraison).getHorraireProperty());
                            dataTournee.add(lv);
                        } else {
                            livraison.setHorraireProperty(distribution.get(livraison).getHorraireProperty());
                            dataTournee.add(livraison);
                        }
                    }
                }
            }

            tableTournee.getColumns().setAll(livraisonCol, horraireCol, dureeLivraisonCol);
            tableTournee.setItems(dataTournee);
            tourneesGroup.getChildren().add(tableTournee);

            //Ecoute les clics de souris sur les lignes du tableau pour récupérer l'ID de la livraison
            tableTournee.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    vueGraph.resetCouleurs();
                    vueGraph.couleurPointFocus(tableTournee.getSelectionModel().getSelectedItem().getNoeud());
                }
            });
        }
    }

    public void addZoneDialogue(String newText, boolean error) {
        Text text = new Text(newText + '\n');
        if (error) {
            text.setFill(Color.RED);
        }
        zoneDialogue.getChildren().addAll(text);
    }

    public void clearZoneDialogue() {
        zoneDialogue.getChildren().clear();
    }

    public void setVueGraph(VueGraphique vueGraph) {
        this.vueGraph = vueGraph;
    }

    public void rueSelectionnee(String nomDeLaRue) {
        labelNomDeLaRue.setText(nomDeLaRue);
    }
}