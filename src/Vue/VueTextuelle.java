package Vue;

import Model.Metier.Livraison;
import Model.Metier.Temps;
import Model.Metier.Tournee;
import Model.Planification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class VueTextuelle extends Vue {

    private VueGraphique vueGraph;

    private Group rootGroup = new Group();
    private Group planGroup;
    private Group tourneesGroup;
    private Group livraisonsGroup;
    private ObservableList<Livraison> dataLivraison = FXCollections.observableArrayList();
    private ObservableList<Livraison> dataTournee = FXCollections.observableArrayList();

    private TableView<Livraison> tableDemandeLivraison = new TableView<>();
    private TableColumn<Livraison, String> idCol = new TableColumn<>("ID Livraison");
    private TableColumn<Livraison, Integer> dureeCol = new TableColumn<>("Duree");

    private TableView<Livraison> tableTournee = new TableView<>();
    private TableColumn<Livraison, String> livraisonCol = new TableColumn<>("ID Livraison");
    private TableColumn<Livraison, String> horraireCol = new TableColumn<>("Heure de Livraison");;


    public VueTextuelle(Planification planification, VueGraphique vueGraph) {

        super(planification);
        this.getChildren().add(rootGroup);
        this.vueGraph = vueGraph;
        planGroup = new Group();
        livraisonsGroup = new Group();
        tourneesGroup = new Group();
        rootGroup.getChildren().addAll(planGroup, tourneesGroup, livraisonsGroup);

    }

    @Override
    void dessinerPlan() {
        // Ignore
    }

    @Override
    void dessinerDemandeDeLivraisons() {

        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();

        tableDemandeLivraison.setEditable(true);

        idCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
        dureeCol.setCellValueFactory(cellData -> cellData.getValue().getDureeProperty().asObject());


        dataLivraison.addAll(demandeLivraisons.getPointsDeLivraisons());
        tableDemandeLivraison.getColumns().setAll(idCol, dureeCol);
        tableDemandeLivraison.setItems(dataLivraison);
        livraisonsGroup.getChildren().add(tableDemandeLivraison);

        //Ecoute les clics de souris sur les lignes du tableau pour r�cup�rer l'ID de la livraison
        tableDemandeLivraison.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vueGraph.resetCouleurs();
                vueGraph.couleurPointFocus(tableDemandeLivraison.getSelectionModel().getSelectedItem().getNoeud());
            }
        });
    }

    @Override
    void dessinerTournees() {

        livraisonsGroup.getChildren().clear();
        tourneesGroup.getChildren().clear();
        dataTournee.clear();
        tableTournee.setEditable(true);

        int i = 1;
        for (Tournee tournee : tournees) {

            Map<Livraison, Temps> distribution = tournee.getHeuresDeLivraison();
            Set<Entry<Livraison, Temps>> setDis = distribution.entrySet();
            Iterator<Entry<Livraison, Temps>> it = setDis.iterator();

            livraisonCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
            horraireCol.setCellValueFactory(cellData -> cellData.getValue().getHeureDeLivraisonProperty());
            Livraison livreur = new Livraison("Livreur :" + i, 0);
            dataTournee.add(livreur);
            i++;

            while (it.hasNext()) {
                Entry<Livraison, Temps> e = it.next();
                if (e.getKey().getNoeud().equals(demandeLivraisons.getEntrepot()))
                {
                	Livraison lv = new Livraison ("Entrepot",0);
                	lv.setHorraireProperty(e.getValue().getHorraireProperty());
                	dataTournee.add(lv);
                }
                else {
                	Livraison livraison = e.getKey();
                	livraison.setHorraireProperty(e.getValue().getHorraireProperty());
                	dataTournee.add(livraison);
                }
            }
        }

        tableTournee.getColumns().setAll(livraisonCol,horraireCol);
        tableTournee.setItems(dataTournee);
        tourneesGroup.getChildren().add(tableTournee);
        
      //Ecoute les clics de souris sur les lignes du tableau pour r�cup�rer l'ID de la livraison
        tableTournee.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vueGraph.resetCouleurs();
                vueGraph.couleurPointFocus(tableTournee.getSelectionModel().getSelectedItem().getNoeud());
            }
        });

    }


}