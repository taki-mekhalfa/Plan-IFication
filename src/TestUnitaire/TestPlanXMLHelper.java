package TestUnitaire;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Model.Metier.Plan;
import Model.XMLHelpers.*;

public class TestPlanXMLHelper {

	private static PlanXMLHelperDom4J pl=new PlanXMLHelperDom4J();
	private final static String cheminPetitPlan="./Documents/fichiersXML2018/petitPlan.xml";
	private final static String cheminPetitPlanNotFound="./Documents/fichiersXML2018/petitPlan1.xml";
	private final static String cheminPlanNoeudNullPointerException="./Documents/fichiersXML2018/petitPlanNoeudNullPointerException.xml";
	private final static String cheminPlanNoeudNumberFormatException="./Documents/fichiersXML2018/petitPlanNoeudNumberFormatException.xml";
	private final static String cheminPlanBaliseFausse="./Documents/fichiersXML2018/petitPlanBaliseFausse.xml";
	private final static String cheminPlanTronconManqueAttribut="./Documents/fichiersXML2018/petitPlanTronconManqueAttribut.xml";
	private final static String cheminPlanTronconNullPointerExecption="./Documents/fichiersXML2018/petitPlanTronconNullPointerExecption.xml";
	private final static String cheminPlanTronconNull="./Documents/fichiersXML2018/petitPlanTronconNull.xml";
	private final static String cheminPlanTronconNumberFormatExecption="./Documents/fichiersXML2018/petitPlanTronconNumberFormatException.xml";
	
	private static File fichierPetitPlan=new File(cheminPetitPlan);
	private static File fichierPetitPlanNotFound=new File(cheminPetitPlanNotFound);
	private static File fichierPlanNoeudNullPointerException=new File(cheminPlanNoeudNullPointerException);
	private static File fichierPlanNoeudNumberFormatException=new File(cheminPlanNoeudNumberFormatException);
	private static File fichierPlanBaliseFausse=new File(cheminPlanBaliseFausse);
	private static File fichierPlanTronconManqueAttribut=new File(cheminPlanTronconManqueAttribut);
	private static File fichierPlanTronconNullPointerExecption=new File(cheminPlanTronconNullPointerExecption);
	private static File fichierPlanTronconNull=new File(cheminPlanTronconNull);
	private static File fichierPlanTronconNumberFormatExecption=new File(cheminPlanTronconNumberFormatExecption);
	
	@Test
	public void testChargerFichier(){
		Plan planTest=pl.getPlan(fichierPetitPlan);
		assertNotEquals(planTest,null);
		System.out.println("testChargerFichier");
	}
	@Test
	public void testFichierNotFound(){
		Plan planTest=pl.getPlan(fichierPetitPlanNotFound);
		assertEquals(planTest,null);
		System.out.println("testFichierNotFound");
	}
	@Test
	public void testFichierNoeudNullPointerException(){
		Plan planTest=pl.getPlan(fichierPlanNoeudNullPointerException);
		//assertEquals(planTest,null);
		System.out.println("testFichierNoeudNullPointerException");
	}
	@Test
	public void testFichierNoeudNumberFormatException(){
		Plan planTest=pl.getPlan(fichierPlanNoeudNumberFormatException);
		//assertEquals(planTest,null);
		System.out.println("testFichierNoeudNumberFormatException");
	}
	@Test
	public void testFichierBaliseFausse(){
		Plan planTest=pl.getPlan(fichierPlanBaliseFausse);
		assertEquals(planTest,null);
		System.out.println("testFichierBaliseFausse");
	}
	@Test
	public void testFichierTronconManqueAttribut(){
		Plan planTest=pl.getPlan(fichierPlanTronconManqueAttribut);
		assertEquals(planTest,null);
		System.out.println("testFichierTronconManqueAttribut");
	}
	@Test
	public void testFichierTronconNullPointerExecption(){
		Plan planTest=pl.getPlan(fichierPlanTronconNullPointerExecption);
		//assertEquals(planTest,null);
		System.out.println("testFichierTronconNullPointerExecption");
	}
	@Test
	public void testFichierTronconNull(){
		Plan planTest=pl.getPlan(fichierPlanTronconNull);
		//assertEquals(planTest,null);
		System.out.println("testFichierTronconNull");
	}
	@Test
	public void testFichierTronconNumberFormatExecption(){
		Plan planTest=pl.getPlan(fichierPlanTronconNumberFormatExecption);
		assertEquals(planTest,null);
		System.out.println("testFichierTronconNumberFormatExecption");
	}
}
