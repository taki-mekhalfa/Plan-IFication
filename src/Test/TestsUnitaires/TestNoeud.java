package Test.TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.*;

import Model.Planification;
import Model.Metier.Noeud;
import Model.Metier.NoeudFactory;

public class TestNoeud {

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
		System.out.println("--------------------Test Noeud----------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testNoeud() {
		System.out.println("----------------------------------------------");
		System.out.println("testNoeud:");
		String idNoeud1=planification.getPlan().getNoeuds().get(2);
		Noeud noeud1=NoeudFactory.getNoeudParId(idNoeud1);
		String idNoeud2=planification.getPlan().getNoeuds().get(3);
		Noeud noeud2=NoeudFactory.getNoeudParId(idNoeud2);
		System.out.println("noeud1:"+noeud1.toString());
		System.out.println("noeud2:"+noeud2.toString());
		System.out.println("noeud1 est egale a noeud1£º"+noeud1.equals(noeud1));
		System.out.println("noeud1 est egale a noeud2£º"+noeud1.equals(noeud2));
		System.out.println("noeud1 est egale a null£º"+noeud1.equals(null));
		System.out.println("Test reussi");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("----------------------------------------------");
		System.out.println("");
	} 
	
	
}
