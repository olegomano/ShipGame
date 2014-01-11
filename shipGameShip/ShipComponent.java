package shipGameShip;
import java.awt.Graphics;

import shipGame.AppData;
import shipGame.MainApp;
import shipGameCommand.MoveCommand;
public class ShipComponent {
	private int xPos;
	private int yPos;
	//private float pixelWidth;
	private Ship parentShip;
	private boolean selected;
	private float l;
	private float t;
	private float r;
	private float b;
	
	public ShipComponent(int x, int y, Ship s){
		xPos = x;
		yPos = y;
		parentShip = s;
	}
	
	public void setSelected(boolean b){
		selected = b;
		parentShip.selected = b;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void drawSelf(Graphics g, float xDis, float yDis, float width){
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
		
	}
	
	public boolean isInside(float x, float y){
		if(x > l && x < r){
			if(y > t && y < b){
				return true;
			}
		}
		return false;
	}
	
	public void action(float x, float y){
		System.out.println("Moving from " + l + " " + t + " to " + x + " " + y);
		MoveCommand c = new MoveCommand(parentShip,MainApp.view.fromScreenX(x), MainApp.view.fromScreenY(y));
		AppData.addCommand(c);
		//TODO: CREATE COMMAND OBJECT AND CREATE MOVE COMMAND FROM THIS LOCATION
	}
}
