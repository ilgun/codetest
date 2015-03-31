package elevator;

import java.util.Map;
import java.util.SortedMap;

import static com.google.common.collect.Maps.newTreeMap;

public class Elevator {
    // Map consists of floor , direction
    private SortedMap<Integer, Integer> elevatorOrders;
    private SortedMap<Integer, Integer> pickupsOrders;
    private final int elevatorId;
    private int currentFloor;
    private int destinationFloor;
    private Integer currentDirection;

    public Elevator(int elevatorId, int currentFloor, int destinationFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        elevatorOrders = newTreeMap();
        pickupsOrders = newTreeMap();
        currentDirection = 1;
    }

    public Map<Integer, Integer> getElevatorOrders() {
        return elevatorOrders;
    }

    public Map<Integer, Integer> getPickupsOrders() {
        return pickupsOrders;
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
}
