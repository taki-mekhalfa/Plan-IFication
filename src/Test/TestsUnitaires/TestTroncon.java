package Test.TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.Plan;
import Model.Metier.Plan.Troncon;

public class TestTroncon {
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
		System.out.println("----------------Test Troncon------------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testTronconToString() {
		System.out.println("testTronconToString:");
		Plan plan=planification.getPlan();
		String idOrigine=plan.getNoeuds().get(0);
		Troncon t=plan.getSuccesseurs(idOrigine).get(1);
		System.out.println(t.toString());
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
		System.out.println("");
	}
}
