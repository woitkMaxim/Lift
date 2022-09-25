package org.example.building;

public class Passenger {
    private final int neededFloor;

    public Passenger(int totalFloors, int currentFloor) {
        neededFloor = chooseFloor(totalFloors, currentFloor);
    }

    private int chooseFloor(int totalFloors, int currentFloor) {
        int choice = (int)(Math.random() * totalFloors);
        return choice != currentFloor ? choice : chooseFloor(totalFloors,currentFloor);
    }
    public int getNeededFloor() {
        return neededFloor;
    }

    @Override
    public String toString() {
        return String.valueOf(neededFloor);
    }
}
