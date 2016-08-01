
public class ModelToggle extends ModelSceneElement {
	
	private int type; // 0 for bool, 1 for set, 2 for increment, 3 for decrement
	private Boolean newBoolVal;
	private int newIntVal;
	private int index; // location of switch
	
	public ModelToggle(String name) {
		this.name = name;
		type = -1;
		newBoolVal = null;
		newIntVal = -1;
	}
	
	/** MUTATORS */
	public void setBool(Boolean val, int index) {
		type = 0;
		newBoolVal = val;
		newIntVal = -1;
		this.index = index;
	}
	
	public void setValue(int val, int index) {
		type = 1;
		newIntVal = val;
		newBoolVal = null;
		this.index = index;
	}
	
	public void setIncrement(int index) {
		type = 2;
		newBoolVal = null;
		newIntVal = -1;
		this.index = index;
	}
	
	public void setDecrement(int index) {
		type = 3;
		newBoolVal = null;
		newIntVal = -1;
		this.index = index;
	}
	
	/** ACCESSORS */
	public int getType() {
		return type;
	}
	
	public Boolean getNewBool() {
		return newBoolVal;
	}
	
	public int getNewValue() {
		return newIntVal;
	}
	
	public int getIndex() {
		return index;
	}

}
