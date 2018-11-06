package Controleur;

import java.util.LinkedList;

public class ListeCommandes {
	private LinkedList <Commande> listeCommandes;
	private int commandeEnCours;
	
	public ListeCommandes(){
		listeCommandes = new LinkedList <Commande>();
		commandeEnCours = 0;
	}
	
	public void add(Commande c){
		if(commandeEnCours < listeCommandes.size()){
			listeCommandes.set(commandeEnCours, c);
			int size = listeCommandes.size();
			int index = commandeEnCours+1;
			for (int i = index; i < size; i ++){
				listeCommandes.remove(index);
			}
			//supr les items suivants
		}else{
			listeCommandes.add(c);
		}
		commandeEnCours++;
	}
	
	public void undo(){
    	if (commandeEnCours >= 1){
    		listeCommandes.get(--commandeEnCours).undoCommande();
    	}
    }
	
    public void redo(){
    	if (commandeEnCours < listeCommandes.size()){
    		listeCommandes.get(commandeEnCours ++).redoCommande();
    	}
    }
}
