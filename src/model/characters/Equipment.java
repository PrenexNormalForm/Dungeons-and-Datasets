package model.characters;

import model.items.Armor;
import model.items.Weapon;

/*
Last updated Oct 22, 2019

Represents the inventory of the character

Contributors:
Brandon Pozil
Jonathan Bacon
 */
/**
 *
 * an class for managing the Equipment object
 */
public class Equipment {
    //variables to hold items
    private Armor HEAD;
    private Armor CHEST;
    private Armor LEGS;
    private Weapon LEFT_ARM;
    private Weapon RIGHT_ARM;

    //default contstructor for equipment object
    public Equipment(){
        this.HEAD = null;
        this.CHEST = null;
        this.LEGS = null;
        this.LEFT_ARM = null;
        this.RIGHT_ARM = null;
    }

// =================== GETTERS ===============================//

    protected Armor getHead(){
        return this.HEAD;
    }

    protected Armor getChest(){
        return this.CHEST;
    }

    protected Armor getLegs(){
        return this.LEGS;
    }

    protected Weapon getLeftArm(){
        return this.LEFT_ARM;
    }

    protected Weapon getRightArm(){
        return this.RIGHT_ARM;
    }

// =================== SETTERS ===============================//

    protected void setHead(Armor _head){
        this.HEAD = _head;
    }

    protected void setChest(Armor _chest){
        this.CHEST = _chest;
    }

    protected void setLegs(Armor _legs){
        this.LEGS = _legs;
    }

    protected void setLeftArm(Weapon _leftArm){
        this.LEFT_ARM = _leftArm;
    }

    protected void setRightArm(Weapon _rightArm){
        this.RIGHT_ARM = _rightArm;
    }
}
