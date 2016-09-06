package MENU;

import java.util.*;
import USERS.Admin;
import Driver.Driver;
import PROGRAMS.*;
/**
 * Created by Martin on 1/09/2016.
 */
public class AdminMenu {

    Driver driverClass = new Driver();
    Program programClass = new Program();

    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|          Study Progress        |");
        System.out.println("|             System             |");
        System.out.println("+--------------------------------+");

    }

    private void menuOptions(){
        System.out.println("\n1. Create Student");
        System.out.println("2. Create Program");
        System.out.println("3. Upload Enrolment(s)");
    }


    private void performChoices(int choice){
        switch (choice){
            case 1:
               Scanner reader = new Scanner(System.in);
            	System.out.println("How many student accounts would you like to add?");
            	int numberOfAccounts = reader.nextInt();
            	
            	int i;
            	for(i=0; i<numberOfAccounts; i++){
            			String studentIDcreated = driverClass.createStudentLogin();        // makes sure it uses the studentID it created previously
            			driverClass.createStudent(studentIDcreated);            			
            			};
            	break;
            case 2:
                driverClass.createProgram();
                break;
            case 3:
                programClass.loadProgram();
                programClass.printPrograms();
                break;
            default:
                System.out.println("4");
                break;

        }


    }


    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 4){										// catches exception if not a number between 0 and 12.
            try {
                System.out.print("\nEnter Your Choice: ");
                choice = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Selection. Please Try Again");
            }
        }
        return choice;
    }






    public void runMenu(){

        printHeader();
        menuOptions();
        int choice = getInput();
        performChoices(choice);


    }





}
