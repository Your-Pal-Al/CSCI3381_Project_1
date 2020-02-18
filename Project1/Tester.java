//Alex Salas
//CSCI 3381
//Project1
//Tester Class

public class Tester {

	public static void main(String[] args) {

		//creates patient collection
		PatientCollection<Patient> PC = new PatientCollection<Patient>();

		//adds patients from original data file
		PC.addPatientsFromFile();

		//prints patient 1
		System.out.println(PC.getPatient("1"));

		//prints all patients in PC
		PC.to_String();

		//adds patients from newdata.csv file
		PC.addPatientsFromFile("./newdata.csv");

		//removes patient 1 from file
		PC.removePatient("1");

		//prints out IDS of all patients
		System.out.println(PC.getIds());

		//sets result for patient 2 to "inconclusive"
		PC.setResultForPatient("2", "inconclusive");

		System.out.println(PC.getPatient("2"));

		//patient 31 doesnt exist, prints error
		System.out.println(PC.getPatient("31"));

		//prints added patients in PC
		PC.to_String();

		//write data into new file
		PC.doWrite("Book1.csv");
	}

}