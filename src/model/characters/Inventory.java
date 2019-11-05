package model.characters;
import java.util.List;

/*
Last updated Oct 22, 2019

Represents the inventory of the character

Contributors:
Brandon Pozil
Jonathan Bacon
 */
/*
Inventory class is used to create and manage a players inventory
*/
public class Inventory {
    private List<Object> BAG ;
    private int BAG_SIZE;
    private int WEIGHT_CAPACITY;
    //default values for bag size
    private static final int DEFAULT_BAG_SIZE = 20;
    private static final int WEIGHT_MULTIPLIER = 15;

    //default constructor creates an inventory
    public Inventory(int _strength){
        this.BAG_SIZE = DEFAULT_BAG_SIZE;
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //constructor for creating an inventory of passed in size
    public Inventory(int _bagSize, int _strength){
        this.BAG_SIZE = _bagSize;
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //method used for adding items to inventory
    protected void addItem(Object _item){
        if(this.BAG.size() < BAG_SIZE){
        this.BAG.add(_item);
        }
    }

    //method used for removing item from inventory
    protected void removeItem(Object _item) {
        this.BAG.remove(_item);
    }

    @Override
    public String toString(){
        return "\nInventory:\n" + this.BAG.toString();
    }

    // =================== GETTERS ===============================//
    protected List getBag(){
        return this.BAG;
    }
}
