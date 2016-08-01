import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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

public class ViewNewScene extends JDialog {

	private ModelStory story;
	private JPanel panel;
	private JPanel namePanel;
	private JPanel bgPanel;
	private JPanel btnPanel;
	private JTextField nameField;
	private JLabel nameLbl;
	private JLabel bgLbl;
	private JComboBox bgBox;
	private JButton okBtn;
	private JButton cancelBtn;
	
	// action listeners
	private class okNewSceneListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == cancelBtn) {
				dispose();
			}
			else if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(panel,
					    "The scene must have a name.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if (bgBox.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(panel,
					    "The scene must have a background image.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else {
				ModelScene scene = new ModelScene(nameField.getText(), bgBox.getSelectedIndex());
				story.addScene(scene);
				dispose();
			}
		}
	}
	
	public ViewNewScene(JFrame parent, ModelStory s) {
		super(parent, true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("New Scene");
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		namePanel = new JPanel();
		bgPanel = new JPanel();
		btnPanel = new JPanel();
		nameField = new JTextField();
		nameLbl = new JLabel("Name:");
		bgLbl = new JLabel("Background:");
		bgBox = new JComboBox();
		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		story = s;
		
		// add background image list to combo box
		DefaultComboBoxModel<String> bgBoxModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < story.getBackgroundSize(); ++i) {
			bgBoxModel.addElement(story.getBackgroundName(i));
		}
		bgBox.setModel(bgBoxModel);
		
		// action listeners
		okBtn.addActionListener(new okNewSceneListener());
		cancelBtn.addActionListener(new okNewSceneListener());
		
		// build window
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.LINE_AXIS));
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLbl);
		namePanel.add(nameField);
		bgPanel.add(bgLbl);
		bgPanel.add(bgBox);
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		panel.add(namePanel);
		panel.add(bgPanel);
		panel.add(btnPanel);
		this.add(panel);
		
		// show window
		this.pack();
		this.setVisible(true);
	}
}
