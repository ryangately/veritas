
public class ModelCounter {
	
	// class vars
	private String name;
	private int val;
	
	public ModelCounter(String name) {
		this.name = name;
		val = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(int val) {
		this.val = val;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return val;
	}

}
