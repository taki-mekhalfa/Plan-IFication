package Vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Classe de gestion de l'affichage des rues.
 * @author H4104
 */
public class LineModifiee extends Line {
    private String nomDeLaRue;
    private Color defaultColor;

    /**
     * Constructeur de la classe LineModifiee
     * @param startX correspondant à la coordonnée de début de la rue selon les abscisses
     * @param startY correspondant à la coordonnée de début de la rue selon les ordonnées
     * @param endX correspondant à la coordonnée de fin de la rue selon les abscisses
     * @param endY correspondant à la coordonnée de fin de la rue selon les ordonnées
     * @param nomDeLaRue correspondant à une string contenant le nom de la rue
     */
    public LineModifiee(double startX, double startY, double endX, double endY, String nomDeLaRue) {
        super(startX, startY, endX, endY);
        this.nomDeLaRue = nomDeLaRue;
        init();
    }

    /**
     * Constructeur par defaut de la classe LineModifiee.
     */
    public LineModifiee() {
        super();
        init();
    }

    /**
     * Méthode d'initialisation de la detection d'évènements.
     */
    private void init() {
        setOnMouseEntered(event -> {
            setStroke(Color.color(9/256.0,132/256.0,227/256.0,1));
        });

        setOnMouseExited(event -> {
            setStroke(defaultColor);
        });
    }

    /**
     * Méthode d'affectation du nom de la rue associée à l'objet.
     * @param nomDeLaRue correspondant au nom de rue choisit
     */
    public void setNomDeLaRue(String nomDeLaRue) {
        this.nomDeLaRue = nomDeLaRue;
    }

    /**
     * Méthode d'affectation de la couleur.
     * @param color correspondant à la couleur choisit dans un type Color
     */
    public void setDefaultColor(Color color) {
        setStroke(color);
        this.defaultColor = color;
    }

    /**
     * Méthode d'obtention du nom de la rue associée à l'objet.
     * @retour nomDeLaRue correspondant au nom de rue choisit
     */
    public String getNomDeLaRue() {
        return nomDeLaRue;
    }
}
