package Test.TestsUnitaires;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.DemandeLivraisons;

public class TestDemandeLivraisons {
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
		System.out.println("--------------Test DemandeLivraisons----------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testDemandeLivraisons() {
		System.out.println("----------------------------------------------");
		System.out.println("testDemandeLivraisons:");
		DemandeLivraisons dl=planification.getDemandeLivraisons();
		System.out.println(dl.toString());
		System.out.println("Test reussi");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("----------------------------------------------");
		System.out.println("");
	} 
}
