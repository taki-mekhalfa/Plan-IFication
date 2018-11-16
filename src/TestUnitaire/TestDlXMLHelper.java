package TestUnitaire;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import Model.Metier.DemandeLivraisons;
import Model.XMLHelpers.*;

public class TestDlXMLHelper {

	private static DemandeLivraisonsXMLHelperDom4J dl=new DemandeLivraisonsXMLHelperDom4J();
	private final static String cheminPetitDl="./Documents/fichiersXML2018/dl-petit-6.xml";
	private final static String cheminPetitDlNotFound="./Documents/fichiersXML2018/dl-petit-666.xml";
	private final static String cheminDlBaliseFausse="./Documents/fichiersXML2018/petitDlBaliseFausse.xml";
	private final static String cheminDlEntrepotManqueAttribut="./Documents/fichiersXML2018/petitDlEntrepotManqueAttribut.xml";
	private final static String cheminDlLivraisonNumberFormatException="./Documents/fichiersXML2018/petitDlLivraisonNumberFormatException.xml";
	private final static String cheminDlLivraisonNullPointerException="./Documents/fichiersXML2018/petitDlLivraisonNullPointerException.xml";
	private final static String cheminDlEntrepotPatternSyntaxException="./Documents/fichiersXML2018/petitDlEntrepotPatternSyntaxException.xml";
	private final static String cheminDlTempsNumberFormatException="./Documents/fichiersXML2018/petitDlTempsNumberFormatException.xml";
	
	private static File fichier;
	@Test
	public void testChargerFichierDl(){
		fichier=new File(cheminPetitDl);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertNotEquals(dlTest,null);
		System.out.println("testChargerFichierDl");
	}
	@Test
	public void testFichierDlNotFound(){
		fichier=new File(cheminPetitDlNotFound);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		System.out.println("testFichierDlNotFound");
	}
	@Test
	public void testFichierDlBaliseFausse(){
		fichier=new File(cheminDlBaliseFausse);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		System.out.println("testFichierDlBaliseFausse");
	}
	@Test
	public void testFichierDlManqueAttribut(){
		fichier=new File(cheminDlEntrepotManqueAttribut);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		System.out.println("testFichierDlEntrepotManqueAttribut");
	}
	@Test
	public void testFichierDlLivraisonNumberFormatException(){
		fichier=new File(cheminDlLivraisonNumberFormatException);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		assertEquals(dlTest,null);
		System.out.println("testFichierDlLivraisonNumberFormatException");
	}
	/*@Test
	public void testFichierDlLivraisonNullPointerException(){//parseInt 只有 numbreformat exception
		fichier=new File(cheminDlLivraisonNullPointerException);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		System.out.println("testFichierDlLivraisonNullPointerException");
	}*/
	/*@Test
	public void testFichierDlEntrepotPatternSyntaxException(){//pattern 只有 regex='+'
		fichier=new File(cheminDlEntrepotPatternSyntaxException);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		System.out.println("testFichierDlEntrepotPatternSyntaxException");
	}*/
	@Test
	public void testFichierDlTempsNumberFormatException(){//pattern 只有 regex='+'
		fichier=new File(cheminDlTempsNumberFormatException);
		DemandeLivraisons dlTest=dl.getDemandeLivraisons(fichier);
		System.out.println("testFichierDlTempsNumberFormatException");
	}
	@After
	public void clean(){
		fichier=null;
	}
}
