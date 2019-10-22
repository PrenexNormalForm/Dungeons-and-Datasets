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

   private static int itemCost;
   private static int itemWeight;

    public Item(int _itemCost, int _itemWeight) {
        this.itemCost = _itemCost;
        this.itemWeight = _itemWeight;
    }
}
