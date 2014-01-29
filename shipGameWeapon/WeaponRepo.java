package shipGameWeapon;

import shipGameShip.ShipComponent;

public class WeaponRepo {
	public static WeaponRepo mThis = new WeaponRepo();

	private Weapon[] weaponArray = { new Weapon(null) };

	public Weapon getWeapon(String s, ShipComponent sc) {
		if (s == null) {
			return null;
		}
		for (int i = 0; i < weaponArray.length; i++) {
			if (weaponArray[i].weaponT().compareTo(s) == 0) {
				System.out.println("CREATING WEAPON");
				return weaponArray[i].createNew(sc);
			}
		}
		return null;
	}
}
