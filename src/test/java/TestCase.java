import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TestCase {
	
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
	
	private static Hospital executeMainProgram(List<String> inputList) {
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
		return hospital;
	}
	
	private static List<String> readFiletoList(String fileName) throws IOException {
		File file = ApplicationUtils.getDataSource(fileName);
    	String content = ApplicationUtils.parseFile(file);
		List<String> contentList = ApplicationUtils.parseFromContent(content);
		return contentList;
	}

	@org.junit.Test
	public void test1() throws Exception{
		List<String> inputList = readFiletoList("sampleInput");
		// sampleOutput is expect answer
		List<String> answerList = readFiletoList("sampleOutput");
		Hospital hospital = executeMainProgram(inputList);
		List<String> resultList = hospital.dumpLog();
		// assert program is equal to expect answer 
		assertTrue(resultList.equals(answerList));
	}
	
	@org.junit.Test
	public void test2() throws Exception{
		List<String> inputList = readFiletoList("testcaseInput1");
		// sampleOutput is expect answer
		List<String> answerList = readFiletoList("testcaseOutput1");
		Hospital hospital = executeMainProgram(inputList);
		List<String> resultList = hospital.dumpLog();
		// assert program is equal to expect answer 
		assertTrue(resultList.equals(answerList));
	}

	@org.junit.Test
	public void test3() throws Exception{
		List<String> inputList = readFiletoList("testcaseInput2");
		// sampleOutput is expect answer
		List<String> answerList = readFiletoList("testcaseOutput2");
		Hospital hospital = executeMainProgram(inputList);
		List<String> resultList = hospital.dumpLog();
		// assert program is equal to expect answer 
		assertTrue(resultList.equals(answerList));
	}
}
