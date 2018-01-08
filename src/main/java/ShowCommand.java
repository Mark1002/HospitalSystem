

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ShowCommand extends Command {

	private static ShowCommand showCommand;

	public static synchronized Command getInstanceByMessage(Message message, Hospital hospital) {
		if (showCommand == null) {
			showCommand = new ShowCommand();
		}

		showCommand.setMessage(message);
		showCommand.setHospital(hospital);

		// 解析Message : show
		return showCommand;
	}

	@Override
	public void execute() {
		
		// Console log
		for (String log : this.getHospital().dumpLog()) {
			System.out.println(log);
		}

		// File
//		String dir = System.getProperty("user.dir");
//		File file = new File(dir + "/output/result");
//		try {
//			FileOutputStream fos = new FileOutputStream(file);
//			PrintWriter pw = new PrintWriter(fos);
//			for (String log : this.getHospital().dumpLog()) {
//				pw.println(log);
//			}
//			pw.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
