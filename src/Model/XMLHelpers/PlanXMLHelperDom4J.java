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
            return null;
        }

        return new Plan(plan);
    }

    private Document readXMLFile(File fichierXML ) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(fichierXML);
    }
    
    //... Si un id de noeud est présent 2 fois dans le fichier, seul le premier rencontré est gardé en mémoire, il n'y a pas d'erreur d'indiquée.
    private Map<String, List<Plan.Troncon>> extrairePlan(Document document) throws DocumentException {
        Element reseau = document.getRootElement();
        if(reseau.getQualifiedName()!= "reseau"){
        	throw new DocumentException("Erreur dans le fichier xml du plan");
        }
        Map<String, List<Plan.Troncon>> plan = new HashMap<>();

        // Extraire les noeuds:
        for (Iterator<Element> noeudsIterator = reseau.elementIterator("noeud"); noeudsIterator.hasNext(); ) {
            Element noeudElement = noeudsIterator.next();
            String id;
            double latitude;
            double longitude;
            try{
            	id = noeudElement.attributeValue("id");
            	latitude = Double.parseDouble(noeudElement.attributeValue("latitude"));
                longitude = Double.parseDouble(noeudElement.attributeValue("longitude"));
                NoeudFactory.makeNoeud(id, latitude, longitude);
                plan.putIfAbsent(id, new LinkedList<>());
            }
            catch (NullPointerException e){
            	throw new DocumentException("Erreur dans le fichier xml du plan");
            } 
            catch (NumberFormatException e){
            	throw new DocumentException("Erreur dans le fichier xml du plan");
            }
            
        }

        //Extraire les troncon:
        for (Iterator<Element> tronconIterator = reseau.elementIterator("troncon"); tronconIterator.hasNext(); ) {
            Element tronconElement = tronconIterator.next();
            String idDestination;
            double longueur;
            String idOrigine;
            String nomDeLaRue;
            try{
            	idDestination = tronconElement.attributeValue("destination");
                longueur = Double.parseDouble(tronconElement.attributeValue("longueur"));
                idOrigine = tronconElement.attributeValue("origine");
                nomDeLaRue = tronconElement.attributeValue("nomRue");
                if(idDestination == null || longueur <-1 || idOrigine == null || nomDeLaRue == null){
                	throw new DocumentException("Erreur dans le fichier xml du plan");
                }
                if(plan.get(idOrigine) == null || plan.get(idDestination)== null){
                	throw new DocumentException("Erreur dans le fichier xml du plan");
                }
                Plan.Troncon troncon = new Plan.Troncon(idOrigine, idDestination, longueur, nomDeLaRue);
                //Ajouter le troncon au noeud d'origine
                plan.get(idOrigine).add(troncon);
            }
            catch (NullPointerException e){
            	throw new DocumentException("Erreur dans le fichier xml du plan");
            } 
            catch (NumberFormatException e){
            	throw new DocumentException("Erreur dans le fichier xml du plan");
            }
        }

        return plan;
    }
}
