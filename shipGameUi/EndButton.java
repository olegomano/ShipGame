package shipGameUi;

import java.awt.Graphics;

import shipGameUi.UiInterface.BUTTON_CLICKED;

public class EndButton extends InterfaceComponent {
	public EndButton(int i) {
		super(i);
	}

	public float percentX() {
		return .15f;
	}

	public float percentY() {
		return .8f;
	}

	public void drawSelf(Graphics g) {
		g.drawRect((int) xPos, (int) yPos, (int) (endOfCx - xPos),	(int) (endOfCy - yPos));
		centerText("End Turn", g, xPos, yPos, endOfCx, endOfCy);
	}

	public BUTTON_CLICKED returnEnum() {
		return BUTTON_CLICKED.END_TURN;
	}
}
