import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;

public class ModelScene {
	
	// class variables
	private String name;
	private ArrayList<ModelSceneElement> elements;
	private int background;
	private int startElement;
	
	// main constructor
	public ModelScene(String nam, int bg) {
		name = nam;
		background = bg;
		elements = new ArrayList<ModelSceneElement>();
		startElement = 0;
	}
	
	/** MUTATORS */
	public void addElement(ModelSceneElement obj) {
		elements.add(obj);
	}
	
	public void setStartElement(int e) {
		startElement = e;
	}
	
	public void setBackground(int bg) {
		background = bg;
	}
	
	/** ACCESSORS */
	public ModelSceneElement getElement(int i) {
		return elements.get(i);
	}
	
	public int getBackground() {
		return background;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStartElement() {
		return startElement;
	}
	
	// returns the number of elements in the scene
	public int getSceneSize() {
		return elements.size();
	}
	
	public DefaultComboBoxModel<String> getElementListWithType() {
		DefaultComboBoxModel<String> nextBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < elements.size(); ++i) {
			String eName = elements.get(i).getName();
			if (elements.get(i) instanceof ModelBeat) {
				eName += " [Beat]";
			}
			else if (elements.get(i) instanceof ModelDecision) {
				eName += " [Decision]";
			}
			else if (elements.get(i) instanceof ModelExploration) {
				eName += " [Explore]";
			}
			else if (elements.get(i) instanceof ModelLogic) {
				eName += " [Logic]";
			}
			else if (elements.get(i) instanceof ModelToggle) {
				eName += " [Toggle]";
			}
			else {
				eName += " [Pointer]";
			}
			if (i == startElement) {
				eName += " *";
			}
			nextBoxModel.addElement(eName);
		}
		return nextBoxModel;
	}

}
