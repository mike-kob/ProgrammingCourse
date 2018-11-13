
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceSix extends Dice {

	DiceSix(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(6, inputRectangle, inputCanvas);		
	}

	@Override
	public void draw() {
		this.drawDiceBorders();
		
		this.drawDotUpperLeft();
		this.drawDotMiddleLeft();
		this.drawDotLowerLeft();				
		this.drawDotUpperRight();
		this.drawDotMiddleRight();
		this.drawDotLowerRight();
	}
}