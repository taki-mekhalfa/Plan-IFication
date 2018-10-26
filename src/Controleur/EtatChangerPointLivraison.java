package controleur;

public class EtatChangerPointLivraison extends EtatDefaut {
	@Override
	public void validerOperation(){
		Controleur.setEtatCourant(Controleur.etatTourneesCalculees);
	}
	
	@Override
	public void annulerOperation(){
		Controleur.setEtatCourant(Controleur.etatPointLivraisonSelectionne);
    }
}
