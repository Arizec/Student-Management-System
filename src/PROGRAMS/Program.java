package PROGRAMS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 6/09/2016.
 */
public class Program {


    ArrayList<ProgramCourses> programList =  new ArrayList<ProgramCourses>();


    private boolean active;

    public void loadProgram(){
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


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }



    public void printShit(){
        for(int i = 0; i< programList.size(); i++){
            System.out.println(programList.get(i).toString());
            programList.get(i).addCourses();
            programList.get(i).printCourses();


        }


    }


}
