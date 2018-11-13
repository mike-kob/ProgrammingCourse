
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceThree extends Dice {

	DiceThree(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(3, inputRectangle, inputCanvas);		
	}

	@Override
	public void draw() {
		this.drawDiceBorders();
		
		this.drawDotUpperLeft();
		this.drawDotCenter();
		this.drawDotLowerRight();
	}
}