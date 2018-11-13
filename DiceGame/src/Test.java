import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

@SuppressWarnings("serial")
public class Test extends GraphicsProgram {

	final GCanvas canvas = this.getGCanvas();

	final GImage background = new GImage("background.png");
	final GImage rollButton = new GImage("rollButton.png");
	final GImage rerollButton = new GImage("rerollButton.png");
	final GImage hint = new GImage("hint.png", 0, 142);
	final GImage rollPrButton = new GImage("rollPrButton.png");
	final GImage rerollPrButton = new GImage("rerollPrButton.png");
	final GImage playButton = new GImage("playButton.png");
	final GImage playPrButton = new GImage("playPrButton.png", 450, 200);
	final GImage restartButton = new GImage("restartButton.png", 150, 200);
	final GImage restartPrButton = new GImage("restartPrButton.png", 150, 200);
	final GImage deskForWinner = new GImage("deskForWinner.png");
	
	
	Dice dice1;
	GLabel winner;
	GRoundRect shadow;

	ArrayList<Integer> selected = new ArrayList<Integer>();
	ArrayList<Integer> selected2 = new ArrayList<Integer>();
	
	private final static int SIZE = 100;
	private double buttonX;
	private byte turn = 1;
	RandomGenerator rgen = RandomGenerator.getInstance();
	
	

	public void run() {
		this.setSize(960, 600);
		add(background);
		add(playButton, (background.getWidth() - playButton.getWidth()) / 2, 200);
		playButton.addMouseListener(playListener);
	}

	private void setUpGame() {

		playButton.removeMouseListener(playListener);
		remove(playButton);

		add(new GImage("desk.png"));

		buttonX = (this.getWidth() - rollButton.getWidth()) / 2;
		add(rollButton, buttonX, 42);
		rollButton.addMouseListener(rollListener);
	}

	final int[] coorXPl1 = new int[] { 50, 200, 350, 130, 270 };
	final int[] coorXPl2 = new int[] { 520, 670, 820, 600, 740 };
	final int[] coorYPl1 = new int[] { 270, 270, 270, 400, 400 };
	final int[] coorYPl2 = new int[] { 270, 270, 270, 400, 400 };
	int[] combValuePl1 = new int[5];
	int[] combValuePl2 = new int[5];
	Combination combinations = new Combination(combValuePl1, combValuePl2);
	GLabel hand1 = new GLabel("", 0, 0);
	GLabel hand2 = new GLabel("", 0, 0);

	private void rolling() {
		// rolling dice
		for (int i = 0; i < 5; i++) {
			int value = rgen.nextInt(1, 6);
			createDice(value, coorXPl1[i], coorYPl1[i]).draw();
			combValuePl1[i] = value;
		}
		for (int i = 0; i < 5; i++) {
			int value = rgen.nextInt(1, 6);
			createDice(value, coorXPl2[i], coorYPl2[i]).draw();
			combValuePl2[i] = value;
		}
		// -------------------

		rollButton.removeMouseListener(rollListener);
		remove(rollButton);
		remove(rollPrButton);

		add(rerollButton, buttonX, 42);
		rerollButton.addMouseListener(rerollListener);

		combinations = new Combination(combValuePl1, combValuePl2);
		showInfo(Color.BLUE,Color.BLACK);
		background.addMouseListener(selectListener);

	}

	private void rerolling() {
		remove(hand1);
		remove(hand2);

		// rolling dice
		for (int i = 0; i < selected.size(); i++) {
			int value = rgen.nextInt(1, 6);
			createDice(value, coorXPl1[selected.get(i)], coorYPl1[selected.get(i)]).draw();
			combValuePl1[selected.get(i)] = value;
		}
		// -------------------
		
		combinations = new Combination(combValuePl1, combValuePl2);
		showInfo(Color.BLACK,Color.BLUE);
		background.removeMouseListener(selectListener);
		background.addMouseListener(select2Listener);
		turn = 2;
	}

	private void rerolling2() {
		remove(hand1);
		remove(hand2);
	
		// rolling dice
		for (int i = 0; i < selected2.size(); i++) {
			int value = rgen.nextInt(1, 6);
			createDice(value, coorXPl2[selected2.get(i)], coorYPl2[selected2.get(i)]).draw();
			combValuePl2[selected2.get(i)] = value;
		}
		// -------------------
		
		combinations = new Combination(combValuePl1, combValuePl2);
		showInfo(Color.BLUE,Color.BLUE);
		
		background.removeMouseListener(select2Listener);
		endGame();

	}



