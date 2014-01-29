package shipGameShip;

import shipGameWeapon.Weapon;

public class ShipComponentRepo {
	public static ShipComponentRepo mthis = new ShipComponentRepo();
	
	private ShipComponent[] componentArray = {new ShipComponentCockpit(), new ShipComponentDefault()};
	
	public ShipComponent createComponent(String s, int x, int y, Ship ship, String w){
		for(int i = 0; i < componentArray.length; i++){
			if (s.compareTo(componentArray[i].component()) ==0){
				return componentArray[i].createSelf(x, y, ship, w);
			}
		}
		return null;
	}
	

}
