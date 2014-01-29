package shipGameShip;

public class ShipComponentDefault extends ShipComponent{
	
	public ShipComponentDefault(int x, int y, Ship s, String w){
		super(x,y,s,w);
	}
	
	public ShipComponentDefault() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String component() {
		// TODO Auto-generated method stub
		return "d";
	}

	@Override
	public void action(float x, float y) {
		// TODO Auto-generated method stub
		if(weapon != null){
			weapon.fireWeapon(x,y);
		}
	}

	@Override
	public String componentName() {
		// TODO Auto-generated method stub
		return "DefaultComponent";
	}

	@Override
	public ShipComponent createSelf(int x, int y, Ship s, String w) {
		// TODO Auto-generated method stub
		return new ShipComponentDefault(x,y,s,w);
	}

}
