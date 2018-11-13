package Model.XMLHelpers;

import Model.Metier.DemandeLivraisons;
import Model.Metier.Livraison;
import Model.Metier.Temps;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant la gestion des fichiers XML liés aux demandes de livraisons.
 * @author mleral
 * @see Model.XMLHelper.DemandeLivraisonsXMLHelper
 */
public class DemandeLivraisonsXMLHelperDom4J implements DemandeLivraisonsXMLHelper {

	/**
	 * Méthode d'obtention des demandes de livraisons.
	 * @param fichierXML correspondant au fichier XML contenant les demandes de livraisons
	 * @return demandeLivraisons correspondant à la serie de demandes de livraisons analysée
	 */
    @Override
    public DemandeLivraisons getDemandeLivraisons(File fichierXML) {
        DemandeLivraisons demandeLivraisons = null;
        try {
            Document document = readXMLFile(fichierXML);
            demandeLivraisons = extraireDemande(document);
        } catch (DocumentException exp) {
            System.out.println(exp.getMessage());
        }
        return demandeLivraisons;
    }

    /**
     * Méthode de lecture du fichier XML.
     * @param fichierXML correspondant à un fichier XML à lire
     * @return document correspondant au fichier XML analysé et passé en ce format
     * @throws DocumentException
     */
    private Document readXMLFile(File fichierXML) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(fichierXML);
    }

    /**
     * Méthode d'extraction des demandes de livraisons à partir du document.
     * @param document correspondant aux éléments du fichier XML extraits
     * @return demandeLivraison correspondant au document fournis mais organiser dans le format de données choisis.
     */
    private DemandeLivraisons extraireDemande(Document document) {
        Element root = document.getRootElement();

        // Extraire l'entrepot:

        Element entrepotElement = root.elements("entrepot").get(0);
        String idEntrepot = entrepotElement.attributeValue("adresse");
        String[] heureDepart = entrepotElement.attributeValue("heureDepart").split(":");

        //Extraire les points de livraison:
        List<Livraison> pointsDeLivraisons = new LinkedList<>();
        for (Iterator<Element> pointsLivraisonIterator = root.elementIterator("livraison"); pointsLivraisonIterator.hasNext(); ) {
            Element pointLivraisonElement = pointsLivraisonIterator.next();
            String idNoeud = pointLivraisonElement.attributeValue("adresse");
            int duree = Integer.parseInt(pointLivraisonElement.attributeValue("duree"));
            Livraison livraison = new Livraison(idNoeud, duree);
            pointsDeLivraisons.add(livraison);

        }

        // Creer la demande de livraisons:

        Temps heureDep = new Temps(Integer.parseInt(heureDepart[0]), Integer.parseInt(heureDepart[1]), Integer.parseInt(heureDepart[2]));
        return new DemandeLivraisons(idEntrepot, pointsDeLivraisons, heureDep);
    }

}
