package Controleur;

import java.io.File;

public class EtatTourneesCalculees extends EtatDefaut {

    @Override
    public void init(){
        Controleur.interfaceGUI.activerBoutonChargerPlan();
        Controleur.interfaceGUI.activerBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonCaluculerTournees();
        Controleur.interfaceGUI.activerBoutonAjouterLivraison();
        Controleur.interfaceGUI.activerBoutonSuprimmerLivraison();
        Controleur.interfaceGUI.activerBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonValider();
        Controleur.interfaceGUI.desactiverBoutonAnnuler();
    }

    @Override
    public void boutonChargerPlan(File fichier) {
        Controleur.planification.chargerPlan(fichier);
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
    }

    @Override
    public void boutonChargerDemandeLivraison(File fichierXML) {
        Controleur.planification.chargerDemandesDeLivraisons(fichierXML);
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatPlanEtDemandeLivraisonCharges);
    }

    @Override
    public void boutonSuprimmerLivraison() {
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatSupressionLivraison);
    }

    @Override
    public void boutonAjouterLivraison() {
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatAjoutLivraison);
    }

    @Override
    public void boutonDeplacerLivraison(){
        Controleur.interfaceGUI.desactiverBoutonChargerPlan();
        Controleur.interfaceGUI.desactiverBoutonChargerDemandeLivraison();
        Controleur.interfaceGUI.desactiverBoutonAjouterLivraison();
        Controleur.interfaceGUI.desactiverBoutonDeplacerLivraison();
        Controleur.interfaceGUI.desactiverBoutonSuprimmerLivraison();
        Controleur.setEtatCourant(Controleur.etatDeplacementLivraison);
    }
    
    @Override
    public void undo(ListeCommandes l){
    	l.undo();
    }
    
    @Override
    public void redo(ListeCommandes l){
    	l.redo();
    }
}
