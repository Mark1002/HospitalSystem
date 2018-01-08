

import java.util.List;

public class RegisterCommand extends Command {

	private RegisterParameter registerParameter;

	public RegisterParameter getRegisterParameter() {
		return registerParameter;
	}

	public void setRegisterParameter(RegisterParameter registerParameter) {
		this.registerParameter = registerParameter;
	}

	private static RegisterCommand registerCommand;

	public static synchronized Command getInstanceByMessage(Message message, Hospital hospital) {
		if (registerCommand == null) {
			registerCommand = new RegisterCommand();
		}

		registerCommand.setMessage(message);
		registerCommand.setHospital(hospital);

		// 解析Message : register Dr.Lee
		RegisterParameter parameter = new RegisterParameter();
		
		List<String> parameters = message.getParameters();
		parameter.setPhysicianName(parameters.get(0));
		registerCommand.setRegisterParameter(parameter);

		return registerCommand;
	}

	@Override
	public void execute() {
		Physician physician = this.getHospital().findPhysician(registerParameter.getPhysicianName());
		if (physician == null) {
			this.getHospital().addLog("Physician not registered!");
			
			physician = new Physician();
			physician.setPhysicianName(registerParameter.getPhysicianName());

			this.getHospital().registerPhysician(physician);
		}
	}

}
