package PROGRAMS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 6/09/2016.
 */
public class Program {

    ArrayList<ArrayList<String>> program = new ArrayList<ArrayList<String>>();
    ArrayList<String> programDetails =  new ArrayList<String>();


    private int programCounter = 0;

    public void loadProgram(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("programs.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String programTxt[] = x.split(":", 5);
                    for(int i = 0; i<5; i++){
                        programDetails.add(programTxt[i]);
                    }
                    program.add(programDetails);


                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }

    public void printPrograms(){


        for(int i = 0; i< 2; i++){
            ArrayList hello = program.get(i);
            for(int j = 0; j<5; j++){
                System.out.println(hello.get(j));

            }


        }


    }


}
