package Model.XMLHelpers;

import Model.Metier.DemandeLivraisons;

import java.io.File;

public interface DemandeLivraisonsXMLHelper {
    DemandeLivraisons getDemandeLivraisons(File fichierXML);
}
