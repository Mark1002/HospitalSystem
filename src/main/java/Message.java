

import java.util.List;

public class Message {

	private List<String> tokens;
	
	public Message(List<String> tokens) {
		super();
		this.tokens = tokens;
	}

	public String getType(){
		return tokens.get(0);
	}
	
	public List<String> getParameters(){
		List<String> rest = tokens;
		rest.remove(0);
		return rest;
	}
}
