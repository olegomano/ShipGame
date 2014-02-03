package shipGameWeapon;

import java.awt.Graphics;

import shipGame.MainApp;
import shipGameCommand.MovementPath;
import shipGameShip.Ship;

public class WeaponProjetile {
	private MovementPath mPath;
	private float speed;
	private float xPos;
	private float yPos;
	private float xStart;
	private float yStart;
	private float xPosEnd;
	private float yPosEnd;
	private Ship sourceOf;
	
	
	public WeaponProjetile(float xStart, float yStart, float xEnd, float yEnd, float speed, Ship source){
		xPos = MainApp.view.fromScreenX(xStart);
		yPos = MainApp.view.fromScreenY(yStart);
		sourceOf = source;
		xStart = xPos;
		yStart = yPos;
		xPosEnd = xEnd;
		yPosEnd = yEnd;
		System.out.println("Projectile Created at " + xPos + "," + yPos);
		System.out.println("Projectile Target at "  + xEnd + "," + yEnd);
		this.speed = speed;
		mPath = new MovementPath(xStart, yStart,( xEnd ),  (yEnd) );
	}
	
	public void move(float dt){
		xPos = mPath.getPathPosition(dt,xPos, yPos, speed)[0];
		yPos = mPath.getPathPosition(dt,xPos, yPos, speed)[1];
	}
	
	public Ship getSource(){
		return sourceOf;
	}
	
	public float getX(){
		return xPos;
	}
	
	public float getY(){
		return yPos;
	}
	
	public void drawSelf(Graphics g){
		//System.out.println("DRAWINGPROJECTILE");
		float xStart = MainApp.view.toScreenX(xPos);
		float yStart = MainApp.view.toScreenY(yPos);
		float xEnd = MainApp.view.toScreenX (xPos +  mPath.getSlope()[0]*20 );
		float yEnd = MainApp.view.toScreenY (yPos + mPath.getSlope()[1]*20 );
		g.drawLine((int) xStart, (int) yStart, (int) xEnd, (int) yEnd);
		//g.drawString( mPath.getSlope()[0] + " " + mPath.getSlope()[1], (int) xStart, (int) yStart);
		g.drawString((int) xPos + " " + (int)yPos, (int) xStart, (int) yStart);
		//g.drawLine( (int) this.xStart, (int) this.yStart, (int) xEnd, (int) yEnd);
	} 
	
	public boolean completed(){
		float distTravel = (mPath.getXStart() - xPos)*(mPath.getXStart() - xPos)+ (mPath.getYStart() - yPos)*(mPath.getYStart() - yPos);
		if(mPath.getLengthSquared() <= distTravel){
			mPath.complete();
			return true;
		}
		return false;
		
	}
	
	
}
