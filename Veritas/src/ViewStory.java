import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;


public class ViewStory extends JPanel {
	
	// class vars
	private JPanel scenePanel;
	private JPanel elementPanel;
	private JPanel controlPanel;
	private JList sceneList;
	private JList elementList;
	private JScrollPane sceneScrollPane;
	private JScrollPane elementScrollPane;
	private ViewElement viewElement;
	
	private JButton sceneAddBtn;
	private JButton sceneDelBtn;
	private JButton sceneStartBtn;
	private JButton elementAddBtn;
	private JButton elementDelBtn;
	private JButton elementStartBtn;
	
	private ModelStory story;
	private ModelScene selectedScene;
	private ModelSceneElement selectedElement;
	
	public ViewStory() {
		
		// initialize GUI components
		scenePanel = new JPanel();
		scenePanel.setBorder(BorderFactory.createTitledBorder("Scenes"));
		scenePanel.setLayout(new BoxLayout(scenePanel, BoxLayout.PAGE_AXIS));
		sceneList = new JList();
		sceneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sceneAddBtn = new JButton("Add");
		sceneDelBtn = new JButton("Del");
		sceneStartBtn = new JButton("Start");
		JPanel sceneBtnPanel = new JPanel();
		sceneBtnPanel.setLayout(new BoxLayout(sceneBtnPanel, BoxLayout.LINE_AXIS));
		
		elementPanel = new JPanel();
		elementPanel.setBorder(BorderFactory.createTitledBorder("Elements"));
		elementPanel.setLayout(new BoxLayout(elementPanel, BoxLayout.PAGE_AXIS));
		elementList = new JList();
		elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		elementAddBtn = new JButton("Add");
		elementDelBtn = new JButton("Del");
		elementStartBtn = new JButton("Start");
		JPanel elementBtnPanel = new JPanel();
		elementBtnPanel.setLayout(new BoxLayout(elementBtnPanel, BoxLayout.LINE_AXIS));
		
		sceneScrollPane = new JScrollPane(sceneList);
		elementScrollPane = new JScrollPane(elementList);
		viewElement = new ViewElement(new ModelBeat("test"));
		viewElement.setBorder(BorderFactory.createTitledBorder("Element Settings"));
		
		// combine GUI components
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		scenePanel.add(sceneScrollPane);
		sceneBtnPanel.add(sceneAddBtn);
		sceneBtnPanel.add(sceneDelBtn);
		sceneBtnPanel.add(sceneStartBtn);
		scenePanel.add(sceneBtnPanel);
		
		elementPanel.add(elementScrollPane);
		elementBtnPanel.add(elementAddBtn);
		elementBtnPanel.add(elementDelBtn);
		elementBtnPanel.add(elementStartBtn);
		elementPanel.add(elementBtnPanel);
		
		this.add(scenePanel);
		this.add(elementPanel);
		this.add(viewElement);
	}
	
	/** ACCESSORS */
	public JList getSceneJList() {
		return sceneList;
	}
	
	public JList getElementJList() {
		return elementList;
	}
	
	public JButton getAddSceneBtn() {
		return sceneAddBtn;
	}
	
	public JButton getStartSceneBtn() {
		return sceneStartBtn;
	}
	
	public JButton getAddElementBtn() {
		return elementAddBtn;
	}
	
	public JButton getStartElementBtn() {
		return elementStartBtn;
	}
	
	public ModelScene getSelectedScene() {
		return selectedScene;
	}
	
	public ModelSceneElement getSelectedElement() {
		return selectedElement;
	}
	
	public JTextField getNameField() {
		return viewElement.getNameField();
	}
	
	public JComboBox getLeftActorBox() {
		return viewElement.getLeftActorComboBox();
	}
	
	public JComboBox getMidActorBox() {
		return viewElement.getMidActorComboBox();
	}
	
	public JComboBox getRightActorBox() {
		return viewElement.getRightActorComboBox();
	}
	
	public JComboBox getNextElementBox() {
		return viewElement.getNextElementComboBox();
	}
	
	public JTextField getSpeakerField() {
		return viewElement.getSpeakerField();
	}
	
	public JTextPane getDialogPane() {
		return viewElement.getDialogPane();
	}
	
	public JCheckBox getUsePrevious() {
		return viewElement.getUsePrevious();
	}
	
	// decision accessors
	public JCheckBox getDecisionCheck(int num) {
		return viewElement.getDecisionCheck(num);
	}
	
	public JTextField getDecisionText(int num) {
		return viewElement.getDecisionText(num);
	}
	
	public JComboBox getDecisionCombo(int num) {
		return viewElement.getDecisionCombo(num);
	}
	
	public JComboBox getPointerCombo() {
		return viewElement.getPointerCombo();
	}
 	
	/** MUTATORS */
	public void setStory(ModelStory story)  {
		this.story = story;
		viewElement.setStory(story);
		buildSceneList();
	}
	
	public void setSelectedScene(ModelScene s) {
		selectedScene = s;
		viewElement.setScene(s);
		buildElementList();
	}
	
	public void setSelectedElement(ModelSceneElement e) {
		selectedElement = e;
		viewElement.setElement(e);
	}
	
	public void buildSceneList() {
		sceneList.setModel(story.getSceneList());
	}
	
	public void buildElementList() {
		elementList.setModel(selectedScene.getElementListWithType());
	}

}
