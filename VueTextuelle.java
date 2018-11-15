package Vue;

import Model.Metier.Chemin;
import Model.Metier.Livraison;
import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classe de gestion pour l'affichage des informations sous forme ï¿½crite.
 * @author H4104
 */
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

    private TextFlow zoneDialogue = new TextFlow(new Text("Cliquez sur le bouton Charger un plan pour charger le fichier xml de plan." + '\n' + "Vous pouvez egalement definir le nombre de livreurs.\n"));

    /**
     * Constructeur de la classe VueTextuelle.
     * @param planification correspondant ï¿½ la planification associï¿½e ï¿½ cette instance de la vue textuelle
     */
   
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


    /**
     * Mï¿½thode de remplissage du tableau en cas d'affichage du plan.
     */
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

    /**
     * Mï¿½thode de remplissage du tableau en cas d'affichage d'une demande de livraisons.
     */
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

            //Ecoute les clics de souris sur les lignes du tableau pour recuperer l'ID de la livraison
            tableDemandeLivraison.setOnMouseClicked(event -> {
                vueGraph.resetCouleurs();
                try {
                    vueGraph.couleurPointFocus(tableDemandeLivraison.getSelectionModel().getSelectedItem().getNoeud());
                } catch (NullPointerException e) {

                }

            });
        }
    }

    /**
     * Mï¿½thode de remplissage du tableau en cas d'affichage des tournï¿½es.
     */
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
            List <Tournee> l = new LinkedList();

            for (Tournee tournee : tournees) {
            	
            	l.add(tournee);
                Map<Livraison, Temps> distribution = tournee.getHeuresDeLivraison();
                SortedSet<Livraison> livraisonsOrdonnees = new TreeSet<>((o1, o2) -> distribution.get(o1).compareTo(distribution.get(o2)));
                livraisonsOrdonnees.addAll(distribution.keySet());

                livraisonCol.setCellValueFactory(cellData -> cellData.getValue().getNoeudProperty());
                horraireCol.setCellValueFactory(cellData -> cellData.getValue().getHeureDeLivraisonProperty());
                dureeLivraisonCol.setCellValueFactory(cellData -> cellData.getValue().getDureeProperty());

                if (tournee.getHeuresDeLivraison().size() != 1) {
                    Livraison livreur = new Livraison("   LIVREUR   " + i, 0);
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
                    if(tableTournee.getSelectionModel().getSelectedItem().getNoeud().length()>10) {
                            if (tableTournee.getSelectionModel().getSelectedItem().getNoeud().substring(3, 10).equals("LIVREUR")) {
                                    String str = tableTournee.getSelectionModel().getSelectedItem().getNoeud();
                                    String index = str.substring(13, str.length());
                                    System.out.println(index);
                                    int k = Integer.parseInt(index);
                                    
                                    Tournee tournee = l.get(k-1);
                                    
                                    vueGraph.dessinerUneTournee(tournee, Color.ORANGE, 6);
                                    
                                    double xPos, yPos, xSuiv, ySuiv;
                                    
                                    String ent = demandeLivraisons.getEntrepot();
                                    Noeud entrepot = NoeudFactory.getNoeudParId(ent);                                
                                    xPos = vueGraph.trX(entrepot.getLongitude());
                                    yPos = vueGraph.trY(entrepot.getLatitude());                                
                                    Velo livreurAnim = new Velo(xPos,yPos);
                                    vueGraph.lancerAnimation(livreurAnim);   
                                    for(int i=0; i<tournee.getChemins().size();i++){
                                    	
                                    	Chemin chemin = tournee.getChemins().get(i);
                                    	List<String> trajet = chemin.getChemin();
                                        
                                        for (int j =0; j< trajet.size();j++) {
                                        	int w =j;
                                        	
                                        	    	  String idNoeud = trajet.get(w);
                                        	    	  System.out.println(idNoeud);
                                        	    	  Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);                                        
                                        	    	  xSuiv = vueGraph.trX(noeud.getLongitude());
                                        	    	  ySuiv = vueGraph.trY(noeud.getLatitude());                                        
                                        	    	  livreurAnim.avancerVelo(xSuiv, ySuiv);
                                        	    	  System.out.println(xSuiv+ " "+ySuiv);
                                        
                                        }                                                                  
                                    }
                                    livreurAnim.avancerVelo(xPos, yPos);
                            }
                    }
                }
            });
        }
    }

    /**
    * MÃ©thode d'affichage d'une message Ã  l'utilisateur
    *@param newText correspond au message Ã  afficher
    *@param color la couleur du message
    */
    public void addZoneDialogue(String newText, int color){
    	Text text = new Text(newText+'\n');
    	switch (color){
    		case 0:
    			text.setFill(Color.BLACK);
    		break;
    		case 1:
    			text.setFill(Color.RED);
        	break;
    		case 2:
    			text.setFill(Color.ORANGE);
        	break;
        	default:
        		text.setFill(Color.BLACK);
        	break;
    	}
    	zoneDialogue.getChildren().addAll(text);
    }
   /**
     * Mï¿½thode de reinitialisation de la zone de dialogue.
     */
    public void clearZoneDialogue() {
        zoneDialogue.getChildren().clear();
    }

    /**
     * Mï¿½thode d'affectation de la vue graphique.
     * @param vueGraph correspondant ï¿½ la vue graphique choisie
     */
    public void setVueGraph(VueGraphique vueGraph) {
        this.vueGraph = vueGraph;
    }

    /**
     * Mï¿½thode de selection d'une rue pour affichage de son nom.
     * @param nomDeLaRue correspondant au nom de la rue selectionnï¿½e par l'utilisateur
     */
    public void rueSelectionnee(String nomDeLaRue) {
        labelNomDeLaRue.setText(nomDeLaRue);
    }
}
