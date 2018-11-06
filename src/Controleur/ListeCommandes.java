package Controleur;

import java.util.LinkedList;

public class ListeCommandes {
	private LinkedList <Commande> listeCommandes;
	private int commandeEnCours;
	
	public ListeCommandes(){
		commandeEnCours = 0;
	}
	
	public void undo(){
    	if (commandeEnCours >= 1){
    		listeCommandes.get(commandeEnCours --).undoCommande();
    	}
    }
	
    public void redo(){
    	if (commandeEnCours < listeCommandes.size()){
    		listeCommandes.get(commandeEnCours ++).redoCommande();
    	}
    }
}
