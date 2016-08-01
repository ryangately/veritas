import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class ViewElementLogic extends JPanel {
	
	private ModelStory story;
	private ModelScene scene;
	private ModelLogic element;
	private ModelSwitch currentSwitch;
	
	private JPanel headerPane;
	private JLabel ifLbl;
	private JComboBox<String> typeBox;
	private JComboBox<String> headerBox;
	
	// card pane
	private JPanel cardPane;
	private CardLayout cl;
	
	// boolean pane
	private JPanel boolPane;
	private JPanel truePane;
	private JPanel falsePane;
	private JLabel isTrueLbl;
	private JComboBox<String> isTrueBox;
	private JLabel isFalseLbl;
	private JComboBox<String> isFalseBox;
	
	// counter pane
	private JPanel counterPane;
	private JPanel counterComparePane;
	private JPanel falseComparePane;
	private JLabel isCompareLbl;
	private JComboBox<String> counterCompareBox;
	private JSpinner counterCompareSpinner;
	private JLabel counterIsTrueLbl;
	private JLabel counterIsFalseLbl;
	private JComboBox<String> counterIsTrueBox;
	private JComboBox<String> counterIsFalseBox;
	
	public ViewElementLogic() {
		
		headerPane = new JPanel();
		headerPane.setLayout(new BoxLayout(headerPane, BoxLayout.LINE_AXIS));
		ifLbl = new JLabel("If ");
		typeBox = new JComboBox<String>();
		typeBox.addItem("Switch");
		typeBox.addItem("Counter");
		typeBox.addActionListener(new TypeBoxListener());
		headerBox = new JComboBox<String>();
		headerBox.addActionListener(new HeaderBoxListener());
		headerPane.add(ifLbl);
		headerPane.add(typeBox);
		headerPane.add(headerBox);
		
		// bool pane
		boolPane = new JPanel();
		boolPane.setLayout(new BoxLayout(boolPane, BoxLayout.PAGE_AXIS));
		
		truePane = new JPanel();
		truePane.setLayout(new BoxLayout(truePane, BoxLayout.LINE_AXIS));
		isTrueLbl = new JLabel("<html>is <b>TRUE</b>, go to: </html>");
		isTrueBox = new JComboBox<String>();
		isTrueBox.addActionListener(new BooleanListener());
		truePane.add(isTrueLbl);
		truePane.add(isTrueBox);
		
		falsePane = new JPanel();
		falsePane.setLayout(new BoxLayout(falsePane, BoxLayout.LINE_AXIS));
		isFalseLbl = new JLabel("<html>is <b>FALSE</b>, go to: </html>");
		isFalseBox = new JComboBox<String>();
		isFalseBox.addActionListener(new BooleanListener());
		falsePane.add(isFalseLbl);
		falsePane.add(isFalseBox);
		
		boolPane.add(truePane);
		boolPane.add(falsePane);
		
		// counter pane
		counterPane = new JPanel();
		counterPane.setLayout(new BoxLayout(counterPane, BoxLayout.PAGE_AXIS));
		
		counterComparePane = new JPanel();
		counterComparePane.setLayout(new BoxLayout(counterComparePane, BoxLayout.LINE_AXIS));
		isCompareLbl = new JLabel("is ");
		counterCompareBox = new JComboBox<String>();
		counterCompareBox.addItem("equal to");
		counterCompareBox.addItem("less than");
		counterCompareBox.addItem("greater than");
		counterCompareSpinner = new JSpinner();
		counterIsTrueLbl = new JLabel("go to: ");
		counterIsTrueBox = new JComboBox<String>();
		counterComparePane.add(isCompareLbl);
		counterComparePane.add(counterCompareBox);
		counterComparePane.add(counterCompareSpinner);
		counterComparePane.add(counterIsTrueLbl);
		counterComparePane.add(counterIsTrueBox);
		counterComparePane.setMaximumSize(counterComparePane.getPreferredSize());
		
		falseComparePane = new JPanel();
		falseComparePane.setLayout(new BoxLayout(falseComparePane, BoxLayout.LINE_AXIS));
		counterIsFalseLbl = new JLabel("otherwise, go to: ");
		counterIsFalseBox = new JComboBox<String>();
		falseComparePane.add(counterIsFalseLbl);
		falseComparePane.add(counterIsFalseBox);
		
		counterPane.add(counterComparePane);
		counterPane.add(falseComparePane);
		
		// card pane
		cardPane = new JPanel(new CardLayout());
		cl = (CardLayout) cardPane.getLayout();
		cardPane.add(boolPane, "BOOLEAN");
		cardPane.add(counterPane, "COUNTER");
		
		// build gui
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(headerPane);
		this.add(cardPane);
	}
	
	public void setStory(ModelStory s) {
		story = s;
	}
	
	public void setScene(ModelScene s) {
		scene = s;
	}

	public void setElement(ModelLogic e) {
		element = e;
	}
	
	private class TypeBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (typeBox.getSelectedItem().equals("Switch")) {
				headerBox.setModel(story.getSwitchBoxList());
				isTrueBox.setModel(scene.getElementListWithType());
				isFalseBox.setModel(scene.getElementListWithType());
				cl.show(cardPane, "BOOLEAN");
				element.setComparison(0);
			}
			else {
				headerBox.setModel(story.getCounterBoxList());
				counterIsTrueBox.setModel(scene.getElementListWithType());
				counterIsFalseBox.setModel(scene.getElementListWithType());
				cl.show(cardPane, "COUNTER");
				element.setComparison(0);
			}
		}
	}
	
	private class HeaderBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (typeBox.getSelectedItem().equals("Switch")) {
				element.setComparison(headerBox.getSelectedIndex());
			}
			else if (typeBox.getSelectedItem().equals("Counter")) {
				element.setComparison(headerBox.getSelectedIndex(), 0, 0);
			}
		}
	}
	
	private class BooleanListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == isTrueBox) {
				element.setNext(isTrueBox.getSelectedIndex());
			}
			else if (ae.getSource() == isFalseBox) {
				element.setNextFalse(isFalseBox.getSelectedIndex());
			}
		}
	}
}
