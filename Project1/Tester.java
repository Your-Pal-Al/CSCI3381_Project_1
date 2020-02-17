//Alex Salas
//CSCI 3381
//Project1
//Tester Class

public class Tester {

	public static void main(String[] args) {

		//creates patient collection
		PatientCollection<Patient> PC = new PatientCollection<Patient>();

		//adds patients from original data file
		PC.addPatientsFromFile("./data.csv");
		
		//prints patient 1
		System.out.println(PC.getPatient("1"));
		
	
		//prints all patients in PC
		PC.to_String();

		//adds patients from newdata.csv file
		PC.addNewPatientsFromFile("./newdata.csv");
		
		//set results for patient ID == 30
		PC.setResultForPatient("30", "unknown");
		
		//prints patient 30
		System.out.println(PC.getPatient("30"));
		
		//prints out IDS of all patients
		System.out.println(PC.getIds());
		
		//prints added patients in PC
		//PC.to_String();
		
		//write data into new file
		PC.doWrite("Book1.csv");
	}

}