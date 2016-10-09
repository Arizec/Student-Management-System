package MENU;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Driver.*;
import USERS.Student;

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
        System.out.println("|          System Admin          |");
        System.out.println("|             Menu               |");
        System.out.println("+--------------------------------+");

    }

    /*
     * Prints menu options available to the user
     */
    private void menuOptions(){
        System.out.println("\n1. Create Student Account(s)"); //done
        System.out.println("2. Create Program"); //done
        System.out.println("3. View Program(s)"); //done
        System.out.println("4. Define Program(s)");
        System.out.println("5. Upload Student Enrolment(s)");
        System.out.println("6. Exit");
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
                programClass.refreshProgramCourses();
                programClass.printPrograms();

                break;

            //View program details
            case 3:

                programClass.printPrograms();
                //print

                break;

            //DEFINE Programs
            case 4:
                System.out.println("Enter Program Code to Define");
                Scanner code = new Scanner(System.in);
                System.out.println(programClass.defineProgram(code.nextLine()));


                break;

            case 5:
                Scanner input = new Scanner(System.in);
                System.out.println("How many student enrolments would you like to upload?");

                //stores amount of student accounts that admin wants to create
                int enrolments = input.nextInt();

                int j;
                //creates as many student accounts according to amount admin entered
                for(j=0; j <enrolments; j++){
                    String studentIDcreated = driverClass.createStudentLogin();        // makes sure it uses the studentID it created previously
                    driverClass.createEnrollment(studentIDcreated);
                };

                System.out.println("Student enrolments(s) have been successfully uploaded!");

                System.out.println("Enter ID of enrolment you wish to view.");
                String id = input.nextLine();
                String test = input.nextLine();

                findID(id);
                findID(test);
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

        while( choice <0 || choice > 6){										// catches exception if not a number between 0 and 12.
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






    private void findID(String id) {
        BufferedReader br;
        try {
            //read external text file containing student info
            br = new BufferedReader(new FileReader("studentList.txt"));
            try {
                String x;

                //read all lines in file
                while ((x = br.readLine()) != null) {


                    String studentTxt[] = x.split(":", 5);
                    String ID = studentTxt[0];
                    String studentName = studentTxt[1];
                    String studentProgram = studentTxt[2];
                    String DOB = studentTxt[3];
                    int credit = Integer.parseInt(studentTxt[4]);

                    if (id.equals(ID)) {
                        Student student = new Student(ID, studentName, studentProgram, DOB, credit, studentProgram.charAt(0));
                        student.addCourses();
                        if (student.enrolCourses()) {
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
    }