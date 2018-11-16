package Model.XMLHelpers;

import Model.Metier.DemandeLivraisons;

import java.io.File;

/**
 * Interface DemandeLivraisonsXMLHelper pour la classe DemandeLivraisonsXMLHelperDom4J
 * @author H4104
 * @see DemandeLivraisonsXMLHelperDom4J
 */
public interface DemandeLivraisonsXMLHelper {
    DemandeLivraisons getDemandeLivraisons(File fichierXML);
}
