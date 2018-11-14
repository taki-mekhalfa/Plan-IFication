package Model.XMLHelpers;

import Model.Metier.DemandeLivraisons;

import java.io.File;

/**
 * Interface DemandeLivraisonsXMLHelper pour la classe DemandeLivraisonsXMLHelperDom4J
 * @author mleral
 * @see Model.XMLHelper.DemandeLivraisonsXMLHelperDom4J
 */
public interface DemandeLivraisonsXMLHelper {
    DemandeLivraisons getDemandeLivraisons(File fichierXML);
}
