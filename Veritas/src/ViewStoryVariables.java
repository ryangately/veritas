import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ViewStoryVariables extends JFrame {
	
	final private String TITLE = "Story Variables";
	private ModelStory story;
	private JTabbedPane tabPane;
	
	// switch panel
	private JPanel switchPanel;
	private JScrollPane switchScrollPane;
	private JList switchList;
	private JPanel switchBtnPanel;
	private JButton switchAddBtn;
	private JButton switchDelBtn;
	
	// counter panel
	private JPanel counterPanel;
	private JScrollPane counterScrollPane;
	private JList counterList;
	private JPanel counterBtnPanel;
	private JButton counterAddBtn;
	private JButton counterDelBtn;
	
	// string panel
	private JPanel stringPanel;
	private JScrollPane stringScrollPane;
	private JList stringList;
	private JPanel stringBtnPanel;
	private JButton stringAddBtn;
	private JButton stringDelBtn;
	
	public ViewStoryVariables(ModelStory s) {
		story = s;
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 300));
		
		// switch tab
		switchPanel = new JPanel();
		switchList = new JList();
		switchList.setModel(story.getSwitchList());
		switchScrollPane = new JScrollPane(switchList);
		switchBtnPanel = new JPanel();
		switchAddBtn = new JButton("Add");
		switchDelBtn = new JButton("Del");
		switchBtnPanel.setLayout(new BoxLayout(switchBtnPanel, BoxLayout.LINE_AXIS));
		switchBtnPanel.add(switchAddBtn);
		switchBtnPanel.add(switchDelBtn);
		switchPanel.setLayout(new BoxLayout(switchPanel, BoxLayout.PAGE_AXIS));
		switchPanel.add(switchScrollPane);
		switchPanel.add(switchBtnPanel);
		
		// counter tab
		counterPanel = new JPanel();
		counterList = new JList();
		counterList.setModel(story.getCounterList());
		counterScrollPane = new JScrollPane(counterList);
		counterBtnPanel = new JPanel();
		counterAddBtn = new JButton("Add");
		counterDelBtn = new JButton("Del");
		counterBtnPanel.setLayout(new BoxLayout(counterBtnPanel, BoxLayout.LINE_AXIS));
		counterBtnPanel.add(counterAddBtn);
		counterBtnPanel.add(counterDelBtn);
		counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.PAGE_AXIS));
		counterPanel.add(counterScrollPane);
		counterPanel.add(counterBtnPanel);
		
		tabPane = new JTabbedPane();
		tabPane.addTab("Switches", switchPanel);
		tabPane.addTab("Counters", counterPanel);
		
		// action listeners
		switchAddBtn.addActionListener(new NewSwitchListener());
		counterAddBtn.addActionListener(new NewCounterListener());
		
		this.add(tabPane);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private class NewSwitchDialog extends JDialog {
		private JPanel itemPanel;
		private JLabel nameLbl;
		private JTextField nameField;
		private JLabel valueLbl;
		private JComboBox<String> valueBox;
		private JButton okBtn;
		private JButton cancelBtn;
		
		public NewSwitchDialog(JFrame parent) {
			super(parent, true);
			itemPanel = new JPanel();
			nameLbl = new JLabel("Name:");
			nameField = new JTextField();
			valueLbl = new JLabel("Value:");
			valueBox = new JComboBox<String>();
			valueBox.addItem("true");
			valueBox.addItem("false");
			okBtn = new JButton("OK");
			cancelBtn = new JButton("Cancel");
			
			okBtn.addActionListener(new NewSwitchDialogBtnListener());
			cancelBtn.addActionListener(new NewSwitchDialogBtnListener());
			
			itemPanel.setLayout(new GridLayout(0,2));
			itemPanel.add(nameLbl);
			itemPanel.add(nameField);
			itemPanel.add(valueLbl);
			itemPanel.add(valueBox);
			itemPanel.add(okBtn);
			itemPanel.add(cancelBtn);
			
			this.setTitle("New Switch");
			this.add(itemPanel);
			this.pack();
			this.setLocationRelativeTo(parent);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
		
		private class NewSwitchDialogBtnListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (a.getSource() == okBtn) {
					if (nameField.getText().equals("")) {
						JOptionPane.showMessageDialog(itemPanel, 
								"The switch must have a name.", 
								"Error", 
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						ModelSwitch s = new ModelSwitch(nameField.getText(), true);
						if (valueBox.getSelectedItem().equals("false")) {
							s.setValue(false);
						}
						story.addSwitch(s);
						switchList.setModel(story.getSwitchList());
						dispose();
					}
				}
				else {
					dispose();
				}
			}
		}
	}
	
	private class NewSwitchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new NewSwitchDialog(null);
		}
	}
	
	private class NewCounterDialog extends JDialog {
		private JPanel itemPanel;
		private JLabel nameLbl;
		private JTextField nameField;
		private JLabel valueLbl;
		private JSpinner valueSpinner;
		private JButton okBtn;
		private JButton cancelBtn;
		
		public NewCounterDialog(JFrame parent) {
			super(parent, true);
			itemPanel = new JPanel();
			nameLbl = new JLabel("Name:");
			nameField = new JTextField();
			valueLbl = new JLabel("Value:");
			valueSpinner = new JSpinner();
			okBtn = new JButton("OK");
			cancelBtn = new JButton("Cancel");
			
			okBtn.addActionListener(new NewCounterDialogBtnListener());
			cancelBtn.addActionListener(new NewCounterDialogBtnListener());
			
			itemPanel.setLayout(new GridLayout(0,2));
			itemPanel.add(nameLbl);
			itemPanel.add(nameField);
			itemPanel.add(valueLbl);
			itemPanel.add(valueSpinner);
			itemPanel.add(okBtn);
			itemPanel.add(cancelBtn);
			
			this.setTitle("New Counter");
			this.add(itemPanel);
			this.pack();
			this.setLocationRelativeTo(parent);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
		
		private class NewCounterDialogBtnListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (a.getSource() == okBtn) {
					try {
						valueSpinner.commitEdit();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (nameField.getText().equals("")) {
						JOptionPane.showMessageDialog(itemPanel, 
								"The counter must have a name.", 
								"Error", 
								JOptionPane.ERROR_MESSAGE);
					}
					else if (valueSpinner.getValue() == "") {
						JOptionPane.showMessageDialog(itemPanel, 
								"The counter must have an initial value.", 
								"Error", 
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						ModelCounter c = new ModelCounter(nameField.getText());
						c.setValue((int) valueSpinner.getValue());
						story.addCounter(c);
						counterList.setModel(story.getCounterList());
						dispose();
					}
				}
				else {
					dispose();
				}
			}
		}
	}
	
	private class NewCounterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new NewCounterDialog(null);
		}
	}

}
