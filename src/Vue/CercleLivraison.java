package Vue;

import Model.Metier.Livraison;
import Model.Metier.Temps;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class CercleLivraison extends Group {
    private Temps heureLivraison = null;
    private boolean selectionne = false;
    private Color defaultColor = Color.BLUE;
    private Circle cercle;
    private Text ordre;
    private Circle shield;
    private Livraison livraison;


    public CercleLivraison(double centerX, double centerY, double radius, Livraison livraison) {
        this.livraison = livraison;
        this.cercle = new Circle(centerX, centerY, radius);
        this.cercle.setFill(defaultColor);
        this.ordre = new Text("");
        this.shield = new Circle(centerX, centerY, radius,Color.TRANSPARENT);
        this.getChildren().addAll(cercle,ordre,shield);
    }

    public void setHeureLivraison(Temps heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    public void setCouleur(Color color) {
        this.cercle.setFill(color);
    }


    public Livraison getLivraison() {
        return livraison;
    }


    public boolean isSelectionne() {
        return selectionne;
    }

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

    public void setSelectionne(boolean selectionne) {
        this.cercle.setFill(selectionne ? Color.ORANGE : defaultColor);
        this.selectionne = selectionne;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
        this.cercle.setFill(defaultColor);
    }
}