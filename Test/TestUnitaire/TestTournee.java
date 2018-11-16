package Test.TestUnitaire;

import java.io.File;
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
	}
	@Test
	public void testTournee() {
		System.out.println("testTournee:");
		Tournee tourneeATester=planification.getTournees().get(1);
		System.out.println(tourneeATester.toString());
		System.out.println("");
	}
}
