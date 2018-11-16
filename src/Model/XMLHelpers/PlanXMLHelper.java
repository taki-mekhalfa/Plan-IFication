package Model.XMLHelpers;

import Model.Metier.Plan;

import java.io.File;

/**
 * Interface PlanXMLHelper pour la classe PlanXMLHelperDom4J
 * @author H4104
 * @see PlanXMLHelperDom4J
 */
public interface PlanXMLHelper {
    public Plan getPlan(File fichierXML);
}
