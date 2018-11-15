package TestsUnitaires;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.Temps;

public class TestTemps {
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
	public void testTemps() {
		System.out.println("testTemps:");
		Temps t=new Temps(8,0,0);
		System.out.println("heures:"+t.getHeures()+" minutes:"+t.getMinutes()+" secondes:"+t.getSecondes());
		System.out.println("heureLivraison:"+t.getHorraireProperty());
		System.out.println("tout en secondes:"+t.getValue());
		System.out.println("compare to 11:00:00:"+t.compareTo(new Temps(11,0,0)));
		System.out.println("compare to 08:02:00:"+t.compareTo(new Temps(8,2,0)));
		System.out.println("compare to 08:00:03:"+t.compareTo(new Temps(8,0,3)));
		System.out.println("");
	}
}
