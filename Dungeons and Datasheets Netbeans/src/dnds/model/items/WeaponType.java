package dnds.model.items;
/*
Last Updated: October 1, 2019
Represents the different types of weapons.

Contributors:
Brandon Pozil
*/

/**
 * An Enum to represent Weapon Types.
 * @author Brandon Pozil
 */

public enum WeaponType {
    SIMPLE_MELEE("Simple Melee Weapon"),
    SIMPLE_RANGED("Simple Ranged Weapon"),
    MARTIAL_MELEE("Martial Melee Weapon"),
    MARTIAL_RANGED("Martial Ranged Weapon");
    private String weaponType;

/**
 * Returns the type of weapon.
 * @return The type of weapon.
 */
    @Override
    public String toString() {
        return weaponType;
    }

    private WeaponType(String _weaponType) {
        this.weaponType = _weaponType;
    }

}


