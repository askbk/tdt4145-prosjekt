package trainingdiary;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // LagApparatCtrl lagApparatCtrl = new LagApparatCtrl();
        //  lagApparatCtrl.lagApparat("Romaskin", "Man ror på denne");
        //  lagApparatCtrl.fullførApparat();
        System.out.println("Hello");
        Console cons = System.console();
        String action = cons.readLine(); 
        switch(action) {
        	case "0":
        		System.out.println("valg 0");
        		break;
        	case "1":
        		System.out.println("valg 1");
        		break;
        	default:
        		System.out.println("velg 1 eller 0");
        		break;
        }
    }
    
    
}
