package model.items;
/*
Last updated: November 7, 2019

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
    //values here must be public for gson to save items
    protected String NAME;
    protected int ITEM_COST;
    protected int ITEM_WEIGHT;
    protected String WEAPON_TYPE;
    protected String DAMAGE_ATTACK_ROLL;
    protected String DAMAGE_TYPE;
    protected String WEAPON_PROPERTIES;

    public Weapon(String _name, int _itemCost, int _itemWeight, String _weaponType, String _damageAttackRoll, String _damageType, String _weaponProperties) {
        this.NAME = _name;
        this.ITEM_COST = _itemCost;
        this.ITEM_WEIGHT = _itemWeight;
        this.WEAPON_TYPE = _weaponType;
        this.DAMAGE_ATTACK_ROLL = _damageAttackRoll;
        this.DAMAGE_TYPE = _damageType;
        this.WEAPON_PROPERTIES = _weaponProperties;
    }

     // =================== GETTERS ===============================//
    @Override
    public String getName(){
        return this.NAME;
    }

    @Override
    public int getCost(){
        return this.ITEM_COST;
    }

    @Override
    public int getWeight(){
        return this.ITEM_WEIGHT;
    }

    public String getWeaponType(){
        return this.WEAPON_TYPE;
    }

    public String getDamageAttackRoll(){
        return this.DAMAGE_ATTACK_ROLL;
    }

    public String getDamageType(){
        return this.WEAPON_TYPE;
    }

    public String getWeaponProperties(){
        return this.WEAPON_PROPERTIES;
    }

     // =================== SETTERS ===============================//
    @Override
    public void setName(String _name){
        this.NAME = _name;
    }

    @Override
    public void setCost(int _cost){
        this.ITEM_COST = _cost;
    }

    @Override
    public void setWeight(int _weight){
        this.ITEM_WEIGHT = _weight;
    }
    public void setWeaponType(String _type){
        this.WEAPON_TYPE = _type;
    }

    public void setDamageAttackRoll(String _roll){
        this.DAMAGE_ATTACK_ROLL = _roll;
    }

    public void setDamageType(String _type){
        this.WEAPON_TYPE = _type;
    }

    public void setWeaponProperties(String _proprety){
        this.WEAPON_PROPERTIES = _proprety;
    }

}

