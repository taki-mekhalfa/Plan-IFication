package Model.XMLHelpers;

import Model.Metier.NoeudFactory;
import Model.Metier.Plan;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.*;

public class PlanXMLHelperDom4J implements PlanXMLHelper {

    @Override
    public Plan getPlan(File fichierXML) {

        Map<String, List<Plan.Troncon>> plan = null;
        try {
            Document document = readXMLFile(fichierXML);
            plan = extrairePlan(document);
        } catch (DocumentException exp) {
            System.out.println(exp.getMessage());
        }

        return new Plan(plan);
    }

    private Document readXMLFile(File fichierXML ) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(fichierXML);
    }

    private Map<String, List<Plan.Troncon>> extrairePlan(Document document) {
        Element reseau = document.getRootElement();
        Map<String, List<Plan.Troncon>> plan = new HashMap<>();

        // Extraire les noeuds:
        for (Iterator<Element> noeudsIterator = reseau.elementIterator("noeud"); noeudsIterator.hasNext(); ) {
            Element noeudElement = noeudsIterator.next();
            String id = noeudElement.attributeValue("id");
            double latitude = Double.parseDouble(noeudElement.attributeValue("latitude"));
            double longitude = Double.parseDouble(noeudElement.attributeValue("longitude"));
            NoeudFactory.makeNoeud(id, latitude, longitude);
            plan.putIfAbsent(id, new LinkedList<>());
        }

        //Extraire les troncon:

        for (Iterator<Element> tronconIterator = reseau.elementIterator("troncon"); tronconIterator.hasNext(); ) {
            Element tronconElement = tronconIterator.next();
            String idDestination = tronconElement.attributeValue("destination");
            double longueur = Double.parseDouble(tronconElement.attributeValue("longueur"));
            String idOrigine = tronconElement.attributeValue("origine");
            String nomDeLaRue = tronconElement.attributeValue("nomRue");
            Plan.Troncon troncon = new Plan.Troncon(idOrigine, idDestination, longueur, nomDeLaRue);

            //Ajouter le troncon au noeud d'origine
            plan.get(idOrigine).add(troncon);

        }

        return plan;
    }
}
