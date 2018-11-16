package Test.TestsUnitaires;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.Tournee;

public class TestTournee {
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
		System.out.println("-----------------Test Tournee-----------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testTournee() {
		System.out.println("testTournee:");
		Tournee tourneeATester=planification.getTournees().get(1);
		System.out.println("tournee a tester:");
		System.out.println(tourneeATester.toString());
		System.out.println("Test reussi");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("----------------------------------------------");
		System.out.println("");
	} 
}
