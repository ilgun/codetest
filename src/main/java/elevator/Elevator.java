package elevator;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Elevator {
    // Map consists of floor , direction
    private Map<Integer, Integer> pickUpOrders;
    private final int elevatorId;
    private int currentFloor;
    private int destinationFloor;

    public Elevator(int elevatorId, int currentFloor, int destinationFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        pickUpOrders = newHashMap();
    }

    public Map<Integer, Integer> getPickUpOrders() {
        return pickUpOrders;
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

    public void addPickUpOrder(int order, Integer direction) {
        pickUpOrders.put(order, direction);
    }

    public void update(Integer currentFloor, Integer destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    public void step() {
        currentFloor = destinationFloor;
        if (pickUpOrders.isEmpty()) {
            destinationFloor = currentFloor;
        } else {
            destinationFloor = pickUpOrders.entrySet().iterator().next().getKey();
        }
    }
}
