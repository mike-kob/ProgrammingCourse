package pack;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Game extends GraphicsProgram {

	final GImage background = new GImage("background.png");
	final GImage stopButton = new GImage("stopButton.png");
	final GImage stopPrButton = new GImage("stopPrButton.png");
	final Copter copter = new Copter();

	private final ArrayList<Drop> allDrops = new ArrayList<Drop>();
	private final ArrayList<Aim> allAims = new ArrayList<Aim>();

	double half;
	int scoreNum;
	GLabel score = new GLabel("", 0, 0);

	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 905;
	public static final int COPTER_Y = 70;

	public void init() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(background);
		add(stopButton);
		add(copter, 0, COPTER_Y);

		printScore(0, Color.BLACK);

		half = copter.getWidth() * 0.5;
	}

	public void run() {
		stopButton.addMouseListener(stopListener);
		background.addMouseMotionListener(copterListener);
		background.addMouseListener(mouse);
		stopButton.addMouseMotionListener(copterListener);
		startAimFactory();
		moveDropsFactory();
	}

	private void startAimFactory() {
		new Thread() {
			public void run() {
				while (true) {
					Aim aim = new Aim();
					add(aim);
					allAims.add(aim);
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void moveDropsFactory() {
		new Thread() {
			public void run() {
				while (true) {
					for (int j = allDrops.size() - 1; j >= 0; j--) {
						if (allDrops.get(j).getY() < background.getHeight()) {
							allDrops.get(j).move(0, 4);

							Aim succeesAim = allDrops.get(j).success(allAims);
							if (succeesAim != null) {
								remove(succeesAim);
								allAims.remove(succeesAim);
								remove(allDrops.get(j));
								allDrops.remove(j);

								scoreNum++;
								printScore(scoreNum, Color.BLUE);
							}
						} else {
							remove(allDrops.get(j));
							allDrops.remove(j);

							scoreNum--;
							printScore(scoreNum, Color.RED);
						}
					}

					pause(10);
				}
			}
		}.start();
	}

	private void printScore(int scoreNum, Color color) {
		remove(score);
		score = new GLabel("" + scoreNum, 1125, 70);
		score.setFont("SanSarif-40");
		score.setColor(color);
		add(score);
	}

	MouseMotionListener copterListener = new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent event) {
			copter.setLocation((int) (event.getX() - half), COPTER_Y);

		}

		@Override
		public void mouseMoved(MouseEvent event) {
			copter.setLocation((int) (event.getX() - half), COPTER_Y);
		}
	};

	MouseListener mouse = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent event) {

			Drop drop = new Drop(copter);
			allDrops.add(drop);
			add(drop);

		}
	};

	MouseListener stopListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			System.exit(0);
		}

		public void mouseEntered(MouseEvent arg0) {
			add(stopPrButton, stopButton.getX(), stopButton.getY());
		}

		public void mouseExited(MouseEvent event) {
			remove(stopPrButton);
		}
	};
}
