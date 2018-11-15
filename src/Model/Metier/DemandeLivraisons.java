package Model.Metier;

import java.util.List;

public class DemandeLivraisons {
    private final String idEntrepot;
    private final List<Livraison> pointsDeLivraisons;
    private final Temps heureDeDepart;

    /**
     * constructeur
     * @param idEntrepot id de l'entrepot
     * @param pointsDeLivraisons liste des livraisons
     * @param heureDeDepart heure de depart
     */
    public DemandeLivraisons(String idEntrepot, List<Livraison> pointsDeLivraisons, Temps heureDeDepart) {
        this.idEntrepot = idEntrepot;
        this.pointsDeLivraisons = pointsDeLivraisons;
        this.heureDeDepart = heureDeDepart;
    }

    /**
     * getter de l'entrepot
     * @return id de l'entrepot
     */
    public String getEntrepot() {
        return idEntrepot;
    }

    /**
     * getter de la liste des livraisons
     * @return liste des livraisons
     */
    public List<Livraison> getPointsDeLivraisons() {
        return pointsDeLivraisons;
    }

    /**
     * getter de l'heure de depart
     * @return heure de depart
     */
    public Temps getHeureDeDepart() {
        return heureDeDepart;
    }

    @Override
    public String toString() {
        return "DemandeLivraisons{" +
                "idEntrepot='" + idEntrepot + '\'' +
                ", pointsDeLivraisons=" + pointsDeLivraisons +
                ", heureDeDepart=" + heureDeDepart +
                '}';
    }
}
