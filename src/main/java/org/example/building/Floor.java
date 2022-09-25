package org.example.building;

import java.util.ArrayList;

public class Floor {
    private ArrayList<Passenger> passengers;

    public Floor(int totalFloors, int currentFloor, int maxPassengersOnFloor) {
        passengers = setPassengers(totalFloors, currentFloor, maxPassengersOnFloor);
    }
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    private ArrayList<Passenger> setPassengers(int totalFloors, int currentFloor, int maxPassengersOnFloor) {
        int passengersNumber = (int)(Math.random() * maxPassengersOnFloor);
        passengers = new ArrayList<>(passengersNumber);
        for (int i = 0; i < passengersNumber; i++) {
            passengers.add(new Passenger(totalFloors, currentFloor));
        }
        return passengers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("| ");
        for (Passenger passenger : passengers) {
            sb.append(passenger);
            sb.append(" ");
        }
        return sb.toString();
    }
}
