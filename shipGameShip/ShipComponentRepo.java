package shipGameShip;

public class ShipComponentRepo {
	public static ShipComponentRepo mthis = new ShipComponentRepo();
	
	private ShipComponent[] componentArray = {new ShipComponentCockpit()};
	
	public ShipComponent createComponent(String s, int x, int y, Ship ship){
		for(int i = 0; i < componentArray.length; i++){
			if (s.compareTo(componentArray[i].component()) ==0){
				return componentArray[i].createSelf(x, y, ship);
			}
		}
		return null;
	}
	

}
