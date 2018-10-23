package Model;

import Model.Calcules.Calculateur;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Plan;
import Model.Metier.Tournee;
import Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J;
import Model.XMLHelpers.PlanXMLHelperDom4J;

import java.io.File;
import java.util.List;

public class Planification {
    private Plan plan;
    private DemandeLivraisons demandeLivraisons;
    private Calculateur calculateur;
    private List<Tournee> tournees;

    public void chargerPlan(File fichier) {
        plan = new PlanXMLHelperDom4J().getPlan(fichier);
        calculateur = new Calculateur(plan);
    }

    public void chargerDemandesDeLivraisons(File fichierXML) {
        demandeLivraisons = new DemandeLivraisonsXMLHelperDom4J().getDemandeLivraisons(fichierXML);
    }

    public void calculerTournees(int nombreLivreurs) {
        tournees = calculateur.getTournees(demandeLivraisons, nombreLivreurs);
    }

    public Plan getPlan() {
        return plan;
    }

    public DemandeLivraisons getDemandeLivraisons() {
        return demandeLivraisons;
    }

    public List<Tournee> getTournees() {
        return tournees;
    }
}
