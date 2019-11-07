package model.items;

/*
Last Updated: November 7, 2019

The Potion class that will be used to store information about Potions throughout
a campaign.

Contributors:
Jonathan Bacon
*/
public class Potion extends Item{
    protected String NAME;
    protected int COST;
    protected int WEIGHT;
    protected String PROPERTY;
    protected int VALUE;

    public Potion(String _name, int _cost, int _weight, String _property, int _value){
        this.NAME = _name;
        this.COST = _cost;
        this.WEIGHT = _weight;
        this.PROPERTY = _property;
        this.VALUE = _value;
    }
     // =================== GETTERS ===============================//
    @Override
    public String getName(){
        return this.NAME;
    }

    @Override
    public int getCost(){
        return this.COST;
    }

    @Override
    public int getWeight(){
        return this.WEIGHT;
    }

    public String getProperty(){
        return this.PROPERTY;
    }

    public int getValue(){
        return this.VALUE;
    }

     // =================== SETTERS ===============================//
    @Override
    public void setName(String _name){
        this.NAME = _name;
    }

    @Override
    public void setCost(int _cost){
        this.COST = _cost;
    }

    @Override
    public void setWeight(int _weight){
        this.WEIGHT = _weight;
    }

    public void setProperty(String _property){
        this.PROPERTY = _property;
    }

    public void setValue(int _value){
        this.VALUE = _value;
    }

}
