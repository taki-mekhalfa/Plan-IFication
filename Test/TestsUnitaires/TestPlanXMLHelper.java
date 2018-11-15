package TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.DemandeLivraisons;
import Model.Metier.Plan;
import Model.XMLHelpers.*;

public class TestPlanXMLHelper {

	private static PlanXMLHelperDom4J pl=new PlanXMLHelperDom4J();
	private final static String cheminPetitPlan="./Documents/fichiersXML2018/petitPlan.xml";
	private final static String cheminPetitPlanNotFound="./Documents/fichiersXML2018/petitPlan1.xml";
	private final static String cheminPlanMalConstruit="./Documents/fichiersXML2018/petitPlanMalConstruit.xml";
	private final static String cheminPlanMalConstruit2="./Documents/fichiersXML2018/petitPlanMalConstruit2.xml";
	private static File fichierPetitPlan=new File(cheminPetitPlan);
	private static File fichierPetitPlanNotFound=new File(cheminPetitPlanNotFound);
	private static File fichierPlanMalConstruit=new File(cheminPlanMalConstruit);
	private static File fichierPlanMalConstruit2=new File(cheminPlanMalConstruit2);
	@Test
	public void testChargerFichier(){
		Plan planTest=pl.getPlan(fichierPetitPlan);
		assertNotEquals(planTest,null);
		System.out.println("testChargerFichier");
	}
	@Test
	public void testChargerFichierNotFound(){
		Plan planTest=pl.getPlan(fichierPetitPlanNotFound);
		assertEquals(planTest,null);
		System.out.println("testChargerFichierNotFound");
	}
	@Test
	public void testChargerFichierMalConstruit(){
		Plan planTest=pl.getPlan(fichierPlanMalConstruit);
		assertEquals(planTest,null);
		System.out.println("testChargerFichierMalConstruit");
	}
	@Test
	public void testChargerFichierMalConstruit2(){
		Plan planTest=pl.getPlan(fichierPlanMalConstruit2);
		assertEquals(planTest,null);
		System.out.println("testChargerFichierMalConstruit2");
	}
}
