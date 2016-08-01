import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class ViewElementBeat extends JPanel {
	
	private JLabel leftActorLbl;
	private JComboBox leftActorBox;
	private JLabel rightActorLbl;
	private JComboBox rightActorBox;
	private JLabel midActorLbl;
	private JComboBox midActorBox;
	private JPanel actorControlPanel;
	private JLabel speakerLbl;
	private JTextField speakerTextField;
	private JCheckBox speakerUsePrevCheck;
	private JLabel speakerUsePrevLbl;
	private JLabel textLbl;
	private JTextPane textPane;
	private JPanel nextPanel;
	private JLabel nextLbl;
	private JComboBox nextBox;
	private ModelBeat beat;
	private ModelScene scene;
	private ModelStory story;
	
	public ViewElementBeat(ModelBeat e) {
		beat = e;
		leftActorLbl = new JLabel("Left Actor:");
		leftActorBox = new JComboBox();
		rightActorLbl = new JLabel("Right Actor:");
		rightActorBox = new JComboBox();
		midActorLbl = new JLabel("Mid Actor:");
		midActorBox = new JComboBox();
		speakerLbl = new JLabel("Speaker:");
		speakerTextField = new JTextField();
		speakerUsePrevCheck = new JCheckBox();
		speakerUsePrevLbl = new JLabel("Use Previous:");
		textLbl = new JLabel("Dialog:");
		
		actorControlPanel = new JPanel();
		actorControlPanel.setLayout(new GridLayout(0,2));
		actorControlPanel.add(leftActorLbl);
		actorControlPanel.add(leftActorBox);
		actorControlPanel.add(rightActorLbl);
		actorControlPanel.add(rightActorBox);
		actorControlPanel.add(midActorLbl);
		actorControlPanel.add(midActorBox);
		actorControlPanel.add(speakerLbl);
		actorControlPanel.add(speakerTextField);
		actorControlPanel.add(speakerUsePrevLbl);
		actorControlPanel.add(speakerUsePrevCheck);
		actorControlPanel.add(textLbl);
		
		textPane = new JTextPane();
		textPane.setEditable(true);
		//textPane.setText("none");
		
		nextPanel = new JPanel();
		nextPanel.setLayout(new BoxLayout(nextPanel, BoxLayout.LINE_AXIS));
		nextLbl = new JLabel("Next Element:");
		nextBox = new JComboBox();
		nextPanel.add(nextLbl);
		nextPanel.add(nextBox);
		
		this.setBorder(BorderFactory.createTitledBorder("Beat"));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(actorControlPanel);
		this.add(textPane);
		this.add(nextPanel);
	}
	
	/** ACCESSORS */
	public JComboBox getLeftActorComboBox() {
		return leftActorBox;
	}
	
	public JComboBox getMidActorComboBox() {
		return midActorBox;
	}
	
	public JComboBox getRightActorComboBox() {
		return rightActorBox;
	}
	
	public JTextField getSpeakerField() {
		return speakerTextField;
	}
	
	public JTextPane getDialogPane() {
		return textPane;
	}
	
	public JComboBox getNextBox() {
		return nextBox;
	}
	
	public JCheckBox getUsePrevious() {
		return speakerUsePrevCheck;
	}
	
	/** MUTATORS */
	public void setElement(ModelBeat e, ModelStory s, ModelScene scene) {
		beat = e;
		story = s;
		
		// build image lists for combo boxes
		DefaultComboBoxModel<String> leftActorBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < story.getActorsSize(); ++i) {
			leftActorBoxModel.addElement(story.getActorName(i));
		}
		leftActorBoxModel.addElement("Previous");
		leftActorBox.setModel(leftActorBoxModel);
		if (e.getLeftActor() == -1) {
			leftActorBox.setSelectedIndex(leftActorBoxModel.getSize() - 1);
		} else {
			leftActorBox.setSelectedIndex(e.getLeftActor());
		}
		
		// list for mid actor
		DefaultComboBoxModel<String> midActorBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < story.getActorsSize(); ++i) {
			midActorBoxModel.addElement(story.getActorName(i));
		}
		midActorBoxModel.addElement("Previous");
		midActorBox.setModel(midActorBoxModel);
		if (e.getMidActor() == -1) {
			midActorBox.setSelectedIndex(midActorBoxModel.getSize() - 1);
		} else {
			midActorBox.setSelectedIndex(e.getMidActor());
		}
		
		// list for right actor
		DefaultComboBoxModel<String> rightActorBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < story.getActorsSize(); ++i) {
			rightActorBoxModel.addElement(story.getActorName(i));
		}
		rightActorBoxModel.addElement("Previous");
		rightActorBox.setModel(rightActorBoxModel);
		if (e.getRightActor() == -1) {
			rightActorBox.setSelectedIndex(rightActorBoxModel.getSize() - 1);
		} else {
			rightActorBox.setSelectedIndex(e.getRightActor());
		}
		
		// load text
		if (e.getSpeaker() == null) {
			speakerUsePrevCheck.setSelected(true);
		}
		else {
			speakerUsePrevCheck.setSelected(false);
			speakerTextField.setText(e.getSpeaker());
		}
		textPane.setText(e.getDialog());
		
		// load next box
		DefaultComboBoxModel<String> nextBoxModel = scene.getElementListWithType();
		nextBoxModel.addElement("None");
		nextBox.setModel(nextBoxModel);
		if (e.getNext() == -1) {
			nextBox.setSelectedIndex(nextBoxModel.getSize() - 1);
		} else {
			nextBox.setSelectedIndex(e.getNext());
		}
	}
}
