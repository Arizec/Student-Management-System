package USERS;

import java.util.*;
import java.io.*;
import USERS.*;

/**
 * Created by Martin on 1/09/2016.
 */
public class Admin extends Staff {



  
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
			fw.write("\n");
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

	private void writeToFile(Student student){

		try
		{

			String filename= "studentList.txt";
			FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			fw.write("\n");
			fw.write(student.toString());//appends the string to the file
			fw.close();
		}
		catch(IOException ioe)
		{
			System.err.println("IOException: " + ioe.getMessage());
		}


	}













}

