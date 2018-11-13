import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Symbol extends GraphicsProgram{
	byte turn;
	GImage imgX = new GImage("x.png");
	GImage imgO = new GImage("0.png");
	
	public Symbol(byte turnX) {
		turn = turnX;
	}
	
	public void drawSymbol(GCanvas canvas, GImage grid, byte row, byte column) {
		
		int coorX = (column-1)*(int)grid.getWidth()/3;
		int coorY = (row-1)*(int)grid.getHeight()/3+(int)grid.getY();
		
		if(turn==1) {
			canvas.add(imgX, coorX, coorY);
		} else {
			canvas.add(imgO, coorX, coorY);
		}
	}
	
	public void removeSymbols(GCanvas canvas) {
		canvas.removeAll();
	}
}
