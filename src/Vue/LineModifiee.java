package Vue;

import javafx.scene.shape.Line;

public class LineModifiee extends Line {
    private String nomDeLaRue;

    public LineModifiee(double startX, double startY, double endX, double endY, String nomDeLaRue) {
        super(startX, startY, endX, endY);
    }
    public LineModifiee(){
        super();
    }

    public void setNomDeLaRue(String nomDeLaRue) {
        this.nomDeLaRue = nomDeLaRue;
    }
}
