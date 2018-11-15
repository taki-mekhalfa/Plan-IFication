package Vue;

import Model.Metier.Livraison;
import Model.Metier.Temps;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Classe de gestion de l'affichage des livraisons.
 * @author H4104
 * @see Model.Metier.Livraison
 */
public class CercleLivraison extends Group {
    private Temps heureLivraison = null;
    private boolean selectionne = false;
    private Color defaultColor = Color.BLUE;
    private Circle cercle;
    private Text ordre;
    private Circle shield;
    private Livraison livraison;

    /**
     * Constructeur de la classe CercleLivraison.
     * @param centerX correspondant au centre de l'objet sur les abscisses
     * @param centerY correspondant au centre de l'objet sur les ordonnées
     * @param radius correspondant au rayon du cercle
     * @param livraison correspondant à la livraison associée à l'objet circulaire
     */
    public CercleLivraison(double centerX, double centerY, double radius, Livraison livraison) {
        this.livraison = livraison;
        this.cercle = new Circle(centerX, centerY, radius);
        this.cercle.setFill(defaultColor);
        this.ordre = new Text("");
        this.shield = new Circle(centerX, centerY, radius,Color.TRANSPARENT);
        this.getChildren().addAll(cercle,ordre,shield);
    }

    /**
     * Méthode d'affectation d'une heure de livraison à l'objet.
     * @param heureLivraison correspondant à l'heure de livraison que l'on veut affecter à l'objet
     */
    public void setHeureLivraison(Temps heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    /**
     * Méthode d'affectation d'une couleur à l'objet.
     * @param color correspondant à la couleur que l'on veut affecter en format color
     */
    public void setCouleur(Color color) {
        this.cercle.setFill(color);
    }

    /**
     * Méthode d'obtention de la livraison.
     * @return livraison correspondant à la livraison associée à l'objet circulaire
     */
    public Livraison getLivraison() {
        return livraison;
    }

    /**
     * Méthode de test pour savoir si l'objet est actuellement selectionné.
     * @return selectionne correspondant à un booleen indiquant le résultat du test
     */
    public boolean isSelectionne() {
        return selectionne;
    }

    /**
     * Méthode d'indication de l'ordre de passage.
     * @param nb correspondant à la valeur de l'odre de passage pour la livraison
     */
    public void setOrdre(int nb){
        ordre.setText(Integer.toString(nb));
        ordre.setBoundsType(TextBoundsType.VISUAL);
        ordre.setLayoutY(this.cercle.getCenterY()+3.5);
        if(nb<10){
            ordre.setLayoutX(this.cercle.getCenterX()-3.5);
        } else {
            ordre.setLayoutX(this.cercle.getCenterX()-7);
        }
        ordre.setStroke(Color.WHITE);

    }

    /**
     * Méthode d'affectation pour l'attribut selectionne.
     * @param selectionne correspondant à la valeur que l'on veut pour l'attribut booleen.
     */
    public void setSelectionne(boolean selectionne) {
        this.cercle.setFill(selectionne ? Color.ORANGE : defaultColor);
        this.selectionne = selectionne;
    }

    /**
     * Méthode d'affectation de la couleur par defaut de l'objet circulaire.
     * @param defaultColor correspondant à la couleur que l'on à choisit, enformat Color
     */
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
        this.cercle.setFill(defaultColor);
    }
}