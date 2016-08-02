import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewNewElement extends JDialog {
	
	private ModelScene scene;
	private JPanel panel;
	private JPanel namePanel;
	private JPanel typePanel;
	private JPanel btnPanel;
	private JLabel nameLbl;
	private JLabel typeLbl;
	private JTextField nameField;
	private JComboBox typeBox;
	private JButton okBtn;
	private JButton cancelBtn;
	
	private class NewElementBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == cancelBtn) {
				dispose();
			}
			else if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(panel,
					    "The element must have a name.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if (typeBox.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(panel,
					    "The element must have a type.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (typeBox.getSelectedItem().equals("Beat")) {
					scene.addElement(new ModelBeat(nameField.getText()));
					dispose();
				}
				else if (typeBox.getSelectedItem().equals("Decision")) {
					scene.addElement(new ModelDecision(nameField.getText()));
					dispose();
				}
				else if (typeBox.getSelectedItem().equals("Pointer")) {
					scene.addElement(new ModelPointer(nameField.getText()));
					dispose();
				}
				else if (typeBox.getSelectedItem().equals("Logic")) {
					scene.addElement(new ModelLogic(nameField.getText()));
					dispose();
				}
				else if (typeBox.getSelectedItem().equals("Toggle")) {
					scene.addElement(new ModelToggle(nameField.getText()));
					dispose();
				}
				else if (typeBox.getSelectedItem().equals("Explore")) {
					scene.addElement(new ModelExploration(nameField.getText()));
					dispose();
				}
			}
		}
	}
	
	public ViewNewElement(JFrame parent, ModelScene scene) {
		super(parent, true);
		this.scene = scene;
		
		// initialize components
		panel = new JPanel();
		namePanel = new JPanel();
		typePanel = new JPanel();
		btnPanel = new JPanel();
		nameLbl = new JLabel("Name:");
		typeLbl = new JLabel("Type:");
		nameField = new JTextField();
		typeBox = new JComboBox();
		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		
		// add action listeners
		okBtn.addActionListener(new NewElementBtnListener());
		cancelBtn.addActionListener(new NewElementBtnListener());
		
		// populate combo box
		DefaultComboBoxModel<String> typeBoxModel = new DefaultComboBoxModel<String>();
		typeBoxModel.addElement("Beat");
		typeBoxModel.addElement("Decision");
		typeBoxModel.addElement("Pointer");
		typeBoxModel.addElement("Logic");
		typeBoxModel.addElement("Toggle");
		typeBoxModel.addElement("Explore");
		typeBox.setModel(typeBoxModel);
		
		// build gui
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLbl);
		namePanel.add(nameField);
		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.LINE_AXIS));
		typePanel.add(typeLbl);
		typePanel.add(typeBox);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		panel.add(namePanel);
		panel.add(typePanel);
		panel.add(btnPanel);
		this.add(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("New Element");
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

}
