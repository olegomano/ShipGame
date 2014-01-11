package shipGameUi;

import java.awt.Graphics;

import shipGameShip.Ship;
import shipGameUi.UiInterface.BUTTON_CLICKED;

public class UiStatusWindow extends InterfaceComponent{
	private Ship shipData;
	public UiStatusWindow(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float percentX() {
		return .76f;
	}

	@Override
	public BUTTON_CLICKED returnEnum() {
		return BUTTON_CLICKED.NONE_CLICKED;
	}
	
	public void setDataToDisplay(Ship s){
		shipData = s;
	}

	@Override
	public float percentY() {
		// TODO Auto-generated method stub
		return .8f;
	}

	@Override
	public void drawSelf(Graphics g) {
		g.drawRect((int) xPos, (int) yPos, (int) (endOfCx - xPos),	(int) (endOfCy - yPos));
		//TODO: Create data container Line 
		//make an array of Lines
		//make Lines autoresize and know their own positions like UI
		
		float fontHight = g.getFontMetrics(g.getFont()).getHeight();
		float numberOfLines = ( (endOfCy - yPos) / fontHight ) - 1;
		float lineWidth = (endOfCy - yPos) / numberOfLines;
		//System.out.println(numberOfLines + " " + lineWidth);
		if(shipData == null){
			centerText("No Ship Selected", g, xPos, yPos, endOfCx, endOfCy);
			return;
		}
		for(int i = 0; i < numberOfLines - 1; i++){
			g.drawLine((int) xPos, (int) (yPos +  i*lineWidth), (int) endOfCx, (int) ( yPos + i*lineWidth));
		}
		centerText("xPos" + shipData.getxPos(), g, xPos, yPos, endOfCx, endOfCy - 50);
		centerText("yPos" + shipData.getyPos(), g, xPos, yPos + 50, endOfCx, endOfCy);
		// TODO Auto-generated method stub
		
	}

}
