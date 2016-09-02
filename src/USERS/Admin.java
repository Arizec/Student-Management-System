package USERS;

import java.util.*;
import java.io.*;

/**
 * Created by Martin on 1/09/2016.
 */
public class Admin extends Staff {

  
  public void createStudentID(){

        try
		{

		    String filename= "loginDetails.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    Scanner reader = new Scanner(System.in);
		  
		    
		    System.out.println("Create Student ID");
		    String studentID = reader.nextLine();
		    System.out.println("Create Student Password");
		    String password = reader.nextLine();
		    
		    String logindetails = studentID + ":" + password;
		   
		    fw.write(logindetails);//appends the string to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
        }
  }
  
  public void createProgram(){
  			try
			{

				String filename= "programs.txt";
				FileWriter fw = new FileWriter(filename,true); //the true will append the new data
				Scanner reader = new Scanner(System.in);

				System.out.println("Creating a new program");

				System.out.println("Program code");
				String programCode = reader.nextLine();

				System.out.println("Version number");
				String versionNo = reader.nextLine();

				System.out.println("Credits to complete program");
				String creditsNeeded = reader.nextLine();

				System.out.println("Program type(Bachelor/Honours/Diploma/Masters)");
				String programType = reader.nextLine();

				System.out.println("Program status");
				String programStatus = reader.nextLine();

				String program = programCode + ":" + versionNo + ":" + creditsNeeded + ":" + programType +  ":" + programStatus;

				fw.write(program);//appends the string to the file
				fw.close();
			}
			catch(IOException ioe)
			{
				System.err.println("IOException: " + ioe.getMessage());
			}
  }


}

