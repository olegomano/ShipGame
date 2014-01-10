package shipGameCommand;
import java.awt.Graphics;

import shipGame.MainApp;
import shipGameShip.Ship;


public class MovementPath {
	private boolean completed = false;
	private float dx;
	private float dy;
	private float xStart;
	private float yStart;
	private float xEnd;
	private float yEnd;
	private float xCurrent;
	private float yCurrent;
	private float lengthSquared; //Kept in Squared format
	//private float previousTime = 0;
	//private float currentTime = 0;
	
	public MovementPath(){
		dx = 0;
		dy = 0;
		xStart = 0;
		yStart = 0;
		xEnd = 0;
		yEnd = 0;
		lengthSquared = 0;
	}
	
	public MovementPath(float xStart, float yStart, float xEnd, float yEnd){
		System.out.println("Path Created");
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		dx = xEnd - xStart;
		dy = yEnd - yStart;
		lengthSquared = dx*dx + dy*dy;
		dx = (float) (dx / Math.sqrt(lengthSquared));
		dy = (float) (dy / Math.sqrt(lengthSquared));
		xCurrent = xStart;
		yCurrent = yStart;
		
	}
	
	public void complete(){
		completed = true;
	}
	
	public float getLengthSquared(){
		return lengthSquared;
	}
	
	public float getXStart(){
		return xStart;
	}
	
	public float getYStart(){
		return yStart;
	}
		
	public float[] getPathPosition(float dt, Ship c){
		//System.out.println(currentTime + " " + previousTime);
		//System.out.println("dt = " + dt);
		float moveThisTurnX = c.getxPos() + dt*dx*c.getMoveSpeed(); 
		float moveThisTurnY = c.getyPos() + dt*dy*c.getMoveSpeed();
		xCurrent = moveThisTurnX;
		yCurrent = moveThisTurnY;
		//float newX = (float) (xStart + ( t*dx ) * c.getMoveSpeed());
		//float newY = (float) (yStart + ( t*dy ) * c.getMoveSpeed()); 
		float[] returnf = {moveThisTurnX, moveThisTurnY};
		return returnf;
	}
	
	public void drawSelf(Graphics g){
		//System.out.println("DRAWING PATH");
		if(!completed){
			g.drawLine((int) MainApp.view.toScreenX(xCurrent) , (int) MainApp.view.toScreenY(yCurrent), (int) MainApp.view.toScreenX(xEnd),(int) MainApp.view.toScreenY(yEnd));
		}
		
	}
	
	
	
	

}
