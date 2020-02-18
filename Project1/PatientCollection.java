//Alex Salas
//CSCI 3381
//Project1
//PatientCollection Class

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PatientCollection <T> implements PatientCollectionADT{

	private ArrayList<Patient> collection = new ArrayList<Patient>();
	private String fileName = null;


	//PatientCollection constructor
	public PatientCollection() {

		fileName = "./data.csv" ;
	}

	// Return the patient with the given id.  Return void if the id does 
	// not exist in the collection
	public Patient getPatient(String id) {

		if(this.getIds().contains(id)) { //if the id exists in the array list of ids, patient exists
			return collection.get(getIds().indexOf(id)); //return patient at index determined by provided id
		}

		//prints to console if patient not found
		System.out.println("ERROR: ID: " + id + " not found within registry.");
		System.out.println("Patient cannot be found.");

		return null; //if id cannot be found, return null
	}

	//removes patient from collection by their ID
	public Patient removePatient(String id) {

		Patient removeMe = new Patient(); //creates temporary patient in order to return removed patient

		for(int i = 0;; i++){ //I know. I don't like how this looks either

			if(id.equals(collection.get(i).get_id())) { // if id == collection.get(i)
				removeMe = collection.get(i); //sets temp patient to collection.get(i) for return
				collection.remove(i); //removes patient
				return removeMe;
			}

			else if(collection.get(i).get_id() == null){

				System.out.println("ERROR: ID: " + id + " not found within registry.");
				System.out.println("Patient cannot be removed.");

				return null;
			}
		}
	}

	//sets result for patient using given ID and predictor class
	public void setResultForPatient(String id, String result) {

		this.getPatient(id).set_Result(result);
	}

	// Return an ArrayList with all of the collection's patient ids
	public ArrayList<String> getIds() {

		ArrayList<String> allIds = new ArrayList<String>(); //creates an ArrayList to store ids within collection

		for(int i = 0; i < collection.size(); i++) {
			allIds.add(collection.get(i).get_id());
		}

		return allIds;
	}

	//adds patients from file using fileName passed as parameter
	public String addPatientsFromFile() {

		BufferedReader lineReader = null;
		try {

			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			String[] tokens = new String[4776];
			boolean duplicate = false;
			boolean proteinArr = false;

			while ((line = lineReader.readLine())!= null) {
				tokens = line.split(","); 

				double arr[] = new double[4776];

				for(int i = 3; i < tokens.length; i++) {
					arr[i-3] = Double.parseDouble(tokens[i]);
				}

				int i = 0;

				while(duplicate == false && proteinArr == false && i < collection.size()) {

					if(collection.get(i).get_id() == tokens[0]) {
						duplicate = true;
					}
					else {
						i++;
					}
				}

				if(duplicate == true) {
					System.out.println("ERROR: Attempted to add patient with duplicate ID. \nPatient was not added to collection.");
				}

				else if(proteinArr == true) {

					System.out.println("ERROR: Attempted to add patient with incorrect protein data. \nPatient was not add to collection.");
				}

				else {	

					double p1 = arr[3697]; //1st protein
					double p2 = arr[3258]; //2nd protein

					Patient newPatient = new Patient(tokens[2],tokens[0], Predictor.predict(p1, p2), arr);
					collection.add(newPatient);
				}
			}	
		} catch (Exception e) {
			System.err.println("");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
		// end of readFile method	

		return null;
	}

	//adds patients from file using fileName passed as parameter
	public String addPatientsFromFile(String fileName) {

		BufferedReader lineReader = null;
		try {

			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			String[] tokens = new String[4776];

			while ((line = lineReader.readLine())!= null) {

				boolean duplicate = false;
				boolean proteinArr = false;

				tokens = line.split(",");

				double arr[] = new double[4776];

				for(int i = 3; i < tokens.length; i++) {
					arr[i-3] = Double.parseDouble(tokens[i]);
				}

				int i = 0;

				while(duplicate == false && proteinArr == false && i < collection.size()) {

					if(collection.get(i).get_id().equals(tokens[0])) {
						duplicate = true;
					}
					i++;
				}

				if(duplicate == true) {
					System.out.println("ERROR: Attempted to add patient with duplicate ID. \nPatient was not added to collection.");
				}

				else if(proteinArr == true) {

					System.out.println("ERROR: Attempted to add patient with incorrect protein data. \nPatient was not add to collection.");
				}

				else {	

					double p1 = arr[3697]; //1st protein
					double p2 = arr[3258]; //2nd protein

					Patient newPatient = new Patient(tokens[0], "unknown", Predictor.predict(p1, p2), arr);
					collection.add(newPatient);
				}
			}	
		} catch (Exception e) {
			System.err.println("");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
		// end of readFile method	

		return null;
	}

	public void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			

			ArrayList<String> idList = new ArrayList<String>();
			idList = this.getIds();

			int max = Integer.parseInt(idList.get(idList.size()-1));

			for(int i = 0; i <= max; i++) {

				if(idList.contains(Integer.toString(i))){

					myOutfile.write(this.getPatient(Integer.toString(i)).get_id() + ",");
					myOutfile.write(this.getPatient(Integer.toString(i)).get_Result() + ",");
					myOutfile.write(this.getPatient(Integer.toString(i)).get_Prediction() + ",");

					for(int j = 0; j < 4775; j++) {
						myOutfile.write(Double.toString(this.getPatient(Integer.toString(i)).get_Proteins()[j]) + ",");
					}

					myOutfile.write("\n");		
				}
			}


			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}	

	//collection to_String method
	public void to_String() {

		int index = 0;

		while(index < collection.size()) {
			System.out.println(collection.get(index)); 
			index++;
		}
	}

}
