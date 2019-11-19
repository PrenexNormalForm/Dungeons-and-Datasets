package model.items;
/*
Last Updated: September 26, 2019

The Armor class that will be used to store information about Armor throughout
a campaign.

Contributors:
Brandon Pozil
Jonathan Bacon
*/

public class Armor extends Item {
    //items here must be public for gson to save them
    protected String NAME;
    protected int COST;
    protected int WEIGHT;
    protected int ARMOR_CLASS;
    private static final int HIGHEST_AC = 29;

    public Armor(String _name, int _cost, int _weight, int _armorClass) {
        this.NAME = _name;
        this.COST = _cost;
        this.WEIGHT = _weight;
        this.ARMOR_CLASS = _armorClass;
    }

     // =================== GETTERS ===============================//

    @Override
    public String getName(){
        return this.NAME;
    }

    @Override
    public int getCost(){
        return this.COST;
    }

    public int getArmorClass() {
        return ARMOR_CLASS;
    }

    @Override
    public int getWeight(){
        return this.WEIGHT;
    }

     // =================== SETTERS ===============================//
    @Override
    public void setName(String _name){
        this.NAME = _name;
    }

    @Override
    public void setCost(int _cost){
        this.COST = _cost;
    }

    @Override
    public void setWeight(int _weight){
        this.WEIGHT = _weight;
    }

    public void setArmorClass(int _armorClass) {
        this.ARMOR_CLASS = _armorClass;
    }
}
