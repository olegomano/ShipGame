package shipGameUi;

import java.awt.Graphics;

import shipGameShip.Ship;
import shipGameUi.UiInterface.BUTTON_CLICKED;

public class UiStatusWindow extends InterfaceComponent{
	private Ship shipData;
	private int amountOfLines = 5;
	private UiDataLine[] dataLine = new UiDataLine[amountOfLines];
	public UiStatusWindow(int i) {
		super(i);
		// TODO Auto-generated constructor stub
		for(int b = 0; b < dataLine.length; b++){
			dataLine[b] = new UiDataLine("Test Line");
		}
		dataLine[0] = new UiDataLine("Ship Name");
		dataLine[1] = new UiDataLine("Selected Component");
		dataLine[2] = new UiDataLine("Position");
	}

	@Override
	public float percentX() {
		return .67f;
	}

	@Override
	public BUTTON_CLICKED returnEnum() {
		return BUTTON_CLICKED.NONE_CLICKED;
	}
	
	public synchronized void setDataToDisplay(Ship s){
		shipData = s;
		
	}
	
	public void resizeComponent(float xPos, float yPos, float UiL, float UiT,
			float UiR, float UiB) {
		super.resizeComponent(xPos, yPos, UiL, UiT, UiR, UiB);
		int lineAmount = dataLine.length;
		float height = endOfCy - this.yPos;
		float lineH = height / lineAmount;
		dataLine[0].resize(this.xPos, this.yPos, endOfCx, this.yPos + lineH);
		for(int i = 1; i < dataLine.length; i++){
			dataLine[i].resize(this.xPos, dataLine[i - 1].getEndY(), endOfCx, dataLine[i - 1].getEndY() + lineH);
		}
		
	}
	

	@Override
	public float percentY() {
		// TODO Auto-generated method stub
		return .8f;
	}
	

	@Override
	public void drawSelf(Graphics g) {
		if(shipData != null){
			dataLine[0].setData(shipData.toString());
			dataLine[1].setData("Ship Selected");
			dataLine[2].setData(shipData.getxPos() + " " + shipData.getyPos());
		}else{
			for(int i = 0; i < dataLine.length; i++){
				dataLine[i].setData("no ship selected");
			}
		}
		g.drawRect((int) xPos,(int) yPos,(int) ( endOfCx - xPos) , (int) (endOfCy - yPos));
		for(int i = 0; i < dataLine.length; i++){
			dataLine[i].drawSelf(g);
		}
	}
}

