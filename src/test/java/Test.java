import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

public class Test {

	@org.junit.Test
	public void test1() throws Exception{
    	File inputFile = ApplicationUtils.getDataSource("sampleInput");
    	String content = ApplicationUtils.parseFile(inputFile);
		List<String> inputList = ApplicationUtils.parseFromContent(content);
		System.out.println(inputList);
		
		assertTrue(inputList == null);
	}
	
	@org.junit.Test
	public void test2() throws Exception{
    	File inputFile = ApplicationUtils.getDataSource("sampleInput");
    	String content = ApplicationUtils.parseFile(inputFile);
		List<String> inputList = ApplicationUtils.parseFromContent(content);
		System.out.println(inputList);
		
		assertTrue(inputList != null);
	}

}
