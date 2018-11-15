package Model.XMLHelpers;

import Model.Metier.DemandeLivraisons;

import java.io.File;

/**
 * Interface DemandeLivraisonsXMLHelper pour la classe DemandeLivraisonsXMLHelperDom4J
 * @author H4104
 * @see Model.XMLHelper.DemandeLivraisonsXMLHelperDom4J
 */
public interface DemandeLivraisonsXMLHelper {
    DemandeLivraisons getDemandeLivraisons(File fichierXML);
}
