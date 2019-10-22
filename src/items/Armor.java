package items;
/*
Last Updated: September 26, 2019

The Armor class that will be used to store information about Armor throughout
a campaign.

Contributors:
Brandon Pozil
*/

public class Armor extends Item {

    private int armorClass;
    public static final int HIGHEST_AC = 29;

    public Armor(int _itemCost, int _itemWeight, int _armorClass) {
        super(_itemCost, _itemWeight);
        this.armorClass = _armorClass;
    }

}