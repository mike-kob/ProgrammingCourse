
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceFour extends Dice {

	DiceFour(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(4, inputRectangle, inputCanvas);		
	}

	@Override
	public void draw() {
		this.drawDiceBorders();
		
		this.drawDotUpperLeft();
		this.drawDotLowerLeft();
		this.drawDotUpperRight();
		this.drawDotLowerRight();
	}
}