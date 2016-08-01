import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewDecisionPanel extends JPanel {

	private JCheckBox optionCheck;
	private JPanel topPane;
	private JPanel bottomPane;
	private JPanel sidePane; // holds top and bottom
	private JLabel optionTextLbl;
	private JTextField optionText;
	private JLabel optionNextLbl;
	private JComboBox optionComboBox;

	public ViewDecisionPanel(String number) {
		optionCheck = new JCheckBox();
		sidePane = new JPanel();
		sidePane.setLayout(new BoxLayout(sidePane, BoxLayout.PAGE_AXIS));
		sidePane.setBorder(BorderFactory.createTitledBorder(number));
		topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.LINE_AXIS));
		bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.LINE_AXIS));
		optionTextLbl = new JLabel("Option:");
		optionText = new JTextField();
		optionNextLbl = new JLabel("Points to:");
		optionComboBox = new JComboBox();
		if (!number.equals("1")) {
			optionText.setEnabled(false);
			optionComboBox.setEnabled(false);
			if (!number.equals("2")) {
				optionCheck.setEnabled(false);
			}
		}

		topPane.add(optionTextLbl);
		topPane.add(optionText);
		bottomPane.add(optionNextLbl);
		bottomPane.add(optionComboBox);
		sidePane.add(topPane);
		sidePane.add(bottomPane);
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		if (!number.equals("1")) {
			this.add(optionCheck);
		}
		this.add(sidePane);
	}

	// ACCESSORS
	public JCheckBox getOptionCheck() {
		return optionCheck;
	}

	public JTextField getOptionText() {
		return optionText;
	}

	public JComboBox getOptionBox() {
		return optionComboBox;
	}

	// MUTATORS
	public void setDecisionEnabled(Boolean val) {
		optionText.setEnabled(val);
		optionComboBox.setEnabled(val);
	}
}