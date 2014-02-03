package shipGameCommand;
import java.awt.Graphics;

import shipGameShip.Ship;
import shipGameShip.ShipComponent;


public abstract class Command {
	protected Ship targetShip;
	protected ShipComponent targetComponent;
	protected float targetX;
	protected float targetY;
	protected boolean completed;
	
	protected abstract boolean asynchCommand();
	//TODO; add boolean consequtive
	
	public Command(Ship s, ShipComponent sc, float x, float y){
		//x and y recieved in screen coordinates
		targetShip = s;
		targetX = x;
		targetY = y;
		targetComponent = sc;
		queAction();
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public boolean isAsynchCommand(){
		return asynchCommand();
	}
	
	
	public Ship getTargetShip(){
		return targetShip;
	}
	
	public abstract boolean executeAction(float timeInSeconds);
	public abstract void queAction();
	public abstract void drawCommand(Graphics g);

}
