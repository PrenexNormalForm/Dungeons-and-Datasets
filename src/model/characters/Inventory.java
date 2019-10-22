package model.characters;
import java.util.Arrays;

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
    private Object[] BAG;
    private int BAG_SIZE;
    private int WEIGHT_CAPACITY;
    //default values for bag size
    private static final int DEFAULT_BAG_SIZE = 20;
    private static final int WEIGHT_MULTIPLIER = 15;

    //default constructor creates an inventory
    public Inventory(int _strength){
        this.BAG = new Object[DEFAULT_BAG_SIZE];
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //constructor for creating an inventory of passed in size
    public Inventory(int _bagSize, int _strength){
        this.BAG = new Object[_bagSize];
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //method used for adjusting bag size
    private void adjustBag(int _bagSize){
        this.BAG = new Object[_bagSize];
    }

    //method used for adding items to inventory
    private void addItem(){
        if(this.BAG.length < BAG_SIZE) {
            //increases the bag size by 1
            Object[] expandedBag = new Object[this.BAG.length+1];
            //copys current bag to expandedBag
            System.arraycopy(this.BAG, 0, expandedBag, 0, this.BAG.length);
            this.BAG = expandedBag;
        }
    }

    @Override
    public String toString(){
        return "\nInventory:\n" + Arrays.toString(this.BAG);
    }

    // =================== GETTERS ===============================//
    protected Object[] getBag(){
        return this.BAG;
    }

    // =================== SETTERS ===============================//
    protected void setBag(int _bagSize){
       adjustBag(_bagSize);
    }

}
