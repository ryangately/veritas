import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game {

	final private String VERSION = "Vino v0.7 (GenCon)";
	private ViewPlay viewPlay;
	private ModelStory story;
	private ModelScene currentScene;
	private ModelSceneElement currentElement;

	public Game(ModelStory ms) throws IOException {

		// main
		story = ms;
		viewPlay = new ViewPlay(story.getTitle() + " - " + VERSION);

		// event listeners
		viewPlay.getNextButton().addActionListener(new nextButtonListener());
		viewPlay.getDecisionButton(0).addActionListener(
				new decisionButtonListener());
		viewPlay.getDecisionButton(1).addActionListener(
				new decisionButtonListener());
		viewPlay.getDecisionButton(2).addActionListener(
				new decisionButtonListener());
		viewPlay.getDecisionButton(3).addActionListener(
				new decisionButtonListener());
		viewPlay.getDecisionButton(4).addActionListener(
				new decisionButtonListener());
		viewPlay.getDecisionButton(5).addActionListener(
				new decisionButtonListener());
		viewPlay.getExplorationButton(0).addActionListener(
				new explorationButtonListener());
		viewPlay.getExplorationButton(1).addActionListener(
				new explorationButtonListener());
		viewPlay.getExplorationButton(2).addActionListener(
				new explorationButtonListener());
		viewPlay.getExplorationButton(3).addActionListener(
				new explorationButtonListener());
		viewPlay.getExplorationButton(4).addActionListener(
				new explorationButtonListener());
		viewPlay.getExplorationButton(5).addActionListener(
				new explorationButtonListener());

		// start game
		runScene(story.getScene(story.getStartPoint()));

	}

	private class nextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (currentElement instanceof ModelBeat
					&& currentElement.getNext() < currentScene.getSceneSize()) {
				runElement(currentScene.getElement(currentElement.getNext()));
			}
		}
	}

	// action listener used for all six decision buttons
	private class decisionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			runElement(currentScene
					.getElement(((ModelDecision) currentElement)
							.getNextForDecision(((ModelDecisionButton) arg0
									.getSource()).getDecisionId())));
		}
	}

	// action listener used for all six exploration objects
	private class explorationButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			runElement(currentScene
					.getElement(((ModelExploration) currentElement)
							.getObjectNext(((ModelExplorationObjectButton) arg0
									.getSource()).getObjectId())));
		}
	}

	// loads a scene to the UI
	private void runScene(ModelScene scene) {
		currentScene = scene;
		viewPlay.setBackground(story.getBackground(scene.getBackground()));
		runElement(scene.getElement(scene.getStartElement()));
	}

	// passes the current element to the appropriate handler
	private void runElement(ModelSceneElement e) {
		if (e instanceof ModelBeat) {
			runBeat((ModelBeat) e);
		} else if (e instanceof ModelDecision) {
			runDecision((ModelDecision) e);
		} else if (e instanceof ModelExploration) {
			runExploration((ModelExploration) e);
		} else if (e instanceof ModelLogic) {
			runLogic((ModelLogic) e);
		} else if (e instanceof ModelToggle) {
			runToggle((ModelToggle) e);
		} else {
			runScene(story.getScene(e.getNext()));
		}
	}

	// loads a beat to the UI
	private void runBeat(ModelBeat beat) {

		currentElement = beat;

		// update any actor changes
		// check for string variable
		// if it exists, replace the placeholder
		if (beat.getSpeaker() != null) {
			if (beat.getSpeaker().contains("%STR")) {
				String newSpeaker = beat.getSpeaker();
				int varIndex = Integer.valueOf(newSpeaker.substring(
						newSpeaker.indexOf("%STR") + 4,
						newSpeaker.indexOf("%STR") + 5));
				int replaceIndex = newSpeaker.indexOf("%STR");
				String replaceVal = newSpeaker.substring(replaceIndex,
						replaceIndex + 5);
				newSpeaker = newSpeaker.replaceAll(replaceVal,
						story.getStringVar(varIndex).getVariable());
				viewPlay.setSpeakerName(newSpeaker);
			} else {
				viewPlay.setSpeakerName(beat.getSpeaker());
			}
		}
		if (beat.getLeftActor() < story.getActorsSize()) {
			viewPlay.setLeftActor(story.getActor(beat.getLeftActor()));
		}
		if (beat.getRightActor() < story.getActorsSize()) {
			viewPlay.setRightActor(story.getActor(beat.getRightActor()));
		}
		if (beat.getMidActor() < story.getActorsSize()) {
			viewPlay.setMidActor(story.getActor(beat.getMidActor()));
		}

		// check for string variable
		// if it exists, replace the placeholder
		if (beat.getDialog().contains("%STR")) {
			String newDialog = beat.getDialog();
			int varIndex = Integer.valueOf(newDialog.substring(
					newDialog.indexOf("%STR") + 4,
					newDialog.indexOf("%STR") + 5));
			int replaceIndex = newDialog.indexOf("%STR");
			String replaceVal = newDialog.substring(replaceIndex,
					replaceIndex + 5);
			newDialog = newDialog.replaceAll(replaceVal,
					story.getStringVar(varIndex).getVariable());
			viewPlay.setDialog(newDialog);
		} else {
			viewPlay.setDialog(beat.getDialog());
		}

		// display dialog text
		viewPlay.showDialog();
	}

	// loads a decision element to the UI
	private void runDecision(ModelDecision decision) {

		currentElement = decision;
		viewPlay.clearDecisions();

		for (int i = 0; i < decision.getDecisionCount(); ++i) {
			viewPlay.setDecision(i,
					((ModelDecision) currentElement).getDecision(i));
		}

		viewPlay.showDecisions();
	}

	// loads an exploration to the UI
	private void runExploration(ModelExploration e) {

		currentElement = e;
		viewPlay.clearExploration();

		for (int i = 0; i < e.getObjectCount(); ++i) {
			viewPlay.setExplorationIcon(i, e.getObjectX(i), e.getObjectY(i),
					story.getExplorationObject(e.getObjectImage(i)));
		}

		viewPlay.showExploration();
	}

	// performs a logical comparison with a switch or counter
	private void runLogic(ModelLogic log) {

		currentElement = log;
		// next element will depend on the comparison
		// if type is -1, it is a bool comparison
		if (log.getType() == -1) {
			runElement(currentScene.getElement(log.compare(story.getSwitch(
					log.getIndex()).getValue())));
		}
		// otherwise it is a counter comparison
		else {
			runElement(currentScene.getElement(log.compare(story.getCounter(
					log.getIndex()).getValue())));
		}
	}

	// changes the value on one switch or counter
	private void runToggle(ModelToggle t) {

		currentElement = t;
		int type = t.getType();

		// new bool value
		if (type == 0) {
			story.getSwitch(t.getIndex()).setValue(t.getNewBool());
		}
		// new integer value
		else if (type == 1) {
			story.getCounter(t.getIndex()).setValue(t.getNewValue());
		}
		// increment
		else if (type == 2) {
			story.getCounter(t.getIndex()).setValue(
					story.getCounter(t.getIndex()).getValue() + 1);
		}
		// decrement
		else if (type == 3) {
			story.getCounter(t.getIndex()).setValue(
					story.getCounter(t.getIndex()).getValue() - 1);
		}
		// string
		else if (type == 4) {
			story.getStringVar(t.getIndex()).setVariable(t.getNewString());
		}

		runElement(currentScene.getElement(t.getNext()));
	}

}
