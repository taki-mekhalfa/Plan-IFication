package Model.Metier;

import java.util.List;

public class DemandeLivraisons {
    private final String idEntrepot;
    private final List<Livraison> pointsDeLivraisons;
    private final Temps heureDeDepart;

    public DemandeLivraisons(String idEntrepot, List<Livraison> pointsDeLivraisons, Temps heureDeDepart) {
        this.idEntrepot = idEntrepot;
        this.pointsDeLivraisons = pointsDeLivraisons;
        this.heureDeDepart = heureDeDepart;
    }

    public String getEntrepot() {
        return idEntrepot;
    }

    public List<Livraison> getPointsDeLivraisons() {
        return pointsDeLivraisons;
    }

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
