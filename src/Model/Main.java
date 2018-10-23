package Model;

import View.JavaFXExample //;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {

        Planification planification = new Planification();
        planification.chargerPlan("./XMLFiles/petitPlan.xml");
        planification.chargerDemandesDeLivraisons("./XMLFiles/dl-petit-6.xml");
        planification.calculerTournees(3);
        List<Tournee> tournees = planification.getTournees();

    }
}
