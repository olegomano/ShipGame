package shipGameWeapon;

import java.awt.Graphics;

import shipGame.AppData;
import shipGame.MainApp;
import shipGameCommand.ShootCommand;
import shipGameShip.ShipComponent;

public class Weapon {
	private ShipComponent parentSc;
	private float fireSpeed = .87f;
	private float timeTillNextShot = fireSpeed;
	private float projVelocity = 100;
	private boolean firedThisTurn;
	
	
	public Weapon(ShipComponent sc){
		parentSc = sc;
	}
	public void fireWeapon(float x, float y)
	{
		//x and y recieved in screen coordinates
		if(!firedThisTurn){
			ShootCommand shoot = new ShootCommand(parentSc.getParent(), parentSc, MainApp.view.fromScreenX(x),MainApp.view.fromScreenY(y));
			AppData.addCommand(shoot);
			firedThisTurn = true;
		}
	}
	
	public void clearTurnEnd(){
		firedThisTurn = false;
	}
	
	public float getVelocity(){
		return projVelocity;
	}
	
	public float getTimeTillNextShot(){
		return timeTillNextShot;
	}
	
	public void setTimeTillNext(float t){
		timeTillNextShot = t;
	}
	
	public void drawSelf(Graphics g){
		
	}
	
	public float getFireSpeed(){
		return fireSpeed;
	}
	
	public String weaponT(){
		return "w";
	}
	
	public Weapon createNew(ShipComponent sc){
		return new Weapon(sc);
	}
}
