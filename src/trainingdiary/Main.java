package trainingdiary;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static LagApparatCtrl lagApparatCtrl = new LagApparatCtrl();
	private static LagApparatOvelseCtrl lagApparatOvelseCtrl = new LagApparatOvelseCtrl();
	private static LagFriOvelseCtrl lagFriOvelseCtrl = new LagFriOvelseCtrl();
	private static LagOktCtrl lagOktCtrl = new LagOktCtrl();
	private static LagOvelseGrupperCtrl lagOvelseGrupperCtrl = new LagOvelseGrupperCtrl();
	private static LagOvelseIGruppeCtrl lagOvelseIGruppeCtrl = new LagOvelseIGruppeCtrl();
	private static SelectionQueries queries = new SelectionQueries();
	
    public static void main(String[] args) {
    	theloop();
    }
    
    private static void theloop() {
    	int action = -1;
    	
    	while (action != 2) {
    		System.out.println("Hva vil du gjore?\n"
            		+ "0)\tRegistrere apparat, ovelse eller treningsokt\n"
            		+ "1)\tLage ovelsesgruppe\n"
            		+ "2)\tAvslutt\n");
            
            action = Integer.parseInt(scanner.nextLine());
            
            switch(action) {
           		case 0:
            		registreringsMeny();
            		break;
            	case 1:
            		ovelseGruppeMeny();
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
       				ovelseRegistrering();
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
    
    private static void ovelseGruppeMeny() {
    	int action = -1;
    	
    	while (action != 3) {
    		System.out.println("Hva vil du gjøre?\n"
            		+ "0)\tLage ovelsegruppe\n"
            		+ "1)\tSe ovelsegrupper\n"
            		+ "2)\tOvelser i antall grupper\n"
            		+ "3)\tIngenting - ta meg tilbake!\n");
            
            action = Integer.parseInt(scanner.nextLine());
            
            switch(action) {
       			case 0:
       				ovelseGruppeRegistrering();
       				break;
       			case 1:
       				ovelseGruppeOversikt();
       				break;
       			case 2:
       				ovelseGruppeAntall();
       				break;
       			case 3:
       				return;
       			default:
       				System.out.println("ugyldig input >:/");
       				break;
            }
    	}
    }
    
    private static void ovelseGruppeOversikt() {
    	QueryResult ovelseGrupper = queries.getOvelseGrupper();
    	int action = -10;
    	
    	for(List<String> row : ovelseGrupper) {
			System.out.println(row.get(0) + ")\t" + row.get(1));
		}
    	
    	action = Integer.parseInt(scanner.nextLine());
    	
    	while (action != -1) {
    		if (ovelseGrupper.getResult(action) != null) {
    			ovelseIGruppeMeny(action);
    		}
    	}
    }
    private static void ovelseGruppeAntall() {
    	QueryResult OvelseGruppeAntall = queries.getOvelseGruppeAntall();
    	int action = -10;
    	
    	for(List<String> row : OvelseGruppeAntall) {
			System.out.println(row.get(0) + ")\t" + row.get(1));
		}
    	
    	action = Integer.parseInt(scanner.nextLine());
    	
    	while (action != -1) {
    		if (OvelseGruppeAntall.getResult(action) != null) {
    			ovelseIGruppeMeny(action);
    		}
    	}
    }
    
    private static void apparatRegistrering() {
    	String input = "n", navn, beskrivelse;
    	
    	while (input.equals("n")) {
    		System.out.println("APPARATREGISTRERING\n"
            		+ "Skriv inn navn på apparatet (blankt for å gå tilbake):\n");
            
            navn = scanner.nextLine();
            
            if (navn.equals("")) {
            	return;
            }
            
            System.out.println("Skriv inn beskrivelsen til apparatet (blankt for å gå tilbake):\n");
            
            beskrivelse = scanner.nextLine();
            
            if (beskrivelse.equals("")) {
            	return;
            }
            
            System.out.println("Er dette riktig?\n"
            		+ "Navn:\t" + navn + "\n"
            		+ "Beskrivelse:\t" + beskrivelse + "\n");
            
            input = scanner.nextLine();
            
            if (input.equals("y")) {
            	lagApparatCtrl.lagApparat(navn, beskrivelse);
                lagApparatCtrl.fullforApparat();
            	break;
            }
    	}
    }
    
    private static void ovelseRegistrering() {
    	QueryResult apparater = new QueryResult();
    	String input = "n", navn, beskrivelse ="Ingen beskrivelse.";
    	int type = 0, apparatId = -1;
    	
    	while (input.equals("n")) {
    		System.out.println("OVELSEREGISTRERING\n"
            		+ "Skriv inn navn på ovelsen (blankt for å gå tilbake):\n");
            
            navn = scanner.nextLine();
            
            if (navn.equals("")) {
            	return;
            }
            
            System.out.println("Er øvelsen en apparatovelse? (y/n):\n");
            
            input = scanner.nextLine();
            
            if (input.equals("y")) {
            	type = 1;
            	apparater = queries.getApparater();
            	System.out.println("Hvilket apparat bruker ovelsen?:\n");
            	
            	for(List<String> apparatRow : apparater) {
            		System.out.println(apparatRow.get(0) + ")\t" + apparatRow.get(1));
            	}
                
                apparatId = Integer.parseInt(scanner.nextLine());
                
                lagApparatOvelseCtrl.lagApparatOvelse(navn, apparatId);
            } else {
            	System.out.println("Skriv inn beskrivelse for friovelse\n");
                
                beskrivelse = scanner.nextLine();
            }
            
            if (type == 0) {
        		lagFriOvelseCtrl.lagFriOvelse(navn, beskrivelse);
        	} else {
        		lagApparatOvelseCtrl.lagApparatOvelse(navn, apparatId);
        	}
            
            System.out.println("Er dette riktig?");
            if (type == 0) {
            	System.out.println(lagFriOvelseCtrl.toString());
            } else {
            	System.out.println(lagApparatOvelseCtrl.toString() + "\n" + apparater.getResult(apparatId).get(1));
            }
            
            input = scanner.nextLine();
            
            if (input.equals("y")) {
            	if (type == 0) {
                	lagFriOvelseCtrl.fullforFriOvelse();
                } else {
                	lagApparatOvelseCtrl.fullforApparatOvelse();
                }
            	
            	return;
            }
    	}
    }
    
    private static void ovelseGruppeRegistrering() {
    	String input = "n", navn;
    	
    	while (input.equals("n")) {
    		System.out.println("OVELSEGRUPPEREGISTRERING\n"
            		+ "Skriv inn navn på ovelsegruppen (blankt for å gå tilbake):\n");
            
            navn = scanner.nextLine();
            
            if (navn.equals("")) {
            	return;
            }
            
            lagOvelseGrupperCtrl.lagOvelseGrupper(navn);
            
            System.out.println("Er dette riktig?\n"
            				+ lagOvelseGrupperCtrl.toString());
            
            input = scanner.nextLine();
            
            if (input.equals("y")) {
            	lagOvelseGrupperCtrl.fullforOvelseGrupper();
            	return;
            }
    	}
    }

    private static void ovelseIGruppeMeny(int gruppeId) {
    	int action = -1;
    	System.out.println("hello");
    	while (action != 3) {
    		System.out.println("Hva vil du gjøre?\n"
            		+ "0)\tSe ovelser\n"
            		+ "1)\tLegge til ovelser\n"
            		+ "2)\tIngenting - ta meg tilbake!\n");
            
            action = Integer.parseInt(scanner.nextLine());
            
            switch(action) {
       			case 0:
       				ovelseIGruppeOversikt(gruppeId);
       				break;
       			case 1:
       				ovelseIGruppeRegistrering(gruppeId);
       				break;
       			case 2:
       				return;
       			default:
       				System.out.println("ugyldig input >:/");
       				break;
            }
    	}
    }
    
    private static void ovelseIGruppeOversikt(int gruppeId) {
    	QueryResult ovelser = queries.getOvelser(gruppeId);
    	
    	for(List<String> ovelseRow : ovelser) {
    		System.out.println(ovelseRow.get(0) + ")\t" + ovelseRow.get(1));
    	}
    }
    
    private static void ovelseIGruppeRegistrering(int gruppeId) {
    	String input = "n";
    	QueryResult ovelser = queries.getOvelser(-1);
    	int ovelseId;
    	
    	for(List<String> ovelseRow : ovelser) {
    		System.out.println(ovelseRow.get(0) + ")\t" + ovelseRow.get(1));
    	}
    	
    	while (input.equals("n")) {
    		System.out.println("OVELSEIGRUPPEREGISTRERING\n"
            		+ "Skriv inn ID på ovelsen du vil sette inn i gruppen (blankt for å gå tilbake):\n");
            
            ovelseId = Integer.parseInt(scanner.nextLine());
            
            lagOvelseIGruppeCtrl.lagOvelseIGruppe(ovelseId, gruppeId);
            
            lagOvelseIGruppeCtrl.fullforOvelseIGruppe();
            return;
    	}
    }
}
