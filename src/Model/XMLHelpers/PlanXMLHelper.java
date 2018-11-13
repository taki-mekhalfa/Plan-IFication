package Model.XMLHelpers;

import Model.Metier.Plan;

import java.io.File;

/**
 * Interface PlanXMLHelper pour la classe PlanXMLHelperDom4J
 * @author mleral
 * @see Model.XMLHelper.PlanXMLHelperDom4J
 */
public interface PlanXMLHelper {
    public Plan getPlan(File fichierXML);
}
