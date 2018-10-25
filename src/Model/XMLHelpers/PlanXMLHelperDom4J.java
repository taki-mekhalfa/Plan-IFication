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

        Map<String, List<Plan.Trancon>> plan = null;
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

    private Map<String, List<Plan.Trancon>> extrairePlan(Document document) {
        Element reseau = document.getRootElement();
        Map<String, List<Plan.Trancon>> plan = new HashMap<>();

        // Extraire les noeuds:
        for (Iterator<Element> noeudsIterator = reseau.elementIterator("noeud"); noeudsIterator.hasNext(); ) {
            Element noeudElement = noeudsIterator.next();
            String id = noeudElement.attributeValue("id");
            double latitude = Double.parseDouble(noeudElement.attributeValue("latitude"));
            double longitude = Double.parseDouble(noeudElement.attributeValue("longitude"));
            NoeudFactory.makeNoeud(id, latitude, longitude);
            plan.putIfAbsent(id, new LinkedList<>());
        }

        //Extraire les trancon:

        for (Iterator<Element> tranconIterator = reseau.elementIterator("troncon"); tranconIterator.hasNext(); ) {
            Element tranconElement = tranconIterator.next();
            String idDestination = tranconElement.attributeValue("destination");
            double longueur = Double.parseDouble(tranconElement.attributeValue("longueur"));
            String idOrigine = tranconElement.attributeValue("origine");
            String nomDeLaRue = tranconElement.attributeValue("nomRue");
            Plan.Trancon trancon = new Plan.Trancon(idOrigine, idDestination, longueur, nomDeLaRue);

            //Ajouter le trancon au noeud d'origine
            plan.get(idOrigine).add(trancon);

        }

        return plan;
    }
}
