
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceTwo extends Dice {

	DiceTwo(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(2, inputRectangle, inputCanvas);		
	}

	@Override
	public void draw() {
		this.drawDiceBorders();
		
		this.drawDotUpperLeft();
		this.drawDotLowerRight();
	}
}
