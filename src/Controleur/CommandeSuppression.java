package Controleur;

import java.util.List;

import Model.Metier.Tournee;

public class CommandeSuppression implements Commande{
	private List <Tournee> tourneesAvant;
	private List <Tournee> tourneesApres;
	
	public CommandeSuppression(List <Tournee> t){
		tourneesAvant = t;
	}
	
	public void redoCommande(){
		Controleur.planification.setTournees(tourneesApres);
	}
	public void undoCommande(){
		tourneesApres = Controleur.planification.getTournees();
		Controleur.planification.setTournees(tourneesAvant);
	}
}