package Vue;

import Model.Metier.Livraison;
import Model.Metier.Temps;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CercleLivraison extends Circle {
    private Livraison livraison;
    private Temps heureLivraison = null;
    private boolean selectionne = false;
    private Color defaultColor = Color.BLUE;

    public CercleLivraison(double centerX, double centerY, double radius, Livraison livraison) {
        super(centerX, centerY, radius);
        this.livraison = livraison;
        setFill(defaultColor);
    }

    public void setHeureLivraison(Temps heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    public void setCouleur(Color color) {
        setFill(color);
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public boolean isSelectionne() {
        return selectionne;
    }

    public void setSelectionne(boolean selectionne) {
        this.setFill(selectionne ? Color.ORANGE : defaultColor);
        this.selectionne = selectionne;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
        setFill(defaultColor);
    }
}
