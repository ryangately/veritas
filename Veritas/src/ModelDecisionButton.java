import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ModelDecisionButton extends JButton {
	
	private int decisionId;
	
	public ModelDecisionButton(int id) {
		//this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setHorizontalAlignment(LEFT);
		decisionId = id;
	}
	
	public int getDecisionId() {
		return decisionId;
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		this.setFont(new Font("Dialog", Font.PLAIN, 24));
		this.setForeground(Color.white);
	}

}
