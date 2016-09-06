package Driver;

import USERS.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Martin on 6/09/2016.
 */
public class Driver {


    private String studentID;
    private String fullName;
    private String dob;
    private String programCode;

    public String createStudentLogin(){

        try
        {

            String filename= "loginDetails.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            Scanner reader = new Scanner(System.in);

            System.out.println("Create Student login details: ");
            System.out.println("Create Student ID");
            String studentID = reader.nextLine();
            System.out.println("Create Student Password");
            String password = reader.nextLine();

            String logindetails = studentID + ":" + password;
            fw.write("\r\n");
            fw.write(logindetails);//appends the string to the file
            fw.close();
            return studentID;

        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

        return "Error";

    }

    public void createStudent(String existingID){
        Scanner reader = new Scanner(System.in);
        //student profile is create. Name+ DOB + program

        System.out.println("\n Now create student profile");
        while(true){			// forces you to write the correct student ID before you can continue.
            System.out.println("Re-enter Student ID");
            String studentID = reader.nextLine();
            if(existingID.equals(studentID)){
                break;
            }
        }

        System.out.println("Enter student name: ");
        String studentName = reader.nextLine();
        System.out.println("Enter DOB: ");
        String DOB = reader.nextLine();
        System.out.println("Enter Program Code");
        String programCode = reader.nextLine();

        Student student = new Student(existingID, studentName, programCode, DOB);
        writeToFile(student);


    }

    public void createProgram(){
        try{

            String filename= "programs.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            Scanner reader = new Scanner(System.in);

            System.out.println("Creating a new program");
            String facultyChoice = schoolFaculty();
            System.out.println("Program code");
            String programCode = facultyChoice + reader.nextLine();

            System.out.println("Version number");
            String versionNo = reader.nextLine();

            System.out.println("Credits to complete program");
            String creditsNeeded = reader.nextLine();

            String programType = programType();

            String programStatus = status();

            String program = programCode + ":" + versionNo + ":" + creditsNeeded + ":" + programType +  ":" + programStatus;
            fw.write("\r\n");
            fw.write(program);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    private void writeToFile(Student student){

        try
        {

            String filename= "studentList.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write("\r\n");
            fw.write(student.toString());//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }


    }


    private String programType(){
        while(true) {
            System.out.println("\nProgram Type:");
            System.out.println("\n1. Bachelor");
            System.out.println("2. Honours");
            System.out.println("3. Master");
            System.out.println("4. Diploma");
            int choice = getInput(4);

            switch(choice){
                case 1:
                    return "Bachelor";
                case 2:
                    return "Honours";
                case 3:
                    return "Master";

                case 4:
                    return "Diploma";

            }


        }
    }

    private String schoolFaculty(){
        while(true) {
            System.out.println("\nEnter Faculty of Program:");
            System.out.println("\n1. School of Science");
            System.out.println("2. School of Business");
            System.out.println("3. School of Information Technology");
            System.out.println("4. School of Engineering");
            int choice = getInput(4);

            switch(choice){
                case 1:
                    return "SS";
                case 2:
                    return "SB";
                case 3:
                    return "IT";

                case 4:
                    return "SE";

            }


        }

    }

    private String status(){
        while(true) {
            System.out.println("\nEnter Faculty of Program:");
            System.out.println("\n1. Active");
            System.out.println("2. Inactive");
            int choice = getInput(2);

            switch(choice){
                case 1:
                    return "Active";
                case 2:
                    return "Inactive";

            }


        }
    }

    private int getInput(int numberofChoices){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice >= numberofChoices){										// catches exception if not a number between 0 and 12.
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










}
