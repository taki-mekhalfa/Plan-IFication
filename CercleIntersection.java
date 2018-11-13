package Vue;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CercleIntersection extends Circle {
    private String idNoeud;
    private boolean selectionne = false;
    private EventHandler<MouseEvent> eventHandlerMouseEntered = event -> {
        setFill(Color.color(9/256.0,132/256.0,227/256.0,1));
        event.consume();
    };

    private EventHandler<MouseEvent> eventEventHandlerMouseExited = event -> {
        setFill(Color.TRANSPARENT);
        event.consume();
    };

    public CercleIntersection(double centerX, double centerY, double radius, String idNoeud) {
        super(centerX, centerY, radius);
        this.idNoeud = idNoeud;
        setFill(Color.TRANSPARENT);
        addEventHandler(MouseEvent.MOUSE_ENTERED,eventHandlerMouseEntered);
        addEventHandler(MouseEvent.MOUSE_EXITED,eventEventHandlerMouseExited);
    }

    @Override
    public String toString() {
        return "CercleIntersection{" +
                "idNoeud='" + idNoeud + '\'' +
                '}';
    }

    public boolean isSelectionne() {
        return selectionne;
    }

    public void setSelectionne(boolean selectionne) {
        if (selectionne) {
            setFill(Color.BLACK);
            removeEventHandler(MouseEvent.MOUSE_ENTERED,eventHandlerMouseEntered);
            removeEventHandler(MouseEvent.MOUSE_EXITED,eventEventHandlerMouseExited);
        }
        else{
            setFill(Color.TRANSPARENT);
            addEventHandler(MouseEvent.MOUSE_ENTERED,eventHandlerMouseEntered);
            addEventHandler(MouseEvent.MOUSE_EXITED,eventEventHandlerMouseExited);
        }
        this.selectionne = selectionne;
    }

    public String getIdNoeud() {
        return idNoeud;
    }
}