package Model;

import java.io.File;
import java.util.List;

import Model.Metier.Tournee;

public class Main {
    public static void main(String[] args) {

        Planification planification = new Planification();
        planification.chargerPlan(new File("C:/Users/cflorant/fichiersXML2018/moyenPlan.xml"));
        planification.chargerDemandesDeLivraisons(new File ("C:/Users/cflorant/fichiersXML2018/dl-moyen-12.xml"));
        planification.calculerTournees(3);
        List<Tournee> tournees = planification.getTournees();
        
        System.out.print(tournees);
    }
}
