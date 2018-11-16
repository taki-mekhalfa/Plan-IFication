package Test.TestsUnitaires;

import java.io.File;
import java.util.List;

import org.junit.*;
import Model.Calcules.*;
import Model.Metier.*;
import Model.Planification;
public class TestModelCalcul {
	private final static String cheminPlan="./Documents/fichiersXML2018/petitPlan.xml";
	private final static String cheminDl="./Documents/fichiersXML2018/dl-petit-6.xml";
	private static Plan plan;
	private static DemandeLivraisons dl;
	private static Planification planification;
	private static Calculateur calculateur;
	@BeforeClass
	public static void initialiser() {
		File fichierPlan=new File(cheminPlan);
		File fichierDl=new File(cheminDl);
		planification=new Planification();
		planification.chargerPlan(fichierPlan);
		planification.chargerDemandesDeLivraisons(fichierDl);
		plan=planification.getPlan();
		dl=planification.getDemandeLivraisons();
		System.out.println("----------------------------------------------");
		System.out.println("--------------Test Model Calcul---------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void TestCalcul(){
		System.out.println("Test Calcul");
		calculateur=new Calculateur(plan);
		int nombreLivreurs=3;
		System.out.println("tournee 1:\n"+calculateur.getTournees(dl, nombreLivreurs).get(0));
		System.out.println("Test reussi");
	}
	@AfterClass
	public static void clean() {
		planification=null;
		plan=null;
		dl=null;
		System.out.println("----------------------------------------------");
		System.out.println("");
	}
}
