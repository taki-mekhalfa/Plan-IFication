package Model.Metier;

import java.util.List;

/**
 * Classe métier pour la gestion des demandes de livraisons.
 * @author H4104
 */
public class DemandeLivraisons {
    private final String idEntrepot;
    private final List<Livraison> pointsDeLivraisons;
    private final Temps heureDeDepart;

    /**
     * Constructeur de la classe DemandeLivraisons
     * @param idEntrepot correspondant à l'identifiant de l'entrepôt à considérer
     * @param pointsDeLivraisons correspondant à la liste des livraisons à effectuer
     * @param heureDeDepart correspondant à l'heure de départ
     * @see Model.Metier.Livraison
     */
    public DemandeLivraisons(String idEntrepot, List<Livraison> pointsDeLivraisons, Temps heureDeDepart) {
        this.idEntrepot = idEntrepot;
        this.pointsDeLivraisons = pointsDeLivraisons;
        this.heureDeDepart = heureDeDepart;
    }

    /**
     * Méthode d'obtention de l'entrepôt.
     * @return idEntrepot correspondant à l'identifiant dde l'entrepôt 
     */
    public String getEntrepot() {
        return idEntrepot;
    }

    /**
     * Méthode d'obtention des points de livraisons.
     * @return pointsDeLivraisons correspondant à une liste de livraisons correspondant aux points de livraisons de cette demande
     * @see Model.Metier.Livraison
     */
    public List<Livraison> getPointsDeLivraisons() {
        return pointsDeLivraisons;
    }

    /**
     * Méthode d'obtention de l'heure de départ.
     * @return heureDeDepart correspondant à l'heure de départ sous le format Temps
     * @see Model.Metier.Temps 
     */
    public Temps getHeureDeDepart() {
        return heureDeDepart;
    }

    /**
     * Méthode de conversion en string.
     * @return Livraison correspondant à une description de la demande de livraisons et de ses composantes
     */
    @Override
    public String toString() {
        return "DemandeLivraisons{" +
                "idEntrepot='" + idEntrepot + '\'' +
                ", pointsDeLivraisons=" + pointsDeLivraisons +
                ", heureDeDepart=" + heureDeDepart +
                '}';
    }
}
