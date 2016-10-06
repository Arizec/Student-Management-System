package USERS;

/**
 * Created by Martin on 5/09/2016.
 */

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class StudentTest {
    Student student;
    //existingID, studentName, programCode, DOB
    @Before
    public void setUp(){
        student = new Student("s3607187", "Martin", "BACH123", "05/29/96", 12, 'B');

    }

    @After
    public void tearDown(){


    }

    @Test
    public void checkStudentID(){
        String studentid = student.getStudentID();
        assertEquals("s3607187", studentid);

    }

    @Test
    public void checkFullName(){
        String studentFullName = student.getFullName();
        assertEquals("Martin", studentFullName);


    }

    @Test
    public void checkProgramCode(){
        String studentProgramCode = student.getprogramCode();
        assertEquals("BACH123", studentProgramCode);


    }

    @Test
    public void checkDOB(){
        String studentDOB = student.getDob();
        assertEquals("05/29/96", studentDOB);


    }


}
