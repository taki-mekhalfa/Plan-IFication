package TestUnitaire;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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
	}
	@Test
	public void testChemin() {
		System.out.println("testChemin:");
		Chemin cheminATester=planification.getTournees().get(0).getChemins().get(1);
		System.out.println("arrive:"+cheminATester.getArrivee());
		System.out.println(cheminATester.toString());
		List<String> listeChemins=cheminATester.getChemin();
		assertFalse(listeChemins.equals(null));
		assertFalse(cheminATester.equals(null));
		assertTrue(cheminATester.equals(cheminATester));
		System.out.println("");
	}
}
