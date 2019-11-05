package model.items;
/*
Last Updated: September 28, 2019

The Item class that will be used as a parent class for all other Item objects,
such as Weapons and Armor. All items have a cost and weight and are declared
here in the class.

Contributors:
Brandon Pozil
*/
public class Item {
   private String NAME;
   private int ITEM_COST;
   private int ITEM_WEIGHT;

    public Item(String _name, int _itemCost, int _itemWeight) {
        this.NAME = _name;
        this.ITEM_COST = _itemCost;
        this.ITEM_WEIGHT = _itemWeight;
    }

     // =================== GETTERS ===============================//
    public String getName(){
        return this.NAME;
    }

    public int getCost(){
        return this.ITEM_COST;
    }

    public int getWeight(){
        return this.ITEM_WEIGHT;
    }

     // =================== SETTERS ===============================//
    public void setName(String _name){
        this.NAME = _name;
    }

    public void setCost(int _cost){
        this.ITEM_COST = _cost;
    }

    public void setWeight(int _weight){
        this.ITEM_WEIGHT = _weight;
    }
}
