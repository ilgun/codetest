package system;

import model.Elevator;
import org.apache.log4j.Logger;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static model.Elevator.Builder.anElevator;
import static org.apache.log4j.Logger.getLogger;

public class ElevatorControlMain {
    private static final Logger LOGGER = getLogger(ElevatorControlMain.class);

    public static void main(String[] args) {
        List<Elevator> elevators = newArrayList();
        elevators.add(anElevator()
                .withElevatorId(1)
                .withCurrentFloor(0)
                .withDestinationFloor(0)
                .build());

        ElevatorControlSystemImpl system = new ElevatorControlSystemImpl(elevators);

        LOGGER.info("Current floor: " + system.getStatus(1).get().getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus(1).get().getDestinationFloor());

        system.step();

        LOGGER.info("Current floor: " + system.getStatus(1).get().getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus(1).get().getDestinationFloor());

        system.update(1, 2, 2);

        LOGGER.info("Current floor: " + system.getStatus(1).get().getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus(1).get().getDestinationFloor());

        system.pickup(1, 5, 1);

        LOGGER.info("Pickup should happen here");
        system.step();
        LOGGER.info("Current floor: " + system.getStatus(1).get().getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus(1).get().getDestinationFloor());
    }
}
