import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ViewStoryImages extends JFrame {

	final private String title = "Story Images";
	private JPanel panel;
	private JTabbedPane tabPane;
	private JPanel globalPane;
	private JPanel actorsPane;
	private JPanel backgroundsPane;
	private JPanel explorePane;

	// global pane
	private JList globalList;
	private JScrollPane globalScrollPane;
	private JButton globalAddBtn;
	private JButton globalDelBtn;

	// actors pane
	private JList actorList;
	private JScrollPane actorScrollPane;
	private JPanel actorBtnPanel;
	private JButton actorAddBtn;
	private JButton actorDelBtn;

	// backgrounds pane
	private JList backgroundList;
	private JScrollPane backgroundScrollPane;
	private JPanel backgroundBtnPanel;
	private JButton backgroundAddBtn;
	private JButton backgroundDelBtn;

	// exploration pane
	private JList exploreList;
	private JScrollPane exploreScrollPane;
	private JPanel exploreBtnPanel;
	private JButton exploreAddBtn;
	private JButton exploreDelBtn;

	private ModelStory story;
	private JFileChooser fc;
	private FileNameExtensionFilter filter;

	private class AddImageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			new NewImageDialog(null);
		}
	}

	public ViewStoryImages(ModelStory s) {
		this.setTitle(title);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 300));
		story = s;
		fc = new JFileChooser();
		filter = new FileNameExtensionFilter("PNG Images", "png");
		fc.setFileFilter(filter);

		panel = new JPanel();
		tabPane = new JTabbedPane();

		// global pane
		globalPane = new JPanel();

		// actor pane
		actorsPane = new JPanel();
		actorList = new JList();
		actorList.setModel(story.getActorList());
		actorScrollPane = new JScrollPane(actorList);
		actorAddBtn = new JButton("Add");
		actorDelBtn = new JButton("Delete");
		actorAddBtn.addActionListener(new AddImageListener());
		actorBtnPanel = new JPanel();
		actorBtnPanel.setLayout(new BoxLayout(actorBtnPanel,
				BoxLayout.LINE_AXIS));
		actorBtnPanel.add(actorAddBtn);
		actorBtnPanel.add(actorDelBtn);
		actorsPane.setLayout(new BoxLayout(actorsPane, BoxLayout.PAGE_AXIS));
		actorsPane.add(actorScrollPane);
		actorsPane.add(actorBtnPanel);

		// background pane
		backgroundsPane = new JPanel();
		backgroundList = new JList();
		backgroundList.setModel(story.getBackgroundList());
		backgroundScrollPane = new JScrollPane(backgroundList);
		backgroundAddBtn = new JButton("Add");
		backgroundDelBtn = new JButton("Delete");
		backgroundAddBtn.addActionListener(new AddImageListener());
		backgroundBtnPanel = new JPanel();
		backgroundBtnPanel.setLayout(new BoxLayout(backgroundBtnPanel,
				BoxLayout.LINE_AXIS));
		backgroundBtnPanel.add(backgroundAddBtn);
		backgroundBtnPanel.add(backgroundDelBtn);
		backgroundsPane.setLayout(new BoxLayout(backgroundsPane,
				BoxLayout.PAGE_AXIS));
		backgroundsPane.add(backgroundScrollPane);
		backgroundsPane.add(backgroundBtnPanel);

		// exploration pane
		explorePane = new JPanel();
		exploreList = new JList();
		exploreList.setModel(story.getExplorationList());
		exploreScrollPane = new JScrollPane(exploreList);
		exploreAddBtn = new JButton("Add");
		exploreDelBtn = new JButton("Delete");
		exploreAddBtn.addActionListener(new AddImageListener());
		exploreBtnPanel = new JPanel();
		exploreBtnPanel.setLayout(new BoxLayout(exploreBtnPanel,
				BoxLayout.LINE_AXIS));
		exploreBtnPanel.add(exploreAddBtn);
		exploreBtnPanel.add(exploreDelBtn);
		explorePane.setLayout(new BoxLayout(explorePane,
				BoxLayout.PAGE_AXIS));
		explorePane.add(exploreScrollPane);
		explorePane.add(exploreBtnPanel);

		tabPane.addTab("Global", globalPane);
		tabPane.addTab("Actors", actorsPane);
		tabPane.addTab("Backgrounds", backgroundsPane);
		tabPane.addTab("Exploration", explorePane);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(tabPane);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// private void

	private class NewImageDialog extends JDialog {

		private JPanel panel;
		private JPanel filePanel;
		private JPanel namePanel;
		private JPanel btnPanel;
		private JButton openFileBtn;
		private JLabel filePathLbl;
		private JLabel nameLbl;
		private JTextField nameField;
		private JButton okBtn;
		private JButton cancelBtn;

		private File imageFile;
		private BufferedImage img;

		public NewImageDialog(JFrame parent) {
			super(parent, true);
			this.setTitle("Import Image");
			this.setLocationRelativeTo(null);
			this.setMinimumSize(new Dimension(200, 20));
			panel = new JPanel();
			filePanel = new JPanel();
			namePanel = new JPanel();
			btnPanel = new JPanel();
			openFileBtn = new JButton("File...");
			filePathLbl = new JLabel("no file loaded");
			nameLbl = new JLabel("Name:");
			nameField = new JTextField();
			okBtn = new JButton("OK");
			cancelBtn = new JButton("Cancel");

			openFileBtn.addActionListener(new ChooseImageFileListener());
			okBtn.addActionListener(new NewImageBtnListener());
			cancelBtn.addActionListener(new NewImageBtnListener());

			filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));
			filePanel.add(openFileBtn);
			filePanel.add(filePathLbl);

			namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
			namePanel.add(nameLbl);
			namePanel.add(nameField);

			btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
			btnPanel.add(okBtn);
			btnPanel.add(cancelBtn);

			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(filePanel);
			panel.add(namePanel);
			panel.add(btnPanel);

			this.add(panel);
			this.pack();
			this.setVisible(true);
		}

		private class ChooseImageFileListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						imageFile = fc.getSelectedFile();
						img = ImageIO.read(imageFile);
						filePathLbl.setText(imageFile.getName());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
								"Cannot read file.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		}

		private class NewImageBtnListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == cancelBtn) {
					dispose();
				} else if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(panel,
							"The image must have a name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (tabPane.getSelectedComponent() == actorsPane) {
					try {
						story.addActorFromFile(img, nameField.getText());
						actorList.setModel(story.getActorList());
						dispose();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (tabPane.getSelectedComponent() == backgroundsPane) {
					try {
						story.addBackgroundFromFile(img, nameField.getText());
						backgroundList.setModel(story.getBackgroundList());
						dispose();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (tabPane.getSelectedComponent() == explorePane) {
					try {
						story.addExploreFromFile(img, nameField.getText());
						exploreList.setModel(story.getExplorationList());
						dispose();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
