import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ControlFileHandler {
	
	private String version;
	private ModelStory story;
	private JFileChooser fc;
	private FileNameExtensionFilter filter;
	private Charset charset;
	
	public ControlFileHandler(String version) {
		
		this.version = version;
		story = null;
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filter = new FileNameExtensionFilter("Visual Novel Story (*.vns)", "vns");
		fc.setFileFilter(filter);
		charset = Charset.forName("US-ASCII");
	}
	
	public void saveStory(ModelStory story) {
		
		this.story = story;
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			// ensure filename is correct
			File file = fc.getSelectedFile();
			Path filePath;
			if (!file.getName().endsWith("vns")) {
				filePath = Paths.get(fc.getSelectedFile().getAbsolutePath() + ".vns");
			} else {
				filePath = Paths.get(fc.getSelectedFile().getAbsolutePath());
			}
			
			// write story data to file
			try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {
				writer.write(outputToFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// build image db
			ArrayList<ModelNamedImage> imagedb = new ArrayList<ModelNamedImage>(story.getBackgroundSize() + story.getActorsSize() + story.getExplorationSize());
			imagedb.addAll(story.getImageDatabase("background"));
			imagedb.addAll(story.getImageDatabase("actor"));
			imagedb.addAll(story.getImageDatabase("exploration"));
			
			// write images db to file
			String vniPath = fc.getSelectedFile().getAbsolutePath();
			if (vniPath.endsWith(".vns")) {
				vniPath = vniPath.substring(0, vniPath.length() - 4) + ".vni";
			}
			else {
				vniPath += ".vni";
			}
			filePath = Paths.get(vniPath);
		    try (ObjectOutputStream out = new ObjectOutputStream(
		    	Files.newOutputStream(filePath))) {
		    	out.writeObject(imagedb);
		    } catch (IOException x) {
		    	System.err.println(x);
		    }

		}
	}
	
	public ModelStory loadStory() {
		
		story = null;
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Path filePath = Paths.get(fc.getSelectedFile().getAbsolutePath());
			
			// story variables
		    int scenecount = 0, switchcount = 0, countercount = 0, actorcount = 0, backgroundcount = 0, explorationcount = 0;
		    ModelScene scene = null;
		    ModelSceneElement element = null;
		    int elementcount = 0;
			
			// read file
			try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
				
				String line = reader.readLine();
			    
			    // line and block reading
			    if (line.equals("<vns>")) {
			    	while (!line.equals("</vns>")) {
				        while (true) {
				        	if (line.startsWith("title=")) {
					        	story = new ModelStory(line.substring(6));
					        	break;
					        }
					        else if (line.startsWith("scenecount=")) {
					        	scenecount = Integer.parseInt(line.substring(11));
					        	break;
					        }
					        else if (line.startsWith("switchcount=")) {
					        	switchcount = Integer.parseInt(line.substring(12));
					        	break;
					        }
					        else if (line.startsWith("countercount=")) {
					        	countercount = Integer.parseInt(line.substring(13));
					        	break;
					        }
					        else if (line.startsWith("actorcount=")) {
					        	actorcount = Integer.parseInt(line.substring(11));
					        	break;
					        }
					        else if (line.startsWith("backgroundcount=")) {
					        	backgroundcount = Integer.parseInt(line.substring(16));
					        	break;
					        }
					        else if (line.startsWith("explorationcount=")) {
					        	explorationcount = Integer.parseInt(line.substring(17));
					        	break;
					        }
					        else if (line.equals("<scene>")) {
					        	line = reader.readLine();
					        	if (line.startsWith("scenename=")) {
					        		scene = new ModelScene(line.substring(10), 0);
					        		elementcount = Integer.parseInt(reader.readLine().substring(13));
					        		scene.setBackground(Integer.parseInt(reader.readLine().substring(11)));
					        		scene.setStartElement(Integer.parseInt(reader.readLine().substring(13)));
					        		if (story != null) {
					        			story.addScene(scene);
					        		}
					        		break;
					        	}	
					        }
					        else if (line.equals("<element>")) {
					        	reader.mark(30);
					        	line = reader.readLine();
					        	if (line.endsWith("beat")) {
					        		element = new ModelBeat(reader.readLine().substring(12));
					        		element.setNext(Integer.parseInt(reader.readLine().substring(12)));
					        		
					        		// dialog loop
					        		String dialog = "";
					        		line = reader.readLine().substring(11);
					        		while (!line.startsWith("beatspeaker=")) {
					        			dialog += line;
					        			line = reader.readLine();
					        		}
					        		
					        		((ModelBeat) element).setDialog(dialog);
					        		((ModelBeat) element).setSpeaker(line.substring(12));
					        		if (((ModelBeat) element).getSpeaker().equals("null")) {
					        			((ModelBeat) element).setSpeaker(null);
					        		}
					        		((ModelBeat) element).setLeftActor(Integer.parseInt(reader.readLine().substring(10)));
					        		((ModelBeat) element).setRightActor(Integer.parseInt(reader.readLine().substring(11)));
					        		((ModelBeat) element).setMidActor(Integer.parseInt(reader.readLine().substring(9)));
					        		if (scene != null) {
					        			scene.addElement(element);
					        		}
					        		break;
					        	}
					        	else if (line.endsWith("decision")) {
					        		element = new ModelDecision(reader.readLine().substring(12));
					        		int decisionCount = Integer.parseInt(reader.readLine().substring(14));
					        		String optString = "";
					        		for (int d = 0; d < decisionCount; ++d) {
					        			optString = reader.readLine().substring(8);
					        			((ModelDecision) element).setDecision(
					        					d, 
					        					optString, 
					        					Integer.parseInt(reader.readLine().substring(9)));
					        		}
					        		if (scene != null) {
					        			scene.addElement(element);
					        		}
					        		break;
					        	}
					        	else if (line.endsWith("pointer")) {
					        		element = new ModelPointer(reader.readLine().substring(12));
					        		element.setNext(Integer.valueOf(reader.readLine().substring(10)));
					        		if (scene != null) {
					        			scene.addElement(element);
					        		}
					        		break;
					        	}
					        	reader.reset();
					        }
				        	break;
				        }
				        line = reader.readLine();
				    }
			    }
			} catch (IOException x) {
			    x.printStackTrace();
			}
			
			// adjust path for images db
			String vniPath = fc.getSelectedFile().getAbsolutePath();
			vniPath = vniPath.substring(0, vniPath.length() - 3) + "vni";
			filePath = Paths.get(vniPath);
			
			// load images from database file
			try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(filePath))) {
				ArrayList<ModelNamedImage> imagedb = (ArrayList<ModelNamedImage>) in.readObject();
				for (int i = 0; i < (backgroundcount + actorcount + explorationcount); ++i) {
					if (i < backgroundcount) {
						story.addBackground(imagedb.get(i));
					}
					else if (i < (backgroundcount + actorcount)) {
						if (imagedb.get(i).getName().equals("Empty")) {
							continue;
						}
						story.addActor(imagedb.get(i));
					}
					else {
						story.addExplorationObject(imagedb.get(i));
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return story;
	}
	
	private String outputToFile() {
		
		StringBuilder output = new StringBuilder();
		Date date = new Date();
		
		// file header info
		output.append("<vns>\r\n"
				+ "<meta>\r\n"
				+ "<created>" + date.toString() + "</created>\r\n"
				+ "<version>" + version + "</version>\r\n"
				+ "</meta>\r\n");
		
		// story info
		output.append("<story>\r\n"
				+ "title=" + story.getTitle()
				+ "\r\nscenecount=" + story.getScenesSize()
				+ "\r\nswitchcount=" + story.getSwitchesSize()
				+ "\r\ncountercount=" + story.getCountersSize()
				+ "\r\nactorcount=" + story.getActorsSize()
				+ "\r\nbackgroundcount=" + story.getBackgroundSize()
				+ "\r\nexplorationcount=" + story.getExplorationSize());
		
		// start scene and elements loop
		for (int i = 0; i < story.getScenesSize(); ++i) {
			
			ModelScene scene = story.getScene(i);
			
			// scene info
			output.append("\r\n\r\n<scene>"
					+ "\r\nscenename=" + scene.getName()
					+ "\r\nelementcount=" + scene.getSceneSize()
					+ "\r\nbackground=" + scene.getBackground()
					+ "\r\nstartelement=" + scene.getStartElement());
			
			// element info loop
			for (int j = 0; j < scene.getSceneSize(); ++j) {
				
				ModelSceneElement element = scene.getElement(j);
				output.append("\r\n<element>\r\n" + "elementtype=");
				if (element instanceof ModelBeat) {
					output.append("beat"
							+ "\r\nelementname=" + element.getName()
							+ "\r\nelementnext=" + element.getNext());
					output.append("\r\nbeatdialog=" + ((ModelBeat) element).getDialog()
							+ "\r\nbeatspeaker=" + ((ModelBeat) element).getSpeaker()
							+ "\r\nleftactor=" + ((ModelBeat) element).getLeftActor()
							+ "\r\nrightactor=" + ((ModelBeat) element).getRightActor()
							+ "\r\nmidactor=" + ((ModelBeat) element).getMidActor());
				}
				else if (element instanceof ModelDecision) {
					output.append("decision"
							+ "\r\nelementname=" + element.getName()
							+ "\r\ndecisioncount=" + ((ModelDecision) element).getDecisionCount());
					for (int k = 0; k < ((ModelDecision) element).getDecisionCount(); ++k) {
						output.append("\r\noption" + Integer.toString(k + 1) + "=" 
								+ ((ModelDecision) element).getDecision(k) + "\r\npointer" 
								+ Integer.toString(k + 1) + "=" 
								+ ((ModelDecision) element).getNextForDecision(k));
					}
				}
				else if (element instanceof ModelPointer) {
					output.append("pointer"
							+ "\r\nelementname=" + element.getName()
							+ "\r\nnextscene=" + element.getNext());
				}
				else if (element instanceof ModelLogic) {
					output.append("logic"
							+ "\r\nelementname=" + element.getName()
							+ "\r\nnextscene=" + element.getNext()
							+ "\r\nfalsenextscene=" + ((ModelLogic) element).getFalseNext()
							+ "\r\ntype=" + ((ModelLogic) element).getType()
							+ "\r\ncompareindex=" + ((ModelLogic) element).getIndex()
							+ "\r\nvalcomparison=" + ((ModelLogic) element).getValComparison());
				}
				else if (element instanceof ModelExploration) {
					output.append("exploration"
							+ "\r\nelementname=" + element.getName()
							+ "\r\nnextscene=" + element.getNext()
							+ "\r\nexploreobjectcount=" + ((ModelExploration) element).getObjectCount()
							);
				}
				output.append("\r\n</element>");
			}
			
			output.append("\r\n</scene>\r\n");
		}
		
		// footer
		output.append("</story>\r\n</vns>");

		return output.toString();
	}

}
