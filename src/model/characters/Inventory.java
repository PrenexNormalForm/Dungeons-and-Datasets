package model.characters;
import java.util.HashMap;
import model.items.Item;

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
    private HashMap<String,Item> BAG;
    private int MAX_BAG_SIZE;
    private int WEIGHT_CAPACITY;
    //default values for bag size
    private static final int DEFAULT_BAG_SIZE = 20;
    private static final int WEIGHT_MULTIPLIER = 15;

    //default constructor creates an inventory
    public Inventory(int _strength){
        this.BAG = new HashMap<>();
        this.MAX_BAG_SIZE = DEFAULT_BAG_SIZE;
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //constructor for creating an inventory of passed in size
    public Inventory(int _bagSize, int _strength){
        this.BAG = new HashMap<>();
        this.MAX_BAG_SIZE = _bagSize;
        this.WEIGHT_CAPACITY = _strength * WEIGHT_MULTIPLIER;
    }

    //method used for adding items to inventory
    protected void addItem(Item _item){
        System.out.println("Adding item");
        this.BAG.put(_item.getName(), _item);
    }

    //method used for removing item from inventory
    protected void removeItem(Item _item) {
        this.BAG.remove(_item);
    }

    @Override
    public String toString(){
        String toReturn = "";
        if(this.BAG.size() == 0){
            toReturn += "\nInventory:\n" + "Bag is empty";

        } else {
            toReturn += "\nInventory:\n" +"Bag Contents:\n";
            for(String key : this.BAG.keySet()){
                toReturn += this.BAG.get(key).toString();
            }
        }
        toReturn += "\nSize of bag: " +this.MAX_BAG_SIZE + "\nMaximum Weight: " + this.WEIGHT_CAPACITY;
        return toReturn;
    }

    // =================== GETTERS ===============================//
    protected HashMap getBag(){
        return this.BAG;
    }
}
