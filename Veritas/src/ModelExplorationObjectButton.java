import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ModelExplorationObjectButton extends JButton {
	
	private int objectId;
	
	public ModelExplorationObjectButton(int id) {
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		objectId = id;
	}
	
	public int getObjectId() {
		return objectId;
	}

}