	private void endGame() {
		
		for (int i = 0; i < shadows.length; i++) {
			if (shadows[i] != null) {
				remove(shadows[i]);
			}
			if (shadows2[i] != null) {
				remove(shadows2[i]);
			}
		}
		remove(rerollButton);
		remove(rerollPrButton);
		remove(hint);

		rerollButton.removeMouseListener(rerollListener);

		add(deskForWinner);

		winner = new GLabel("The winner is " + combinations.getWinner(), 70, 90);
		winner.setFont(new Font("Serif", Font.ITALIC, 81));
		winner.setColor(Color.RED);
		add(winner);

		add(restartButton, (this.getWidth() - restartButton.getWidth()) / 2, 100);
		restartButton.addMouseListener(restartListener);

	}

	

	private void restarting() {
		restartButton.removeMouseListener(restartListener);
		removeAll();
		selected = new ArrayList<Integer>();
		selected2 = new ArrayList<Integer>();
		turn = 1;
		init();
		run();
	}

	private void showInfo(Color color1, Color color2) {
		hand1 = new GLabel("\"" + combinations.getName(1) + "\"", 60, 250);
		hand1.setFont("SanSarif-40");
		hand1.setColor(color1);
		add(hand1);

		hand2 = new GLabel("\"" + combinations.getName(2) + "\"", 540, 250);
		hand2.setFont("SanSarif-40");
		hand2.setColor(color2);
		add(hand2);

		add(hint);
	}
	
	GRoundRect[] shadows = new GRoundRect[5];
	GRoundRect[] shadows2 = new GRoundRect[5];
	
	private void addingShadowFor1(int num) {
		shadow = new GRoundRect(coorXPl1[num] - 3, coorYPl1[num] - 3, 106, 106);
		shadow.setFilled(true);
		shadow.setFillColor(Color.GREEN);
		add(shadow);
		
		shadows[num] = shadow;

		shadow.sendToBack();
		shadow.sendForward();
		shadow.sendForward();
		shadow.sendForward();
	}

	private void addingShadowFor2(int num) {
		shadow = new GRoundRect(coorXPl2[num] - 3, coorYPl2[num] - 3, 106, 106);
		shadow.setFilled(true);
		shadow.setFillColor(Color.PINK);
		add(shadow);
		
		shadows2[num] = shadow;
		
		shadow.sendToBack();
		shadow.sendForward();
		shadow.sendForward();
		shadow.sendForward();
	}

	private Dice createDice(int value, int x, int y) {
		switch (value) {
		case 1:
			return new DiceOne(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		case 2:
			return new DiceTwo(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		case 3:
			return new DiceThree(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		case 4:
			return new DiceFour(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		case 5:
			return new DiceFive(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		default:
			return new DiceSix(new GRoundRect(x, y, SIZE, SIZE), this.getGCanvas());
		}

	}

	MouseListener playListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			setUpGame();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			add(playPrButton, playButton.getX(), 200);

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			remove(playPrButton);
		}
	};

	MouseListener restartListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			restarting();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			add(restartPrButton, restartButton.getX(), restartButton.getY());
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			remove(restartPrButton);
		}
	};

	MouseListener rollListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent event) {
			rolling();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			add(rollPrButton, buttonX, 42);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			remove(rollPrButton);
		}
	};

	MouseListener rerollListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (turn == 1) {
				rerolling();
			} else if (turn == 2) {
				rerolling2();

			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			add(rerollPrButton, buttonX, 42);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			remove(rerollPrButton);
		}
	};

	private int[] selectedTwice = new int[5];
	private int[] selectedTwice2 = new int[5];

	MouseListener selectListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent event) {
			for (int i = 0; i < 5; i++) {
				if ((event.getX() > coorXPl1[i]) && (event.getX() < coorXPl1[i] + 100) && (event.getY() > coorYPl1[i])
						&& (event.getY() < coorYPl1[i] + 100)) {
					selectedTwice[i]++;
					if (selectedTwice[i] == 2) {
						selectedTwice[i] = 0;
						remove(shadows[i]);
						selected.remove(selected.size()-1);

					} else {
						addingShadowFor1(i);
						selected.add(i);
					}

				}
			}
		}
	};

	MouseListener select2Listener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent event) {
			for (int i = 0; i < 5; i++) {
				if ((event.getX() > coorXPl2[i]) && (event.getX() < coorXPl2[i] + 100) && (event.getY() > coorYPl2[i])
						&& (event.getY() < coorYPl2[i] + 100)) {
					selectedTwice2[i]++;
					if (selectedTwice2[i] == 2) {
						selectedTwice2[i] = 0;
						remove(shadows2[i]);
						if (selected.isEmpty() == false) {
							selected2.remove(selected2.indexOf(i));
						}

					} else {
						addingShadowFor2(i);
						selected2.add(i);
					}
				}
			}
		}
	};
}
