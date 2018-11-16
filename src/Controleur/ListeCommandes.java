package Controleur;

import java.util.LinkedList;

/**
 * Classe ListeCommandes, qui gere les undo redo
 * @author H4104
 * @see Commande
 * @see Controleur
 */
public class ListeCommandes {
    private LinkedList<Commande> listeCommandes;
    private int commandeEnCours;

    /**
     * Declenchement des actions liees au clic sur le bouton undo
     */
    public ListeCommandes() {
        listeCommandes = new LinkedList<Commande>();
        commandeEnCours = -1;
    }

    /**
     * Ajout d'une commande a la liste
     * @param c la commande a ajouter a la liste
     */
    public void add(Commande c) {
        commandeEnCours = commandeEnCours + 1;
        if (commandeEnCours == 0) Controleur.interfaceGUI.activerBoutonUndo();
        if (commandeEnCours < listeCommandes.size()) {
            listeCommandes.set(commandeEnCours, c);
            int size = listeCommandes.size();
            int index = commandeEnCours + 1;
            for (int i = index; i < size; i++) {
                listeCommandes.remove(index);
            }
            //supr les items suivants
        } else {
            listeCommandes.add(c);
        }
    }

    /**
     * Declenchement des actions liees au clic sur le bouton undo
     */
    public void undo() {
        if (commandeEnCours >= 0) {
            listeCommandes.get(commandeEnCours).undoCommande();
            if(commandeEnCours + 1 == listeCommandes.size()) Controleur.interfaceGUI.activerBoutonRedo();
            commandeEnCours = commandeEnCours - 1;
            if (commandeEnCours == -1) Controleur.interfaceGUI.desactiverBoutonUndo();
        }
    }

    /**
     * Declenchement des actions liees au clic sur le bouton redo
     */
    public void redo() {
        if (commandeEnCours < listeCommandes.size() - 1) {
            commandeEnCours = commandeEnCours + 1;
            if (commandeEnCours == 0) Controleur.interfaceGUI.activerBoutonUndo();
            if (commandeEnCours == listeCommandes.size() - 1) Controleur.interfaceGUI.desactiverBoutonRedo();
            listeCommandes.get(commandeEnCours).redoCommande();
        }
    }

    /**
     * Initialisation de la liste de commandes
     */
    public void init(){
        listeCommandes = new LinkedList<Commande>();
        commandeEnCours = -1;
        Controleur.interfaceGUI.desactiverBoutonUndo();
        Controleur.interfaceGUI.desactiverBoutonRedo();
    }
}
