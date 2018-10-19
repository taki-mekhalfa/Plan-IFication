package Model.Metier;

public class Temps {
    private int heures, minutes, secondes;

    public Temps(int heures, int minutes, int secondes) {
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
    }

    public int getHeures() {
        return heures;
    }

    public int getMinutes() {
        return minutes;
    }

    public void addConvert(double temps){
    	int h;
    	int m;
    	int s;
    	h= (int)(temps/3600);
    	m= (int)((temps-h*60)/60);
    	s= (int)((temps-m*60-h*3600));
    	this.heures += h;
        this.minutes += m;
        this.secondes += s;
    }
    
    public int getSecondes() {
        return secondes;
    }

    @Override
    public String toString() {
        return heures + ":" + minutes + ":" + secondes;
    }
}
