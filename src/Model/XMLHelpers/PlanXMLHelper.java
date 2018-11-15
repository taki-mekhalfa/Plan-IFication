package Model.XMLHelpers;

import Model.Metier.Plan;

import java.io.File;

public interface PlanXMLHelper {
    public Plan getPlan(File fichierXML);
}
