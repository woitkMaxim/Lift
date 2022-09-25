package org.example.building;

import java.util.ArrayList;
import java.util.Iterator;

import static org.example.values.Value.*;

public class Lift {
    private final Building building;
    private final int capacity = MAX_PASSENGERS_IN_LIFT;
    private final ArrayList<Passenger> passengers = new ArrayList<>(capacity);
    private int currentFloor = 0;
    private int destinationFloor = 0;
    private int direction = NO_DIRECTION;

    public Lift (Building building) {
        this.building = building;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void run() {
            dropPassengers();
            takePassengers();
            move();
    }
    private void dropPassengers() {
        passengers.removeIf(passenger -> passenger.getNeededFloor() == currentFloor);
        if (passengers.size() == 0) {
            direction = NO_DIRECTION;
            destinationFloor = 0;
        }
    }
    private void takePassengers() {
        if (passengers.size() == MAX_PASSENGERS_IN_LIFT) {
            return;
        }
        int placesInLift = MAX_PASSENGERS_IN_LIFT - passengers.size();
        letPassengersIn(placesInLift, currentFloor);
        if (passengers.size() == 0) {
            moveToClosestPassenger();
        }
    }



    private void letPassengersIn(int placesInLift, int currentFloor) {
        int counter = 0;
        Iterator<Passenger> iterator = building.getFloor(currentFloor).getPassengers().iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (direction == UP && passenger.getNeededFloor() > currentFloor) {
                passengers.add(passenger);
                iterator.remove();
                if (passenger.getNeededFloor() > destinationFloor) {
                    destinationFloor = passenger.getNeededFloor();
                }
                counter++;
            }
            if (direction == DOWN && passenger.getNeededFloor() < currentFloor) {
                passengers.add(passenger);
                iterator.remove();
                if (passenger.getNeededFloor() < destinationFloor) {
                    destinationFloor = passenger.getNeededFloor();
                }
                counter++;
            }
            if (direction == NO_DIRECTION) {
                passengers.add(passenger);
                iterator.remove();
                destinationFloor = passenger.getNeededFloor();
                direction = passenger.getNeededFloor() > currentFloor ? UP : DOWN;
                counter++;
            }
            if (counter == placesInLift) {
                return;
            }
        }
    }
    private void moveToClosestPassenger() {
        int gap = Integer.MAX_VALUE;
        int floorWithPassenger = 0;
        for (int i = building.getFloors().length - 1; i >= 0; i--) {
            if (building.getFloor(i).getPassengers().size() != 0) {
                if (Math.abs(currentFloor - i) < gap) {
                    floorWithPassenger = i;
                    gap = Math.abs(currentFloor - i);
                }
            }
        }
        if (currentFloor < floorWithPassenger) {
            currentFloor = floorWithPassenger - 1;
            direction = UP;
        }
        if (currentFloor > floorWithPassenger) {
            currentFloor = floorWithPassenger + 1;
            direction = DOWN;
        }
    }
    private void move() {
        if (direction == UP) {
            currentFloor++;
        }
        if (direction == DOWN) {
            currentFloor--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ ] ");
        for (Passenger passenger : passengers) {
            sb.append(passenger);
            sb.append(" ");
        }
        return sb.toString();
    }
}
