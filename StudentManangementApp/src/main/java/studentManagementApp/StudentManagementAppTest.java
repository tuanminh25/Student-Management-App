package studentManagementApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class StudentManagementAppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testShowStudents() {
        String input = "6\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.menu();
        String output = outputStream.toString();

        // Assert the expected output for displaying students

        // Example assertion for empty result
        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: "
        		, output);

    }
    
    @Test
    public void testAddStudent() {
        String input = "1\nJason Ming\n22\nLondon UK \n29\n1\nKim Jae\n22Seoul Korean\n3.3\n\6\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        StudentManagementApp.menu();
        String output = outputStream.toString();
        System.out.println("Here");
        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: 1\r\n"
        		+ "Enter Student name: \r\n"
        		+ "Jason Ming\r\n"
        		+ "Enter Student age: \r\n"
        		+ "22\r\n"
        		+ "Enter Student address: \r\n"
        		+ "London UK \r\n"
        		+ "Enter Student gpa: \r\n"
        		+ "2.9\r\n"
        		+ "\r\n"
        		+ "Successfully added new student!\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: 1\r\n"
        		+ "Enter Student name: \r\n"
        		+ "Kim Jae\r\n"
        		+ "Enter Student age: \r\n"
        		+ "22\r\n"
        		+ "Enter Student address: \r\n"
        		+ "Seoul Korean\r\n"
        		+ "Enter Student gpa: \r\n"
        		+ "3.3\r\n"
        		+ "\r\n"
        		+ "Successfully added new student!\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: 6\r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "1       Jason Ming      22      London UK               2.9\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: ", output);
    }

    @Test
    public void testEditStudent() {
        String input = "2\n0\n2\n1\ny\nKing Kam\ny\n23\nn\ng\nn\n0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.menu();
        String output = outputStream.toString();

        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "Enter student ID to edit: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Invalid input ID!\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "Enter student ID to edit: \r\n"
        		+ "\r\n"
        		+ "Do you want a new Student Name? (y/n) \r\n"
        		+ "Edit Student name: \r\n"
        		+ "\r\n"
        		+ "Do you want a new Student Age? (y/n) \r\n"
        		+ "Edit Student age: \r\n"
        		+ "\r\n"
        		+ "Do you want a new Student Address? (y/n) \r\n"
        		+ "Do you want a new Student gpa? (y/n) \r\n"
        		+ "y or n only !\r\n"
        		+ "Do you want a new Student gpa? (y/n) \r\n"
        		+ "\r\n"
        		+ "Successfully updated student infor! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: ", output);
    }



    @Test
    public void testSortStudentsByGPA() {
        String input = "4\n1\n4\n2\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.menu();
        String output = outputStream.toString();

        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "1. Ascending order\r\n"
        		+ "2. Descending order\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "1       King Kam        23      London UK               2.9\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "1. Ascending order\r\n"
        		+ "2. Descending order\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "1       King Kam        23      London UK               2.9\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: ", output);
    }


    @Test
    public void testSortStudentsByName() {
        String input = "5\n1\n5\n2\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.menu();
        String output = outputStream.toString();


        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "1. Ascending order\r\n"
        		+ "2. Descending order\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "1       King Kam        23      London UK               2.9\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "1. Ascending order\r\n"
        		+ "2. Descending order\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "1       King Kam        23      London UK               2.9\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: ", output);
    }

    
    
    @Test
    public void testDeleteStudent() {
        String input = "3\n0\n3\n1\n6\n3\n2\n6\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        StudentManagementApp.menu();
        String output = outputStream.toString();

        assertEquals("Welcome to Student Management App! \r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "Enter student ID to delete: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Input student ID does not exist !\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "Enter student ID to delete: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Successfully deleted student !\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "2       Kim Jae         22      Seoul Korean            3.3\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "Enter student ID to delete: \r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Successfully deleted student !\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: \r\n"
        		+ "\r\n"
        		+ "ID	Name		Age	Address			GPA\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "Student Mananagemnt App\r\n"
        		+ "1. Add student\r\n"
        		+ "2. Edit student by ID\r\n"
        		+ "3. Delete student by ID\r\n"
        		+ "4. Sort student by GPA\r\n"
        		+ "5. Sort student by name\r\n"
        		+ "6. Show students\r\n"
        		+ "0. Exit\r\n"
        		+ "\r\n"
        		+ "Your selection: ", output);
    }
}
