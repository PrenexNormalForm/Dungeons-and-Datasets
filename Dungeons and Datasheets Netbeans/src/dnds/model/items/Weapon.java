package dnds.model.items;
/*
Last updated: October 1, 2019

The Weapons class that extends Item. Weapons are pretty complex and need
more data allocated to them throughout a campaign. Weapons can
also be upgraded to add additional stats or damage rolls against certain
types of monsters. All this will be accounted for at a later date.
The arrays of Strings are just a place holder for the Enum class of weaponType,
damageAttackRoll, damageType and weaponProperties that will be built later.

Contributors:
Brandon Pozil
*/

public class Weapon extends Item {

    private String weaponType;
    private String damageAttackRoll;
    private String damageType;
    private String weaponProperties;

    public Weapon(int _itemCost, int _itemWeight, String _weaponType, String _damageAttackRoll, String _damageType, String _weaponProperties) {
        super(_itemCost, _itemWeight);
        this.weaponType = _weaponType;
        this.damageAttackRoll = _damageAttackRoll;
        this.damageType = _damageType;
        this.weaponProperties = _weaponProperties;
    }
}
