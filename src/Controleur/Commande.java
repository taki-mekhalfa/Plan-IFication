package Controleur;

/**
 * Interface Commande pour les classes CommandeAjout, CommandeDeplacement, CommandeSuppression
 * @author H4104
 *
 */
public interface Commande {
	void redoCommande();
	void undoCommande();
}
