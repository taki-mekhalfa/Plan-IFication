package Vue;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Velo extends Parent {
    
    ImageView velo = new ImageView(new Image("File:\\C:\\\\Users\\ALEX\\Desktop\\Nouveau dossier\\NVPRJ\\bike.png"));
 
	
	public Velo(){}	

	public Velo(double posX, double posY) {
		
		this.getChildren().clear();
        velo.setFitHeight(30);
        velo.setPreserveRatio(true);        
        velo.setTranslateX(posX-10);
        velo.setTranslateY(posY-12);  
        this.getChildren().add(velo);
        
	}
	
	public void avancerVelo(double destX,double destY) {
	
		
		  //Creating Translate Transition 
	      TranslateTransition translateTransition = new TranslateTransition(); 
	      
	      //Setting the duration of the transition  
	      translateTransition.setDuration(Duration.seconds(1)); 
	      
	      //Setting the node for the transition 
	      translateTransition.setNode(velo); 
	      
	      //Setting the value of the transition along the x axis. 
	      translateTransition.setToX(destX-10); 
	      
	      //Setting the value of the transition along the y axis. 
	      translateTransition.setToY(destY-12); 
	      	      
	      //Setting auto reverse value to false 
	      translateTransition.setAutoReverse(false); 
	      
	      //Playing the animation 
	      translateTransition.play(); 	 
	   
	      		
	}    
}
