
public class Combination {
	int rangOfComb = 0;
	int[] combPl1;
	int[] combPl2;

	Combination(int[] valuePl1, int[] valuePl2) {
		combPl1 = valuePl1;
		combPl2 = valuePl2;
	}

	public int getRangOfComb() {
		return rangOfComb;
	}

	public String getWinner() {
		if (getComb(combPl1) > getComb(combPl2)) {
			return "Player 1";
		} else if (getComb(combPl1) < getComb(combPl2)) {
			return "Player 2";
		}

		// Find out what face-value of each player is
		int[] faceValue = new int[2];
		int[] valuesNum = calcNumber(combPl1);

		for (int j = 2; j <= 5; j++) {
			for (int i = 1; i < 7; i++) {
				if (valuesNum[i] == j) {
					faceValue[0] = i;
				}
			}
		}
		valuesNum = calcNumber(combPl2);
		for (int j = 2; j <= 5; j++) {
			for (int i = 1; i < 7; i++) {
				if (valuesNum[i] == j) {
					faceValue[1] = i;
				}
			}
		}
		// ----------------------------

		// Get winner using face-value of dice
		if (faceValue[0] > faceValue[1]) {
			return "Player 1";
		} else if (faceValue[0] < faceValue[1]) {
			return "Player 2";
		}
		// ----------------------------------

		// Find out what the sum of 'extra' dice is
		int[] sum = new int[2];
		for (int i = 1; i < 7; i++) {
			if (i != faceValue[0] && valuesNum[i] != 0) {
				sum[1] += i;
			}
		}

		valuesNum = calcNumber(combPl1);
		for (int i = 1; i < 7; i++) {
			if (i != faceValue[0] && valuesNum[i] != 0) {
				sum[0] += i;
			}
		}

		if (sum[0] > sum[1]) {
			return "Player 1";
		} else if (sum[0] < sum[1]) {
			return "Player 2";
		}

		return "Draw";
	}

	public int getComb(int[] combination) {
		int[] valuesNum = calcNumber(combination);

		if (valuesNum[1] == 1 && valuesNum[2] == 1 && valuesNum[3] == 1 && valuesNum[4] == 1 && valuesNum[5] == 1) {
			rangOfComb = 4;
			// "Five High Straight";
			return 4;
		}

		if (valuesNum[2] == 1 && valuesNum[3] == 1 && valuesNum[4] == 1 && valuesNum[5] == 1 && valuesNum[6] == 1) {
			rangOfComb = 5;
			// "Six High Straight";
			return 5;
		}

		// Sorting the array in descending order
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < i; j++) {
				if (valuesNum[i] > valuesNum[j]) {
					int x = valuesNum[i];
					valuesNum[i] = valuesNum[j];
					valuesNum[j] = x;
				}
			}
		}

		if (valuesNum[1] == 5) {
			rangOfComb = 8;
			// "Five-of-a-Kind";
			return 8;
		}

		if (valuesNum[1] == 4) {
			rangOfComb = 7;
			// "Four-of-a-Kind";
			return 7;
		}

		if (valuesNum[1] == 3) {
			if (valuesNum[2] == 2) {
				rangOfComb = 6;
				// "Full House";
				return 6;
			} else {
				rangOfComb = 3;
				// "Three-of-a-Kind";
				return 3;
			}
		}

		if (valuesNum[1] == 2) {
			if (valuesNum[2] == 2) {
				rangOfComb = 2;
				// "Two pairs";
				return 2;
			} else {
				rangOfComb = 1;
				// "Pair";
				return 1;
			}
		}

		// "Nothing";
		return 0;
	}

	/**
	 * Gets the name of hand, depending on the player ("1"-Player 1, "2"-Player2)
	 * 
	 * @param player
	 * @return
	 */
	public String getName(int player) {
		if (player == 1) {
			switch (getComb(combPl1)) {
			case 1:
				return "Pair";
			case 2:
				return "Two pairs";
			case 3:
				return "Three-of-a-Kind";
			case 4:
				return "Five High Straight";
			case 5:
				return "Six High Straight";
			case 6:
				return "Full House";
			case 7:
				return "Four-of-a-Kind";
			case 8:
				return "Five-of-a-Kind";
			default:
				return "Nothing";
			}

		} else {
			switch (getComb(combPl2)) {
			case 1:
				return "Pair";
			case 2:
				return "Two pairs";
			case 3:
				return "Three-of-a-Kind";
			case 4:
				return "Five High Straight";
			case 5:
				return "Six High Straight";
			case 6:
				return "Full House";
			case 7:
				return "Four-of-a-Kind";
			case 8:
				return "Five-of-a-Kind";
			default:
				return "Nothing";
			}
		}
	}

	private int[] calcNumber(int[] array) {
		int[] values = new int[7];
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j <= 6; j++) {
				if (array[i] == j) {
					values[j]++;
				}
			}
		}
		return values;
	}
}
