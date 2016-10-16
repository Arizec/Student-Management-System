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

    //fac admin is displayed menu for selection
    private void menuOptions(){
        System.out.println("\n1. View Student Results");
        System.out.println("2. Graduate Student(s)");
        System.out.println("3. Exit"); //exit the system
    }

    //perform the choice selected at menu
    private void performChoices(int choice){
        switch (choice){
            case 1:
                //enter ID of student that fac wants to view the result of
                System.out.println("Enter ID");
                Scanner input = new Scanner(System.in);
                String id = input.nextLine();

                //finds students results associated with id entered
                findID(id);
                break;
                
            case 2:
                //graduate all students eligible to graduate
                System.out.println("Students graduated are: ");
                driverClass.graduateStudent();
                break;

            case 3:
                //exit the system
                System.exit(0);
            default:
                System.out.println("4");
                break;

        }
    }

    private int getInput(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice > 3){			// catches exception if number is invalid
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

    //retrieves past results of id fac entered
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

                    //if student exists in system
                    if(id.equals(ID)){
                        Student student = new Student(ID, studentName, studentProgram, DOB, credit, studentProgram.charAt(0));
                        student.addCourses(); //dynamically add student courses at runtime (to test system works)

                        //show past results of students and courses they are enrolled in this semester
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

    //run every operation
    public void runMenu(){

        printHeader();
        menuOptions();
        int choice = getInput();
        performChoices(choice);

    }

}
