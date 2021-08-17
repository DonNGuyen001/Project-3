package com.company;

import java.util.*;
import java.util.Scanner;

public class Arena {
    private int turn = 0;

    Queue<Creature> west = new LinkedList<Creature>();
    Queue<Creature> east = new LinkedList<Creature>();
    Queue<Creature> north = new LinkedList<Creature>();
    Queue<Creature> south = new LinkedList<Creature>();
    Queue side[] = {west, east, north, south};

    Player slayer = new Player();

    // create the arena
    public Arena() {

    }
    // the game loop until game over
    public void gameLoop(){
        while(!isGameOver()==true) {
            playersTurn();
            monsterTurn();
            createMonster();
            turnCounter();
            if(isGameOver())
                break;
        }
    }

    // check if the game is over
    public boolean isGameOver(){
         return slayer.getHealth() == 0;
    }


    /*
    * Every turn the player and four monsters take a turn.
    * The player is asked in which direction they want to attack (NSWE)
    * and the type of attack (normal or cast a spell).
    */
    private void createMonster(){
        Creature monster = new Creature();
        int power = new Random().nextInt(100);
        // 25% chance new monster can have a spell
        if(power >= 75)
            monster.canCast = true;
            monster.canCast(spellType.generateRandomSpell());
        int random = new Random().nextInt(4);
        switch (random){
            case 0:
                south.add(monster);
                break;
            case 1:
                north.add(monster);
                break;
            case 2:
                west.add(monster);
                break;
            case 3:
                east.add(monster);
                break;
        }
    }

    /*
    * if the player is frozen, they can't do anything this turn
    * if the player is on fire, they take damage
    * tell the player what direction there are monsters; maybe tell them how strong the monsters are?; do they have spells?
    * get the direction of attack
    * get whether the player wants to do a normal attack or cast a spell
    * perform the player's action, gain XP if the monster is killed
    * decrement all the player's cool down timers (on fire, frozen)
    * if the player can level up, do so (may use a helper method for this)
    */
    private void playersTurn(){
        if(slayer.isOnFire()){
            slayer.decFireTimer();
        }
        if(slayer.isFrozen()==false){
            if (slayer.canCast == true) {
                System.out.println("The slayer spell " + slayer.getSpell() + " do you want to use y/n ?");
                Scanner choice = new Scanner(System.in);
                String cast = choice.toString();

                if (cast.equals("y")) {
                    switch (slayer.getSpell()) {
                        case FIRE:
                            chooseSide().peek().setOnFire();
                            break;
                        case FROST:
                            chooseSide().peek().freeze();
                            break;
                        case HEAL:
                            slayer.heal(50);
                            break;
                        case LIGHTNING:
                            west.peek().hurt(55);
                            east.peek().hurt(55);
                            south.peek().hurt(55);
                            north.peek().hurt(55);
                            break;
                        default:
                            System.out.println("You don't remember the spell! Now they are attacking you!");
                            return;
                    }
                    return;
                }
            } else {
                Queue<Creature> target = chooseSide();
                target.peek().hurt(slayer.attack());
                if(target.peek().getHealth() == 0){
                    target.remove();
                }

            }
        }
        else{
            slayer.decFreezeTimer();
        }

    }

    /*
    *for each monster at the head of the four queues...
    *if the monster can cast a spell, they do
    *otherwise, they just attack
    *decrement all the monster's cooldown timers
    * monster cannot use heal
    */

    private void monsterTurn(){
        Queue<Creature> arena[] = new Queue[]{west, east, south, east};
        for(int i = 0; i < 4; i++ ){
            Queue<Creature> currentSide = arena[i];
            //if the side is empty there will be 50% a monster is created and assign to that side
            if(currentSide.size() == 0 ){
                int k = new Random().nextInt(10) + 1;
                if(k > 5){
                    Creature monster = new Creature();
                    currentSide.add(monster);
                }
            }
            else{
                if(currentSide.peek().canCast(spellType.FROST)){
                    slayer.freeze();
                }
                if(currentSide.peek().canCast(spellType.LIGHTNING)){
                    slayer.hurt(50);
                }
                if(currentSide.peek().canCast(spellType.FIRE)){
                    slayer.setOnFire();
                }
                else{}
                currentSide.peek().attack();
            }
            currentSide.peek().decFireTimer();
            currentSide.peek().decFreezeTimer();
        }
    }

    // increase count
    private void turnCounter(){
        turn ++;
    }
    //use to choose a queue side each time
    private Queue<Creature> chooseSide(){
        Queue<Creature> side = null;
        System.out.println("Which side you want to attack? North-N   South-S   East-E   West-W   ");
        Scanner scan = new Scanner(System.in);
        String navigate = scan.nextLine();
        switch (navigate){
            case "s":
                side = south;
                break;
            case "n":
                side = north;
                break;
            case "w":
                side = west;
                break;
            case "e":
                side = east;
                break;
            default:
                System.out.println("Your strike missed the target!");
        }
            return side;
    }
}

