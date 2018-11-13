
import acm.graphics.GCanvas;
import acm.graphics.GRoundRect;

public final class DiceOne extends Dice {
	
	DiceOne(GRoundRect inputRectangle, GCanvas inputCanvas) {
		super(1, inputRectangle, inputCanvas);		
	}
	@Override
	public void draw() {
		this.drawDiceBorders();
		this.drawDotCenter();
		
	}
}
