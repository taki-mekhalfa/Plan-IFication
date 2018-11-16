package Test.TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import Model.Planification;
import Model.Metier.Chemin;

public class TestChemin {
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
		System.out.println("---------------Test Chemin--------------------");
		System.out.println("----------------------------------------------");
	}
	@After
	public void after() {
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
	}
	
	@Test
	public void testConstucteurChemin() {
		System.out.println("testConstucteurChemin:");
		List<String> listeChemin=new ArrayList<String>();
		listeChemin.add("123");
		listeChemin.add("234");
		Chemin cheminATester=new Chemin(listeChemin,200);
		assertFalse(cheminATester.equals(null));
		System.out.println("arrivee de ce chemin:"+cheminATester.getArrivee());	
	}
	@Test
	public void testToString() {
		System.out.println("testToStringChemin:");
		List<String> listeChemin=new ArrayList<String>();
		listeChemin.add("123");
		listeChemin.add("234");
		Chemin cheminATester=new Chemin(listeChemin,200);
		System.out.println(cheminATester.toString());
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("");
	} 
}
