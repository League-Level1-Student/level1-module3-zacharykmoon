package _01_intro_to_static;


public class Athlete {
    static int nextBibNumber;
    static String raceLocation = "New York";
    static String raceStartTime = "9.00am";

    String name;
    int speed;
    public int bibNumber=1;
   

   public  Athlete (String name, int speed){
        this.name = name;
        this.speed = speed;
        this.bibNumber=nextBibNumber;
        nextBibNumber++;
        
    }

    public static void main(String[] args) {
        //create two athletes
        //print their names, bibNumbers, and the location of their race.
    }
  
    		
    		
    	}
    }
}