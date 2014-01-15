package shipGameUi;
import java.awt.Color;
import java.awt.Graphics;

import shipGameShip.Ship;
import shipGameUi.UiInterface.BUTTON_CLICKED;


public class GameUi implements UiInterface {
	float bottomX = 0;
	float bottomY = 0;
	float topX = 0;
	float topY = 0;
	private final float UI_HORIZONTAL_SIZE = .8f; 
	private final float UI_VERTICAL_SIZE = .85f;
	private float UI_START_X = 10;
	private float UI_START_Y = 10;
	//Create UIobject class and make an array of it here
	InterfaceComponent uiComponents[] = {new EndButton(0), new UiStatusWindow(1), new EndButton(2)}; 
	
	public void initializeUI() {
		resizeUi();
	}
		
	public BUTTON_CLICKED buttonClicked(float xClick, float yClick) {
		for(int i = 0; i < uiComponents.length; i++){
			return uiComponents[i].isClicked(xClick, yClick);
		}
		return BUTTON_CLICKED.NONE_CLICKED;
		
	}

	public void setUiSize(float x, float y) {
		System.out.println("Resizing UI");
		bottomX = x * UI_HORIZONTAL_SIZE;
		bottomY = y;
		topX = x * (1 - UI_HORIZONTAL_SIZE);
		topY = y * UI_VERTICAL_SIZE;
		/*
		for(int i = 0; i < uiComponents.length; i++){
			uiComponents[i].resizeComponent(UI_START_X,UI_START_Y,topX, topY,bottomX, bottomY);
		}
		*/
		resizeUi();
		System.out.println(topX + " " + topY);
		System.out.println(bottomX + " " + bottomY);
		
	}
	
	public void drawUI(Graphics g) {
		//System.out.println("Drawing UI");
		g.setColor(Color.gray);
		g.fillRect((int) topX, (int) topY, (int) ( bottomX - topX ), (int) ( bottomY - topY ));
		g.setColor(Color.black);
		g.drawRect((int) topX, (int) topY, (int) ( bottomX - topX ), (int) ( bottomY - topY ));
		for(int i = 0; i < uiComponents.length; i ++){
			uiComponents[i].drawSelf(g);
		}
		
	}
	  
	private void resizeUi(){
		float uiSpaceOccupied = 0; 
		for(int i = 0; i < uiComponents.length; i++){
			uiSpaceOccupied += uiComponents[i].percentX() * (bottomX - topX);
		}
		float freeSpace = (bottomX - topX) - uiSpaceOccupied;
		float xOffSet = freeSpace / ( uiComponents.length  + 1);
		
		for(int i = 0; i < uiComponents.length; i++){
			if(i == 0){
				float uiYpos = ( (bottomY - topY) * (1 - uiComponents[i].percentY() ) ) /2.0f + topY;
				uiComponents[i].resizeComponent(xOffSet + topX, uiYpos, topX, topY, bottomX, bottomY);
			}
			else{
				float previousX = uiComponents[i - 1].returnEnd()[0] + xOffSet;
				float previousY = ( (bottomY - topY) * (1 - uiComponents[i].percentY() ) ) /2.0f + topY;
				System.out.println("PREV UI ELEMENT END: " + previousX +" " + previousY);
				uiComponents[i].resizeComponent(previousX, previousY, topX, topY, bottomX, bottomY);
			}
		}		
	}

	@Override
	public void setData(Ship s) {
		for(int i = 0; i < uiComponents.length; i++){
			if(uiComponents[i] instanceof UiStatusWindow){
				((UiStatusWindow) uiComponents[i]).setDataToDisplay(s);
			}
		}
	}
}
	

