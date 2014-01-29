package shipGameShip;
import java.awt.Graphics;

import shipGame.AppData;
import shipGame.MainApp;


public class Ship {
	
	
	
	public Ship(float x, float y, String ship){
		xPos = x;
		yPos = y;
		projectedXPos = x;
		projectedYPos = y;
		stringToShip(ship);
		for(int i = 0; i < components.length;i ++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b] != null){
					System.out.print("[S]");
				}
				else{
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}
	
	
	private ShipComponent[][] components;
	private ShipComponent selectedComponent;
	private float xPos;
	private float yPos;
	private float projectedXPos;
	private float projectedYPos;
	private float moveSpeed = 60;
	private int completedCommands = 0;
	protected boolean selected;
	
	public boolean getSelected(){
		return selected;
	}
	
	public boolean action(float x, float y){
		for(int i = 0; i < components.length; i ++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b] != null && components[i][b].isSelected()){
					components[i][b].action(x, y);
					return true;
				}
			}
		}
		return false;
	}
	
	public ShipComponent getSelectedComponent(){
		/*
		for(int i = 0; i <  components.length; i++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b].selected){
					return components[i][b];
				}
			}
		}
		*/
		return selectedComponent;
	}
	
	public void drawShip(Graphics g, float xDis, float yDis, float width){
		//int toScreenX = (int) MainApp.view.toScreenX(xPos);
		//int toScreenY = (int) MainApp.view.toScreenY(yPos);
		//g.drawString("Xpos = " + xPos + "Ypos =" + yPos,toScreenX,toScreenY);
		for(int i = 0; i < components.length; i ++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b] != null){
					components[i][b].drawSelf(g, xDis, yDis, width);
				}
			}
		}
	}
	
	public boolean isSelected(float x, float y){
		for(int i = 0; i < components.length;i ++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b] == null){
					continue;
				}
				if(components[i][b].isInside(x, y)){
					components[i][b].setSelected(true);
					selectedComponent = components[i][b];
					return true;
				}
			}
		}
		return false;
	}
	
	public void clearSelect(){
		for(int i = 0; i < components.length; i++){
			for(int b = 0; b < components[0].length; b++){
				if(components[i][b] != null){
					components[i][b].setSelected(false);
				}
			}
		}
	}
		
	
	public void moveShip(float dt){
		float timeInSeconds = dt; //TODO: Do this before move is called
		//System.out.println(this.toString());
		if(AppData.getCommandArray().size() > 0 && completedCommands < AppData.getCommandArray().size()){
			if(AppData.getCommandArray().get(completedCommands).getTargetShip() == this){
				if(AppData.getCommandArray().get(completedCommands).executeAction(timeInSeconds)){
					completedCommands++;
				}
			}
			else{
				completedCommands++;
			}
			
		}
	}
	
	public void clearOnTurnEnd(){
		completedCommands = 0;
		projectedXPos = xPos;
		projectedYPos = yPos;
	}
	
	public void displaceShip(float x, float y){
		xPos = x;
		yPos = y;
	}
	
	private void stringToShip(String ship){
		String[] stringArray = 	ship.split(",");
		int rowCount = 0;
		for(int i = 0; i < stringArray.length; i ++){
			if(stringArray[i].compareTo("BREAK") == 0){
				rowCount++;
			}
		}
		System.out.println("Ship Hiehgt is " + rowCount );
		int[] lengths = new int[rowCount];
		int countLength = 0;
		int countArray = 0;
		for(int i = 0; i < stringArray.length; i ++){
			if(stringArray[i].compareTo("BREAK") == 0){
				lengths[countArray] = countLength;
				countLength = 0;
				countArray++;
				continue;
			}
			countLength++;
		}
		int max = lengths[0];
		for(int i = 0; i < lengths.length; i ++){
			if(lengths[i] >  max){
				max = lengths[i];
			}
		}
		System.out.println("Ship Length is " + max );
		components = new ShipComponent[max][rowCount];
		String[][] conversionStringArray = new String[max][rowCount];
		int countX = 0;
		int countY = 0;
		
		for(int i = 0; i < stringArray.length; i ++){
			//System.out.println(conversionStringArray.length);
			//System.out.println(conversionStringArray[0].length);
			if(countY == conversionStringArray[0].length){
				break;
			}
			if(stringArray[i].compareTo("BREAK") != 0){
				System.out.println("X = " + countX + " " + max);
				System.out.println("Y = " + countY + " " + rowCount);
				conversionStringArray[countX][countY] = stringArray[i];
				countX++;
			}else{
				countX = 0;
				countY++;
			}
				
		}
		for(int i = 0; i < components.length; i ++){
			for(int b = 0; b < components[0].length; b++){
				String[] secondSplit = conversionStringArray[i][b].split("/");
				
				String weapon = null;
				if(secondSplit.length > 1){
					weapon = secondSplit[1];
					System.out.println(weapon);
				}
				components[i][b] = ShipComponentRepo.mthis.createComponent(secondSplit[0],i,b,this,weapon);
			}
		}
		
	}

	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public float getProjectedXPos() {
		return projectedXPos;
	}

	public void setProjectedXPos(float projectedXPos) {
		this.projectedXPos = projectedXPos;
	}

	public float getProjectedYPos() {
		return projectedYPos;
	}

	public void setProjectedYPos(float projectedYPos) {
		this.projectedYPos = projectedYPos;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	

}
