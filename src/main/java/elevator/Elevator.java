package elevator;

import com.google.common.base.Optional;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Maps.newTreeMap;
import static java.lang.Integer.reverse;

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

    //TODO put in to IMPL.
    //TODO destination floor is not needed, put it into the orders directly.
    public void step() {
        if (elevatorOrders.isEmpty()) {
            currentFloor = destinationFloor;
            destinationFloor = currentFloor;
        } else {
            currentFloor = getNextOrder().getKey();
            elevatorOrders.remove(currentFloor);
            if (!elevatorOrders.isEmpty()) {
                destinationFloor = getNextOrder().getKey();
            } else {
                destinationFloor = currentFloor;
            }
        }
    }


    public Integer getCurrentDirection() {
        return currentDirection;
    }

    private Entry<Integer, Integer> getNextOrder() {
        Optional<Entry<Integer, Integer>> nextOptimumOrder = getNextOptimumOrder(elevatorOrders.entrySet().iterator(), currentDirection);

        if (nextOptimumOrder.isPresent()) {
            Entry<Integer, Integer> nextOrder = nextOptimumOrder.get();
            return nextOrder;
        } else {
            Entry<Integer, Integer> nextReverseOrder = getNextReverseOrder(elevatorOrders.entrySet().iterator(), reverse(currentDirection));
            return nextReverseOrder;
        }
    }

    //Trying to get the currentDirection for next order.
    private Optional<Entry<Integer, Integer>> getNextOptimumOrder(Iterator<Entry<Integer, Integer>> orderIterator, Integer currentDirection) {
        while (orderIterator.hasNext()) {
            Entry<Integer, Integer> next = orderIterator.next();
            if (isNegative(next.getValue()) == isNegative(currentDirection)) {
                return Optional.of(next);
            }
        }
        return absent();
    }

    //If there is not current direction order, then get the reverse one.
    private Entry<Integer, Integer> getNextReverseOrder(Iterator<Entry<Integer, Integer>> orderIterator, Integer currentDirection) {
        return orderIterator.next();
    }

    private boolean isNegative(Integer l) {
        return (Math.abs(l - 1) > Math.abs(l));
    }
}
