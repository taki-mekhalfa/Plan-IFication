package Vue;

import Controleur.Controleur;
import Model.Metier.*;
import Model.Planification;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Classe de gestion de l'affichage sur la carte des tournées et livraisons.
 * @author H4104
 * @see Vue.VueTextuelle
 */
public class VueGraphique extends Vue {
    private double minLongitude;
    private double maxLongitude;
    private double minLatitude;
    private double maxLatitude;
    private Group planGroup;
    private Group tourneesGroup;
    private Group livraisonsGroup;
    private List<Color> colors;
    private List<CercleLivraison> cerclesLivraisonsSelectionnes = new LinkedList<>();
    private CercleIntersection cercleIntersection;
    private VueTextuelle vueTextuelle;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double ratio = 0.8;

    /**
     * Constructeur de la classe VueGraphique.
     * @param planification correspondant à la classe de planification à laquelle on fait référence
     */
    public VueGraphique(Planification planification) {
        super(planification);
        initCouleurs();

        Group rootGroup = new Group();
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();

        planGroup = new Group();
        livraisonsGroup = new Group();
        tourneesGroup = new Group();

        rootGroup.getChildren().addAll(planGroup, tourneesGroup, livraisonsGroup);

        //--------------Danger---------------------------------------------------
        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(2);
        slider.setBlockIncrement(0.05);

        rootGroup.scaleXProperty().bind(slider.valueProperty());
        rootGroup.scaleYProperty().bind(slider.valueProperty());
        scrollPane.setContent(new Group(rootGroup));
        scrollPane.setPrefViewportHeight(screenSize.getHeight()*ratio);
        scrollPane.setPrefViewportWidth(screenSize.getHeight()*ratio);
        scrollPane.setFocusTraversable(false);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(slider);
        this.getChildren().add(borderPane);

        //-------------------------------------------------------------------------
        livraisonsGroup.setOnMouseClicked(event -> {
            CercleLivraison cercleLivraison = (CercleLivraison)((Node) event.getTarget()).getParent();
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
        planGroup.setOnMouseMoved(event -> {
            if (event.getTarget() instanceof LineModifiee) {
                LineModifiee lineModifiee = (LineModifiee) event.getTarget();
                vueTextuelle.rueSelectionnee(lineModifiee.getNomDeLaRue());
            }

        });
        tourneesGroup.setOnMouseMoved(event -> {
            if (event.getTarget() instanceof LineModifiee) {
                LineModifiee lineModifiee = (LineModifiee) event.getTarget();
                vueTextuelle.rueSelectionnee(lineModifiee.getNomDeLaRue());
            }

        });
    }

    /**
     * Méthode d'affichage du plan.
     */
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

    /**
     * Méthode d'affichage de la demande de livraisons.
     */
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

    /**
     * Méthode d'affichage des tournées calculées.
     */
    @Override
    void dessinerTournees(int idTournee) {
    	Color colorSelection = Color.BLACK;
        tourneesGroup.getChildren().clear();
        if (tournees != null) {
            int indexTournee = 0;
            for (Tournee tournee : tournees) {
                Color color = getColor(indexTournee);
                List<Chemin> chemins = tournee.getChemins();
                int nb = 1;
                if (indexTournee != idTournee){
	                for (Chemin chemin : chemins) {
	                		Noeud premierNoeud = NoeudFactory.getNoeudParId(chemin.getDepart());
		                    for(Node node : livraisonsGroup.getChildren()){
		                        CercleLivraison cercleLivraison= (CercleLivraison) node;
		                        if(!cercleLivraison.getLivraison().getNoeud().equals(demandeLivraisons.getEntrepot()) && cercleLivraison.getLivraison().getNoeud().equals(premierNoeud.getId())){
		                            cercleLivraison.setOrdre(nb);
		                            nb++;
		                        }
		                    }
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
                	}
                	else if (idTournee != -1){
	            		colorSelection = color;
	            	}
                indexTournee = indexTournee + 1;
            }
            if (idTournee != -1){
            	Tournee tournee = tournees.get(idTournee);
            	List<Chemin>chemins = tournee.getChemins();
                int nb = 1;
                for (Chemin chemin : chemins) {
            		Noeud premierNoeud = NoeudFactory.getNoeudParId(chemin.getDepart());
                    for(Node node : livraisonsGroup.getChildren()){
                        CercleLivraison cercleLivraison= (CercleLivraison) node;
                        if(!cercleLivraison.getLivraison().getNoeud().equals(demandeLivraisons.getEntrepot()) && cercleLivraison.getLivraison().getNoeud().equals(premierNoeud.getId())){
                            cercleLivraison.setOrdre(nb);
                            nb++;
                        }
                    }
                    LineModifiee line = new LineModifiee();
                    line.setStartX(trX(premierNoeud.getLongitude()));
                    line.setStartY(trY(premierNoeud.getLatitude()));
                    for (String idNoeud : chemin.getChemin().subList(1, chemin.getChemin().size())) {
                        Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                        line.setEndX(trX(noeud.getLongitude()));
                        line.setEndY(trY(noeud.getLatitude()));
                        line.setDefaultColor(colorSelection);//line.setDefaultColor(Color.ORANGE);
                        line.setNomDeLaRue(planification.getNomDeLaRue(premierNoeud.getId(), noeud.getId()));
                        line.setStrokeWidth(6);
                        tourneesGroup.getChildren().add(line);
                        line = new LineModifiee();
                        premierNoeud = noeud;
                        line.setStartX(trX(noeud.getLongitude()));
                        line.setStartY(trY(noeud.getLatitude()));
                    }
                }
            }
        }
    }

    /**
     * Méthode d'indication visuelle pour le noeud selectionné qui sera en orange.
     * @param idNoeud correspondant à l'identifiant du noeud selectionné
     */
    void couleurPointFocus(String idNoeud) {
    	if (idNoeud.startsWith("Livreur :")){
    		int idLivraison = Integer.parseInt(idNoeud.substring(9))-1;
    		dessinerTournees(idLivraison);
    	}
    	else{
    		for (int i = 0; i < livraisonsGroup.getChildren().size(); i++) {
	            CercleLivraison cercle = (CercleLivraison) livraisonsGroup.getChildren().get(i);
	            if (cercle.getLivraison().getNoeud().equals(idNoeud)) {
	                cercle.setCouleur(Color.AQUA);
	                break;
	            }
	        }
    	}
    }

    /**
     * Méthode pour reinitialiser les couleurs prises en compte pour mettre du bleu sauf pour l'entrepôt en rouge..
     */
    void resetCouleurs() {
        for (int i = 0; i < livraisonsGroup.getChildren().size(); i++) {
            CercleLivraison cercle = (CercleLivraison) livraisonsGroup.getChildren().get(i);

            if (demandeLivraisons.getEntrepot().equals(cercle.getLivraison().getNoeud()))
                cercle.setCouleur(Color.RED);

            else
                cercle.setCouleur(Color.BLUE);

        }
    }

    /**
     * Méthode d'adaptation des echelles en Y.
     * @param longitude correspondant à la valeur initiale en longitude
     * @return echelleHor correspondant à la valeur finale par rapport à la taille de l'écran
     */
    private double trX(double longitude) {
        double echelleHor = screenSize.getHeight()*ratio / (maxLongitude - minLongitude);
        return echelleHor * (longitude - minLongitude);
    }

    /**
     * Méthode d'adaptation des echelles en X.
     * @param latitude correspondant à la valeur initiale en latitude
     * @return echelleVer correspondant à la valeur finale par rapport à la taille de l'écran
     */
    private double trY(double latitude) {
        double echelleVer = screenSize.getHeight()*ratio / (maxLatitude - minLatitude);
        return echelleVer * (maxLatitude - latitude);
    }

    /**
     * Méthode de calcul des limites de l'affichage pour le plan.
     */
    private void calculerCoins() {
        minLongitude = Double.POSITIVE_INFINITY;
        maxLongitude = Double.NEGATIVE_INFINITY;
        minLatitude = Double.POSITIVE_INFINITY;
        maxLatitude = Double.NEGATIVE_INFINITY;
        for (String idNoeud : plan.getNoeuds()) {
            Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
            if (noeud.getLongitude() < minLongitude) minLongitude = noeud.getLongitude();
            if (noeud.getLongitude() > maxLongitude) maxLongitude = noeud.getLongitude();
            if (noeud.getLatitude() < minLatitude) minLatitude = noeud.getLatitude();
            if (noeud.getLatitude() > maxLatitude) maxLatitude = noeud.getLatitude();
        }
    }

    /**
     * Méthode d'initialisation des couleurs.
     */
    private void initCouleurs() {
        colors = new LinkedList<>();
        colors.add(Color.BROWN);
        colors.add(Color.SEAGREEN);
        colors.add(Color.AQUA);
        colors.add(Color.YELLOWGREEN);
        colors.add(Color.CRIMSON);
        colors.add(Color.BLUEVIOLET);
        colors.add(Color.SPRINGGREEN);
        colors.add(Color.TOMATO);
        colors.add(Color.YELLOW);
        colors.add(Color.CORNFLOWERBLUE);
    }

    /**
     * Méthode d'affectation des couleurs avec conservations des valeurs dans une liste.
     * @param n correspondant au code associé à l'élément concerné
     * @return Color correspondant à la couleur qui sera utilisé
     */
    private Color getColor(int n) {
        if (n < colors.size()) {
            return colors.get(n);
        }
        Random random = new Random();
        Color newColor = Color.color(random.nextInt(100) / 100.0, random.nextInt(100) / 100.0, random.nextInt(100) / 100.0);
        colors.add(newColor);
        return newColor;
    }

    /**
     * Méthode d'annulation des modifications.
     */
    public void annulerModification() {
        for (CercleLivraison cercleLivraison : cerclesLivraisonsSelectionnes) cercleLivraison.setSelectionne(false);
        if (cercleIntersection != null) cercleIntersection.setSelectionne(false);
        cercleIntersection = null;
        cerclesLivraisonsSelectionnes.clear();
    }

    /**
     * Méthode d'affectation de la vue textuelle associée à la vue graphique.
     * @param vueTextuelle correspondant à la vue textuelle choisie
     */
    public void setVueTextuelle(VueTextuelle vueTextuelle) {
        this.vueTextuelle = vueTextuelle;
    }
}



