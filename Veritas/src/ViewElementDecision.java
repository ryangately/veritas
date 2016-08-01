import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewElementDecision extends JPanel {

	private ModelDecision decision;
	private ModelStory story;
	private ModelScene scene;
	private ArrayList<ViewDecisionPanel> decisions;
	
	public ViewElementDecision(ModelDecision e) {
		decision = e;
		this.setBorder(BorderFactory.createTitledBorder("Decision"));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		decisions = new ArrayList<ViewDecisionPanel>(6);
		for (int i = 0; i < 6; ++i) {
			decisions.add(new ViewDecisionPanel(Integer.toString(i + 1)));
			this.add(decisions.get(i));
		}
	}
	
	/** ACCESSORS */
	public JCheckBox getDecisionCheck(int num) {
		return decisions.get(num).getOptionCheck();
	}
	
	public JTextField getDecisionText(int num) {
		return decisions.get(num).getOptionText();
	}
	
	public JComboBox getDecisionComboBox(int num) {
		return decisions.get(num).getOptionBox();
	}
	
	/** MUTATORS */
	public void setElement(ModelDecision d, ModelStory s, ModelScene sc) {
		decision = d;
		story = s;
		scene = sc;
		updateComboBoxes();
		int count = d.getDecisionCount();
		for (int i = 0; i < count; ++i) {
			if (i > 0) {
				decisions.get(i).getOptionCheck().setSelected(true);
			}
			decisions.get(i).getOptionText().setText(d.getDecision(i));
			decisions.get(i).getOptionBox().setSelectedIndex(d.getNextForDecision(i));
		}
	}
	
	// builds the list of elements in the current scene
	public void updateComboBoxes() {
		DefaultComboBoxModel<String> decisionOneBoxModel = scene.getElementListWithType();
		decisions.get(0).getOptionBox().setModel(decisionOneBoxModel);
		
		DefaultComboBoxModel<String> decisionTwoBoxModel = scene.getElementListWithType();
		decisions.get(1).getOptionBox().setModel(decisionTwoBoxModel);
		
		DefaultComboBoxModel<String> decisionThreeBoxModel = scene.getElementListWithType();
		decisions.get(2).getOptionBox().setModel(decisionThreeBoxModel);
		
		DefaultComboBoxModel<String> decisionFourBoxModel = scene.getElementListWithType();
		decisions.get(3).getOptionBox().setModel(decisionFourBoxModel);
		
		DefaultComboBoxModel<String> decisionFiveBoxModel = scene.getElementListWithType();
		decisions.get(4).getOptionBox().setModel(decisionFiveBoxModel);
		
		DefaultComboBoxModel<String> decisionSixBoxModel = scene.getElementListWithType();
		decisions.get(5).getOptionBox().setModel(decisionSixBoxModel);
		
		for (int i = 0; i < 6; ++i) {
			decisions.get(i).getOptionBox().setSelectedIndex(decision.getNextForDecision(i));
		}
	}
}
