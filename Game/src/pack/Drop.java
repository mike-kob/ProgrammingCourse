package pack;

import java.util.ArrayList;

import acm.graphics.GImage;

public class Drop extends GImage {

	private static final String IMAGE_PATH = "asphalt.png";

	public Drop(Copter copter) {
		super(IMAGE_PATH, copter.getX() + copter.getWidth() / 2-75, 240);
		//this.move(, arg1);
	}

	public boolean isOnWindow() {
		return getY() + getHeight() <= Game.WINDOW_HEIGHT;
	}

	public Aim success(ArrayList<Aim> aims) {
		for (int i = 0; i < aims.size(); i++) {
			if (this.getY() + this.getHeight()*0.5 >= Aim.Y && aims.get(i).getX() < this.getX()
					&& aims.get(i).getX() + aims.get(i).getWidth() > this.getX() + this.getWidth()) {
				return aims.get(i);
			}

		}
		return null;
	}
}
