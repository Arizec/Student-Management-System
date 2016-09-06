package PROGRAMS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Martin on 6/09/2016.
 */
public class Program {

    String[] program = new String[100];
    private int programCounter = 0;

    public String loadProgram(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("loginDetails.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String programDetails[] = x.split(":", 5);
                    program[programCounter] = programDetails;

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
