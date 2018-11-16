package Test.TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

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
	@BeforeClass
	public static void beforeClass() {
		System.out.println("----------------------------------------------");
		System.out.println("--------------Test Plan XMLHelper-------------");
		System.out.println("----------------------------------------------");
	}
	@After
	public void after() {
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
	}
	@AfterClass
	public static void clean() {
		pl=null;
	}
	
	@Test
	public void testChargerFichier(){
		System.out.println("test Charger Bon Fichier:");
		pl.getPlan(fichierPetitPlan);
		System.out.println("C'est un Bon Fichier:");
		
	}
	@Test
	public void testFichierNotFound(){
		System.out.println("test Fichier Not Found:");
		pl.getPlan(fichierPetitPlanNotFound);
		
	}
	@Test
	public void testFichierNoeudNullPointerException(){
		System.out.println("test Fichier Noeud NullPointerException:");
		pl.getPlan(fichierPlanNoeudNullPointerException);
		
	}
	@Test
	public void testFichierNoeudNumberFormatException(){
		System.out.println("test Fichier Noeud NumberFormatException:");
		pl.getPlan(fichierPlanNoeudNumberFormatException);
		
	}
	@Test
	public void testFichierBaliseFausse(){
		System.out.println("test Fichier Balise Fausse:");
		pl.getPlan(fichierPlanBaliseFausse);
		
	}
	@Test
	public void testFichierTronconManqueAttribut(){
		System.out.println("test Fichier Troncon Manque Attribut:");
		pl.getPlan(fichierPlanTronconManqueAttribut);
		
	}
	@Test
	public void testFichierTronconNullPointerExecption(){
		System.out.println("test Fichier Troncon NullPointerExecption:");
		pl.getPlan(fichierPlanTronconNullPointerExecption);
		
	}
	@Test
	public void testFichierTronconNull(){
		System.out.println("test Fichier Troncon Null:");
		pl.getPlan(fichierPlanTronconNull);
		
	}
	@Test
	public void testFichierTronconNumberFormatExecption(){
		System.out.println("test Fichier Troncon NumberFormatExecption:");
		pl.getPlan(fichierPlanTronconNumberFormatExecption);
		
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("");
	} 
}
