//Alex Salas
//CSCI 3381
//Project1
//Patient Class

public class Patient {

	private String result; //CR or DP
	private String prediction; // unknown, predCR, predDP
	private String id; 
	private double[] proteins; //4776 proteins, significant: [3697], [3258]

	//default constructor
	public Patient(){
		result = null;
		prediction = null;
		id = null;
		proteins = null;
	}

	//Patient constructor, parameterized
	public Patient(String pID, String pResult, String pPred, double [] pPro){
		result = pResult;
		prediction = pPred;
		id = pID;
		proteins = pPro;
	}

	//result getter, returns patient care result
	public String get_Result(){
		return result;
	}

	//result setter, sets result
	public void set_Result(String res) {
		if(res != null) {
			result = res;
		}
	}

	//prediction getter, returns patient care result prediction
	public String get_Prediction(){
		return prediction;
	}

	//id getter, returns patient ID
	public String get_id(){
		return id;
	}

	//protein getter, returns array of pateint proteins
	public double[] get_Proteins() {
		return proteins;
	}

	//toString method, returns a string consisting of the patient's id, result, prediction and significant protein values
	public String toString() {
		String to_string = (id + " " + result + " " + prediction + " " + proteins[3258] + " " + proteins[3697]);
		return to_string;
	}	
}
