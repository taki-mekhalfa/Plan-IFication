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

public class DemandeLivraisonsXMLHelperDom4J implements DemandeLivraisonsXMLHelper {

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

    private Document readXMLFile(File fichierXML) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(fichierXML);
    }

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
        Temps heureDep = new Temps (0,0,0);
        try{
        	int h = Integer.parseInt(heureDepart[0]);
        	int m = Integer.parseInt(heureDepart[1]);
        	int s = Integer.parseInt(heureDepart[2]);
        	if(!(h > 23 || h < 0 || m > 59 || m <0 || s > 59|| s < 0))
        		heureDep = new Temps(h, m, s);
        }
        catch (NumberFormatException e){
        	throw new DocumentException("Erreur dans le fichier xml de la demande de livraison");
        }
        return new DemandeLivraisons(idEntrepot, pointsDeLivraisons, heureDep);
    }

}
