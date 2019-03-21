package trainingdiary;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static LagApparatCtrl lagApparatCtrl = new LagApparatCtrl();
	private static LagApparatOvelseCtrl lagApparatOvelseCtrl = new LagApparatOvelseCtrl();
	private static LagFriOvelseCtrl lagFriOvelseCtrl = new LagFriOvelseCtrl();
	private static LagOktCtrl lagOktCtrl = new LagOktCtrl();
	private static SelectionQueries queries = new SelectionQueries();

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

		private static void oktRegistrering() {
			String input = "n", navn, dato, tidspunkt, varighet ="Ingen beskrivelse.";
			int type = 0, oktId = -1, form = 0, prestasjon = 0;

			while (input.equals("n")) {
				System.out.println("OKTREGISTRERING\n"
								+ "Skriv inn navn på okten (blankt for å gå tilbake):\n"); // navn eller id?

						navn = scanner.nextLine();

						if (navn.equals("")) {
							return;
						}

						System.out.println("Skriv inn dato på Okten:\n");

						dato = scanner.nextLine();

						if (dato.equals("")) {
							return;
						}

						System.out.println("Skriv inn tidspunkt på Okten:\n");

						tidspunkt = scanner.nextLine();

						if (tidspunkt.equals("")) {
							return;
						}

						System.out.println("Skriv inn varighet på Okten:\n");

						varighet = scanner.nextLine();

						if (varighet.equals("")) {
							return;
						}

						form = scanner.nextInt();

						prestasjon = scanner.nextInt();

						System.out.println("Er dette riktig?(y/n)\n"
            		+ "Navn:\t" + dato + "\n"
            		+ "dato:\t" + navn + "\n"
								+ "Tidspunkt:\t" + tidspunkt + "\n"
								+ "Varighet:\t" + varighet + "\n"
								+ "Form:\t" + form + "\n"
								+ "Prestasjon:\t" + prestasjon + "\n");

						input = scanner.nextLine();
						if (input.equals("y")) {
            	lagOktCtrl.lagOkt(dato, tidspunkt, varighet, form, prestasjon);
                lagOktCtrl.fullforOkt();
            	break;


						}
			}
		}
}
