package Model;

import Model.Calcules.Calculateur;
import Model.Calcules.Djikstra;
import Model.Calcules.PetalClustring;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Livraison;
import Model.Metier.Plan;
import Model.Metier.Tournee;
import Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J;
import Model.XMLHelpers.PlanXMLHelperDom4J;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Planification extends Observable {
    private Plan plan;
    private DemandeLivraisons demandeLivraisons;
    private Calculateur calculateur;
    private List<Tournee> tournees;

    /*public static void main(String[] args){
        Planification planification =new Planification();
        planification.chargerPlan(new File("./moyenPlan.xml"));
        planification.chargerDemandesDeLivraisons(new File("dl-moyen-9.xml"));
        planification.calculerTournees(3);
        List<Tournee> tourneeList = planification.getTournees();
        List<Livraison> livraisons = new LinkedList<>(tourneeList.get(0).getHeuresDeLivraison().keySet());
        System.out.println(tourneeList.get(0));
        planification.supprimerPointDeLivraison(tourneeList.get(0),livraisons.get(0));
        System.out.println(tourneeList.get(0));
    }*/

    public void chargerPlan(File fichier) {
        plan = new PlanXMLHelperDom4J().getPlan(fichier);
        calculateur = new Calculateur(plan);
        notifierAbonnes("plan");
    }

    public void chargerDemandesDeLivraisons(File fichierXML) {
        demandeLivraisons = new DemandeLivraisonsXMLHelperDom4J().getDemandeLivraisons(fichierXML);
        notifierAbonnes("livraisons");
    }

    public void calculerTournees(int nombreLivreurs) {
        tournees = calculateur.getTournees(demandeLivraisons, nombreLivreurs);
        notifierAbonnes("tournees");
    }

    public void ajouterPointDeLivraison(String idPointLivraison, int duree, Livraison livraison1, Livraison livraison2){
        for (Tournee tournee: tournees){
            if (tournee.consecutives(livraison1,livraison2)){
                Livraison livraison = new Livraison(idPointLivraison,duree);
                demandeLivraisons.getPointsDeLivraisons().add(livraison);
                tournee.ajouterLivraison(livraison, livraison1,livraison2,plan);
                break;
            }
        }
        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }

    public void deplacerLivraison(Livraison livraisonADeplacer, Livraison livraison1, Livraison livraison2){
        boolean supprimee = false;
        boolean ajoutee = false;
        for (Tournee tournee : tournees){
            if (tournee.consecutives(livraison1,livraison2)) {
                tournee.ajouterLivraison(livraisonADeplacer, livraison1, livraison2, plan);
                ajoutee = true;
            }
            else if (tournee.contientLivraison(livraisonADeplacer)) {
                tournee.supprimerLivraison(livraisonADeplacer,plan);
                supprimee = true;
            }

            if (supprimee && ajoutee) break;
        }

        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
    }

    public void supprimerPointDeLivraison(Livraison livraison){
        for (Tournee tournee: tournees){
            if (tournee.contientLivraison(livraison)){
                tournee.supprimerLivraison(livraison,plan);
                demandeLivraisons.getPointsDeLivraisons().remove(livraison);
                break;
            }
        }
        notifierAbonnes("livraisons");
        notifierAbonnes("tournees");
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


    private void notifierAbonnes(String quoi) {
        setChanged();
        notifyObservers(quoi);
    }

    public boolean livraisonsConsecutives(Livraison livraison1, Livraison livraison2){
        for (Tournee tournee : tournees){
            if (tournee.consecutives(livraison1,livraison2)) return true;
        }

        return false;
    }
}
