import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Veritas {
	
	// class vars
	final private String VERSION = "Veritas v0.6.801";
	private Veritas veritas;
	private ViewMain viewMain;
	
	//debug
	private ModelStory story;
	
	// file chooser
	private JFileChooser fileChooser;
	private ControlFileHandler fileHandler;
	
	// components with listeners
	private JMenuItem menuNew;
	private JMenuItem menuSave;
	private JMenuItem menuDebugSave;
	private JMenuItem menuOpen;
	private JMenuItem menuSettings;
	private JMenuItem menuImages;
	private JMenuItem menuTest;
	private JMenuItem menuVariables;
	private JList sceneList;
	private JList elementList;
	private JTextField elementNameField;
	private JButton addSceneBtn;
	private JButton startSceneBtn;
	private JButton addElementBtn;
	private JButton startElementBtn;
	
	// view element comboboxes
	private JComboBox leftActorComboBox;
	private JComboBox midActorComboBox;
	private JComboBox rightActorComboBox;
	private JTextField speakerTextField;
	private JTextPane dialogPane;
	private JComboBox nextElementComboBox;
	private JCheckBox speakerUsePrevCheck;
	
	// view decision components
	private JCheckBox decisionTwoCheck;
	private JCheckBox decisionThreeCheck;
	private JCheckBox decisionFourCheck;
	private JCheckBox decisionFiveCheck;
	private JCheckBox decisionSixCheck;
	private JTextField decisionOneText;
	private JTextField decisionTwoText;
	private JTextField decisionThreeText;
	private JTextField decisionFourText;
	private JTextField decisionFiveText;
	private JTextField decisionSixText;
	private JComboBox decisionOneCombo;
	private JComboBox decisionTwoCombo;
	private JComboBox decisionThreeCombo;
	private JComboBox decisionFourCombo;
	private JComboBox decisionFiveCombo;
	private JComboBox decisionSixCombo;
	
	// view pointer
	private JComboBox pointerComboBox;

	public static void main(String[] args) {
		try {
			new Veritas();
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					e.toString(),
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Veritas() throws Exception {
		viewMain = new ViewMain(VERSION);
		menuNew = viewMain.getFileMenuNew();
		menuSave = viewMain.getFileMenuSave();
		menuDebugSave = viewMain.getFileMenuDebugSave();
		menuOpen = viewMain.getFileMenuOpen();
		menuSettings = viewMain.getStoryMenuSettings();
		menuImages = viewMain.getStoryMenuImages();
		menuVariables = viewMain.getStoryMenuVariables();
		menuTest = viewMain.getBuildMenuTest();
		sceneList = viewMain.getViewStory().getSceneJList();
		elementList = viewMain.getViewStory().getElementJList();
		elementNameField = viewMain.getViewStory().getNameField();
		addSceneBtn = viewMain.getViewStory().getAddSceneBtn();
		startSceneBtn = viewMain.getViewStory().getStartSceneBtn();
		addElementBtn = viewMain.getViewStory().getAddElementBtn();
		startElementBtn = viewMain.getViewStory().getStartElementBtn();
		leftActorComboBox = viewMain.getViewStory().getLeftActorBox();
		midActorComboBox = viewMain.getViewStory().getMidActorBox();
		rightActorComboBox = viewMain.getViewStory().getRightActorBox();
		speakerTextField = viewMain.getViewStory().getSpeakerField();
		speakerUsePrevCheck = viewMain.getViewStory().getUsePrevious();
		dialogPane = viewMain.getViewStory().getDialogPane();
		nextElementComboBox = viewMain.getViewStory().getNextElementBox();
		
		// decision component initialization
		decisionTwoCheck = viewMain.getViewStory().getDecisionCheck(1);
		decisionThreeCheck = viewMain.getViewStory().getDecisionCheck(2);
		decisionFourCheck = viewMain.getViewStory().getDecisionCheck(3);
		decisionFiveCheck = viewMain.getViewStory().getDecisionCheck(4);
		decisionSixCheck = viewMain.getViewStory().getDecisionCheck(5);
		decisionOneText = viewMain.getViewStory().getDecisionText(0);
		decisionTwoText = viewMain.getViewStory().getDecisionText(1);
		decisionThreeText = viewMain.getViewStory().getDecisionText(2);
		decisionFourText = viewMain.getViewStory().getDecisionText(3);
		decisionFiveText = viewMain.getViewStory().getDecisionText(4);
		decisionSixText = viewMain.getViewStory().getDecisionText(5);
		decisionOneCombo = viewMain.getViewStory().getDecisionCombo(0);
		decisionTwoCombo = viewMain.getViewStory().getDecisionCombo(1);
		decisionThreeCombo = viewMain.getViewStory().getDecisionCombo(2);
		decisionFourCombo = viewMain.getViewStory().getDecisionCombo(3);
		decisionFiveCombo = viewMain.getViewStory().getDecisionCombo(4);
		decisionSixCombo = viewMain.getViewStory().getDecisionCombo(5);
		
		pointerComboBox = viewMain.getViewStory().getPointerCombo();
		
		// add action listeners
		sceneList.addListSelectionListener(new SceneSelectionListener());
		elementList.addListSelectionListener(new ElementSelectionListener());
		elementNameField.addActionListener(new NameChangeListener());
		addSceneBtn.addActionListener(new AddSceneListener());
		startSceneBtn.addActionListener(new StartSceneListener());
		addElementBtn.addActionListener(new AddElementListener());
		startElementBtn.addActionListener(new StartElementListener());
		
		// beat component listeners
		leftActorComboBox.addActionListener(new ActorChangeListener());
		midActorComboBox.addActionListener(new ActorChangeListener());
		rightActorComboBox.addActionListener(new ActorChangeListener());
		//speakerTextField.addActionListener(new SpeakerChangeListener());
		speakerUsePrevCheck.addItemListener(new UsePreviousSpeakerListener());
		nextElementComboBox.addActionListener(new NextElementChangeListener());
		
		// document listener for the speaker text box
		speakerTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateSpeaker();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updateSpeaker();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updateSpeaker();
			}
			
			private void updateSpeaker() {
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setSpeaker(speakerTextField.getText());
			}
		});
		
		// document listener for the dialog box
		dialogPane.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateDialog();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updateDialog();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updateDialog();
			}
			
			private void updateDialog() {
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setDialog(dialogPane.getText());
			}
		});
		
		// decision listeners
		decisionTwoCheck.addItemListener(new DecisionCheckListener());
		decisionThreeCheck.addItemListener(new DecisionCheckListener());
		decisionFourCheck.addItemListener(new DecisionCheckListener());
		decisionFiveCheck.addItemListener(new DecisionCheckListener());
		decisionSixCheck.addItemListener(new DecisionCheckListener());
		
		decisionOneText.getDocument().addDocumentListener(new DecisionTextListener());
		decisionTwoText.getDocument().addDocumentListener(new DecisionTextListener());
		decisionThreeText.getDocument().addDocumentListener(new DecisionTextListener());
		decisionFourText.getDocument().addDocumentListener(new DecisionTextListener());
		decisionFiveText.getDocument().addDocumentListener(new DecisionTextListener());
		decisionSixText.getDocument().addDocumentListener(new DecisionTextListener());
		
		decisionOneCombo.addActionListener(new DecisionComboListener());
		decisionTwoCombo.addActionListener(new DecisionComboListener());
		decisionThreeCombo.addActionListener(new DecisionComboListener());
		decisionFourCombo.addActionListener(new DecisionComboListener());
		decisionFiveCombo.addActionListener(new DecisionComboListener());
		decisionSixCombo.addActionListener(new DecisionComboListener());
		
		pointerComboBox.addActionListener(new PointerComboBoxListener());
		
		// menu item listeners
		menuSave.addActionListener(new SaveStoryListener());
		//menuDebugSave.addActionListener(l);
		menuOpen.addActionListener(new LoadStoryListener());
		menuSettings.addActionListener(new StorySettingsListener());
		menuImages.addActionListener(new StoryImagesListener());
		menuVariables.addActionListener(new StoryVariablesListener());
		menuTest.addActionListener(new BuildTestListener());
		
		// file objects
		fileChooser = new JFileChooser();
		fileHandler = new ControlFileHandler(VERSION);
		
		// debug
		//story = new ViNoDebug().getDebugStory();
		story = new ModelStory("A New Story");
		viewMain.setStory(story);
	}
	
	
	/** ACTION LISTENERS */
	// used to update the list of scene elements
	private class SceneSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (((JList) arg0.getSource()).getSelectedIndex() > -1) {
				viewMain.getViewStory().setSelectedScene(story.getScene(((JList) arg0.getSource()).getSelectedIndex()));
			}
		}
	}
	
	// updates the element viewer
	private class ElementSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (elementList.getSelectedIndex() > -1) {
				viewMain.getViewStory().setSelectedElement(viewMain.getViewStory().getSelectedScene().getElement(elementList.getSelectedIndex()));
			}
		}
	}
	
	// updates the name of the element and the element list when the name field value is changed
	private class NameChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			int index = elementList.getSelectedIndex();
			viewMain.getViewStory().getSelectedElement().setName(elementNameField.getText());
			viewMain.getViewStory().setSelectedScene(viewMain.getViewStory().getSelectedScene());
			elementList.setSelectedIndex(index);
		}
	}
	
	// updates the next element field when the value is changed
	private class NextElementChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewMain.getViewStory().getSelectedElement().setNext(nextElementComboBox.getSelectedIndex());
		}
	}
	
	// file menu listeners
	private class SaveStoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			fileHandler.saveStory(story);
		}
		
	}
	
	private class LoadStoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ModelStory storyFromFile = fileHandler.loadStory();
			if (storyFromFile != null) {
				story = storyFromFile;
				viewMain.setStory(story);
			}
		}
	}
	
	// story menu listeners
	private class StorySettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ViewStorySettings(story);
		}
	}
	
	private class StoryImagesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ViewStoryImages(story);
		}
	}
	
	private class StoryVariablesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ViewStoryVariables(story);
		}
	}
	
	// build menu listeners
	private class BuildTestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				new Game(story);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// scene button listeners
	private class AddSceneListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			new ViewNewScene(viewMain.getFrame(), story);
			viewMain.getViewStory().buildSceneList();
			sceneList.setSelectedIndex(sceneList.getModel().getSize() - 1);
		}
	}
	
	private class StartSceneListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			story.setStartPoint(sceneList.getSelectedIndex());
			viewMain.getViewStory().buildSceneList();
		}
	}
	
	// element button listeners
	private class AddElementListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (sceneList.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null,
					    "No scene selected.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			new ViewNewElement(viewMain.getFrame(), story.getScene(sceneList.getSelectedIndex()));
			viewMain.getViewStory().buildElementList();
			elementList.setSelectedIndex(elementList.getModel().getSize() - 1);
		}
	}
	
	private class StartElementListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewMain.getViewStory().getSelectedScene().setStartElement(elementList.getSelectedIndex());
			viewMain.getViewStory().buildElementList();
		}
	}
	
	// element view listeners
	private class ActorChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == leftActorComboBox) {
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setLeftActor(((JComboBox) ae.getSource()).getSelectedIndex());
			}
			else if (ae.getSource() == midActorComboBox) {
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setMidActor(((JComboBox) ae.getSource()).getSelectedIndex());
			}
			else if (ae.getSource() == rightActorComboBox) {
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setRightActor(((JComboBox) ae.getSource()).getSelectedIndex());
			}
		}
	}
	
	private class UsePreviousSpeakerListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				speakerTextField.setEnabled(false);
				((ModelBeat) viewMain.getViewStory().getSelectedElement()).setSpeaker(null);
			}
			else {
				speakerTextField.setEnabled(true);
				speakerTextField.setText(((ModelBeat) viewMain.getViewStory().getSelectedElement()).getSpeaker());
			}
		}
	}
	
	// decision view listeners
	private class DecisionCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent ie) {
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				((ViewDecisionPanel) ((Component) ie.getSource()).getParent()).setDecisionEnabled(true);
				if (ie.getSource() == decisionTwoCheck) {
					decisionThreeCheck.setEnabled(true);
				}
				else if (ie.getSource() == decisionThreeCheck) {
					decisionFourCheck.setEnabled(true);
				}
				else if (ie.getSource() == decisionFourCheck) {
					decisionFiveCheck.setEnabled(true);
				}
				else {
					decisionSixCheck.setEnabled(true);
				}
			}
			else {
				((ViewDecisionPanel) ((Component) ie.getSource()).getParent()).setDecisionEnabled(false);
				if (ie.getSource() == decisionTwoCheck) {
					decisionTwoText.setText("");
					((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(1, null, -1);
					decisionThreeCheck.setEnabled(false);
					decisionThreeCheck.setSelected(false);
				}
				else if (ie.getSource() == decisionThreeCheck) {
					decisionThreeText.setText("");
					((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(2, null, -1);
					decisionFourCheck.setEnabled(false);
					decisionFourCheck.setSelected(false);
				}
				else if (ie.getSource() == decisionFourCheck) {
					decisionFourText.setText("");
					((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(3, null, -1);
					decisionFiveCheck.setEnabled(false);
					decisionFiveCheck.setSelected(false);
				}
				else if (ie.getSource() == decisionFiveCheck) {
					decisionFiveText.setText("");
					((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(4, null, -1);
					decisionSixCheck.setEnabled(false);
					decisionSixCheck.setSelected(false);
				}
				else if (ie.getSource() == decisionSixCheck) {
					decisionSixText.setText("");
					((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(5, null, -1);
				}
			}
		}
	}
	
	// listener to update decision when the text field is changed
	private class DecisionTextListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateDecisionText(e);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			updateDecisionText(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateDecisionText(e);
		}
		
		private void updateDecisionText(DocumentEvent de) {
			if (de.getDocument() == decisionOneText.getDocument()) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						0, 
						decisionOneText.getText(), 
						decisionOneCombo.getSelectedIndex());
			}
			else if (de.getDocument() == decisionTwoText.getDocument()) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						1, 
						decisionTwoText.getText(), 
						decisionTwoCombo.getSelectedIndex());
			}
			else if (de.getDocument() == decisionThreeText.getDocument()) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						2, 
						decisionThreeText.getText(), 
						decisionThreeCombo.getSelectedIndex());
			}
			else if (de.getDocument() == decisionFourText.getDocument()) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						3, 
						decisionFourText.getText(), 
						decisionFourCombo.getSelectedIndex());
			}
			else if (de.getDocument() == decisionFiveText.getDocument()) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						4, 
						decisionFiveText.getText(), 
						decisionFiveCombo.getSelectedIndex());
			}
			else {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setDecision(
						5, 
						decisionSixText.getText(), 
						decisionSixCombo.getSelectedIndex());
			}
		}
	}
	
	private class DecisionComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == decisionOneCombo) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						0,
						decisionOneCombo.getSelectedIndex());
			}
			else if (ae.getSource() == decisionTwoCombo) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						1,
						decisionTwoCombo.getSelectedIndex());
			}
			else if (ae.getSource() == decisionThreeCombo) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						2,
						decisionThreeCombo.getSelectedIndex());
			}
			else if (ae.getSource() == decisionFourCombo) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						3,
						decisionFourCombo.getSelectedIndex());
			}
			else if (ae.getSource() == decisionFiveCombo) {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						4,
						decisionFiveCombo.getSelectedIndex());
			}
			else {
				((ModelDecision) viewMain.getViewStory().getSelectedElement()).setNextForDecision(
						5,
						decisionSixCombo.getSelectedIndex());
			}
		}
	}
	
	private class PointerComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			viewMain.getViewStory().getSelectedElement().setNext(((JComboBox) ae.getSource()).getSelectedIndex());
		}
	}
}
