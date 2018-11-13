
import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRoundRect;

public abstract class Dice{
	protected final int myValue;
	protected final GRoundRect myRectangle;
	protected final GCanvas myCanvas;
	protected final double myRectangleSize;
	protected final double dotSize;
		
	Dice(int inputValue, GRoundRect inputRectangle, GCanvas inputCanvas){
		myValue = inputValue;
		myRectangle = inputRectangle;
		myCanvas = inputCanvas;
		myRectangleSize = myRectangle.getWidth();
		dotSize = myRectangleSize/6;
	}
		
	public abstract void draw();
	
	public int getMyValue() {
		return myValue;
	}
	
	public GRoundRect getMyRectangle() {
		return myRectangle;
	}
	protected void drawDiceBorders() {
		myRectangle.setFillColor(Color.WHITE);
		myRectangle.setFilled(true);
		myCanvas.add(myRectangle);
	}
	protected void drawDotCenter() {
		GOval dot = new GOval(myRectangle.getX() + myRectangleSize/2 - dotSize/2,
				myRectangle.getY() + myRectangle.getWidth()/2 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotUpperLeft() {
		GOval dot = new GOval(myRectangle.getX() + myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + myRectangleSize/4 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotMiddleLeft() {
		GOval dot = new GOval(myRectangle.getX() + myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + myRectangleSize/2 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotLowerLeft() {
		GOval dot = new GOval(myRectangle.getX() + myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + 3*myRectangleSize/4 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotUpperRight() {
		GOval dot = new GOval(myRectangle.getX() + 3*myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + myRectangleSize/4 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotMiddleRight() {
		GOval dot = new GOval(myRectangle.getX() + 3*myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + myRectangleSize/2 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
	
	protected void drawDotLowerRight() {
		GOval dot = new GOval(myRectangle.getX() + 3*myRectangleSize/4 - dotSize/2,
				myRectangle.getY() + 3*myRectangleSize/4 - dotSize/2,
				dotSize,
				dotSize);
		dot.setFillColor(Color.BLACK);
		dot.setFilled(true);
		myCanvas.add(dot);
	}
}
