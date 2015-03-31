package system;

import model.ElevatorStatus;

import java.util.List;

public interface ElevatorControlSystem {

    List<ElevatorStatus> getStatus();

    void update(Integer elevatorId, Integer currentFloor, Integer destinationFloor);

    void pickup(Integer elevatorId, Integer pickupFloor, Integer direction);

    void step();

}
