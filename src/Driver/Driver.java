package Driver;

import USERS.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import PROGRAMS.ProgramCourses;

/**
 * Created by Martin on 6/09/2016.
 */
public class Driver {
	
    private String studentID;
    private String fullName;
    private String programCode;
    private String dob;

    
	ArrayList<ProgramCourses> programList =  new ArrayList<ProgramCourses>();
	ArrayList<Student> studentList =  new ArrayList<Student>();

    /*
     * To see if a student is has enough credits to graduate
     */
    public void applyToGraduate(String existingID){
    	BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("studentList.txt"));
            try {
                String x;
                
                while ( (x = br.readLine()) != null ) {
                
                	
                    String studentTxt[] = x.split(":", 5);
                    String ID = studentTxt[0];
                    String credits = studentTxt[4];
                    //credits completed= program course credits required
                    //288 is current placeholder
                    int creditsCompleted= 288;
                    if(existingID.equals(ID)){
                    	
                    	if(Integer.parseInt(credits) != creditsCompleted){
                            System.out.println("\nCredits completed: "+ credits);
                            System.out.println("Credits needed to graduate: "+ creditsCompleted);
                    		System.out.println("Sorry, you are not able to graduate");
                    		break;
                    	}
                    	
                    	else
                            System.out.println("\nCredits completed: "+ credits);
                            System.out.println("Credits needed to graduate: "+ creditsCompleted);
                    		System.out.println("Congratulations, youa are able to graduate!");
                    
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
    
    /*
     * View student progress
     */
    public void viewProgress(String existingID){
    	BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("studentList.txt"));
            try {
                String x;
                
                while ( (x = br.readLine()) != null ) {
                	
                	
                    String studentTxt[] = x.split(":", 5);
                    String ID = studentTxt[0];
                    String studentName = studentTxt[1];
                    String studentProgram = studentTxt[2];
                    String DOB = studentTxt[3];
                    String credits = studentTxt[4];
                    
                    if(existingID.equals(ID)){
                    	System.out.println("Hi " + studentName+"! Here are your "
                    			+ "current details");
                    	
                    	System.out.println("Program: " + studentProgram);
                    	System.out.println("DOB: " + DOB);
                    	System.out.println("Credits completed: " + credits);
                    	
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
    
    /*
     * function to create student login account, i.e their
     * username and password
     */
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

    
    public String returnID(String existingID){
    	return existingID;
    }
    /*
     * Create student profile/information
     */
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

    /*
     * Creates a Program which includes, program code, ver no, credits to complete
     */
    public void createProgram(){
        try{

            String filename= "programs.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            Scanner reader = new Scanner(System.in);

            System.out.println("Creating a new program");
            String facultyChoice = schoolFaculty();
            System.out.println("Program code [numbers only]");
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

    /*
     * Appent student information to an external text file
     */
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


    /*
     * Returns program type (i.e Bachelors ect)
     */
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

    /*
     * Returns Faculty of Program.
     * Every program will start with Facaulty number (i.e SS/IT/SE/SB)
     */
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

    /*
     * Choose whether program will be active or inactive
     */
    private String status(){
        while(true) {
            System.out.println("\nEnter Program status:");
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

    /*
     * Tests whether choice entered at menu selection is valid
     */
    private int getInput(int numberofChoices){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while( choice <0 || choice >= numberofChoices){			// catches exception if not a number between 0 and 12.
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
