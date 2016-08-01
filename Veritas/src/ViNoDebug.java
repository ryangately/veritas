import java.io.IOException;

public class ViNoDebug {
	
	private ModelStory story;
	private ModelScene sceneOne;
	private ModelBeat beatOne;
	private ModelBeat beatTwo;
	private ModelBeat beatThree;
	private ModelDecision decision;
	private ModelBeat beatFive;
	private ModelBeat beatSix;
	private ModelExploration exploration;
	private ModelBeat beatSeven;
	private ModelLogic logicEight;
	private ModelLogic logicNine;
	private ModelToggle toggleTen;
	
	public ViNoDebug() throws IOException {
		
		story = new ModelStory("The 3D Printed Man");
		story.addBackground("images/spacebg.png", "Space Station");
		story.addActor("images/character.png", "Bruce");
		story.addExplorationObject("images/smallbook.png", "Book");
		story.addSwitch(new ModelSwitch("Test bool", false));
		story.addCounter(new ModelCounter("Test counter"));
		story.getCounter(0).setValue(4);
		
		sceneOne = new ModelScene("one", 0);
		
		beatOne = new ModelBeat("B0");
		beatOne.setDialog("Hello today. Am I the 3D printed man today?");
		beatOne.setSpeaker("Bruce");
		beatOne.setLeftActor(1);
		beatOne.setNext(1);
		
		beatTwo = new ModelBeat("B1");
		beatTwo.setDialog("As for me, I'd have to say my favorite food is...");
		beatTwo.setNext(2);
		
		beatThree = new ModelBeat("B2");
		beatThree.setDialog("Every one of the bananas.");
		beatThree.setNext(3);
		
		decision = new ModelDecision("D3");
		decision.setDecision(0, "What's your favorite movie?", 4);
		decision.setDecision(1, "You're cute.", 10);
		
		beatFive = new ModelBeat("B4");
		beatFive.setDialog("Who me? As for me, I'd have to say my favorite movie is Fight House.");
		beatFive.setNext(6);
		
		beatSix = new ModelBeat("B5");
		beatSix.setDialog("Thanks. I could say the same about you! Ha ha!");
		beatSix.setNext(8);
		
		exploration = new ModelExploration("E6");
		exploration.addExplorationObject(0, 100, 100, 0, 7);
		
		beatSeven = new ModelBeat("B7");
		beatSeven.setDialog("Who me? That's my favorite book: Fight House.");
		beatSeven.setNext(3);
		
		logicEight = new ModelLogic("L8");
		logicEight.setNext(1);
		logicEight.setNextFalse(7);
		logicEight.setComparison(0);
		
		logicNine = new ModelLogic("L9");
		logicNine.setNext(5);
		logicNine.setNextFalse(0);
		logicNine.setComparison(0, 3, 0);
		
		toggleTen = new ModelToggle("T10");
		toggleTen.setNext(9);
		toggleTen.setDecrement(0);
		
		sceneOne.addElement(beatOne);
		sceneOne.addElement(beatTwo);
		sceneOne.addElement(beatThree);
		sceneOne.addElement(decision);
		sceneOne.addElement(beatFive);
		sceneOne.addElement(beatSix);
		sceneOne.addElement(exploration);
		sceneOne.addElement(beatSeven);
		sceneOne.addElement(logicEight);
		sceneOne.addElement(logicNine);
		sceneOne.addElement(toggleTen);
		story.addScene(sceneOne);
	}
	
	public ModelStory getDebugStory() {
		return story;
	}

}
