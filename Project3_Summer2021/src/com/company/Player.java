package com.company;


import java.util.Random;

public class Player extends Creature {
    private int XP;
    private int maxHealth = 200;
    //evoke super constructor
    public Player(){
        super();
        strength = 50;
        spell = spellType.generateRandomSpell();
    }

    protected spellType getSpell(){
        return this.spell;
    }

    //return the amount of XP
    protected int getXP(){
        return XP;
    }

    protected boolean canLevelUp(){
        return XP > 100;
    }

    protected void levelUp(spellType type){
        if(canLevelUp()){
            XP -= 100;
        }
        setStrength(getStrength() + 5);
        maxHealth += 10;
        setHealth(maxHealth);
        giveSpell(spellType.generateRandomSpell());
    }


}
