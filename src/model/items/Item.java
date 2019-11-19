package model.items;

/*
Last Updated: September 28, 2019

The Item class that will be used as a parent class for all other Item objects,
such as Weapons and Armor. All items have a name, cost and weight so they are
established here in this false abstract class. Since we are using Gson to save
characters this cannot be truly made abstract or it will break the builder.

Contributors:
Brandon Pozil
Jonathan Bacon
 */
public class Item {
    //these items must be static for the gson writer to work with a master class
    private static String NAME;
    private static int COST;
    private static int WEIGHT;
    //empty constructor to make the child classes happy
    public Item(){

    }

    // =================== GETTERS ===============================//
    public String getName() {
        return this.NAME;
    }

    public int getCost() {
        return this.COST;
    }

    public int getWeight() {
        return this.WEIGHT;
    }

    // =================== SETTERS ===============================//
    public void setName(String _name) {
        this.NAME = _name;
    }

    public void setCost(int _cost) {
        this.COST = _cost;
    }

    public void setWeight(int _weight) {
        this.WEIGHT = _weight;
    }
}
