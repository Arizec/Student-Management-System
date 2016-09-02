package MENU;

import java.util.Scanner;

/**
 * Created by Martin on 2/09/2016.
 */
public class CoordinatorMenu {

    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|            Coordinator           |");
        System.out.println("|               Menu               |");
        System.out.println("+----------------------------------+");

    }

    private void menuOptions(){
        System.out.println("\n1. Define Programs");
        System.out.println("2. Add Student Account(s)");
        System.out.println("3. Upload Enrolment(s)");
        System.out.println("4. Enter Student Information");
        System.out.println("5. View Results of Student(s)");
    }

    private void performChoices(int choice){
        switch (choice){
            case 1:
                System.out.println("Define Programs");
                break;
            case 2:
                System.out.println("Add Student Account(s)");
                break;
            case 3:
                System.out.println("Upload Enrolment(s)");
                break;
            case 4:
                System.out.println("Enter Student Information");
                break;
            case 5:
                System.out.println("View Results of Student(s)");
                break;
            default:
                System.out.println("4");
                break;

        }
    }

    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 5){										// catches exception if not a number between 0 and 12.
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
