
public class ModelLogic extends ModelSceneElement {
	
	private int index; // location of switch to compare to
	private Integer valComparison;
	private int type; // 0 for equals, 1 for less than, 2 for greater than
	private int nextFalse;
	
	public ModelLogic(String name) {
		this.name = name;
		next = -1;
		type = -1;
		index = -1;
		valComparison = null;
		nextFalse = -1;
	}
	
	/** MUTATORS */
	public void setComparison(int index, int val, int type) {
		this.index = index;
		valComparison = val;
		this.type = type;
	}
	
	public void setComparison(int index) {
		this.index = index;
		type = -1;
		valComparison = null;
	}
	
	public void setNextFalse(int fail) {
		nextFalse = fail;
	}
	
	/** ACCESSORS */
	public int getIndex() {
		return index;
	}
	
	public int getType() {
		return type;
	}
	
	public int getFalseNext() {
		return nextFalse;
	}
	
	public Integer getValComparison() {
		return valComparison;
	}
	
	public int compare(Boolean val) {
		if (val) {
			return next;
		}
		else {
			return nextFalse;
		}
	}
	
	public int compare(int val) {
		if (type == 0) { // equals comparison
			if (val == valComparison) {
				return next;
			}
			else {
				return nextFalse;
			}
		}
		else if (type == 1) { // less than comparison
			if (val < valComparison) {
				return next;
			}
			else {
				return nextFalse;
			}
		}
		else {
			if (val > valComparison) { // greater than comparison
				return next;
			}
			else {
				return nextFalse;
			}
		}
	}
}
