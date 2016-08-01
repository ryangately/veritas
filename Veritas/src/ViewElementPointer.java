import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewElementPointer extends JPanel {

	private JLabel nextLbl;
	private JComboBox nextBox;
	private ModelStory story;
	private ModelPointer pointer;
	
	public ViewElementPointer() {
		nextLbl = new JLabel("Next Scene:");
		nextBox = new JComboBox();
		story = null;
		pointer = null;
		
		this.setBorder(BorderFactory.createTitledBorder("Pointer"));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(nextLbl);
		this.add(nextBox);
	}
	
	/** ACCESSORS */
	public JComboBox getPointerBox() {
		return nextBox;
	}
	
	/** MUTATORS */
	public void setElement(ModelPointer pointer) {
		this.pointer = pointer;
	}
	
	public void setStory(ModelStory story) {
		this.story = story;
		nextBox.setModel(story.getSceneBoxList());
		nextBox.setSelectedIndex(pointer.getNext());
	}
}
