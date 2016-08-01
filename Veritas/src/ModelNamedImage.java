import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ModelNamedImage implements Serializable {

	private static final long serialVersionUID = -2855375875681666033L;
	private ImageIcon img;
	private String name;
	
	/** CONSTRUCTORS */
	public ModelNamedImage(String path, String name) throws IOException {
		img = new ImageIcon(ImageIO.read(new File(path)));
		this.name = name;
	}
	
	public ModelNamedImage(BufferedImage bi, String name) throws IOException {
		img = new ImageIcon(bi);
		this.name = name;
	}
	
	/** ACCESSORS */
	public String getName() {
		return name;
	}
	
	public ImageIcon getImage() {
		return img;
	}
	
	/** MUTATORS */
	public void setName(String name) {
		this.name = name;
	}

}
