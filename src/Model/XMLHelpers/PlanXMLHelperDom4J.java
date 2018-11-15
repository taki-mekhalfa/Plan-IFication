package Model.XMLHelpers;

import Model.Metier.NoeudFactory;
import Model.Metier.Plan;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.*;

/**
 * Classe permettant la gestion des fichiers XML liés au plan.
 * @author H4104
 * @see Model.XMLHelper.PlanXMLHelper
 */
public class PlanXMLHelperDom4J implements PlanXMLHelper {

	/**
	 * Méthode d'obtention du plan à partir du fichier XML.
	 * @return plan correspondant au plan obtenu à partir du fichier XML
	 */
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

    /**
     * Méthode de lecture du fichier XML
     * @param fichierXML correspondant à l'élément à faire lire par la méthode
     * @return Document correspondant au fichier XML lus
     * @throws DocumentException
     */
    private Document readXMLFile(File fichierXML) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(fichierXML);
    }
    
    //... Si un id de noeud est présent 2 fois dans le fichier, seul le premier rencontré est gardé en mémoire, il n'y a pas d'erreur d'indiquée.
    /**
     * Méthode d'extraction du plan à partir du document
     * Si un id de noeud est présent 2 fois dans le fichier, seul le premier rencontré est gardé en mémoire, il n'y a pas d'erreur d'indiquée.
     * @param document correspondant à l'élément tiré du XML et contenant les éléments du plan
     * @return plan correspondant à la structure de donnée une fois analysée et prête à être utilisée
     * @throws DocumentException
     */
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
                if(idDestination == null || longueur < 0 || idOrigine == null || nomDeLaRue == null){
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
