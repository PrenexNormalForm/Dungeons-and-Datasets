package model.items;
/*
Last Updated: November 7, 2019

The GenericItem class that will be used to store information about any Generic Item throughout
a campaign.

Contributors:
Jonathan Bacon
*/
public class GenericItem extends Item{
    //items here must be public for gson to save them
   protected String NAME;
   protected int COST;
   protected int WEIGHT;

    public GenericItem(String _name, int _cost, int _weight) {
        this.NAME = _name;
        this.COST = _cost;
        this.WEIGHT = _weight;
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
}
