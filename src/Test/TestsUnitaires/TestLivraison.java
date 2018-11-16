package Test.TestsUnitaires;

import java.io.File;

import org.junit.AfterClass;
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
		System.out.println("----------------------------------------------");
		System.out.println("-----------------Test Livraison---------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testLivraison() {
		System.out.println("testLivraisonToString:");
		Livraison livraison1=new Livraison();
		Livraison livraison2=new Livraison("1234",60);
		System.out.println("livraison1:\n"+livraison1.toString()+",heureDeLivraison="+livraison1.getHeureDeLivraisonProperty());
		System.out.println("livraison2:\n"+livraison2.toString()+",heureDeLivraison="+livraison2.getHeureDeLivraisonProperty());
		livraison1.setNoeud(new SimpleStringProperty("4321"));
		livraison1.setDureeProperty(new SimpleStringProperty("120"));
		livraison2.setHorraireProperty(new SimpleStringProperty("8:0:0"));
		System.out.println("");
		System.out.println("Apres avoir ajoute des parametres:");
		System.out.println("livraison1:\n"+livraison1.toString()+",heureDeLivraison="+livraison1.getHeureDeLivraisonProperty());
		System.out.println("livraison2:\n"+livraison2.toString()+",heureDeLivraison="+livraison2.getHeureDeLivraisonProperty());
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("");
	} 

}
