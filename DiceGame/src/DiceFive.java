
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceFive extends Dice {

	DiceFive(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(5, inputRectangle, inputCanvas);		
	}

	@Override
	public void draw() {
		this.drawDiceBorders();
		
		this.drawDotUpperLeft();
		this.drawDotLowerLeft();
		this.drawDotCenter();
		this.drawDotUpperRight();
		this.drawDotLowerRight();
	}
}