package shipGame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

import shipGameUi.UiInterface;


public class MyView extends JComponent {
	private AppData appData;
	
	
	private float zoomFactor = 1;
	private float left = 0;
	private float top = 0;
	private float right = 0;
	private float bottom = 0;
	private float rightP = 0;
	private float bottomP = 0;
	private UiInterface gameUI;
	Timer fixedTimer = new Timer();
	public float timeInPhaseExecute = 0;
	public float timeInPhasePlan = 0;
	float previousTime = 0;
	float currentTime = 0;
	private int previousPhase = 0;

	public MyView(AppData data, UiInterface ui) {
		appData = data;
		gameUI = ui;
		timerInit();
		gameUI.initializeUI();
	}

	public void paint(Graphics g) {
		rightP = right;
		bottomP = bottom;
		right = getSize().width;
		bottom = getSize().height;
		if(rightP != right || bottom != bottomP){
			gameUI.setUiSize(getSize().width, getSize().height);
		}
		g.setColor(getBackground());
		g.drawRect(0, 0, 10000, 1000);
		g.setColor(Color.black);
		g.drawString("GamePhase: " + AppData.GAME_PHASE, 200, 30);
		g.drawString(" Turn Timer:" + timeInPhaseExecute, 200, 50);
		g.drawString(" Plan Timer:" + timeInPhasePlan, 200, 70);
		if(appData.getCoordList().size() > 0){
			Coordinate cord = appData.getCoordList().get(appData.getCoordList().size() - 1);
			g.drawRect(cord.getX(), cord.getY(), (int) ( 10 + zoomFactor ),(int) ( 10 + zoomFactor) );
		}
		for(int i = 0; i < AppData.getProjectileArray().size(); i++){
			//System.out.println(AppData.getProjectileArray().size());
			AppData.getProjectileArray().get(i).drawSelf(g);
		}
		for(int i = 0; i < appData.getShips().length; i++){
			appData.getShips()[i].drawShip(g , left, top, 40);
		}
		appData.drawCommandArray(g);
		gameUI.drawUI(g);
	}
		//System.out.println(zoomFactor);

	
	
	private void timerInit()
	{
		
		fixedTimer.scheduleAtFixedRate(new TimerTask() 
		{
			int i = 0;
			public void run() 
			{
				i++;
				//System.out.println(" " + i);
				onTimerEvent(i);
			}
		}, 100, 33);//scheduleAtFixedRate end 
	}
	
	private void onTimerEvent(int i)
	{
		//This function is called on every Timer tick
		previousTime = currentTime;
		currentTime = System.nanoTime();
		float dt = (currentTime - previousTime)  / 1000000000.0f;
		if(AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.PLAN){
			timeInPhaseExecute = 0;
			timeInPhasePlan = ( System.nanoTime() - AppData.GAME_PHASE_0_START_TIME ) / 1000000000.0f;
			super.repaint();
		}
		if(AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.EXECUTE){
			timeInPhasePlan = 0;
			timeInPhaseExecute = ( System.nanoTime() - AppData.GAME_PHASE_1_START_TIME ) / 1000000000.0f;
			for(int c = 0; c < AppData.getProjectileArray().size(); c++){
				if(AppData.getProjectileArray().get(c).completed()){
					AppData.getProjectileArray().remove(c);
					continue;
				}
				AppData.getProjectileArray().get(c).move(dt);
			}
			for(int b = 0; b < appData.getShips().length; b++){
				appData.getShips()[b].moveShip(dt);
			}
			//CALCULATE COLLISION HERE

			super.repaint();
		}
		if(timeInPhaseExecute > AppData.TURN_TIME){
			//This happens when ExecutionPhase ends
			//Command Array is Cleared and its Time is reset
			//Start time for the PlanPhase is started here
			AppData.GAME_PHASE_0_START_TIME = System.nanoTime();
			AppData.GAME_PHASE = AppData.GAME_PHASES_LIST.PLAN;
			appData.resetOnTurn();
			super.repaint();
		}
		if(timeInPhasePlan > AppData.PLAN_TIME){
			//This happens when PLANTIME runs out
			//The Game_Phase is forcibly set to execute
			AppData.GAME_PHASE_1_START_TIME = System.nanoTime();
			AppData.GAME_PHASE = AppData.GAME_PHASES_LIST.EXECUTE;
			super.repaint();
		}
	}
	
	public void setZoomFactor(float z){
		if(zoomFactor + z <= .2){
			return;
		}
		zoomFactor += z;
	}
	
	//The x coordinate of the center of the screen
	public float getCenterX(){
		return ( (right - left) / 2) + left;
	}
	
	public float getCenterY(){
		return ( (bottom - top) / 2 + top);
	}
	
	public void setDis(float x, float y){
		left += x;
		top += y;
		//right = left + getSize().width;
		//bottom = top + getSize().height;
		
	}
	
	public float toScreenX(float x){
		float returnX = ( x *  zoomFactor );
		
		//System.out.println("x " + returnX);
		//zoomFactor = 0;
		return (returnX) + getXDis();
	}
	
	public float toScreenY(float y){
		float returnY =  ( y  *  zoomFactor )  ;
		//System.out.println("y " + returnY);
		//zoomFactor = 0;
		return (returnY + getYDis()) ;
	}
	
	public float getXDis(){
		return left;
	}
	
	public float getYDis(){
		return top;
	}
	
	public float fromScreenX(float x){
		float returnX =  ((x - getXDis() ) / zoomFactor);
		return returnX;
	}
	
	public float fromScreenY(float y){
		float returnY = ( ( y - getYDis() )/ zoomFactor);
		return returnY;
	}
	
	public float getZoomFactor(){
		return zoomFactor;
	}

}
