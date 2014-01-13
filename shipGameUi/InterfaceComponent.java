package shipGameUi;

import java.awt.Font;
import java.awt.Graphics;

import shipGameUi.UiInterface.BUTTON_CLICKED;

public abstract class InterfaceComponent {
	protected float xPos;
	protected float yPos;
	protected float endOfCx;
	protected float endOfCy;
	protected float UiStartX;
	protected float UiStartY;
	protected int placeInUi;

	public InterfaceComponent(int i) {
		placeInUi = i;
	}

	public void resizeComponent(float xPos, float yPos, float UiL, float UiT,
			float UiR, float UiB) {
		// xPos displacement of this from start of Ui bar
		this.xPos = xPos;
		this.yPos = yPos;
		//System.out.println("UI ELEMENT STARTS AT: " + this.xPos + " " + this.yPos);
		float widthOfUi = UiR - UiL;
		float hieghtOfUi = UiB - UiT;
		float widthOfComponent = widthOfUi * percentX();
		float heightOfComponent = hieghtOfUi * percentY();
		endOfCx = this.xPos + widthOfComponent;
		endOfCy = this.yPos + heightOfComponent;
	}
	
	public BUTTON_CLICKED isClicked(float x, float y) {
		if (x > xPos && x < endOfCx) {
			if (y > yPos && y < endOfCy) {
				return returnEnum();
			}
		}
		return BUTTON_CLICKED.NONE_CLICKED;
	}

	public float[] returnEnd() {
		float[] returnF = { endOfCx, endOfCy };
		return returnF;
	}
	
	public static void centerText(String printOut, Graphics g, float l , float t, float r, float b){		
		float textY = ( (b - t)/2.0f ) + t + (g.getFont().getSize2D()/2);
		float textX = ( (r - l )/2.0f ) + l - (g.getFontMetrics(g.getFont()).stringWidth(printOut)/2.0f);
		g.drawString(printOut, (int) textX, (int) textY);
	}

	public abstract float percentX();
	public abstract BUTTON_CLICKED returnEnum();
	public abstract float percentY();

	

	public abstract void drawSelf(Graphics g);
}
