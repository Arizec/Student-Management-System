package MENU;

import java.util.*;
import USERS.Admin;
/**
 * Created by Martin on 1/09/2016.
 */
public class AdminMenu {

    Admin useAdmin = new Admin();

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
                useAdmin.createStudentLogin();
                useAdmin.createStudent();
                break;
            case 2:
                useAdmin.createProgram();
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("4");
                break;

        }


    }


    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 3){										// catches exception if not a number between 0 and 12.
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
