package system;

import com.google.common.base.Optional;
import model.Elevator;
import model.ElevatorStatus;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.reverse;
import static org.apache.log4j.Logger.getLogger;

public class ElevatorControlSystemImpl implements ElevatorControlSystem {

    private static final Logger LOGGER = getLogger(ElevatorControlSystemImpl.class);

    private final List<Elevator> elevators;

    public ElevatorControlSystemImpl(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    @Override
    public List<ElevatorStatus> getStatus() {
        List<ElevatorStatus> statuses = newArrayList();
        for (Elevator elevator : elevators) {
            statuses.add(new ElevatorStatus(elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDestinationFloor()));
        }
        return statuses;
    }

    @Override
    public void update(Integer elevatorId, Integer currentFloor, Integer destinationFloor) {
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                elevator.update(currentFloor, destinationFloor);
                LOGGER.info("Elevator: " + elevatorId + " updated");
            }
        }
    }

    @Override
    public void pickup(Integer elevatorId, Integer pickupFloor, Integer direction) {
        if (direction == 0) throw new IllegalArgumentException();
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                elevator.addPickUpOrder(pickupFloor, direction);
            }
        }
    }

    @Override
    public void step() {
        for (Elevator elevator : elevators) {
            Map<Integer, Integer> elevatorOrders = elevator.getElevatorOrders();
            if (elevatorOrders.isEmpty()) {
                elevator.setCurrentFloor(elevator.getDestinationFloor());
                elevator.setDestinationFloor(elevator.getCurrentFloor());
            } else {
                elevator.setCurrentFloor(getNextOrder(elevatorOrders, elevator.getCurrentDirection()).getKey());
                elevatorOrders.remove(getNextOrder(elevatorOrders, elevator.getCurrentDirection()).getKey());
                if (!elevatorOrders.isEmpty()) {
                    elevator.setDestinationFloor(getNextOrder(elevatorOrders, elevator.getCurrentDirection()).getKey());
                } else {
                    elevator.setDestinationFloor(elevator.getCurrentFloor());
                }
            }
        }
    }

    private Map.Entry<Integer, Integer> getNextOrder(Map<Integer, Integer> elevatorOrders, Integer currentDirection) {
        Optional<Map.Entry<Integer, Integer>> nextOptimumOrder = getNextOptimumOrder(elevatorOrders.entrySet().iterator(), currentDirection);

        if (nextOptimumOrder.isPresent()) {
            Map.Entry<Integer, Integer> nextOrder = nextOptimumOrder.get();
            return nextOrder;
        } else {
            Map.Entry<Integer, Integer> nextReverseOrder = getNextReverseOrder(elevatorOrders.entrySet().iterator(), reverse(currentDirection));
            return nextReverseOrder;
        }
    }

    //Trying to get the currentDirection for next order.
    private Optional<Map.Entry<Integer, Integer>> getNextOptimumOrder(Iterator<Map.Entry<Integer, Integer>> orderIterator, Integer currentDirection) {
        while (orderIterator.hasNext()) {
            Map.Entry<Integer, Integer> next = orderIterator.next();
            if (isNegative(next.getValue()) == isNegative(currentDirection)) {
                return Optional.of(next);
            }
        }
        return absent();
    }

    //If there is not current direction order, then get the reverse one.
    private Map.Entry<Integer, Integer> getNextReverseOrder(Iterator<Map.Entry<Integer, Integer>> orderIterator, Integer currentDirection) {
        return orderIterator.next();
    }

    private boolean isNegative(Integer l) {
        return (Math.abs(l - 1) > Math.abs(l));
    }
}
