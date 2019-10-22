package characters;
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
    private Object[][] BAG;
    private int WEIGHT_CAPACITY;
    //default values for bag size
    private static final int BAG_COLUMNS = 5;
    private static final int DEFAULT_ROWS = 4;
    private static final int WEIGHT_MULTIPLIER = 15;

    //default constructor creates an inventory
    public Inventory(int _strength){
        this.BAG = new Object[BAG_COLUMNS][DEFAULT_ROWS];
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //constructor for creating an inventory of passed in size
    public Inventory(int _bagSize, int _strength){
        //divides the desired size by 5 to create multiple rows of 5
        int rows = _bagSize/BAG_COLUMNS;
        this.BAG = new Object[BAG_COLUMNS][rows];
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //method used for adjusting bag size
    private void adjustBag(int _bagSize){
        int rows = _bagSize/BAG_COLUMNS;
        this.BAG = new Object[BAG_COLUMNS][rows];
    }

    @Override
    public String toString(){
        return "\nInventory:\n" + Arrays.toString(this.BAG);
    }

    // =================== GETTERS ===============================//
    protected Object[][] getBag(){
        return this.BAG;
    }

    // =================== SETTERS ===============================//
    protected void setBag(int _bagSize){
       adjustBag(_bagSize);
    }

}
