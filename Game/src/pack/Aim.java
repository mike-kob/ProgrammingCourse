package pack;

import java.util.Random;

import acm.graphics.GOval;

public class Aim extends GOval {

	public static final int Y = 760;
	private static final int WIDTH = 200;
	private static final int HEIGHT = 40;

	public Aim() {
		super(getRandomX(), Y, WIDTH, HEIGHT);
		setFilled(true);
	}

	private static int getRandomX() {
		Random random = new Random();
		return random.nextInt(Game.WINDOW_WIDTH - WIDTH);
	}

}
