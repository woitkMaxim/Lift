package org.example.building;

import static org.example.values.Value.MAX_PASSENGERS_ON_FLOOR;

public class Building {
    private final Floor[] floors;
    private final Lift lift;

    public Building(int floorsNumber) {
        floors = new Floor[floorsNumber];
        lift = new Lift(this);
    }
    public Lift getLift() {
        return lift;
    }
    public Floor[] getFloors() {
        return floors;
    }
    public Floor getFloor(int floorNumber) {
        return floors[floorNumber];
    }

    public void setRandomPassengers() {
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new Floor(floors.length, i, MAX_PASSENGERS_ON_FLOOR);
        }
    }
    public boolean hasPassengers() {
        if (getLift().getPassengers().size() != 0) {
            return true;
        }
        for (Floor floor : floors) {
            if (floor.getPassengers().size() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = floors.length - 1; i >= 0; i--) {
            sb.append(i);
            sb.append("\t");
            sb.append(" | ");
            sb.append("\t");
            if (getLift().getCurrentFloor() == i) {
                sb.append(lift);
                sb.append(" ");
            } else {
                sb.append("       " );
            }
            sb.append(floors[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
