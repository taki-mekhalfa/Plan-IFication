package Controleur;

import java.util.LinkedList;

public class ListeCommandes {
    private LinkedList<Commande> listeCommandes;
    private int commandeEnCours;

    public ListeCommandes() {
        listeCommandes = new LinkedList<Commande>();
        commandeEnCours = -1;
    }

    public void add(Commande c) {
        commandeEnCours = commandeEnCours + 1;
        System.out.println(commandeEnCours);
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

    public void undo() {
        if (commandeEnCours >= 0) {
            listeCommandes.get(commandeEnCours).undoCommande();
            if(commandeEnCours + 1 == listeCommandes.size()) Controleur.interfaceGUI.activerBoutonRedo();
            commandeEnCours = commandeEnCours - 1;
            if (commandeEnCours == -1) Controleur.interfaceGUI.desactiverBoutonUndo();
        }
    }

    public void redo() {
        if (commandeEnCours < listeCommandes.size() - 1) {
            commandeEnCours = commandeEnCours + 1;
            if (commandeEnCours == 0) Controleur.interfaceGUI.activerBoutonUndo();
            if (commandeEnCours == listeCommandes.size() - 1) Controleur.interfaceGUI.desactiverBoutonRedo();
            listeCommandes.get(commandeEnCours).redoCommande();
        }
    }

    public void init(){
        listeCommandes = new LinkedList<Commande>();
        commandeEnCours = -1;
        Controleur.interfaceGUI.desactiverBoutonUndo();
        Controleur.interfaceGUI.desactiverBoutonRedo();
    }
}
