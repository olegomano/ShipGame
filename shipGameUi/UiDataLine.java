package shipGameUi;

import java.awt.Graphics;

public class UiDataLine {
	private String name;
	private String data;
	private float l;
	private float t;
	private float r;
	private float b;
	public UiDataLine(String name){
		this.name = name;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public void resize(float l, float t, float r, float b){
		this.l = l;
		this.t = t;
		this.r = r;
		this.b = b;
	}
	
	public void drawSelf(Graphics g){
		g.drawLine((int)l, (int)b,(int)r,(int)b);
		//g.drawString(name, (int) l, (int) t);
		InterfaceComponent.centerText(name + ": " + data, g, l, t, r, b);
		
	}
	
	public float getEndY(){
		return b;
	}

}
