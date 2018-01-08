

import java.util.LinkedList;
import java.util.List;

public class Physician {

	private String physicianName;
	private List<Patient> patients = new LinkedList<Patient>();

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void addPatients(Patient patient) {
		patients.add(patient);
	}

}
