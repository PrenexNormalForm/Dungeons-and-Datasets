/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.items;

/**
 *
 * @author jodba5
 */
public class GenericItem extends Item{
    //items here must be public for gson to save them
   public String NAME;
   public int ITEM_COST;
   public int ITEM_WEIGHT;

    public GenericItem(String _name, int _itemCost, int _itemWeight) {
        this.NAME = _name;
        this.ITEM_COST = _itemCost;
        this.ITEM_WEIGHT = _itemWeight;
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
}
