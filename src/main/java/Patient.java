

import java.util.LinkedList;
import java.util.List;

public class Patient {

	private String patientName;
	private Physician physician;
	private List<Treatment> treatments = new LinkedList<Treatment>();

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void addTreatment(Treatment treatment) {
		treatments.add(treatment);
	}
	
	public void executeCommand(Command command){
		command.execute();
	}
}
