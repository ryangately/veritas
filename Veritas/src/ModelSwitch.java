
public class ModelSwitch {
	
	// class vars
	private String name;
	private Boolean bin;
	
	public ModelSwitch(String name, Boolean init) {
		this.name = name;
		bin = init;
	}
	
	public String getName() {
		return name;
	}
	
	public Boolean getValue() {
		return bin;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(Boolean val) {
		bin = val;
	}

}
