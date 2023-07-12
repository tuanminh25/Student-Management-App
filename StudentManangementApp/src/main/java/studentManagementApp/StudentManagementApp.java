package studentManagementApp;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;

public class StudentManagementApp {
	private static final String jdbcURL = "jdbc:h2:./databaseFile";	
	private static final String username = "sa1";
	private static final String password = "";
	private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS students (" +
			"id INT PRIMARY KEY AUTO_INCREMENT," +
			"name VARCHAR(40), " +
			"age INT, " +
			"address VARCHAR(100), " +
			"gpa DOUBLE" +
			")";
	
	private static String ADD_STUDENT = "INSERT INTO students (name, age, address, gpa) VALUES (?, ?, ?, ?)";
	private static String SHOW_STUDENTS = "SELECT * FROM students";
	private static String SORT_STUDENTS_BY_NAME_ASC = "SELECT * FROM students ORDER BY name ASC";
	private static String SORT_STUDENTS_BY_NAME_DESC = "SELECT * FROM students ORDER BY name DESC";
	private static String SORT_STUDENTS_BY_GPA_ASC = "SELECT * FROM students ORDER BY gpa ASC";
	private static String SORT_STUDENTS_BY_GPA_DESC = "SELECT * FROM students ORDER BY gpa DESC";
	private static String DELETE_STUDENTS_BY_ID = "DELETE FROM students WHERE id = ?";
	private static String EDIT_STUDENTS_BY_ID = "UPDATE students SET name = ?, age = ?, address = ?, gpa = ? WHERE id = ?";
	private static Connection connection;
	
	public static void main (String[] args) {
		menu();
		
	}
	
	// Menu 
	public static void menu() {
		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			
			System.out.println("Welcome to Student Management App! \n");
			
			Scanner scanner = new Scanner(System.in);

	        boolean exit = false;
	        
	        createTableIfNotExisted();
	        
	        while (!exit) {
	            System.out.println("Student Mananagemnt App");
	            System.out.println("1. Add student");
	            System.out.println("2. Edit student by ID");
	            System.out.println("3. Delete student by ID");
	            System.out.println("4. Sort student by GPA");
	            System.out.println("5. Sort student by name");            
	            System.out.println("6. Show students");
	            System.out.println("0. Exit\n");

	            System.out.print("Your selection: ");


	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1 :
	                // add student function
	                addStudent(scanner);
	                break;

	                case 2 :
	                // edit student function by ID function
                	EditStudentByID(scanner);
	                break;

	                case 3:
	                // delete student function by ID function
                	DeleteStudentByID(scanner);
	                break;

	                case 4:
	                // sort student by GPA method
	                sortStudentGPA(scanner);
	                break;

	                case 5:
	                // sort student by name method
                	sortStudentName(scanner);
	                break;

	                case 6:
	                // show student method
	                showStudents();
	                break;

	                case 0: 
	                // exit program
	                exit = true;
	                
	                break;

	                default: 
	                System.out.println("Invalid syntax, please input choice number from 0 to 6 !\n");

	            }
	        }


	        scanner.close();
		
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Create table if not existed 
	public static void createTableIfNotExisted() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(CREATE_TABLE_QUERY);
			statement.close();
			
			
		} catch (SQLException e){
			System.out.println("Table creation failed. Error: " + e);
			e.printStackTrace();
		}
	}
	
	// Add student
	public static void addStudent(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Enter Student name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Student age: ");
		int age = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Student address: ");
		String address = scanner.nextLine();
		System.out.println("Enter Student gpa: ");
		double gpa = scanner.nextDouble();
		
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, address);
			preparedStatement.setDouble(4, gpa);
			preparedStatement.executeUpdate();
			System.out.println("\nSuccessfully added new student!\n");
		} catch (SQLException e) {
			System.out.println("\nFailed to add student. Error: " + e);
			e.printStackTrace();
		}
	}
	
	
	// Edit student by ID
	public static void EditStudentByID(Scanner scanner) {
		System.out.println("\nEnter student ID to edit: ");
		int EditID = scanner.nextInt();
		
		
		try {
			Statement Statement = connection.createStatement();

			Statement.executeQuery("SELECT * FROM students WHERE id = " + EditID);
			
			ResultSet resultSet =  Statement.executeQuery("SELECT * FROM students WHERE id = " + EditID);
			
			
			
			
			
			if (!resultSet.next()) {
				// Invalid input ID
				System.out.println("\nInvalid input ID!\n");	
			} else {
				// Valid input ID
				
				// Get the default information of that student
				int DefaultID = resultSet.getInt("id");
				String DefaultName = resultSet.getString("name");
				int DefaultAge = resultSet.getInt("age");
				String DefaultAddress = resultSet.getString("address");
				double DefaultGpa = resultSet.getDouble("gpa");
				
				// Whether to change that attribute or not
				String choice = "y";
				
				// Change the name
				String NewName = null;
				while (true) {
					System.out.print("Do you want a new Student Name? (y/n) ");
					choice = scanner.next();
					if (choice.equals("n") ) {
						NewName = DefaultName;
						break;
					} else if (choice.equals("y")) {
						System.out.println("Edit Student name: ");
						scanner.nextLine();
						NewName = scanner.nextLine(); 
						break;
					} else {
						System.out.println("y or n only !");
					}
				}
		
				
				// Change the age
				int NewAge = -1;
				while (true) {
					System.out.print("Do you want a new Student Age? (y/n) ");
					choice = scanner.next();
					if (choice.equals("n") ) {
						NewAge = DefaultAge;
						break;
					} else if (choice.equals("y")) {
						System.out.println("Edit Student age: ");
						NewAge = scanner.nextInt();
						break;
					} else {
						System.out.println("y or n only !");
					}
				}
				
				
				// Change the address
				String NewAddress = null;
				while (true) {
					System.out.print("Do you want a new Student Address? (y/n) ");
					choice = scanner.next();
					if (choice.equals("n") ) {
						NewAddress = DefaultAddress;
						break;
					} else if (choice.equals("y")) {
						System.out.println("Edit Student Address: ");
						scanner.nextLine();
						NewAddress = scanner.nextLine();
						break;
					} else {
						System.out.println("y or n only !");
					}
				}

				
				// Change the age
				double NewGpa = -1;
				while (true) {
					System.out.print("Do you want a new Student gpa? (y/n) ");
					choice = scanner.next();
					if (choice.equals("n") ) {
						NewGpa = DefaultGpa;
						break;
					} else if (choice.equals("y")) {
						System.out.println("Edit Student gpa: ");
						NewGpa = scanner.nextDouble();
						break;
					} else {
						System.out.println("y or n only !");
					}
				}
				
			
				
				try {
					// Try to edit student information
					PreparedStatement preparedStatement2 = connection.prepareStatement(EDIT_STUDENTS_BY_ID);
					preparedStatement2.setString(1, NewName);
					preparedStatement2.setInt(2, NewAge);
					preparedStatement2.setString(3, NewAddress);
					preparedStatement2.setDouble(4, NewGpa);
					preparedStatement2.setInt(5, EditID);
					preparedStatement2.executeUpdate();
					System.out.println("\nSuccessfully updated student infor! \n");
				} catch (SQLException e) {
					System.out.println("\nInvalid input ID. Error: " + e);
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			System.out.println("\nFind Student By ID failed. Error: " + e);
			e.printStackTrace();
		}
		
		
		

		
	}
	
	
	
	// Delete students
	public static void DeleteStudentByID(Scanner scanner) {
		System.out.println("\nEnter student ID to delete: ");

		int DeleteID = scanner.nextInt();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS_BY_ID);
			preparedStatement.setInt(1, DeleteID);
			int rowUpdated = preparedStatement.executeUpdate();
			if (rowUpdated > 0) {
				System.out.println("\nSuccessfully deleted student !\n");
			} else {
				System.out.println("\nInput student ID does not exist !\n");
			}
			
		} catch (SQLException e) {
			System.out.println("Deletion process failed. Error: " + e);
			e.printStackTrace();
		}
		

	}
	
	
	
	
	// Show students
	public static void showStudents() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SHOW_STUDENTS);
			printToConsole(resultSet);
			
			statement.close();
		} catch (SQLException e) {
			System.out.println("showStudents failed. Error: " + e);
			e.printStackTrace();
		}
		
	}
	
	// Sort students by gpa
	public static void sortStudentGPA(Scanner scanner) {

		try {
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = null;
			while (true) {
				System.out.println("\n1. Ascending order");
				System.out.println("2. Descending order");
				System.out.println("\nYour selection: ");
				int choice = scanner.nextInt();
				
				if (choice == 1) {
					resultSet = statement.executeQuery(SORT_STUDENTS_BY_GPA_ASC);
					break;
				} else if (choice == 2) {
					resultSet = statement.executeQuery(SORT_STUDENTS_BY_GPA_DESC);
					break;
				} else {
					System.out.println("Please choose 1 or 2 only !");
				}
			}
			
			
			
			
			printToConsole(resultSet);
			
			statement.close();
		} catch (SQLException e) {
			System.out.println("Sort students by name failed. Error: " + e);
			e.printStackTrace();

		}
	}
	
	// Sort students by name
	public static void sortStudentName(Scanner scanner) {
		try {
			Statement statement = connection.createStatement();

			
			ResultSet resultSet = null;
			while (true) {
				System.out.println("\n1. Ascending order");
				System.out.println("2. Descending order");
				System.out.println("\nYour selection: ");
				int choice = scanner.nextInt();

				if (choice == 1) {
					resultSet = statement.executeQuery(SORT_STUDENTS_BY_NAME_ASC);
					break;
				} else if (choice == 2) {
					resultSet = statement.executeQuery(SORT_STUDENTS_BY_NAME_DESC);
					break;
				} else {
					System.out.println("Please choose 1 or 2 only !");
				}
			}
			
			
			printToConsole(resultSet);
			
			statement.close();
		} catch (SQLException e) {
			System.out.println("Sort students by name failed. Error: " + e);
			e.printStackTrace();

		}
	}
	

	
	public static void printToConsole(ResultSet resultSet) throws SQLException {
		System.out.println("\nID\tName\t\tAge\tAddress\t\t\tGPA");
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			String address = resultSet.getString("address");
			double gpa = resultSet.getDouble("gpa");
			
			System.out.printf("%-7d %-15s %-7d %-23s %.1f\n", id, name, age, address, gpa);
		}
		System.out.println("\n"); 
	}
	
	
}