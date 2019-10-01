package dnds.items;
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

    private String[] weaponType = {"Simple Melee Weapon", "Simple Ranged Weapon", "Martial Melee Weapon", "Martial Ranged Weapon"};
    private String[] damageAttackRoll = {"1d4", "2d4", "1d6", "2d6", "1d8", "1d10", "1d12"};
    private String[] damageType = {"Bludgeoning", "Piercing", "Slashing"};
    private String[] weaponProperties = {"Light", "Heavy", "Finesse", "Versatile", "Two-Handed", "One-Handed"};

    public Weapon(int _itemCost, int _itemWeight, String[] _weaponType, String[] _damageAttackRoll, String[] _damageType, String[] _weaponProperties) {
        super(_itemCost, _itemWeight);
        this.weaponType = _weaponType;
        this.damageAttackRoll = _damageAttackRoll;
        this.damageType = _damageType;
        this.weaponProperties = _weaponProperties;
    }
}
