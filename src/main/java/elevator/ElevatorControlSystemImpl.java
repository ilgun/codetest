package elevator;

import org.apache.log4j.Logger;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
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
            } else {
                LOGGER.error("Elevator cannot be found");
            }
        }
    }

    @Override
    public void pickup(Integer elevatorId, Integer pickupFloor, Integer direction) {
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorId() == elevatorId) {
                elevator.addPickUpOrder(pickupFloor, direction);
            } else {
                LOGGER.error("Elevator cannot be found");
            }
        }
    }

    @Override
    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }
}
