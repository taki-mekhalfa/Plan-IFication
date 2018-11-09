package Model.Metier;

import javafx.beans.property.SimpleStringProperty;

public class Temps {
    private int heures, minutes, secondes;
    private final SimpleStringProperty horraire;
    private int value;

    public Temps(int heures, int minutes, int secondes) {
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        horraire = this.PropertytoString();
        value = heures*60*60+minutes*60+secondes;        		
    }

    public static Temps addConvert(Temps debut, double secondesAajouter) {
        int h, m, s;

        int total = (int) (debut.heures * 3600 + debut.minutes * 60 + debut.secondes + secondesAajouter);

        h = (total / 3600);
        m = (total - h * 3600) / 60;
        s = total - m * 60 - h * 3600;
        return new Temps(h, m, s);
    }

    public int getHeures() {
        return heures;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecondes() {
        return secondes;
    }
    
    public int getValue () {
    	return value;
    }
    public SimpleStringProperty getHorraireProperty() {
    	return horraire;
    }

    @Override
    public String toString() {
        return heures + ":" + minutes + ":" + secondes;
    }
    public SimpleStringProperty PropertytoString() {
    	SimpleStringProperty propriete = new SimpleStringProperty (heures + ":" + minutes + ":" + secondes);
        return propriete;
    }
}
