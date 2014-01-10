package shipGameUi;
import java.awt.Graphics;

import shipGameShip.Ship;


public interface UiInterface {
	public static enum BUTTON_CLICKED{
		END_TURN,
		NONE_CLICKED;
	}
	
	public BUTTON_CLICKED buttonClicked(float xClick, float yClick);
	public void initializeUI();
	public void setUiSize(float bottomX, float bottomY);
	public void drawUI(Graphics g);
	public void setData(Ship s); //TODO: Create type Data and pass it instead of ship
}
