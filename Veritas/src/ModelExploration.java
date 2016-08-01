
public class ModelExploration extends ModelSceneElement {
	
	// exploration object class
	// only used within an Exploration object
	protected class ExplorationObject {
		
		private int x, y, img, next;
		
		protected ExplorationObject(int x, int y, int img, int next) {
			this.x = x;
			this.y = y;
			this.img = img;
			this.next = next;
		}
		
		protected int getX() {
			return x;
		}
		
		protected int getY() {
			return y;
		}
		
		protected int getImage() {
			return img;
		}
		
		protected int getNext() {
			return next;
		}
	}
	
	// class variables
	private ExplorationObject[] objects = {null, null, null, null, null, null};
	
	
	// main constructor
	public ModelExploration(String name) {
		
		this.name = name;
		this.next = -1;
	}
	
	/** MUTATORS */
	public void addExplorationObject(int num, int x, int y, int img, int next) {
		objects[num] = new ExplorationObject(x, y, img, next);
	}
	
	public void removeExplorationObject(int num) {
		objects[num] = null;
	}
	
	/** ACCESSORS */
	public int getObjectCount() {
		int count = 0;
		for (ExplorationObject o : objects) {
			if (o != null) {
				count++;
			}
		}
		return count;
	}
	
	public int getObjectX(int num) {
		return objects[num].getX();
	}
	
	public int getObjectY(int num) {
		return objects[num].getY();
	}
	
	public int getObjectImage(int num) {
		return objects[num].getImage();
	}
	
	public int getObjectNext(int num) {
		return objects[num].getNext();
	}

}
