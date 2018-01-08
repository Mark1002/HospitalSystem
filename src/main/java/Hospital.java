

import java.util.LinkedList;
import java.util.List;

public class Hospital {

	private List<Physician> physicians = new LinkedList<Physician>();
	private List<Patient> patients = new LinkedList<Patient>();
	private List<String> commandLogs = new LinkedList<String>();

	public void registerPhysician(Physician physician) {
		physicians.add(physician);
	}

	public Physician findPhysician(String physicianName) {
		for (Physician physician : physicians) {
			if (physicianName.equals(physician.getPhysicianName())) {
				return physician;
			}
		}
		return null;
	}

	public void admitPatient(Patient patient) {
		patients.add(patient);
	}

	public Patient findPatient(String patientName) {
		for (Patient patient : patients) {
			if (patientName.equals(patient.getPatientName())) {
				return patient;
			}
		}
		return null;
	}

	public void addLog(String log) {
		commandLogs.add(log);
	}
	
	public List<String> dumpLog() {
		return commandLogs;
	}
	
	public void executeCommand(Command command){
		command.execute();
	}
}
