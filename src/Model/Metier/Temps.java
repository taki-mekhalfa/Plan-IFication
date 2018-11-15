package Model.Metier;

import javafx.beans.property.SimpleStringProperty;

public class Temps implements Comparable{
    private final SimpleStringProperty horraire;
    private int heures, minutes, secondes;
    private int value;

    /**
     * constructeur
     * @param heures heures
     * @param minutes minutes
     * @param secondes secondes
     */
    public Temps(int heures, int minutes, int secondes) {
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        horraire = this.PropertytoString();
        value = heures * 3600 + minutes * 60 + secondes;
    }

    /**
     * ajouter des secondes a partir du temps de debut
     * @param debut temps de debut
     * @param secondesAajouter secondes a ajouter
     * @return temps temps actuel
     */
    public static Temps addConvert(Temps debut, double secondesAajouter) {
        int h, m, s;

        int total = (int) (debut.heures * 3600 + debut.minutes * 60 + debut.secondes + secondesAajouter);

        h = (total / 3600);
        m = (total - h * 3600) / 60;
        s = total - m * 60 - h * 3600;
        return new Temps(h, m, s);
    }

    /**
     * getter heures
     * @return heures
     */
    public int getHeures() {
        return heures;
    }

    /**
     * getter minutes
     * @return minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * getter secondes
     * @return secondes
     */
    public int getSecondes() {
        return secondes;
    }

    /**
     * convertir le temps en secondes
     * @return secondes
     */
    public int getValue() {
        return value;
    }

    /**
     * getter horaire propriete
     * @return horaire
     */
    public SimpleStringProperty getHorraireProperty() {
        return horraire;
    }

    @Override
    public String toString() {
        String res;
        if(heures<10){
            res = "0"+heures;
        }else{
            res =""+heures;
        }
        if(minutes<10){
            res+=":"+"0"+minutes;
        }else{
            res+=":"+minutes;
        }
        return res;
    }

    /**
     * @return
     */
    public SimpleStringProperty PropertytoString() {
        return new SimpleStringProperty(toString());
    }
    
    @Override
    public int compareTo(Object o) {
        Temps temps = (Temps) o;
        if (heures != temps.heures) return heures - temps.heures;
        if (minutes != temps.minutes) return minutes - temps.minutes;
        return secondes - temps.secondes;
    }
}
