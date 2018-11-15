package Model.Metier;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe de gestion des heures dans le cadre de l'application.
 * @author H4104
 */
public class Temps implements Comparable{
    private final SimpleStringProperty horraire;
    private int heures, minutes, secondes;
    private int value;

    /**
     * Constructeur de la classe Temps.
     * @param heures correspondant au nombre d'heures sous forme entière
     * @param minutes correspondant au nombre de minutes sous forme entière
     * @param secondes correspondant au nombre de secondes sous forme entière
     */
    public Temps(int heures, int minutes, int secondes) {
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        horraire = this.PropertytoString();
        value = heures * 3600 + minutes * 60 + secondes;
    }

    /**
     * Méthode d'ajout d'un temps en seconde à un autre temps .
     * @param debut correspondant au temps sous forme d'objet
     * @param secondesAajouter correspondant à la durée à ajouter en seconde
     * @return Temps correspondant au nouveau temps ainsi obtenu
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
     * Méthode d'obtention du nombre d'heure associé au temps.
     * @return heures correspondant à ce nombre d'heures sous forme entière
     */
    public int getHeures() {
        return heures;
    }

    /**
     * Méthode d'obtention du nombre de minutes associé au temps.
     * @return minutes correspondant à ce nombre de minutes sous forme entière
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Méthode d'obtention du nombre de secondes associé au temps.
     * @return secondes correspondant à ce nombre de secondes sous forme entière
     */
    public int getSecondes() {
        return secondes;
    }

    /**
     * Méthode d'obtention de la valeur totale associé au temps.
     * @return value correspondant à la valeur totale de ce temps en secondes
     */
    public int getValue() {
        return value;
    }

    /**
     * Méthode d'obtention de la durée associée à ce temps sous forme de SimpleStringProperty.
     * @return horraire correspondant à cette SimpleStringProperty
     */
    public SimpleStringProperty getHorraireProperty() {
        return horraire;
    }

    /**
     * Méthode de conversion de ce temps en string.
     * @return res correspondant à un string contenant la description totale de ce temps en heures, minutes et secondes
     */
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
     * Méthode d'obtention de la SimpleStringProperty associée à un string. 
     * @return SimpleStringProperty
     */
    public SimpleStringProperty PropertytoString() {
        return new SimpleStringProperty(toString());
    }
    
    /**
     * Méthode de comparaison
     * @return int correspondant à l'écart en heures, minutes ou secondes selon où se trouve la première différence
     */
    @Override
    public int compareTo(Object o) {
        Temps temps = (Temps) o;
        if (heures != temps.heures) return heures - temps.heures;
        if (minutes != temps.minutes) return minutes - temps.minutes;
        return secondes - temps.secondes;
    }
}
