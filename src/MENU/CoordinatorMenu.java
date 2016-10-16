package MENU;

import java.util.Scanner;

import Driver.Driver;
import Driver.ProgramDriver;
import USERS.Student;

import java.io.*;
/**
 * Created by Martin on 2/09/2016.
 */
public class CoordinatorMenu {
    Driver driverClass = new Driver();
    ProgramDriver programClass = new ProgramDriver();

    private void printHeader(){

        System.out.println("\n+--------------------------------+");
        System.out.println("|            Coordinator           |");
        System.out.println("|               Menu               |");
        System.out.println("+----------------------------------+");

    }

    private void menuOptions(){
        System.out.println("\n1. Define Program(s)");
        System.out.println("2. Create Student Account(s)"); //done
        System.out.println("3. Upload Student Enrolment(s)");
        System.out.println("4. View Results of Student(s)"); //done
        System.out.println("5. Graduate Student(s)"); //done
    }

    private void performChoices(int choice){
        switch (choice){
            case 1:
                System.out.println("Enter Program Code to Define");
                Scanner code = new Scanner(System.in);
                System.out.println(programClass.defineProgram(code.nextLine()));
                break;
            case 2:
                Scanner reader = new Scanner(System.in);
             	System.out.println("How many student accounts would you like to add?");

                 //stores amount of student accounts wanting to be created
             	int numberOfAccounts = reader.nextInt();
             	
             	int i;
                 //creates as many student accounts according to amount entered
             	for(i=0; i<numberOfAccounts; i++){
             			String studentIDcreated = driverClass.createStudentLogin();        // makes sure it uses the studentID it created previously
             			driverClass.createStudent(studentIDcreated);            			
                 };

                break;
                
            case 3:
                System.out.println("Upload Enrolment(s)");
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
            case 4:
                System.out.println("Enter ID");
                Scanner input1 = new Scanner(System.in);

                String idz = input1.nextLine();

                findID(idz);





                break;
            
            case 5:
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

        while( choice <0 || choice > 6){										// catches exception if not a number between 0 and 12.
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
        driverClass.appendStudent();
        programClass.initializeProgramANDCourses();
        while(true){
            printHeader();
            menuOptions();
            int choice = getInput();
            performChoices(choice);

        }


    }

    /* print past results and current enrolment of specified student */
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




}
