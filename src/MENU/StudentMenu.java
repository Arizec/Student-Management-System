package MENU;

import MENU.*;

import java.util.Scanner;

import Driver.Driver;


/**
 * Created by Martin on 2/09/2016.
 */
public class StudentMenu {
    Driver driverClass = new Driver();
    private String studentID;

    public StudentMenu(String studentID){
        this.studentID = studentID;


    }

    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|            Student               |");
        System.out.println("|              Menu                |");
        System.out.println("+----------------------------------+");

    }

    private void menuOptions(){
        System.out.println("\n1. View Results");
        System.out.println("2. View Progress");
        System.out.println("3. Graduate");
    }

    private void performChoices(int choice){

        switch (choice){
            case 1:
                System.out.println("View Result");
                break;
            case 2:
                System.out.println("View Progress");
                driverClass.viewProgress(studentID);
                break;
            case 3:
                System.out.println("Graduate");
                driverClass.applyToGraduate(studentID);
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
