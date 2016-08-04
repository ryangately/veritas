import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ViewElementToggle extends JPanel {

	private ModelStory story;
	private ModelScene scene;
	private ModelToggle element;
	private ModelSwitch currentSwitch;

	private JPanel typePane;
	private JLabel typeLbl;
	private JComboBox typeComboBox;

	private JLabel setSwitchLbl;
	private JComboBox switchComboBox;
	private JLabel setToLbl;
	private JComboBox settingComboBox;
	private JTextField settingTextFld;
	private JPanel switchPane;
	private JComboBox nextBox;

	public ViewElementToggle() {

		story = null;
		scene = null;
		element = new ModelToggle("null");

		typePane = new JPanel();
		typeLbl = new JLabel("Type:");
		typeComboBox = new JComboBox();
		typeComboBox.addActionListener(new ToggleTypeBoxListener());
		typePane.setLayout(new BoxLayout(typePane, BoxLayout.LINE_AXIS));
		typePane.add(typeLbl);
		typePane.add(typeComboBox);

		setSwitchLbl = new JLabel("Set");
		switchComboBox = new JComboBox();
		switchComboBox.addActionListener(new ToggleComboBoxListener());
		setToLbl = new JLabel("to");
		settingComboBox = new JComboBox();
		settingComboBox.addActionListener(new ToggleComboBoxListener());
		settingComboBox.addItem("True");
		settingComboBox.addItem("False");
		settingTextFld = new JTextField();
		settingTextFld.getDocument().addDocumentListener(
				new SettingTextFieldListener());
		switchPane = new JPanel();
		nextBox = new JComboBox();
		nextBox.addActionListener(new NextBoxListener());

		switchPane.setLayout(new BoxLayout(switchPane, BoxLayout.LINE_AXIS));
		switchPane.setBorder(BorderFactory.createTitledBorder("Toggle"));
		switchPane.add(setSwitchLbl);
		switchPane.add(switchComboBox);
		switchPane.add(setToLbl);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(typePane);
		this.add(switchPane);
		this.add(nextBox);
	}

	public void setStory(ModelStory s) {
		story = s;
		typeComboBox.setModel(new DefaultComboBoxModel());
		typeComboBox.addItem("Switch");
		typeComboBox.addItem("Counter");
		typeComboBox.addItem("String");
		switchComboBox.setModel(story.getSwitchBoxList());
	}

	public void setScene(ModelScene sc) {
		scene = sc;
		nextBox.setModel(scene.getElementListWithType());
	}

	public void setElement(ModelToggle e) {
		element = e;
		nextBox.setSelectedIndex(element.getNext());
		// switch type
		if (e.getType() == 0) {
			typeComboBox.setSelectedIndex(0);
		}
		// string type
		else if (e.getType() == 4) {
			typeComboBox.setSelectedIndex(2);
		}
	}

	private class ToggleTypeBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (typeComboBox.getSelectedItem() == "Switch") {
				switchComboBox.setModel(story.getSwitchBoxList());
				switchPane.remove(settingTextFld);
				switchPane.add(settingComboBox);
			} else if (typeComboBox.getSelectedItem() == "Counter") {
				switchComboBox.setModel(story.getCounterBoxList());
			} else if (typeComboBox.getSelectedItem() == "String") {
				switchComboBox.setModel(story.getStringVarBoxList());
				switchPane.remove(settingComboBox);
				switchPane.add(settingTextFld);
				settingTextFld.setText(element.getNewString());
			}
		}
	}

	private class ToggleComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == switchComboBox
					&& typeComboBox.getSelectedItem() == "Switch"
					&& settingComboBox.getSelectedIndex() == 0) {
				element.setBool(true, switchComboBox.getSelectedIndex());
			} else if (e.getSource() == switchComboBox
					&& typeComboBox.getSelectedItem() == "Switch"
					&& settingComboBox.getSelectedIndex() == 1) {
				element.setBool(false, switchComboBox.getSelectedIndex());
			} else if (e.getSource() == switchComboBox
					&& typeComboBox.getSelectedItem() == "String") {
				element.setString(settingTextFld.getText(),
						switchComboBox.getSelectedIndex());
			}
		}
	}

	private class NextBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			element.setNext(nextBox.getSelectedIndex());
		}
	}

	// listener to update String value when the text field is changed
	private class SettingTextFieldListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateSettingText(e);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			updateSettingText(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateSettingText(e);
		}

		private void updateSettingText(DocumentEvent de) {
			element.setString(settingTextFld.getText(),
					switchComboBox.getSelectedIndex());
		}
	}

}
