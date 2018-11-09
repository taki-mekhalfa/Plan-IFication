package Vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineModifiee extends Line {
    private String nomDeLaRue;
    private Color defaultColor;

    public LineModifiee(double startX, double startY, double endX, double endY, String nomDeLaRue) {
        super(startX, startY, endX, endY);
        this.nomDeLaRue = nomDeLaRue;
        init();
    }

    public LineModifiee() {
        super();
        init();
    }

    private void init() {
        setOnMouseEntered(event -> {
            setStroke(Color.color(9/256.0,132/256.0,227/256.0,1));
            System.out.println(nomDeLaRue);
        });

        setOnMouseExited(event -> {
            setStroke(defaultColor);
        });
    }

    public void setNomDeLaRue(String nomDeLaRue) {
        this.nomDeLaRue = nomDeLaRue;
    }

    public void setDefaultColor(Color color) {
        setStroke(color);
        this.defaultColor = color;
    }
}
