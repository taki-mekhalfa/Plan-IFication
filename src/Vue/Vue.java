package Vue;

import Controleur.Controleur;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Plan;
import Model.Metier.Tournee;
import Model.Planification;
import javafx.scene.Parent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Vue extends Parent implements Observer {

    Plan plan = null;
    DemandeLivraisons demandeLivraisons = null;
    List<Tournee> tournees = null;
    Planification planification;

    Vue(Planification planification) {
        this.planification = planification;
        planification.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        String quoi = (String) arg;

        switch (quoi) {
            case "plan": {
                plan = planification.getPlan();
                dessinerPlan();
            }
            break;
            case "livraisons": {
                demandeLivraisons = planification.getDemandeLivraisons();
                dessinerDemandeDeLivraisons();
            }
            break;
            case "tournees": {
                tournees = planification.getTournees();
                dessinerTournees();
            }
        }
    }

    abstract void dessinerPlan();

    abstract void dessinerDemandeDeLivraisons();

    abstract void dessinerTournees();
}

