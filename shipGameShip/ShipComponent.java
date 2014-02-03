package shipGameShip;
import java.awt.Graphics;

import shipGame.AppData;
import shipGame.MainApp;
import shipGameCommand.MoveCommand;
import shipGameWeapon.Weapon;
import shipGameWeapon.WeaponRepo;
public abstract class ShipComponent {
	private int xPos;
	private int yPos;
	protected Weapon weapon;
	//private float pixelWidth;
	protected Ship parentShip;
	protected boolean selected;
	protected float l;
	protected float t;
	protected float r;
	protected float b;
	protected boolean dead;
	
	public ShipComponent(){
		//ONLY USE THIS FOR INITIATING IN COMMANDREPO
	}
	
	public float getX(){
		return l;
	}
	
	public void destroyComponent(){
		dead = true;
	}
	
	
	public float getY(){
		return t;
	}
	public float[] getDem(){
		float[] retF = {l,t,r,b};
		return retF;
	}
	
	public Weapon getWep(){
		return weapon;
	}
	
	public Ship getParent(){
		return parentShip;
	}
	
	public ShipComponent(int x, int y, Ship s, String w){
		xPos = x;
		yPos = y;
		parentShip = s;
		weapon = WeaponRepo.mThis.getWeapon(w, this);
	}
	
	public void setSelected(boolean b){
		selected = b;
		parentShip.selected = b;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	
	public void drawSelf(Graphics g, float xDis, float yDis, float width){
		if(dead){
			return;
		}
		float x = MainApp.view.getXDis();
		float y = MainApp.view.getYDis();
		l =  MainApp.view.toScreenX(parentShip.getxPos() + xPos*width);
		t =  MainApp.view.toScreenY(parentShip.getyPos() + yPos*width);
		r =  MainApp.view.toScreenX(parentShip.getxPos() + xPos*width + width);
		b =  MainApp.view.toScreenY(parentShip.getyPos() + yPos*width + width);
		//g.drawRect(xPos, yPos, 30, 30);
		g.drawRect((int) l, (int) t, (int) ( r - l ), (int) (b - t)); 
		g.drawString(yPos + " " + xPos, (int) l , (int) b);
		if(selected){
			g.drawOval((int) l , (int) t, (int)(r - l) ,(int)( b - t));
			//System.out.println(yPos + " " + xPos);
		}
		String weaponS = "";
		if(weapon != null){
			weaponS += weapon.weaponT();
		}
		g.drawString(component() + " " + weaponS, (int) ( l +  (r - l) / 2),(int) ( t + (b - t) / 2));
		
	}
	
	public boolean isInside(float x, float y){
		if(dead){
			return false;
		}
		System.out.println("Is " + x + " " + y + " Inside " + l + " " + r);
		if(x > l && x < r){
			if(y > t && y < b){
				return true;
			}
		}
		return false;
	}
	public abstract String component();
	public abstract void action(float x, float y); // Recieved in Screen coordinates
	public abstract String componentName();
	public abstract ShipComponent createSelf(int x, int y, Ship s, String w);
}
