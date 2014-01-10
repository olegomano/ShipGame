package shipGameCommand;
import java.awt.Graphics;

import shipGameShip.Ship;


public abstract class Command {
	protected Ship targetShip;
	protected float targetX;
	protected float targetY;
	protected boolean completed;
	
	public Command(Ship s, float x, float y){
		targetShip = s;
		targetX = x;
		targetY = y;
		queAction();
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public Ship getTargetShip(){
		return targetShip;
	}
	
	public abstract boolean executeAction(float timeInSeconds);
	public abstract void queAction();
	public abstract void drawCommand(Graphics g);

}
