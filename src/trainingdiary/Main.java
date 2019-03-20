package trainingdiary;

import java.io.*;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static LagApparatCtrl lagApparatCtrl = new LagApparatCtrl();
	
    public static void main(String[] args) {
    	theloop();
    }
    
    private static void theloop() {
    	int action = -1;
    	
    	while (action != 2) {
    		System.out.println("Hva vil du gjore?\n"
            		+ "0)\tRegistrere apparat, ovelse eller treningsokt\n"
            		+ "1)\tNoe annet\n"
            		+ "2)\tAvslutt\n");
            
            action = Integer.parseInt(scanner.nextLine());
            
            switch(action) {
           		case 0:
            		registreringsMeny();
            		break;
            	case 1:
            		break;
            	case 2:
            		System.out.println("Farvel ;)");
            		return;
            	default:
            		System.out.println("ugyldig input >:(");
            		break;
           }
    	}
    }
    
    private static void registreringsMeny() {
    	int action = -1;
    	
    	while (action != 3) {
    		System.out.println("Hva vil du registrere?\n"
            		+ "0)\tApparat\n"
            		+ "1)\tOvelse\n"
            		+ "2)\tTreningsokt\n"
            		+ "3)\tIngenting - ta meg tilbake!\n");
            
            action = Integer.parseInt(scanner.nextLine());
            
            switch(action) {
       			case 0:
       				apparatRegistrering();
       				break;
       			case 1:
       				break;
       			case 2:
       				break;
       			case 3:
       				return;
       			default:
       				System.out.println("ugyldig input >:/");
       				break;
            }
    	}
    }
    
    private static void apparatRegistrering() {
    	String input = "n", navn, beskrivelse;
    	
    	while (input == "n") {
    		System.out.println("APPARATREGISTRERING\n"
            		+ "Skriv inn navn på ovelsen (blankt for å gå tilbake):\n");
            
            navn = scanner.nextLine();
            
            if (navn == "") {
            	return;
            }
            
            System.out.println("Skriv inn beskrivelsen til apparatet (blankt for å gå tilbake):\n");
            
            beskrivelse = scanner.nextLine();
            
            if (beskrivelse == "") {
            	return;
            }
            
            System.out.println("Er dette riktig?\n"
            		+ "Navn:\t" + navn + "\n"
            		+ "Beskrivelse:\t" + beskrivelse + "\n");
            
            input = scanner.nextLine();
            
            if (input == "y") {
            	lagApparatCtrl.lagApparat(navn, beskrivelse);
                lagApparatCtrl.fullforApparat();
            	break;
            }
    	}
    }
}
