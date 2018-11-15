package TestUnitaire;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.Livraison;
import javafx.beans.property.SimpleStringProperty;

public class TestLivraison {
	private final static String cheminPlan="./Documents/fichiersXML2018/petitPlan.xml";
	private final static String cheminDl="./Documents/fichiersXML2018/dl-petit-6.xml";
	private static Planification planification;
	@BeforeClass
	public static void initialiser() {
		File fichierPlan=new File(cheminPlan);
		File fichierDl=new File(cheminDl);
		planification=new Planification();
		planification.chargerPlan(fichierPlan);
		planification.chargerDemandesDeLivraisons(fichierDl);
		int nombreLivreurs=3;
		planification.calculerTournees(nombreLivreurs);
	}
	@Test
	public void testLivraison() {
		System.out.println("testLivraison:");
		Livraison livraisonATester=new Livraison();
		SimpleStringProperty heureLivraison=new SimpleStringProperty("8:00");
		SimpleStringProperty idNoeud=new SimpleStringProperty("1234");
		SimpleStringProperty duree=new SimpleStringProperty("60");
		livraisonATester.setDureeProperty(duree);
		livraisonATester.setHorraireProperty(heureLivraison);
		livraisonATester.setNoeud(idNoeud);
		
		System.out.println("duree:"+livraisonATester.getDureeProperty()+";heureLivraison:"+livraisonATester.getHeureDeLivraisonProperty()+";noeud:"+livraisonATester.getNoeudProperty());
		System.out.println("");
	}

}
