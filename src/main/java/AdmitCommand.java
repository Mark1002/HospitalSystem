

import java.util.List;

public class AdmitCommand extends Command {

	private AdmitParameter admitParameter;

	public AdmitParameter getAdmitParameter() {
		return admitParameter;
	}

	public void setAdmitParameter(AdmitParameter admitParameter) {
		this.admitParameter = admitParameter;
	}

	private static AdmitCommand admitCommand;

	public static synchronized Command getInstanceByMessage(Message message, Hospital hospital) {
		if (admitCommand == null) {
			admitCommand = new AdmitCommand();
		}

		admitCommand.setMessage(message);
		admitCommand.setHospital(hospital);

		// 解析Message : admit Dr.Lee MarkLiu
		AdmitParameter parameter = new AdmitParameter();
		
		List<String> parameters = message.getParameters();
		parameter.setPhysicianName(parameters.get(0));
		parameter.setPatientName(parameters.get(1));
		admitCommand.setAdmitParameter(parameter);

		return admitCommand;
	}

	@Override
	public void execute() {
		Physician physician = this.getHospital().findPhysician(admitParameter.getPhysicianName());
		if (physician == null) {
			this.getHospital().addLog("Physician not registered!");
		} else {
			Patient patient = this.getHospital().findPatient(admitParameter.getPatientName());
			if (patient == null) {
				this.getHospital().addLog("Patient not admitted!");
				
				patient = new Patient();
				patient.setPatientName(admitParameter.getPatientName());
				patient.setPhysician(physician);

				physician.addPatients(patient);
				
				StringBuffer sb = new StringBuffer();
				for (Patient p : physician.getPatients()){
					sb.append(" " + p.getPatientName());
				}
				this.getHospital().addLog("[Physician] Name: " + physician.getPhysicianName() + " AdmitPatients:" + sb.toString());
				
				this.getHospital().admitPatient(patient);
				this.getHospital().addLog("[Patient] Name: " + patient.getPatientName() + " AdmitPhysician: " + physician.getPhysicianName());
			}
		}
	}

}
