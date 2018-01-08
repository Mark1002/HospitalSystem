import static org.junit.Assert.*;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TestTestCase {
	
	private static Message parseMessage(String inputMessage){
		List<String> tokens = new LinkedList<String>();
		String[] sArray = inputMessage.trim().split(" ");
		Collections.addAll(tokens, sArray);
		Message message = new Message(tokens);
		return message;
	}
	
	private static Command generateCommand(Message message, Hospital hospital){
		// 依Message判斷是哪一種Command
		if (Command.COMMAND_REGISTER.equalsIgnoreCase(message.getType())){
			return RegisterCommand.getInstanceByMessage(message, hospital);
		} else if (Command.COMMAND_ADMIT.equalsIgnoreCase(message.getType())){
			return AdmitCommand.getInstanceByMessage(message, hospital);
		} else if (Command.COMMAND_TREAT.equalsIgnoreCase(message.getType())){
			return TreatCommand.getInstanceByMessage(message, hospital);
		} else if (Command.COMMAND_SHOW.equalsIgnoreCase(message.getType())){
			return ShowCommand.getInstanceByMessage(message, hospital);
		} else {
			return null;
		}
	}

	@org.junit.Test
	public void test1() throws Exception{
    	File inputFile = ApplicationUtils.getDataSource("testcaseInput1");
    	String content = ApplicationUtils.parseFile(inputFile);
		List<String> inputList = ApplicationUtils.parseFromContent(content);
		
		Hospital hospital = new Hospital();
		for(String input : inputList){
			Message message = parseMessage(input);
			Command command = generateCommand(message, hospital);
			if (command != null){
				hospital.executeCommand(command);
			} else {
				System.out.println("not regular command!");
			}
		}
		System.out.println(hospital.dumpLog());
	}
	
	@org.junit.Test
	public void test2() throws Exception{
		assertTrue(true == true);
	}

}
