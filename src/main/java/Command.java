

public abstract class Command {
	
	public static final String COMMAND_REGISTER = "register";
	public static final String COMMAND_ADMIT = "admit";
	public static final String COMMAND_TREAT = "treat";
	public static final String COMMAND_SHOW = "show";
	
	private Hospital hospital;
	private Message message;

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

//	public abstract static Command getInstanceByMessage(Message message, Hospital hospital);

	public abstract void execute();

}
