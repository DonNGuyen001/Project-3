package com.company;

import java.util.Random;

public class Creature {
    protected int strength;
    protected int health;
    protected boolean canCast = true;
    protected boolean frozen = false;
    protected boolean burning = false;
    protected int freezeTime = 0;
    protected int burnTime = 0;
    protected spellType spell;

    protected Creature(){
        strength = 50;
        health = 100;
        spell = null;
    }

    public int getHealth(){
        return this.health ;
    }

    protected void setHealth(int health){
        this.health = health;
    }

    protected int getStrength(){
        return this.strength ;
    }

    protected void setStrength(int health){
        this.strength = strength;
    }
    protected int attack(){
        int damage = 0;
        Random strike = new Random();
        if(isFrozen() != true)
            damage = strike.nextInt(strength + 1);
        return damage;
    }

    protected void hurt(int damage){
        this.health = this.health - damage;
    }
    protected void giveSpell(spellType spell){
        this.spell = spellType.generateRandomSpell();
    }

    boolean canCast(spellType type){

        return canCast;
    }

    void freeze(){
        this.frozen = true;
    }

    boolean isFrozen(){
        return frozen;
    }

    void decFreezeTimer(){
        //monster will be frozen in 3 rounds
        if(isFrozen() == true && freezeTime > 0)
            freezeTime --;
    }
    protected boolean isOnFire(){
        return burning;
    }

    //set turn that burning and marked that this creature is burning

    protected void setOnFire(){
        this.burnTime = 4;
        this.burning = true;
    }

    /*count down the turn that creature is burning
    * take damage as burning
    * when count down reached 0 no more burning*/
    protected void decFireTimer(){
        if(isOnFire() == true && burnTime >0 ){
            hurt(4);
            burnTime --;
        }
        else if(isOnFire() == true && burnTime == 0)
            this.burning = false;
    }

    protected void heal(int health){
        setHealth(getHealth() + 10);
    }
    protected boolean isDead(){
        return health <= 0;
    }

}
