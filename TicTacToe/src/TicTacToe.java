import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class TicTacToe extends GraphicsProgram {

	private static byte turn = 1;
	static byte[][] winnerTable = new byte[4][4];

	final GImage grid = new GImage("grid.jpg");
	final GImage restartButton = new GImage("restartButton.png");
	final GImage restartPrButton = new GImage("restartPrButton.png");

	CrossLine line;
	Symbol symbol;
	GLabel winner;

	public void run() {
		this.setSize((int) grid.getWidth(), 900);
		grid.addMouseListener(listener);
		add(grid, 0, 20);
	}

	private void drawTicTac(int coorX, int coorY) {
		byte row = getRow(coorY);
		byte column = getColumn(coorX);

		if (winnerTable[row][column] == 0) {
			symbol = new Symbol(turn);
			symbol.drawSymbol(this.getGCanvas(), grid, row, column);

			winnerTable[row][column] = turn;
			line = new CrossLine(winnerTable);
			turn = (byte) (-turn);
		}

		if (line.winnerExists()) {
			winner = (turn == -1) ? new GLabel("The winners are crosses!")
					: new GLabel("The winners are noughts!");

			winner.setFont("SanSarif-65");
			winner.setColor(Color.BLUE);
			add(winner, (this.getWidth() - winner.getWidth()) / 2, 750);

			line.drawLine(this.getGCanvas());
			grid.removeMouseListener(listener);
			add(restartButton, (this.getWidth() - restartButton.getWidth()) / 2, 800);
			restartButton.addMouseListener(restartListener);
		}
		
		if (line.isDraw()) {
			winner = new GLabel("It is a draw!");
			winner.setFont("SanSarif-65");
			winner.setColor(Color.BLUE);
			add(winner, (this.getWidth() - winner.getWidth()) / 2, 750);

			grid.removeMouseListener(listener);
			add(restartButton, (this.getWidth() - restartButton.getWidth()) / 2, 800);
			restartButton.addMouseListener(restartListener);
		}
	}

	private void restart() {
		restartButton.removeMouseListener(restartListener);
		removeAll();
		turn = 1;
		winnerTable = new byte[4][4];
		run();
	}

	public byte getRow(int coorY) {
		if (coorY - 20 < grid.getHeight() / 3) {
			return 1;
		} else if (coorY - 20 < grid.getHeight() / 3 * 2) {
			return 2;
		} else {
			return 3;
		}
	}

	public byte getColumn(int coorX) {
		if (coorX < grid.getWidth() / 3) {
			return 1;
		} else if (coorX < grid.getWidth() / 3 * 2) {
			return 2;
		} else {
			return 3;
		}
	}

	MouseListener listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			drawTicTac(event.getX(), event.getY());
		}
	};

	MouseListener restartListener = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			restart();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			add(restartPrButton, restartButton.getX(), restartButton.getY());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			remove(restartPrButton);

		}

	};
}
