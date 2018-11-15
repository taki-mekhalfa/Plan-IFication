package TestsUnitaires;

import Model.Metier.DemandeLivraisons;
import Model.XMLHelpers.DemandeLivraisonsXMLHelperDom4J;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TestDlXMLHelper {

    private final static String cheminPetitDl = "./Documents/fichiersXML2018/dl-petit-6.xml";
    private final static String cheminPetitDlNotFound = "./Documents/fichiersXML2018/dl-petit-666.xml";
    private final static String cheminDlMalConstruit = "./Documents/fichiersXML2018/petitDlMalConstruit.xml";
    private final static String cheminDlMalConstruit2 = "./Documents/fichiersXML2018/petitDlMalConstruit2.xml";
    private final static String cheminDlMalConstruit3 = "./Documents/fichiersXML2018/petitDlMalConstruit3.xml";
    private static DemandeLivraisonsXMLHelperDom4J dl = new DemandeLivraisonsXMLHelperDom4J();
    private static File fichierPetitDlNotFound = new File(cheminPetitDlNotFound);
    private static File fichierDlMalConstruit = new File(cheminDlMalConstruit);
    private static File fichierDlMalConstruit2 = new File(cheminDlMalConstruit2);
    private static File fichierDlMalConstruit3 = new File(cheminDlMalConstruit3);

    @Test
    public void testChargerFichierNotFound() {
        DemandeLivraisons dlTest = dl.getDemandeLivraisons(fichierPetitDlNotFound);
        assertEquals(dlTest, null);
    }

    @Test
    public void testChargerFichierMalConstruit() {
        DemandeLivraisons dlTest = dl.getDemandeLivraisons(fichierDlMalConstruit);
        assertEquals(dlTest, null);
        System.out.println("testChargerFichierMalConstruit");
    }

    @Test
    public void testChargerFichierMalConstruit2() {
        DemandeLivraisons dlTest = dl.getDemandeLivraisons(fichierDlMalConstruit2);
        assertEquals(dlTest, null);
        System.out.println("testChargerFichierMalConstruit2");
    }

    @Test
    public void testChargerFichierMalConstruit3() {
        DemandeLivraisons dlTest = dl.getDemandeLivraisons(fichierDlMalConstruit3);
        assertEquals(dlTest, null);
        System.out.println("testChargerFichierMalConstruit3");
    }
}
