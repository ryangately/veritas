
public class ModelDecision extends ModelSceneElement {
	
	// class variables
	private String[] decisionText = {null, null, null, null, null, null};
	private int[] decisionPointers = {-1, -1, -1, -1, -1, -1};
	
	public ModelDecision(String name) {
		this.name = name;
		next = -1;
	}
	
	// sets a decision value to the string and next specified
	public void setDecision(int num, String text, int next) {
		decisionText[num] = text;
		decisionPointers[num] = next;
	}
	
	public void setNextForDecision(int num, int next) {
		decisionPointers[num] = next;
	}
	
	// returns the number of available decisions
	public int getDecisionCount() {
		int count = 0;
		for (int i: decisionPointers) {
			if (i > -1) {
				count++;
			}
		}
		return count;
	}
	
	// returns the next value associated with the specified decision
	public int getNextForDecision(int num) {
		return decisionPointers[num];
	}
	
	// returns the text value of the specified decision
	public String getDecision(int num) {
		return decisionText[num];
	}

}
