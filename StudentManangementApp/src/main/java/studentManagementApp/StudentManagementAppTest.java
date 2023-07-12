package studentManagementApp;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

public class StudentManagementAppTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    
	@Test
	public void main() {
		Scanner scanner = new Scanner(System.in);
		


		testAddStudent();

		scanner.close();
	}
	
	@Test
	public void testMenu() {
		// Menu test
		StudentManagementApp.menu();
	}
	

	
	@Test
	public void testAddStudent() {
		// Menu test
        String input = "John\n25\n90.5\n5\n";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        StudentManagementApp.addStudent(new java.util.Scanner(System.in));

        String expectedOutput = "Enter student name: Enter student age: Enter student grade: Student added successfully!\n";
        assertEquals(expectedOutput, testOut.toString());
	}
	
}
