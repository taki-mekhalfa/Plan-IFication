package Test.TestsUnitaires;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Planification;
import Model.Metier.DemandeLivraisons;
import Model.XMLHelpers.*;

public class TestDlXMLHelper {

	private static DemandeLivraisonsXMLHelperDom4J dl=new DemandeLivraisonsXMLHelperDom4J();
	private final static String cheminPetitDl="./Documents/fichiersXML2018/dl-petit-6.xml";
	private final static String cheminPetitDlNotFound="./Documents/fichiersXML2018/dl-petit-666.xml";
	private final static String cheminDlBaliseFausse="./Documents/fichiersXML2018/petitDlBaliseFausse.xml";
	private final static String cheminDlEntrepotManqueAttribut="./Documents/fichiersXML2018/petitDlEntrepotManqueAttribut.xml";
	private final static String cheminDlLivraisonNumberFormatException="./Documents/fichiersXML2018/petitDlLivraisonNumberFormatException.xml";
	private final static String cheminDlTempsNumberFormatException="./Documents/fichiersXML2018/petitDlTempsNumberFormatException.xml";
	
	private static File fichier;
	@BeforeClass
	public static void before() {
		System.out.println("----------------------------------------------");
		System.out.println("--------------Test DlXMLHelper----------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testChargerFichierDl(){
		System.out.println("testChargerFichierDl:");
		fichier=new File(cheminPetitDl);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertNotEquals(dlTest,null);
		System.out.println("C'est bien charge!");
	}
	@Test
	public void testFichierDlNotFound(){
		System.out.println("testFichierDlNotFound:");
		fichier=new File(cheminPetitDlNotFound);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		
	}
	@Test
	public void testFichierDlBaliseFausse(){
		System.out.println("testFichierDlBaliseFausse:");
		fichier=new File(cheminDlBaliseFausse);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		
	}
	@Test
	public void testFichierDlManqueAttribut(){
		System.out.println("testFichierDlEntrepotManqueAttribut:");
		fichier=new File(cheminDlEntrepotManqueAttribut);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		
	}
	@Test
	public void testFichierDlLivraisonNumberFormatException(){
		System.out.println("testFichierDlLivraisonNumberFormatException£º");
		fichier=new File(cheminDlLivraisonNumberFormatException);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		
	}
	@Test
	public void testFichierDlTempsNumberFormatException(){
		System.out.println("testFichierDlTempsNumberFormatException£º");
		fichier=new File(cheminDlTempsNumberFormatException);
		dl.getDemandeLivraisons(fichier);
		
	}
	@After
	public void clean(){
		fichier=null;
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
		System.out.println("");
	}
}
