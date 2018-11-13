import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class CrossLine extends GraphicsProgram {

	static byte[][] winnerTable = new byte[4][4];

	CrossLine(byte[][] table) {
		winnerTable = table;
	}

	public boolean winnerExists() {
		if (combination() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isDraw() {
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (winnerTable[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void drawLine(GCanvas canvas) {
		canvas.add(getLine());
	}

	private GImage getLine() {
		GImage crossLine = new GImage("");
		switch (combination()) {
		case 7:
			crossLine = new GImage("crossDiagonal.png", 0, 20);
			return crossLine;

		case 8:
			crossLine = new GImage("crossDiagonal2.png", 0, 20);
			return crossLine;

		case 1:
			crossLine = new GImage("crossHorizontal.png", 0, 20);
			return crossLine;

		case 2:
			crossLine = new GImage("crossHorizontal.png", 0, 240);
			return crossLine;

		case 3:
			crossLine = new GImage("crossHorizontal.png", 0, 420);
			return crossLine;

		case 4:
			crossLine = new GImage("crossVertical.png", 0, 20);
			return crossLine;

		case 5:
			crossLine = new GImage("crossVertical.png", 240, 20);
			return crossLine;

		case 6:
			crossLine = new GImage("crossVertical.png", 500, 20);
			return crossLine;
		}
		return crossLine;

	}

	private static byte combination() {
		if ((winnerTable[1][1] == 1 && winnerTable[2][2] == 1 && winnerTable[3][3] == 1)
				|| (winnerTable[1][1] == -1 && winnerTable[2][2] == -1 && winnerTable[3][3] == -1)) {
			return 7;
		}

		if ((winnerTable[1][3] == 1 && winnerTable[2][2] == 1 && winnerTable[3][1] == 1)
				|| (winnerTable[1][3] == -1 && winnerTable[2][2] == -1 && winnerTable[3][1] == -1)) {
			return 8;
		}

		if ((winnerTable[1][1] == 1 && winnerTable[1][2] == 1 && winnerTable[1][3] == 1)
				|| (winnerTable[1][1] == -1 && winnerTable[1][2] == -1 && winnerTable[1][3] == -1)) {
			return 1;
		}

		if ((winnerTable[2][1] == 1 && winnerTable[2][2] == 1 && winnerTable[2][3] == 1)
				|| (winnerTable[2][1] == -1 && winnerTable[2][2] == -1 && winnerTable[2][3] == -1)) {
			return 2;
		}

		if ((winnerTable[3][1] == 1 && winnerTable[3][2] == 1 && winnerTable[3][3] == 1)
				|| (winnerTable[3][1] == -1 && winnerTable[3][2] == -1 && winnerTable[3][3] == -1)) {
			return 3;
		}

		if ((winnerTable[1][1] == 1 && winnerTable[2][1] == 1 && winnerTable[3][1] == 1)
				|| (winnerTable[1][1] == -1 && winnerTable[2][1] == -1 && winnerTable[3][1] == -1)) {
			return 4;
		}

		if ((winnerTable[1][2] == 1 && winnerTable[2][2] == 1 && winnerTable[3][2] == 1)
				|| (winnerTable[1][2] == -1 && winnerTable[2][2] == -1 && winnerTable[3][2] == -1)) {
			return 5;
		}

		if ((winnerTable[1][3] == 1 && winnerTable[2][3] == 1 && winnerTable[3][3] == 1)
				|| (winnerTable[1][3] == -1 && winnerTable[2][3] == -1 && winnerTable[3][3] == -1)) {
			return 6;
		}
		return 0;
	}

}
