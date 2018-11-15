package TestsUnitaires;

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
	private static Djikstra dPlan=new Djikstra();
	@BeforeClass
	public static void initialiser() {
		File fichierPlan=new File(cheminPlan);
		File fichierDl=new File(cheminDl);
		planification=new Planification();
		planification.chargerPlan(fichierPlan);
		planification.chargerDemandesDeLivraisons(fichierDl);
		plan=planification.getPlan();
		dl=planification.getDemandeLivraisons();
	}
	@Test
	public void TestDijkstrasPlan(){
		String idSource=dl.getEntrepot();
		List<Livraison> pointsDestination=dl.getPointsDeLivraisons();
		dPlan.getPlusCourtsChemins(idSource, pointsDestination, plan);
	}
	@Test
	public void TestCalculateur(){
		calculateur=new Calculateur(plan);
		int nombreLivreurs=3;
		calculateur.getTournees(dl, nombreLivreurs);
	}
	@AfterClass
	public static void clean() {
		planification=null;
		plan=null;
		dl=null;
	}

}
