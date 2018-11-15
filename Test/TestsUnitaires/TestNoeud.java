package TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

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
	}
	@Test
	public void testNoeud() {
		System.out.println("testNoeud:");
		String idNoeudATester=planification.getPlan().getNoeuds().get(2);
		Noeud noeudATester=NoeudFactory.getNoeudParId(idNoeudATester);
		String idNoeudAComparer=planification.getPlan().getNoeuds().get(3);
		Noeud noeudAComparer=NoeudFactory.getNoeudParId(idNoeudAComparer);
		System.out.println(noeudATester.toString());
		System.out.println("id de ce noeud:"+noeudATester.getId());
		System.out.println("hashcode de ce noeud:"+noeudATester.hashCode());
		assertTrue(noeudATester.equals(noeudATester));
		assertFalse(noeudATester.equals(noeudAComparer));
		assertFalse(noeudATester.equals(null));
		System.out.println("");
	}
}
