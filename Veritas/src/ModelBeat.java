/**
 * Holds information on what to display for a beat in a scene.
 * Speaker should be set to null to use the same speaker from the previous beat.
 * @author Ryan
 *
 */

public class ModelBeat extends ModelSceneElement {
	
	// class variables
	private String dialog;
	private String speaker;
	private int leftActor;
	private int rightActor;
	private int midActor;
	
	// main constructor
	public ModelBeat(String name) {
		this.name = name;
		dialog = null;
		speaker = null;
		leftActor = -1;
		rightActor = -1;
		midActor = -1;
		next = -1;
	}
	
	/** ACCESSORS */
	public String getDialog() {
		return dialog;
	}
	
	public String getSpeaker() {
		return speaker;
	}
	
	public int getLeftActor() {
		return leftActor;
	}
	
	public int getRightActor() {
		return rightActor;
	}
	
	public int getMidActor() {
		return midActor;
	}
	
	/** MUTATORS */
	public void setDialog(String text) {
		dialog = text;
	}
	
	public void setSpeaker(String spea) {
		speaker = spea;
	}
	
	public void setLeftActor(int leftA) {
		leftActor = leftA;
	}
	
	public void setRightActor(int rightA) {
		rightActor = rightA;
	}
	
	public void setMidActor(int midA) {
		midActor = midA;
	}

}
