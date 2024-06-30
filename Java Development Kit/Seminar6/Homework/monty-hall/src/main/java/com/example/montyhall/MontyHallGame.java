package com.example.montyhall;

import java.util.Random;

public class MontyHallGame {
    private static final Random random = new Random();

    public enum Strategy {
        STAY, SWITCH
    }

    private final int carDoor;
    private final int chosenDoor;

    public MontyHallGame() {
        carDoor = random.nextInt(3);
        chosenDoor = random.nextInt(3);
    }

    public boolean play(Strategy strategy) {
        int revealedDoor = revealDoor();
        int finalChoice = (strategy == Strategy.SWITCH) ? switchDoor(revealedDoor) : chosenDoor;
        return finalChoice == carDoor;
    }

    private int revealDoor() {
        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3);
        } while (revealedDoor == carDoor || revealedDoor == chosenDoor);
        return revealedDoor;
    }

    private int switchDoor(int revealedDoor) {
        for (int door = 0; door < 3; door++) {
            if (door != chosenDoor && door != revealedDoor) {
                return door;
            }
        }
        throw new IllegalStateException("No door to switch to");
    }
}
