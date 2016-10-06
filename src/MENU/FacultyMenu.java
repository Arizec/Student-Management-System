package MENU;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Driver.Driver;
import USERS.Student;

/**
 * Created by Martin on 2/09/2016.
 */
public class FacultyMenu {

    Driver driverClass = new Driver();
    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|         Faculty Admin            |");
        System.out.println("|              Menu                |");
        System.out.println("+----------------------------------+");

    }

    private void menuOptions(){
        System.out.println("\n1. View Student Results"); //done
        System.out.println("2. Graduate Student(s)"); //done
    }

    private void performChoices(int choice){
        switch (choice){
            case 1:
                System.out.println("Enter ID");
                Scanner input = new Scanner(System.in);
                String id = input.nextLine();

                findID(id);
                break;
                
            case 2:
                System.out.println("Students graduated are: ");
                driverClass.graduateStudent();

                break;
            default:
                System.out.println("4");
                break;

        }
    }

    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 2){			// catches exception if not a number between 0 and 12.
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

    private void findID(String id){
        BufferedReader br;
        try {
            //read external text file containing student info
            br = new BufferedReader(new FileReader("studentList.txt"));
            try {
                String x;

                //read all lines in file
                while ( (x = br.readLine()) != null ) {


                    String studentTxt[] = x.split(":", 5);
                    String ID = studentTxt[0];
                    String studentName = studentTxt[1];
                    String studentProgram = studentTxt[2];
                    String DOB = studentTxt[3];
                    int credit = Integer.parseInt(studentTxt[4]);

                    if(id.equals(ID)){
                        Student student = new Student(ID, studentName, studentProgram, DOB, credit, studentProgram.charAt(0));
                        student.addCourses();
                        if(student.enrolCourses()){
                            student.enrolPastResults();
                            student.viewPastEnrolments();

                        }

                    }







                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void runMenu(){

        printHeader();
        menuOptions();
        int choice = getInput();
        performChoices(choice);

    }

}
