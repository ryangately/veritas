import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ViewStorySettings extends JDialog {
	
	final private String TITLE = "Story Settings";
	private JPanel panel;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JPanel btnPanel;
	private JButton okBtn;
	private JButton cancelBtn;
	
	private ModelStory story;
	
	private class StorySettingsBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == cancelBtn) {
				dispose();
			}
			else if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(panel,
					    "The story must have a name.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else {
				story.setTitle(nameField.getText());
				dispose();
			}
		}
	}
	
	public ViewStorySettings(ModelStory s) {
		this.setTitle(TITLE);
		story = s;
		panel = new JPanel();
		namePanel = new JPanel();
		nameLabel = new JLabel("Name:");
		nameField = new JTextField(story.getTitle());
		btnPanel = new JPanel();
		okBtn = new JButton("OK");
		cancelBtn = new JButton("Cancel");
		
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(namePanel);
		panel.add(btnPanel);
		
		// add action listeners
		okBtn.addActionListener(new StorySettingsBtnListener());
		cancelBtn.addActionListener(new StorySettingsBtnListener());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(getParent());
		this.setVisible(true);
	}

}
