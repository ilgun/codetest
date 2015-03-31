package system;

import com.google.common.base.Optional;
import model.ElevatorStatus;

public interface ElevatorControlSystem {

    Optional<ElevatorStatus> getStatus(Integer elevatorId);

    void update(Integer elevatorId, Integer currentFloor, Integer destinationFloor);

    void pickup(Integer elevatorId, Integer pickupFloor, Integer direction);

    void step();

}
