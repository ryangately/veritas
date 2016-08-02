import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ViewElementExploration extends JPanel {
	
	// internal panel used to build the gui
	private class ViewExplorationObject extends JPanel {
		
		private String number;
		private JLabel objectImageLbl;
		private JComboBox objectImageComboBox;
		private JLabel objectXLbl;
		private JLabel objectYLbl;
		private JSpinner objectXSpinner;
		private JSpinner objectYSpinner;
		private JLabel objectPointerLbl;
		private JComboBox objectPointer;
		
		public ViewExplorationObject(String num) {
			
			number = num;
			objectImageLbl = new JLabel("Image:");
			objectImageComboBox = new JComboBox();
			objectXLbl = new JLabel("X:");
			objectYLbl = new JLabel("Y:");
			objectXSpinner = new JSpinner();
			objectXSpinner.setModel(new SpinnerNumberModel());
			objectYSpinner = new JSpinner();
			objectPointerLbl = new JLabel("Points to:");
			objectPointer = new JComboBox();
			
			this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			this.setBorder(BorderFactory.createTitledBorder(number));
			this.add(objectImageLbl);
			this.add(objectImageComboBox);
			this.add(objectXLbl);
			this.add(objectXSpinner);
			this.add(objectYLbl);
			this.add(objectYSpinner);
			this.add(objectPointerLbl);
			this.add(objectPointer);
		}
	}
	
	private ModelStory story;
	private ModelScene scene;
	private ModelExploration element;
	private ViewExplorationObject[] exploreObjects;
	
	public ViewElementExploration() {
		
		exploreObjects = new ViewExplorationObject[6];
		
		this.setBorder(BorderFactory.createTitledBorder("Exploration Objects"));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (int i = 0; i < 6; ++i) {
			exploreObjects[i] = new ViewExplorationObject(String.valueOf(i + 1));
			this.add(exploreObjects[i]);
			exploreObjects[i].objectImageComboBox.addActionListener(new ExploreObjectBoxListener());
		}
		
	}
	
	public void setStory(ModelStory s) {
		story = s;
		for (int i = 0; i < 6; ++i) {
			exploreObjects[i].objectImageComboBox.setModel(story.getExploreBoxList());
		}
	}
	
	public void setScene(ModelScene sc) {
		scene = sc;
		for (int i = 0; i < 6; ++i) {
			exploreObjects[i].objectPointer.setModel(scene.getElementListWithType());
		}
	}
	
	public void setElement(ModelExploration e) {
		element = e;
	}
	
	private class ExploreObjectBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exploreObjects[0].objectImageComboBox) {
				element.addExplorationObject(0, 
						(int)exploreObjects[0].objectXSpinner.getValue(), 
						(int)exploreObjects[0].objectYSpinner.getValue(), 
						exploreObjects[0].objectImageComboBox.getSelectedIndex() - 1, 
						exploreObjects[0].objectPointer.getSelectedIndex());
			} else if (e.getSource() == exploreObjects[1].objectImageComboBox) {
				element.addExplorationObject(1, 
						(int)exploreObjects[1].objectXSpinner.getValue(), 
						(int)exploreObjects[1].objectYSpinner.getValue(), 
						exploreObjects[1].objectImageComboBox.getSelectedIndex() - 1, 
						exploreObjects[1].objectPointer.getSelectedIndex());
			} else if (e.getSource() == exploreObjects[2].objectImageComboBox) {
				element.addExplorationObject(2, 
						(int)exploreObjects[2].objectXSpinner.getValue(), 
						(int)exploreObjects[2].objectYSpinner.getValue(), 
						exploreObjects[2].objectImageComboBox.getSelectedIndex() - 1, 
						exploreObjects[2].objectPointer.getSelectedIndex());
			} else if (e.getSource() == exploreObjects[3].objectImageComboBox) {
				element.addExplorationObject(3, 
						(int)exploreObjects[3].objectXSpinner.getValue(), 
						(int)exploreObjects[3].objectYSpinner.getValue(), 
						exploreObjects[3].objectImageComboBox.getSelectedIndex() - 1, 
						exploreObjects[3].objectPointer.getSelectedIndex());
			}
		}
	}

}
