package DandD.items;

import DandD.items.Item;

public class Item {

   private static int itemCost;
   private static int itemWeight;

    public Item(int _itemCost, int _itemWeight) {
        this.itemCost = _itemCost;
        this.itemWeight = _itemWeight;
    }
}
