package shipGame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import shipGameUi.UiInterface;
import shipGameUi.UiInterface.BUTTON_CLICKED;

public class MouseInputCollector implements MouseListener, MouseWheelListener {
	private AppData appData;
	private MyView v;
	private float zoomSpeed = .134f;
	private int xPressed;
	private int yPressed;
	private UiInterface gameUI;

	public MouseInputCollector(AppData data, MyView view, UiInterface ui) {
		appData = data;
		v = view;
		gameUI = ui;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		clickLogic(arg0.getX(), arg0.getY(), arg0.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		xPressed = arg0.getX();
		yPressed = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		v.setDis(arg0.getX() - xPressed, arg0.getY() - yPressed);
		v.repaint();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("MOVING MOUSE WHEEL");
		//System.out.println(arg0.getWheelRotation());
		if (arg0.getWheelRotation() > 0) {
			v.setZoomFactor(-zoomSpeed);
		} else {
			v.setZoomFactor(zoomSpeed);
		}

		v.repaint();
	}

	private void clickLogic(float xClick, float yClick, int button) {
		if (AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.PLAN) {
			gamePhasePlanLogic(xClick, yClick, button);
		} else if (AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.EXECUTE) {
			gamePhaseExecuteLogic(xClick, yClick, button);
		}
	}

	private void gamePhaseExecuteLogic(float xClick, float yClick, int button) {
		clearSelect();
		selectShip(xClick,yClick);
	}

	private void gamePhasePlanLogic(float xClick, float yClick, int button) {
		monitorClick(xClick, yClick); //Draws square on click location
		//TODO make UI checker its own function wich reurns a boolean
		switch(gameUI.buttonClicked(xClick, yClick)){
		case END_TURN: endTurnButton();
			return;
		default:
			break; 
		}
		if(button == 3){
			//button 3 is rightClick
			clearSelect();
			return;
		}
		if(callShipAction(xClick, yClick)){
			return;
		}
		selectShip(xClick,yClick);
		v.repaint();
	}
	
	private void endTurnButton(){
		//Sets start Time for Phase 1
		AppData.GAME_PHASE = AppData.GAME_PHASES_LIST.EXECUTE;
		AppData.GAME_PHASE_1_START_TIME = System.nanoTime();
	}
	
	private boolean callShipAction(float xClick, float yClick){
		for (int i = 0; i < appData.getShips().length; i++) {
			// Checks if a ship component is selected
			// If a ship component is selected it then calls its action using
			// the click for its action
			if (appData.getShips()[i].getSelected()) {
				appData.getShips()[i].action(xClick, yClick);
				return true;
			}
		}
		return false;
	}
	
	private void clearSelect(){
		for (int i = 0; i < appData.getShips().length; i++) {
			// Deselects all of the ShipComponents
			appData.getShips()[i].clearSelect();
			gameUI.setData(null);
		}

	}
	
	private void monitorClick(float xClick, float yClick){
		Coordinate click = new Coordinate((int) xClick, (int) yClick);
		appData.addCoord(click);
	}
	
	private void selectShip(float xClick, float yClick){
		for (int i = 0; i < appData.getShips().length; i++) {
			// Selects a Ship Component
			if (appData.getShips()[i].isSelected(xClick, yClick)) {
				gameUI.setData(appData.getShips()[i]);
				return;
			}
		}
	}

}
