
public class ModelStringVar {
	
	private String name;
	private String var;
	private String defaultVal;
	
	ModelStringVar(String n, String v) {
		name = n;
		var = v;
		defaultVal = v;
	}
	
	// accessors
	public String getName() {
		return name;
	}
	
	public String getVariable() {
		return var;
	}
	
	public String getDefault() {
		return defaultVal;
	}
	
	// mutators
	public void setName(String n) {
		name = n;
	}
	
	public void setVariable(String v) {
		var = v;
	}

}
