import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class ModelStory implements Serializable {

	private static final long serialVersionUID = 6473244257385927147L;
	
	// class variables
	private String title;
	private ArrayList<ModelNamedImage> backgrounds;
	private ArrayList<ModelNamedImage> actors;
	private ArrayList<ModelNamedImage> explorationObjects;
	private ArrayList<ModelScene> scenes;
	private ArrayList<ModelSwitch> switches;
	private ArrayList<ModelCounter> counters;
	private int startPoint;
	
	// constructor
	public ModelStory(String title) throws IOException {
		this.title = title;
		backgrounds = new ArrayList<ModelNamedImage>();
		actors = new ArrayList<ModelNamedImage>();
		explorationObjects = new ArrayList<ModelNamedImage>();
		scenes = new ArrayList<ModelScene>();
		switches = new ArrayList<ModelSwitch>();
		counters = new ArrayList<ModelCounter>();
		startPoint = 0;
		actors.add(new ModelNamedImage("images/empty.png", "Empty"));
	}
	
	/** MUTATORS */
	// adds a background image to the internal db
	public void addBackground(String path, String name) throws IOException {
		backgrounds.add(new ModelNamedImage(path, name));
	}
	
	// adds a background image to the internal db using an existing file
	public void addBackgroundFromFile(BufferedImage img, String name) throws IOException {
		backgrounds.add(new ModelNamedImage(img, name));
	}
	
	// adds an existing ModelNamedImage object to the backgrounds database
	public void addBackground(ModelNamedImage bg) {
		backgrounds.add(bg);
	}
	
	// adds an actor image to the internal db
	public void addActor(String path, String name) throws IOException {
		actors.add(new ModelNamedImage(path, name));
	}
	
	// adds an actor image to the internal db using an existing file
	public void addActorFromFile(BufferedImage img, String name) throws IOException {
		actors.add(new ModelNamedImage(img, name));
	}
	
	// adds an existing ModelNamedImage object to the actors database
	public void addActor(ModelNamedImage actor) {
		actors.add(actor);
	}
	
	public void addExplorationObject(String path, String name) throws IOException {
		explorationObjects.add(new ModelNamedImage(path, name));
	}
	
	// adds an existing ModelNamedImage object to the exploration database
	public void addExplorationObject(ModelNamedImage eo) {
		explorationObjects.add(eo);
	}
	
	// adds a scene to the internal db
	public void addScene(ModelScene scene) {
		scenes.add(scene);
	}
	
	// changes the start point to (scene, element)
	public void setStartPoint(int s) {
		startPoint = s;
	}
	
	// adds a boolean variable to the internal db
	public void addSwitch(ModelSwitch s) {
		switches.add(s);
	}
	
	// adds a counter variable to the internal db
	public void addCounter(ModelCounter c) {
		counters.add(c);
	}
	
	// sets the title of the story
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	/** ACCESSORS */
	// returns the number of backgrounds in the db
	public int getBackgroundSize() {
		return backgrounds.size();
	}
	
	// returns the number of actor images in the db
	public int getActorsSize() {
		return actors.size();
	}
	
	public int getExplorationSize() {
		return explorationObjects.size();
	}
	
	// returns the number of scenes in the db
	public int getScenesSize() {
		return scenes.size();
	}
	
	public int getSwitchesSize() {
		return switches.size();
	}
	
	public int getCountersSize() {
		return counters.size();
	}
	
	// returns the name of the story
	public String getTitle() {
		return title;
	}
	
	// returns the starting point for the story
	public int getStartPoint() {
		return startPoint;
	}
	
	public ModelScene getScene(int index) {
		return scenes.get(index);
	}
	
	public ImageIcon getBackground(int index) {
		return backgrounds.get(index).getImage();
	}
	
	public String getBackgroundName(int index) {
		return backgrounds.get(index).getName();
	}
	
	public ImageIcon getActor(int index) {
		//if (index == 0) return null;
		return actors.get(index).getImage();
	}
	
	public String getActorName(int index) {
		return actors.get(index).getName();
	}
	
	// gets one of the image dbs for use with file storing
	public ArrayList<ModelNamedImage> getImageDatabase(String type) {
		if (type.equals("background")) {
			return backgrounds;
		}
		else if (type.equals("actor")) {
			return actors;
		}
		else if (type.equals("exploration")) {
			return explorationObjects;
		}
		else {
			return null;
		}
	}
	
	public ImageIcon getExplorationObject(int index) {
		return explorationObjects.get(index).getImage();
	}
	
	public ModelSwitch getSwitch(int index) {
		return switches.get(index);
	}
	
	public ModelCounter getCounter(int index) {
		return counters.get(index);
	}
	
	public DefaultListModel<String> getActorList() {
		DefaultListModel<String> actorListModel = new DefaultListModel<String>();
		for (int i = 0; i < actors.size(); ++i) {
			actorListModel.addElement(actors.get(i).getName());
		}
		return actorListModel;
	}
	
	public DefaultListModel<String> getBackgroundList() {
		DefaultListModel<String> backgroundListModel = new DefaultListModel<String>();
		for (int i = 0; i < backgrounds.size(); ++i) {
			backgroundListModel.addElement(backgrounds.get(i).getName());
		}
		return backgroundListModel;
	}
	
	public DefaultListModel<String> getSceneList() {
		DefaultListModel<String> sceneList = new DefaultListModel<String>();
		for (int i = 0; i < scenes.size(); ++i) {
			if (i == startPoint) {
				sceneList.addElement(scenes.get(i).getName() + " *");
			}
			else {
				sceneList.addElement(scenes.get(i).getName());
			}
		}
		return sceneList;
	}
	
	public DefaultComboBoxModel<String> getSceneBoxList() {
		DefaultComboBoxModel<String> sceneList = new DefaultComboBoxModel<String>();
		for (int i = 0; i < scenes.size(); ++i) {
			if (i == startPoint) {
				sceneList.addElement(scenes.get(i).getName() + " *");
			}
			else {
				sceneList.addElement(scenes.get(i).getName());
			}
		}
		return sceneList;
	}
	
	public DefaultListModel<String> getSwitchList() {
		DefaultListModel<String> switchList = new DefaultListModel<String>();
		for (int i = 0; i < switches.size(); ++i) {
			switchList.addElement(switches.get(i).getName());
		}
		return switchList;
	}
	
	public DefaultComboBoxModel<String> getSwitchBoxList() {
		DefaultComboBoxModel<String> switchList = new DefaultComboBoxModel<String>();
		for (int i = 0; i < switches.size(); ++i) {
			switchList.addElement(switches.get(i).getName());
		}
		return switchList;
	}
	
	public DefaultListModel<String> getCounterList() {
		DefaultListModel<String> counterList = new DefaultListModel<String>();
		for (int i = 0; i < counters.size(); ++i) {
			counterList.addElement(counters.get(i).getName());
		}
		return counterList;
	}
	
	public DefaultComboBoxModel<String> getCounterBoxList() {
		DefaultComboBoxModel<String> counterList = new DefaultComboBoxModel<String>();
		for (int i = 0; i < counters.size(); ++i) {
			counterList.addElement(counters.get(i).getName());
		}
		return counterList;
	}

}
