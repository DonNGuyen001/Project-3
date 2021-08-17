package com.company;
import java.util.Random;
public enum spellType {
    FIRE, FROST, LIGHTNING, HEAL;

    public static spellType generateRandomSpell() {
        spellType[] values;
        values = spellType.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }

}
