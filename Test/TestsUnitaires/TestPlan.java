package TestsUnitaires;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.Plan;

public class TestPlan {
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
	public void testPlan() {
		System.out.println("testPlan:");
		Plan plan=planification.getPlan();
		String idOrigine=plan.getNoeuds().get(0);
		String idDestination=plan.getSuccesseurs(idOrigine).get(1).getDestination();
		String nomDeLaRue=plan.getNomDeLaRue(idOrigine, idDestination);
		System.out.println("nom de la rue:"+nomDeLaRue);
		System.out.println(plan.toString());
		String idDestinationNotFound="1234";
		String nomDeLaRueNotFound=plan.getNomDeLaRue(idOrigine, idDestinationNotFound);
		System.out.println("nom de la rue pour une destination not found:"+nomDeLaRueNotFound);
		System.out.println("distance pour la destination not found:"+plan.getDistance(idOrigine, idDestinationNotFound));
		System.out.println("");
	}
}
