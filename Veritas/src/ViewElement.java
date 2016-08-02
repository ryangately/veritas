import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class ViewElement extends JPanel {
	
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JPanel cards;
	private CardLayout cl;
	
	private ViewElementBeat beatView;
	private ViewElementDecision decisionView;
	private ViewElementPointer pointerView;
	private ViewElementLogic logicView;
	private ViewElementToggle toggleView;
	private ViewElementExploration exploreView;
	
	private ModelSceneElement element;
	private ModelStory story;
	private ModelScene scene;
	
	public ViewElement(ModelSceneElement e) {
		element = e;
		
		namePanel = new JPanel();
		nameLabel = new JLabel("Name:");
		nameField = new JTextField();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		cards = new JPanel(new CardLayout());
		cl = (CardLayout)(cards.getLayout());
		beatView = new ViewElementBeat((ModelBeat) e);
		decisionView = new ViewElementDecision(null);
		pointerView = new ViewElementPointer();
		logicView = new ViewElementLogic();
		toggleView = new ViewElementToggle();
		exploreView = new ViewElementExploration();
		
		cards.add(beatView, "beat");
		cards.add(decisionView, "decision");
		cards.add(pointerView, "pointer");
		cards.add(logicView, "logic");
		cards.add(toggleView, "toggle");
		cards.add(exploreView, "explore");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(namePanel);
		this.add(cards);
	}
	
	/** ACCESSORS */
	public JTextField getNameField() {
		return nameField;
	}
	
	public JComboBox getLeftActorComboBox() {
		return beatView.getLeftActorComboBox();
	}
	
	public JComboBox getMidActorComboBox() {
		return beatView.getMidActorComboBox();
	}
	
	public JComboBox getRightActorComboBox() {
		return beatView.getRightActorComboBox();
	}
	
	public JTextField getSpeakerField() {
		return beatView.getSpeakerField();
	}
	
	public JTextPane getDialogPane() {
		return beatView.getDialogPane();
	}
	
	public JComboBox getNextElementComboBox() {
		return beatView.getNextBox();
	}
	
	public JCheckBox getUsePrevious() {
		return beatView.getUsePrevious();
	}
	
	// decision accessors
	public JCheckBox getDecisionCheck(int num) {
		return decisionView.getDecisionCheck(num);
	}
	
	public JTextField getDecisionText(int num) {
		return decisionView.getDecisionText(num);
	}
	
	public JComboBox getDecisionCombo(int num) {
		return decisionView.getDecisionComboBox(num);
	}
	
	public JComboBox getPointerCombo() {
		return pointerView.getPointerBox();
	}
	
	/** MUTATORS */
	public void setElement(ModelSceneElement e) {
		element = e;
		updateName();
		if (story != null && e instanceof ModelBeat) {
			beatView.setElement((ModelBeat) e, story, scene);
			cl.show(cards, "beat");
		}
		else if (e instanceof ModelDecision) {
			decisionView.setElement((ModelDecision) e, story, scene);
			cl.show(cards, "decision");
		}
		else if (e instanceof ModelPointer) {
			pointerView.setElement((ModelPointer) e);
			pointerView.setStory(story);
			cl.show(cards, "pointer");
		}
		else if (e instanceof ModelLogic) {
			logicView.setStory(story);
			logicView.setScene(scene);
			logicView.setElement((ModelLogic) e);
			cl.show(cards, "logic");
		}
		else if (e instanceof ModelToggle) {
			toggleView.setStory(story);
			toggleView.setScene(scene);
			toggleView.setElement((ModelToggle) e);
			cl.show(cards, "toggle");
		}
		else if (e instanceof ModelExploration) {
			exploreView.setStory(story);
			exploreView.setScene(scene);
			exploreView.setElement((ModelExploration) e);
			cl.show(cards, "explore");
		}
	}
	
	public void setStory(ModelStory s) {
		story = s;
	}
	
	public void setScene(ModelScene s) {
		scene = s;
	}
	
	public void updateName() {
		nameField.setText(element.getName());
	}

}
