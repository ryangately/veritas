import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewElementToggle extends JPanel {
	
	private ModelStory story;
	private ModelScene scene;
	private ModelToggle element;
	private ModelSwitch currentSwitch;
	
	private JLabel setSwitchLbl;
	private JComboBox switchComboBox;
	private JLabel setToLbl;
	private JComboBox settingComboBox;
	private JPanel switchPane;
	private JComboBox nextBox;
	
	public ViewElementToggle() {
		
		setSwitchLbl = new JLabel("Set");
		switchComboBox = new JComboBox();
		switchComboBox.addActionListener(new ToggleComboBoxListener());
		setToLbl = new JLabel("to");
		settingComboBox = new JComboBox();
		settingComboBox.addActionListener(new ToggleComboBoxListener());
		switchPane = new JPanel();
		nextBox = new JComboBox();
		
		switchPane.setLayout(new BoxLayout(switchPane, BoxLayout.LINE_AXIS));
		switchPane.setBorder(BorderFactory.createTitledBorder("Toggle"));
		switchPane.add(setSwitchLbl);
		switchPane.add(switchComboBox);
		switchPane.add(setToLbl);
		switchPane.add(settingComboBox);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(switchPane);
		this.add(nextBox);
	}
	
	public void setStory(ModelStory s) {
		story = s;
		switchComboBox.setModel(story.getSwitchBoxList());
		DefaultComboBoxModel settingList = new DefaultComboBoxModel();
		settingList.addElement("True");
		settingList.addElement("False");
		settingComboBox.setModel(settingList);
	}
	
	public void setScene(ModelScene sc) {
		scene = sc;
		nextBox.setModel(scene.getElementListWithType());
	}
	
	public void setElement(ModelToggle e) {
		element = e;
	}
	
	private class ToggleComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (settingComboBox.getSelectedIndex() == 0) {
				element.setBool(true, switchComboBox.getSelectedIndex());
			}
			else {
				element.setBool(false, switchComboBox.getSelectedIndex());
			}
			
		}
	}
	
	private class NextBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			element.setNext(nextBox.getSelectedIndex());
		}
	}

}
