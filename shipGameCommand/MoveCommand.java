package shipGameCommand;
import java.awt.Graphics;

import shipGameShip.Ship;
import shipGameShip.ShipComponent;


public class MoveCommand extends Command{
	
	private float myCurrentPojectedX;
	private float myCurrentProjectedY;
	private MovementPath move;
	public MoveCommand(Ship s, ShipComponent sc, float x, float y) {
		super(s, sc, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean executeAction(float t) {
		float xWent = targetShip.getxPos() - move.getXStart();
		float yWent = targetShip.getyPos() - move.getYStart();
		float lengthMoved = xWent*xWent + yWent*yWent;
		if(lengthMoved >= move.getLengthSquared()){
			completed = true;
			targetShip.displaceShip(targetX, targetY);
			move.complete();
			return true;
		}
		float dx = targetShip.getxPos() - move.getPathPosition(t, targetShip.getxPos(), targetShip.getyPos(), targetShip.getMoveSpeed())[0];
		float dy = targetShip.getyPos() - move.getPathPosition(t, targetShip.getxPos(), targetShip.getyPos(), targetShip.getMoveSpeed())[1];
		//System.out.println("dx = " +  dx);
		//System.out.println("dy = " + dy);
		targetShip.displaceShip(move.getPathPosition(t, targetShip.getxPos(), targetShip.getyPos(), targetShip.getMoveSpeed())[0],move.getPathPosition(t, targetShip.getxPos(), targetShip.getyPos(), targetShip.getMoveSpeed())[1]);
		return false;
	}
	
	public MovementPath getMovementPath(){
		return move;
	}

	public void drawCommand(Graphics g) {
		//g.drawLine((int )MainApp.view.toScreenX(myCurrentPojectedX), (int) MainApp.view.toScreenY(myCurrentProjectedY), (int) MainApp.view.toScreenX(targetX),(int) MainApp.view.toScreenY(targetY));
		// TODO Auto-generated method stub
		move.drawSelf(g);
		
	}

	@Override
	public void queAction() {
		// TODO Auto-generated method stub
		//TODO: Create DataType vector and use it instead
		myCurrentPojectedX = targetShip.getProjectedXPos();
		myCurrentProjectedY = targetShip.getProjectedYPos();
		targetShip.setProjectedXPos(targetX);
		targetShip.setProjectedYPos(targetY);
		move = new MovementPath(myCurrentPojectedX, myCurrentProjectedY, targetX, targetY);
				
	}

	@Override
	protected boolean asynchCommand() {
		return false;
	}
}
