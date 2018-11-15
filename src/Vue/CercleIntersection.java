package Vue;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Classe de gestion de l'affichage des noeuds.
 * @author H4104
 * @see Model.Metier.Noeud
 */
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

    /**
     * Constructeur de la classe CercleIntersection.
     * @param centerX correspondant au centre de l'objet sur les abscisses
     * @param centerY correspondant au centre de l'objet sur les ordonnées
     * @param radius correspondant au rayon du cercle
     * @param idNoeud correspondant au noeud associé à l'objet cercle
     */
    public CercleIntersection(double centerX, double centerY, double radius, String idNoeud) {
        super(centerX, centerY, radius);
        this.idNoeud = idNoeud;
        setFill(Color.TRANSPARENT);
        addEventHandler(MouseEvent.MOUSE_ENTERED,eventHandlerMouseEntered);
        addEventHandler(MouseEvent.MOUSE_EXITED,eventEventHandlerMouseExited);
    }

    /**
     * Méthode de conversion de l'objet en string.
     * @return CercleIntersection correspondant à la description de cet objet avec le noeud concerné
     */
    @Override
    public String toString() {
        return "CercleIntersection{" +
                "idNoeud='" + idNoeud + '\'' +
                '}';
    }

    /**
     * Méthode de test pour savoir si l'objet est actuellement selectionné.
     * @return selectionne correspondant à un booleen indiquant le résultat du test
     */
    public boolean isSelectionne() {
        return selectionne;
    }

    /**
     * Méthode d'affectation pour l'attribut selectionne.
     * @param selectionne correspondant à la valeur que l'on veut pour l'attribut booleen.
     */
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

    /**
     * Méthode d'obtention de l'identifiant du noeud.
     * @return idNoeud correspondant à l'identifiant du noeud associé à l'objet circulaire
     */
    public String getIdNoeud() {
        return idNoeud;
    }
}