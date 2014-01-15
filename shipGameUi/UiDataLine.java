package shipGameUi;

import java.awt.Font;
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
		Font f = new Font("TimesNewRoman", Font.PLAIN,(int) ( (b - t)*.76f ));
		g.setFont(f);
		//TODO: dynamically resize font based on size of bunding box
		//g.drawString(name, (int) l, (int) t);
		//InterfaceComponent.centerText(name + ": " + data, g, l, t, r, b);
		float yOffset = ( (b - t) - g.getFont().getSize2D() )  / 2.0f;  
		g.drawString("   " + name + ": " + data,(int)l,(int) (b - yOffset) );
		
	}
	
	public float getEndY(){
		return b;
	}

}
