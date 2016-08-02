import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ViewMain {
	
	//class vars
	private JFrame frame;
	private JMenuBar mainMenuBar;
	private ViewStory viewStory;
	
	private ModelStory story;
	
	// file menu
	private JMenu fileMenu;
	private JMenuItem fileMenuNew;
	private JMenuItem fileMenuOpen;
	private JMenuItem fileMenuSave;
	private JMenuItem fileMenuDebugSave;
	
	// story menu
	private JMenu storyMenu;
	private JMenuItem storyMenuSettings;
	private JMenuItem storyMenuImages;
	private JMenuItem storyMenuVariables;
	
	// build menu
	private JMenu buildMenu;
	private JMenuItem buildMenuTest;
	
	public ViewMain(String version) {
		
		// initialize GUI components
		frame = new JFrame(version);
		mainMenuBar = new JMenuBar();
		viewStory = new ViewStory();
		
		// file menu
		fileMenu = new JMenu("File");
		fileMenuNew = new JMenuItem("New Story");
		fileMenuOpen = new JMenuItem("Open");
		fileMenuSave = new JMenuItem("Save");
		fileMenuDebugSave = new JMenuItem("Debug Save");
		fileMenu.add(fileMenuNew);
		fileMenu.add(fileMenuOpen);
		fileMenu.add(fileMenuSave);
		fileMenu.add(fileMenuDebugSave);
		
		// story menu
		storyMenu = new JMenu("Story");
		storyMenuSettings = new JMenuItem("Settings");
		storyMenuImages = new JMenuItem("Images");
		storyMenuVariables = new JMenuItem("Variables");
		storyMenu.add(storyMenuSettings);
		storyMenu.add(storyMenuImages);
		storyMenu.add(storyMenuVariables);
		
		// build menu
		buildMenu = new JMenu("Build");
		buildMenuTest = new JMenuItem("Test");
		buildMenu.add(buildMenuTest);
		
		// combine into window
		mainMenuBar.add(fileMenu);
		mainMenuBar.add(storyMenu);
		mainMenuBar.add(buildMenu);
		frame.setJMenuBar(mainMenuBar);
		frame.add(viewStory);
		
		// finalize GUI settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/** ACCESSORS */
	public JMenuItem getFileMenuNew() {
		return fileMenuNew;
	}
	
	public JMenuItem getFileMenuSave() {
		return fileMenuSave;
	}
	
	public JMenuItem getFileMenuDebugSave() {
		return fileMenuDebugSave;
	}
	
	public JMenuItem getFileMenuOpen() {
		return fileMenuOpen;
	}
	
	public JMenuItem getStoryMenuSettings() {
		return storyMenuSettings;
	}
	
	public JMenuItem getStoryMenuImages() {
		return storyMenuImages;
	}
	
	public JMenuItem getStoryMenuVariables() {
		return storyMenuVariables;
	}
	
	public JMenuItem getBuildMenuTest() {
		return buildMenuTest;
	}
	
	public ViewStory getViewStory() {
		return viewStory;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	/** MUTATORS */
	public void setStory(ModelStory story) {
		this.story = story;
		viewStory.setStory(story);
	}

}
