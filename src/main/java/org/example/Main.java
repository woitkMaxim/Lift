package org.example;

import org.example.building.Building;

import static org.example.values.Value.MIN_FLOOR;
import static org.example.values.Value.MAX_FLOOR;


public class Main {
    public static void main(String[] args) {
        int floorsNumber = (int) (Math.random() * (MAX_FLOOR - MIN_FLOOR)) + MIN_FLOOR;
        Building building = new Building(floorsNumber);
        building.setRandomPassengers();
        System.out.println(building);
        while (building.hasPassengers()) {
            building.getLift().run();
            System.out.println(building);
        }
    }
}
