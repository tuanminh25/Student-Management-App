package studentManagementApp;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class StudentManagementAppVer2 {
	private static final String jdbcURL = "jdbc:h2:./databaseFile";	
	private static final String username = "sa1";
	private static final String password = "";
	private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS students (" 
														+ "id INT PRIMARY KEY AUTO_INCREMENT," 
														+ "name VARCHAR(40), " 
														+ "age INT, " 
														+ "address VARCHAR(100), " 
														+ "gpa DOUBLE" 
														+ ")";
	
	private static String ADD_STUDENT = "INSERT INTO students (name, age, address, gpa) VALUES (?, ?, ?, ?)";
	private static String SHOW_STUDENTS = "SELECT * FROM students";
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
	            System.out.println("7. Average GPA of Students > 5");
	            System.out.println("8. Min Age of Students whose Average GPA of Students > (Average GPA of Students > 5)");
	            System.out.println("9. Max Age of Students whose Average GPA of Students > (Average GPA of Students > 5)");
	            System.out.println("10. Average gpa in any age\n");
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

	                case 7:
	                // find average gpa of student whose gpa is larger than an input number
	                findAverageGpaLargerThanFive();
	                break;
	                
	                case 8:
	                // find average gpa of student whose gpa is larger than an input number
	                findMinAgeWhereGpaGreaterThan5();
	                break;
	                
	                case 9:
	                // find average gpa of student whose gpa is larger than an input number
	                findMaxAgeWhereGpaGreaterThan5();
	                break;
	                
	                case 10:
	                // find average gpa of student whose gpa is larger than an input number
                	AvgGpaEachAge();
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
	
	// Find student data 
	public static ResultSet FindStudentByID(int ID) {
		
		try {
			Statement Statement = connection.createStatement();
			
			ResultSet resultSet =  Statement.executeQuery("SELECT * FROM students");
			
			while (resultSet.next()) {
				int curr_id = resultSet.getInt("id");
				if (curr_id == ID) {
					
					return resultSet;
				}
			}
			
			
			Statement.close();
	
		} catch (SQLException e) {
			System.out.println("Find process failed. Error: " + e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	// Edit student by ID
	public static void EditStudentByID(Scanner scanner) {
		System.out.println("\nEnter student ID to edit: ");
		int EditID = scanner.nextInt();
		
		
		try {
			Statement Statement = connection.createStatement();

			ResultSet resultSet = FindStudentByID(EditID);
			
			
			
			if (resultSet == null) {
				// Invalid input ID
				System.out.println("\nInvalid input ID!\n");	
			} else {
				// Valid input ID
				
				// Get the default information of that student 
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
					preparedStatement2.close();
				} catch (SQLException e) {
					System.out.println("\nInvalid input ID. Error: " + e);
					e.printStackTrace();
				}
			}
			
			
			Statement.close();
			
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
			preparedStatement.close();
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
			
			// Create a list of objects of Students' Data
			List<ManagementAppData> Data = new ArrayList<>();
			
			ResultSet AllInfor = statement.executeQuery("SELECT * FROM students");

			
			while (AllInfor.next()) {
				ManagementAppData single = new ManagementAppData(
												AllInfor.getInt("ID"),
												AllInfor.getString("Name"),
												AllInfor.getString("Address"),
												
												AllInfor.getInt("Age"),
												AllInfor.getDouble("GPA")
												);	
				
				Data.add(single);
	
			}
					

			while (true) {
				System.out.println("\n1. Ascending order");
				System.out.println("2. Descending order");
				System.out.println("\nYour selection: ");
				int choice = scanner.nextInt();
				
				if (choice == 1) {
					Collections.sort(Data, Comparator.comparing(ManagementAppData::getGpa));
					break;
				} else if (choice == 2) {
					Collections.sort(Data, Comparator.comparing(ManagementAppData::getGpa).reversed());
					break;
				} else {
					System.out.println("Please choose 1 or 2 only !");
				}
			}
		
			System.out.println("\nID\tName\t\tAge\tAddress\t\t\tGPA");
			for (ManagementAppData singleData : Data) {
				System.out.println(singleData);
			}
			System.out.println("\n");
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

			List<ManagementAppData> Data = new ArrayList<>();
			
			ResultSet AllInfor = statement.executeQuery("SELECT * FROM students");

			
			while (AllInfor.next()) {
				ManagementAppData single = new ManagementAppData(
												AllInfor.getInt("ID"),
												AllInfor.getString("Name"),
												AllInfor.getString("Address"),
												
												AllInfor.getInt("Age"),
												AllInfor.getDouble("GPA")
												);	
				
				Data.add(single);
	
			}
			

			while (true) {
				System.out.println("\n1. Ascending order");
				System.out.println("2. Descending order");
				System.out.println("\nYour selection: ");
				int choice = scanner.nextInt();

				if (choice == 1) {
					Collections.sort(Data, Comparator.comparing(ManagementAppData::getName));
					break;
				} else if (choice == 2) {
					Collections.sort(Data, Comparator.comparing(ManagementAppData::getName).reversed());
					break;
				} else {
					System.out.println("Please choose 1 or 2 only !");
				}
			}

			
			System.out.println("\nID\tName\t\tAge\tAddress\t\t\tGPA");
			
			for (ManagementAppData singleData : Data) {
				System.out.println(singleData);
			}
			
			System.out.println("\n");
			
			

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
	

	public static void findAverageGpaLargerThanFive() {
		try {
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
			int counter = 0;
			double sum = 0;
			double avg = 0;
			
			while(resultSet.next()) {
				double gpa = resultSet.getDouble("gpa");
				if (gpa > 5) {
					counter++;
					sum = sum + gpa;
				}
			};
			
			if (counter > 0) {
				avg = sum / counter;
			} 

			
			System.out.println("\nAverage gpa which is greater than 5 is: " + avg);
			
			
			System.out.println();
			
			statement.close();
		} catch (SQLException e) {
			
			System.out.println("findAvgGpaFromGivenNum error: " + e);
			e.printStackTrace();
		}
	
		
		
	}
	
	
	public static void findMinAgeWhereGpaGreaterThan5() {
		try {
			// findAverageGpaLargerThanFive 
			Statement statement = connection.createStatement();
			
			ResultSet resultSetGpa = statement.executeQuery("SELECT * FROM students");
			int counter = 0;
			double sum = 0;
			double avg_gpa = 0;
			
			while(resultSetGpa.next()) {
				double gpa = resultSetGpa.getDouble("gpa");
				if (gpa > 5) {
					counter++;
					sum = sum + gpa;
				}
			};
			
			if (counter > 0) {
				avg_gpa = sum / counter;
			} 
			
			// find min age
			int min_age = 100;
			
			ResultSet resultSetAge = statement.executeQuery("SELECT * FROM students");
			
			while (resultSetAge.next()) {
				double gpa = resultSetAge.getDouble("gpa");
				int age = resultSetAge.getInt("age");
				
				if (gpa >= avg_gpa && age < min_age) {
					min_age = age;
				}
				
			}
			
			
			System.out.println("\nMin age of student who has greater gpa than Average GPA of Students > 5 is: " + min_age);
			System.out.println();
			
			statement.close();
		} catch (SQLException e) {
			
			System.out.println("findMinAgeWhereGpaGreaterThan5 error: " + e);
			e.printStackTrace();
		}
	
	}
	
	public static void findMaxAgeWhereGpaGreaterThan5() {
		try {
			// findAverageGpaLargerThanFive 
			Statement statement = connection.createStatement();
			
			ResultSet resultSetGpa = statement.executeQuery("SELECT * FROM students");
			int counter = 0;
			double sum = 0;
			double avg_gpa = 0;
			
			while(resultSetGpa.next()) {
				double gpa = resultSetGpa.getDouble("gpa");
				if (gpa > 5) {
					counter++;
					sum = sum + gpa;
				}
			};
			
			if (counter > 0) {
				avg_gpa = sum / counter;
			} 
			
			// find max age
			int max_age = 0;
			
			ResultSet resultSetAge = statement.executeQuery("SELECT * FROM students");
			
			while (resultSetAge.next()) {
				double gpa = resultSetAge.getDouble("gpa");
				int age = resultSetAge.getInt("age");
				
				if (gpa >= avg_gpa && age > max_age) {
					max_age = age;
				}
				
			}
			
			System.out.println("\nMax age of student who has greater gpa than Average GPA of Students > 5 is: " + max_age);
			System.out.println();
			
			statement.close();
		} catch (SQLException e) {
			
			System.out.println("findMaxAgeWhereGpaGreaterThan5 error: " + e);
			e.printStackTrace();
		}
	
	}
	
	public static void AvgGpaEachAge() {
		try {
			Statement statement = connection.createStatement();

			
			List<ManagementAppData> Data = new ArrayList<>();
			
			ResultSet AllInfor = statement.executeQuery("SELECT * FROM students");

			
			while (AllInfor.next()) {
				ManagementAppData single = new ManagementAppData(
												AllInfor.getInt("ID"),
												AllInfor.getString("Name"),
												AllInfor.getString("Address"),
												
												AllInfor.getInt("Age"),
												AllInfor.getDouble("GPA")
												);	
				
				Data.add(single);
			}
			
			
			List<Integer> DistinctAge = Data.stream()
											.mapToInt(ManagementAppData::getAge)
											.distinct()
											.boxed()
											.sorted()
											.collect(Collectors.toList());

			System.out.println("\nAge\tAverage GPA");
	
			for (int i = 0; i < DistinctAge.size(); i++) {
				double sum_gpa = 0;
				double avg_gpa = 0;
				int counter = 0;
				
				for (ManagementAppData singleData : Data) {
					if (singleData.getAge() == DistinctAge.get(i)) {
						sum_gpa = sum_gpa + singleData.getGpa();
						counter++;
					}
				}
				 
				if (counter != 0 ) { 
					avg_gpa = sum_gpa / counter;
					System.out.printf("%-7d %.1f\n", DistinctAge.get(i), avg_gpa);
				}
			}
			
			statement.close();
			System.out.println("");
			
			
		} catch (SQLException e) {
			System.out.println("AvgGpaEachAge error: " + e);
			e.printStackTrace();
		}
	
	}
}
