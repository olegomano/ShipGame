package shipGameCommand;

import java.awt.Graphics;

import shipGame.AppData;
import shipGame.MainApp;
import shipGameShip.Ship;
import shipGameShip.ShipComponent;
import shipGameWeapon.WeaponProjetile;

public class ShootCommand extends Command{
	private WeaponProjetile wepProj;
	private float timeOfLastShot = 0;
	private WeaponProjetile[] projArray;
	public ShootCommand(Ship s, ShipComponent sc, float x, float y) {
		// x and y recieved in screenCoordinates
		super(s, sc, x, y);
		//timeTillNextShot = sc.getWep().getFireSpeed();
		System.out.println("Created Shoot Command");
		// TODO Auto-generated constructor stub
	}
	//TODO: CREATE SEPERATE TIMER FOR EACH COMMAND
	
	private float count = 0;
	public boolean executeAction(float dt) {
		targetComponent.getWep().setTimeTillNext(targetComponent.getWep().getTimeTillNextShot() - dt);
		if(targetComponent.getWep().getTimeTillNextShot() < 0){
			timeOfLastShot = MainApp.view.timeInPhaseExecute;
			targetComponent.getWep().setTimeTillNext(targetComponent.getWep().getFireSpeed());
			//float x = targetComponent.getDem()[0] + ( targetComponent.getDem()[2] - targetComponent.getDem()[0]) / 2;
			//float y = targetComponent.getDem()[1] + ( targetComponent.getDem()[3] - targetComponent.getDem()[1]) / 2;
			float x = targetComponent.getX();
			float y = targetComponent.getY(); 
			wepProj = new WeaponProjetile(x,y,targetX,targetY, targetComponent.getWep().getVelocity() );
			AppData.addProjectile(wepProj);
			System.out.println("Active Projectiles: " + AppData.getProjectileArray().size());
		}
		//System.out.println(count);
		return false;
	}
	@Override
	public void queAction() {
		// TODO Auto-generated method stub
		/* 
		int numProj = (int) (AppData.TURN_TIME / targetComponent.getWep().getFireSpeed());
	    projArray = new WeaponProjetile[numProj];
	    for(int i = 0; i < numProj; i++){			 
		
	    }
	    */
	}

	@Override
	public void drawCommand(Graphics g) {
		// TODO Auto-generated method stub
		if(AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.PLAN){
			float x = targetComponent.getDem()[0] + ( targetComponent.getDem()[2] - targetComponent.getDem()[0]) / 2;
			float y = targetComponent.getDem()[1] + ( targetComponent.getDem()[3] - targetComponent.getDem()[1]) / 2;
			g.drawLine( (int) (x), (int) (y),(int) MainApp.view.toScreenX(targetX), (int)MainApp.view.toScreenY(targetY));
			
			
		}
		else if(AppData.GAME_PHASE == AppData.GAME_PHASES_LIST.EXECUTE){
			
		}
	}

}
