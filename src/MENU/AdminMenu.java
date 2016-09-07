package MENU;

import java.util.*;

import Driver.*;

/**
 * Created by Martin on 1/09/2016.
 */
public class AdminMenu {

    Driver driverClass = new Driver();
    ProgramDriver programClass = new ProgramDriver();


    /*
     * Prints header of menu
     */
    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|          Study Progress        |");
        System.out.println("|             System             |");
        System.out.println("+--------------------------------+");

    }

    /*
     * Prints menu options available to the user
     */
    private void menuOptions(){
        System.out.println("\n1. Create Student");
        System.out.println("2. Create Program");
        System.out.println("3. View Program Details");
        System.out.println("4. Exit");
    }

    /*
     * Performs choice entered at menu
     */
    private void performChoices(int choice){
        switch (choice){
            // creates student, which includes their login info and personal info
            case 1:
               Scanner reader = new Scanner(System.in);
            	System.out.println("How many student accounts would you like to add?");

                //stores amount of student accounts that admin wants to create
            	int numberOfAccounts = reader.nextInt();
            	
            	int i;
                //creates as many student accounts according to amount admin entered
            	for(i=0; i<numberOfAccounts; i++){
            			String studentIDcreated = driverClass.createStudentLogin();        // makes sure it uses the studentID it created previously
            			driverClass.createStudent(studentIDcreated);            			
                };
            	break;

            //creates program
            case 2:
                driverClass.createProgram();
                break;

            //View program details
            case 3:
                programClass.refreshProgramCourses();
                programClass.printPrograms();
                //print

                break;

            //exit the program
            case 4:
                System.out.println("Exit");
                break;

            //if invalid selection exits the menu
            //is unlikely to occur however
            default:
                System.out.println("4");
                break;

        }


    }

    /*
     * Checks validity of choice entered at menu. If invalid, asks user again
     * until valid choice is entered.
     */
    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 5){										// catches exception if not a number between 0 and 12.
            try {
                //User enteres their choice
                System.out.print("\nEnter Your Choice: ");
                choice = Integer.parseInt(input.nextLine());
            }

                // Choice is invalid
            catch(NumberFormatException e){
                System.out.println("Invalid Selection. Please Try Again");
            }
        }
        //returns choice if valid
        return choice;
    }





    /*
     * Runs every function above
     */
    public void runMenu(){
        programClass.initializeProgramANDCourses();

        while(true){
            printHeader();
            menuOptions();
            int choice = getInput();
            performChoices(choice);
        }

    }





}
