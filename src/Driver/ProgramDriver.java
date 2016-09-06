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

    public void initializeProgramANDCourses(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("programs.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file

                    String programTxt[] = x.split(":", 5);
                    String programCode = programTxt[0];
                    String programName = programTxt[1];
                    int creditsRequired = Integer.parseInt(programTxt[2]);
                    String programType = programTxt[3];

                    if(programTxt[4].equals("Active")){
                        active = true;

                    }else{
                        active = false;

                    }

                    ProgramCourses program = new ProgramCourses(programCode, programName, creditsRequired, programType, active);
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

    public void refreshProgramCourses(){
        for(int i = 0; i < programList.size(); i++){
            programList.remove(i);

        }
        programList.clear();
        initializeProgramANDCourses();
    }


    public void printPrograms(){
        for(int i = 0; i< programList.size(); i++){
            String programCode = programList.get(i).getProgramCode();
            System.out.println("=============================");
            System.out.println("    Program Code: " + programCode);
            System.out.println("======Courses=Included======= ");
            System.out.println("-------------------------------------------");
            programList.get(i).printCourseDetails();
            System.out.println("-------------------------------------------");
        }
        System.out.println("=============================");
    }


    private void addCoursesToProgram(){
        for(int i = 0; i< programList.size(); i++){
            programList.get(i).addCourses();
        }
    }


}
