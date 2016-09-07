package Driver;

import PROGRAMS.ProgramCourses;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Martin on 6/09/2016.
 */
public class ProgramDriver {


    ArrayList<ProgramCourses> programList =  new ArrayList<ProgramCourses>();


    private boolean active;


    /*
     * Append program information to external text file
     */
    public void initializeProgramANDCourses(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("programs.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file

                    String programTxt[] = x.split(":", 6);
                    String programCode = programTxt[0];
                    String programName = programTxt[1];
                    String version = programTxt[2];
                    int creditsRequired = Integer.parseInt(programTxt[3]);
                    String programType = programTxt[4];

                    if(programTxt[5].equals("Active")){
                        active = true;

                    }else{
                        active = false;

                    }

                    ProgramCourses program = new ProgramCourses(programCode, programName, version, creditsRequired, programType, active);
                    programList.add(program);



                }
                addCoursesToProgram();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }

    /*
     * Clears the program buffer, if user wants to create another program
     */
    public void refreshProgramCourses(){
        for(int i = 0; i < programList.size(); i++){
            programList.remove(i);

        }
        programList.clear();
        initializeProgramANDCourses();
    }


    /*
     * Print associated core courses and specialisation courses of related
     * programs
     */
    public void printPrograms(){
        for(int i = 0; i< programList.size(); i++){
            String programCode = programList.get(i).getProgramCode();
            String programName = programList.get(i).getProgramName();
            String programType = programList.get(i).getProgramType();
            System.out.println("=============================");
            System.out.println("    Program Name: "+ programType +" of "+ programName);
            System.out.println("    Program Code: " + programCode);
            System.out.println("============================= ");
            System.out.println("    Core Courses");
            programList.get(i).printCourseDetails();
            System.out.println("    Specialization Courses");
            programList.get(i).printSpecialCourseDetails();
        }
        System.out.println("=============================");
    }

    /*
     * Assign courses to certain programs based on program faculty and
     * course type
     */
    private void addCoursesToProgram(){
        for(int i = 0; i< programList.size(); i++){
            programList.get(i).addCourses();
        }
    }




}
