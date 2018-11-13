package Vue;

import Model.Metier.Livraison;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class CercleModifie extends Circle {
    private Livraison livraison;
    private CercleLivraison parent;
    
    public CercleModifie(double centerX, double centerY, double radius, Livraison livraison, Color color, CercleLivraison parent) {
        super( centerX,  centerY,  radius,color);
        this.parent = parent;
    	this.livraison = livraison;
    }
    
    public Livraison getLivraison() {
        return livraison;
    }
    
    public CercleLivraison getCercleParent(){
    	return parent;
    }
}
