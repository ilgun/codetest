package model;

import java.util.Map;
import java.util.SortedMap;

import static com.google.common.collect.Maps.newTreeMap;

public class Elevator {

    private final int elevatorId;
    private int currentFloor;
    private int destinationFloor;
    // Map consists of floor , direction
    private SortedMap<Integer, Integer> elevatorOrders;
    private Integer currentDirection;

    private Elevator(Builder builder) {
        this.elevatorId = builder.elevatorId;
        this.currentFloor = builder.currentFloor;
        this.destinationFloor = builder.destinationFloor;
        elevatorOrders = newTreeMap();
        currentDirection = 1;
    }

    public Map<Integer, Integer> getElevatorOrders() {
        return elevatorOrders;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void addElevatorOrder(int order, Integer direction) {
        elevatorOrders.put(order, direction);
    }

    public void addPickUpOrder(int order, Integer direction) {
        elevatorOrders.put(order, direction);
    }

    public void update(Integer currentFloor, Integer destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    public Integer getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public static class Builder {
        private int elevatorId;
        private int currentFloor;
        private int destinationFloor;

        public Builder withElevatorId(int elevatorId) {
            this.elevatorId = elevatorId;
            return this;
        }

        public Builder withCurrentFloor(int currentFloor) {
            this.currentFloor = currentFloor;
            return this;
        }

        public Builder withDestinationFloor(int destinationFloor) {
            this.destinationFloor = destinationFloor;
            return this;
        }

        public static Builder anElevator() {
            return new Builder();
        }

        public Elevator build() {
            return new Elevator(this);
        }
    }
}
