package shipGameShip;

import shipGame.AppData;
import shipGame.MainApp;
import shipGameCommand.MoveCommand;

public class ShipComponentCockpit extends ShipComponent{

	public ShipComponentCockpit(int x, int y, Ship s) {
		super(x, y, s);
		// TODO Auto-generated constructor stub
	}

	public ShipComponentCockpit() {
		//DO NOT USE FOR ANYTHING OTHER THAN INITIALIZING IN COMPONENTREPO
	}

	@Override
	public void action(float x, float y) {
		// TODO Auto-generated method stub
		System.out.println("Moving from " + l + " " + t + " to " + x + " " + y);
		MoveCommand c = new MoveCommand(parentShip,MainApp.view.fromScreenX(x), MainApp.view.fromScreenY(y));
		AppData.addCommand(c);
		//TODO: CREATE COMMAND OBJECT AND CREATE MOVE COMMAND FROM THIS LOCATION
		
	}

	@Override
	public String component() {
		// TODO Auto-generated method stub
		return "s";
	}

	@Override
	public ShipComponent createSelf(int x, int y, Ship s) {
		System.out.println("Creating new Ship at " + x + " " + y);
		return new ShipComponentCockpit(x,y,s);
	}

	@Override
	public String componentName() {
		// TODO Auto-generated method stub
		return "Cockpit";
	}

}
