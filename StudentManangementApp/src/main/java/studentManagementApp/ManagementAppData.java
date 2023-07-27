package studentManagementApp;

public class ManagementAppData {
	public int ID;
	public String Name;
	public String Address;
	public int Age;
	public double Gpa;
	
	// Constructor of object
	public ManagementAppData(int ID, String name, String Address, int age, double Gpa) {
		this.Name = name;
		this.Age = age;
		this.Address = Address;
		this.ID = ID;
		this.Gpa = Gpa;
	}
	
	// Method
	public String getName() {
		return Name;
	}
	
	public String getAddress() {
		return Address;
	}
	
    public int getAge() {
        return Age;
    }
    
    public int getID() {
        return ID;
    }
    
    public double getGpa() {
        return Gpa;
    }
    
    @Override
    public String toString() {
    	System.out.printf("%-7d %-15s %-7d %-23s %.1f", ID, Name, Age, Address, Gpa);
    	return "";
    }
}
