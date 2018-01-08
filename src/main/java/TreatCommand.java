

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TreatCommand extends Command {

	private TreatParameter treatParemeter;

	public TreatParameter getTreatParemeter() {
		return treatParemeter;
	}

	public void setTreatParemeter(TreatParameter treatParemeter) {
		this.treatParemeter = treatParemeter;
	}

	private static TreatCommand treatCommand;

	public static synchronized Command getInstanceByMessage(Message message, Hospital hospital) {
		if (treatCommand == null) {
			treatCommand = new TreatCommand();
		}

		treatCommand.setMessage(message);
		treatCommand.setHospital(hospital);

		// 解析Message : treat Dr.Lee MarkLiu 2017/10/10 9:00
		TreatParameter parameter = new TreatParameter();
		
		List<String> parameters = message.getParameters();
		parameter.setPhysicianName(parameters.get(0));
		parameter.setPatientName(parameters.get(1));

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

		parameter.setDate(LocalDate.parse(parameters.get(2), dateFormatter));
		parameter.setTime(LocalTime.parse(parameters.get(3), timeFormatter));
		treatCommand.setTreatParemeter(parameter);

		return treatCommand;
	}

	@Override
	public void execute() {
		Physician physician = this.getHospital().findPhysician(treatParemeter.getPhysicianName());
		Patient patient = this.getHospital().findPatient(treatParemeter.getPatientName());
		if (physician == null) {
			this.getHospital().addLog("Physician not registered!");
		} else if (patient == null) {
			this.getHospital().addLog("Patient not admitted!");
		} else {
			Treatment treatment = new Treatment();
			treatment.setDate(treatParemeter.getDate());
			treatment.setTime(treatParemeter.getTime());
			treatment.setPhysician(physician);
			treatment.setPatient(patient);

			patient.addTreatment(treatment);
			
			//TODO:若以sampleOutput來看，date & time 列為String類型才能完全滿足 9:00的Pattern，否則會出現09:00
			this.getHospital().addLog("[Treatment] Date: " + treatment.getDate() + " Time: " + treatment.getTime()
					+ " Results: " + treatment.getPatient().getPatientName() + " was treated by " + treatment.getPhysician().getPhysicianName());

		}
	}

}
