import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ViewPlay {
	
	// gui components
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JLabel bgLabel;
	private JLabel textPaneLabel;
	private JLabel leftActorLabel;
	private JLabel rightActorLabel;
	private JLabel midActorLabel;
	private JLabel speakerLabel;
	private JLabel textLabel;
	private JLabel nextArrowLabel;
	private JButton nextButton;
	
	private ModelDecisionButton decisionOne;
	private ModelDecisionButton decisionTwo;
	private ModelDecisionButton decisionThree;
	private ModelDecisionButton decisionFour;
	private ModelDecisionButton decisionFive;
	private ModelDecisionButton decisionSix;
	
	private ModelExplorationObjectButton explorationOne;
	private ModelExplorationObjectButton explorationTwo;
	private ModelExplorationObjectButton explorationThree;
	private ModelExplorationObjectButton explorationFour;
	private ModelExplorationObjectButton explorationFive;
	private ModelExplorationObjectButton explorationSix;
	
	private Integer layerIntHidden;
	private Integer layerIntBackground;
	private Integer layerIntActor;
	private Integer layerIntPanel;
	private Integer layerIntText;
	
	// images
	private ImageIcon bgImageIcon;
	private ImageIcon textPaneImage;
	private ImageIcon textPaneIcon;
	private ImageIcon leftActorIcon;
	private ImageIcon rightActorIcon;
	private ImageIcon midActorIcon;
	private ImageIcon nextArrowImage;
	private ImageIcon nextArrowIcon;
	
	public ViewPlay(String title) throws IOException {
		
		// create gui components
		frame = new JFrame(title);
		layeredPane = new JLayeredPane();
		bgLabel = new JLabel();
		textPaneLabel = new JLabel();
		textPaneImage = null;
		leftActorLabel = new JLabel();
		rightActorLabel = new JLabel();
		midActorLabel = new JLabel();
		speakerLabel = new JLabel();
		textLabel = new JLabel();
		nextArrowLabel = new JLabel();
		nextButton = new JButton();
		
		decisionOne = new ModelDecisionButton(0);
		decisionTwo = new ModelDecisionButton(1);
		decisionThree = new ModelDecisionButton(2);
		decisionFour = new ModelDecisionButton(3);
		decisionFive = new ModelDecisionButton(4);
		decisionSix = new ModelDecisionButton(5);
		
		explorationOne = new ModelExplorationObjectButton(0);
		explorationTwo = new ModelExplorationObjectButton(1);
		explorationThree = new ModelExplorationObjectButton(2);
		explorationFour = new ModelExplorationObjectButton(3);
		explorationFive = new ModelExplorationObjectButton(4);
		explorationSix = new ModelExplorationObjectButton(5);
		
		layerIntHidden = new Integer(0);
		layerIntBackground = new Integer(1);
		layerIntActor = new Integer(2);
		layerIntPanel = new Integer(3);
		layerIntText = new Integer(4);
		
		// gui settings
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		layeredPane.setPreferredSize(new Dimension(1280, 720));
		layeredPane.setOpaque(true);
		
		// load images
		
		/* DEBUG
		bgImage = ImageIO.read(new File("images/spacebg.png"));
		bgImageIcon = new ImageIcon(bgImage);
		bgLabel.setIcon(bgImageIcon);
		
		leftActorImage = ImageIO.read(new File("images/character.png"));
		leftActorIcon = new ImageIcon(leftActorImage);
		leftActorLabel.setIcon(leftActorIcon);
		*/
		
		bgLabel.setBounds(0, 0, 1280, 720);
		
		//textPaneImage = new ImageIcon();
		textPaneIcon = new ImageIcon(ImageIO.read(new File("images/panel.png")));
		textPaneLabel.setIcon(textPaneIcon);
		textPaneLabel.setBounds(42, 450, textPaneIcon.getIconWidth(), textPaneIcon.getIconHeight());
		
		//nextArrowImage = ImageIO.read(new File("images/nextarrow.png"));
		nextArrowIcon = new ImageIcon(ImageIO.read(new File("images/nextarrow.png")));
		nextArrowLabel.setIcon(nextArrowIcon);
		nextArrowLabel.setBounds(1150, 660, nextArrowIcon.getIconWidth(), nextArrowIcon.getIconHeight());
		
		// text components
		speakerLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		speakerLabel.setForeground(Color.white);
		speakerLabel.setBounds(120, 468, 1000, 30);
		
		textLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		textLabel.setForeground(Color.white);
		textLabel.setVerticalAlignment(JLabel.TOP);
		textLabel.setBounds(80, 520, 1150, 190);
		
		// decision components
		decisionOne.setBounds(80, 470, 550, 65);
		decisionTwo.setBounds(80, 545, 550, 65);
		decisionThree.setBounds(80, 620, 550, 65);
		decisionFour.setBounds(640, 470, 550, 65);
		decisionFive.setBounds(640, 545, 550, 65);
		decisionSix.setBounds(640, 620, 550, 65);
		
		// build window
		layeredPane.add(bgLabel, layerIntBackground);
		layeredPane.add(leftActorLabel, layerIntActor);
		layeredPane.add(rightActorLabel, layerIntActor);
		layeredPane.add(midActorLabel, layerIntActor);
		layeredPane.add(textPaneLabel, layerIntPanel);
		layeredPane.add(speakerLabel, layerIntText, 0);
		layeredPane.add(textLabel, layerIntText, 1);
		layeredPane.add(nextArrowLabel, layerIntText, 2);
		layeredPane.add(nextButton, layerIntHidden);
		layeredPane.add(decisionOne, layerIntText, 3);
		layeredPane.add(decisionTwo, layerIntText, 3);
		layeredPane.add(decisionThree, layerIntText, 3);
		layeredPane.add(decisionFour, layerIntText, 3);
		layeredPane.add(decisionFive, layerIntText, 3);
		layeredPane.add(decisionSix, layerIntText, 3);
		layeredPane.add(explorationOne, layerIntActor, 1);
		layeredPane.add(explorationTwo, layerIntActor, 1);
		layeredPane.add(explorationThree, layerIntActor, 1);
		layeredPane.add(explorationFour, layerIntActor, 1);
		layeredPane.add(explorationFive, layerIntActor, 1);
		layeredPane.add(explorationSix, layerIntPanel, 1);
		frame.add(layeredPane);
		frame.getRootPane().setDefaultButton(nextButton);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	// sets the displayed speaker
	public void setSpeakerName(String name) {
		name = "<html>" + name + "</html>";
		speakerLabel.setText(name);
	}
	
	// sets the displayed dialog text
	public void setDialog(String text) {
		text = "<html>" + text + "</html>";
		textLabel.setText(text);
	}
	
	// turns the "continue reading" arrow on or off
	public void setNextArrow(Boolean toggle) {
		nextArrowLabel.setVisible(toggle);
	}
	
	public void setBackground(ImageIcon background) {
		bgImageIcon = background;
		bgLabel.setIcon(bgImageIcon);
	}
	
	public void setLeftActor(ImageIcon leftActor) {
		if (leftActor == null) {
			leftActorLabel.setVisible(false);
			return;
		}
		leftActorIcon = leftActor;
		leftActorLabel.setIcon(leftActorIcon);
		leftActorLabel.setBounds(50, 720 - leftActorIcon.getIconHeight(), leftActorIcon.getIconWidth(), leftActorIcon.getIconHeight());
		leftActorLabel.setVisible(true);
	}
	
	public void setRightActor(ImageIcon rightActor) {
		rightActorIcon = rightActor;
		rightActorLabel.setIcon(rightActorIcon);
		rightActorLabel.setBounds(1230 - rightActorIcon.getIconWidth(), 720 - rightActorIcon.getIconHeight(), rightActorIcon.getIconWidth(), rightActorIcon.getIconHeight());
	}
	
	public void setMidActor(ImageIcon midActor) {
		midActorIcon = midActor;
		midActorLabel.setIcon(midActorIcon);
		midActorLabel.setBounds(640 - (midActorIcon.getIconWidth() / 2), 720 - midActorIcon.getIconHeight(), midActorIcon.getIconWidth(), midActorIcon.getIconHeight());
	}
	
	// set decision text
	public void setDecision(int num, String text) {
		if (num == 0) {
			decisionOne.setText(text);
		}
		if (num == 1) {
			decisionTwo.setText(text);
		}
		if (num == 2) {
			decisionThree.setText(text);
		}
		if (num == 3) {
			decisionFour.setText(text);
		}
		if (num == 4) {
			decisionFive.setText(text);
		}
		if (num == 5) {
			decisionSix.setText(text);
		}
	}
	
	// set exploration icon
	public void setExplorationIcon(int num, int x, int y, ImageIcon img) {
		if (num == 0) {
			ImageIcon explorationIcon = img;
			explorationOne.setIcon(explorationIcon);
			explorationOne.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
		else if (num == 1) {
			ImageIcon explorationIcon = img;
			explorationTwo.setIcon(explorationIcon);
			explorationTwo.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
		else if (num == 2) {
			ImageIcon explorationIcon = img;
			explorationThree.setIcon(explorationIcon);
			explorationThree.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
		else if (num == 3) {
			ImageIcon explorationIcon = img;
			explorationFour.setIcon(explorationIcon);
			explorationFour.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
		else if (num == 4) {
			ImageIcon explorationIcon = img;
			explorationFive.setIcon(explorationIcon);
			explorationFive.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
		else if (num == 5) {
			ImageIcon explorationIcon = img;
			explorationSix.setIcon(explorationIcon);
			explorationSix.setBounds(x, y, explorationIcon.getIconWidth(), explorationIcon.getIconHeight());
		}
	}
	
	// clears the text of all decisions
	public void clearDecisions() {
		for (int i = 1; i < 7; ++i) {
			setDecision(i, null);
		}
	}
	
	// clears the icons of all exploration buttons
	public void clearExploration() {
		explorationOne.setIcon(null);
		explorationTwo.setIcon(null);
		explorationThree.setIcon(null);
		explorationFour.setIcon(null);
		explorationFive.setIcon(null);
		explorationSix.setIcon(null);
	}
	
	// shows only dialog components
	public void showDialog() {
		decisionOne.setVisible(false);
		decisionTwo.setVisible(false);
		decisionThree.setVisible(false);
		decisionFour.setVisible(false);
		decisionFive.setVisible(false);
		decisionSix.setVisible(false);
		explorationOne.setVisible(false);
		explorationTwo.setVisible(false);
		explorationThree.setVisible(false);
		explorationFour.setVisible(false);
		explorationFive.setVisible(false);
		explorationSix.setVisible(false);
		leftActorLabel.setVisible(true);
		midActorLabel.setVisible(true);
		rightActorLabel.setVisible(true);
		textPaneLabel.setVisible(true);
		textLabel.setVisible(true);
		speakerLabel.setVisible(true);
		nextButton.setVisible(true);
		nextArrowLabel.setVisible(true);
	}
	
	// shows only decision components
	public void showDecisions() {
		textLabel.setVisible(false);
		speakerLabel.setVisible(false);
		nextButton.setVisible(false);
		nextArrowLabel.setVisible(false);
		
		explorationOne.setVisible(false);
		explorationTwo.setVisible(false);
		explorationThree.setVisible(false);
		explorationFour.setVisible(false);
		explorationFive.setVisible(false);
		explorationSix.setVisible(false);
		
		textPaneLabel.setVisible(true);
		
		if (decisionOne.getText() != null) {
			decisionOne.setVisible(true);
		}
		if (decisionTwo.getText() != null) {
			decisionTwo.setVisible(true);
		}
		if (decisionThree.getText() != null) {
			decisionThree.setVisible(true);
		}
		if (decisionFour.getText() != null) {
			decisionFour.setVisible(true);
		}
		if (decisionFive.getText() != null) {
			decisionFive.setVisible(true);
		}
		if (decisionSix.getText() != null) {
			decisionSix.setVisible(true);
		}
	}
	
	// shows only exploration components
	public void showExploration() {
		textLabel.setVisible(false);
		speakerLabel.setVisible(false);
		nextButton.setVisible(false);
		nextArrowLabel.setVisible(false);
		textPaneLabel.setVisible(false);
		leftActorLabel.setVisible(false);
		rightActorLabel.setVisible(false);
		midActorLabel.setVisible(false);
		
		decisionOne.setVisible(false);
		decisionTwo.setVisible(false);
		decisionThree.setVisible(false);
		decisionFour.setVisible(false);
		decisionFive.setVisible(false);
		decisionSix.setVisible(false);
		
		if (explorationOne.getIcon() != null) {
			explorationOne.setVisible(true);
		}
		if (explorationTwo.getIcon() != null) {
			explorationTwo.setVisible(true);
		}
		if (explorationThree.getIcon() != null) {
			explorationThree.setVisible(true);
		}
		if (explorationFour.getIcon() != null) {
			explorationFour.setVisible(true);
		}
		if (explorationFive.getIcon() != null) {
			explorationFive.setVisible(true);
		}
		if (explorationSix.getIcon() != null) {
			explorationSix.setVisible(true);
		}
		
	}
	
	// gets the next button for use with an action listener
	public JButton getNextButton() {
		return nextButton;
	}
	
	// gets one of six decision buttons for use with an action listener
	public ModelDecisionButton getDecisionButton(int num) {
		if (num == 0) {
			return decisionOne;
		}
		if (num == 1) {
			return decisionTwo;
		}
		if (num == 2) {
			return decisionThree;
		}
		if (num == 3) {
			return decisionFour;
		}
		if (num == 4) {
			return decisionFive;
		}
		if (num == 5) {
			return decisionSix;
		}
		else return null;
	}
	
	// gets one of six exploration buttons for use with an action listener
	public ModelExplorationObjectButton getExplorationButton(int num) {
		if (num == 0) {
			return explorationOne;
		}
		if (num == 1) {
			return explorationTwo;
		}
		if (num == 2) {
			return explorationThree;
		}
		if (num == 3) {
			return explorationFour;
		}
		if (num == 4) {
			return explorationFive;
		}
		if (num == 5) {
			return explorationSix;
		}
		else return null;
	}
	
	

}
