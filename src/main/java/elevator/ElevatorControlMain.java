package elevator;

import org.apache.log4j.Logger;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.log4j.Logger.getLogger;

public class ElevatorControlMain {
    private static final Logger LOGGER = getLogger(ElevatorControlMain.class);

    public static void main(String[] args) {
        List<Elevator> elevators = newArrayList();
        elevators.add(new Elevator(1, 0, 1));

        ElevatorControlSystemImpl system = new ElevatorControlSystemImpl(elevators);

        LOGGER.info("Current floor: " + system.getStatus().get(0).getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus().get(0).getDestinationFloor());

        system.step();

        LOGGER.info("Current floor: " + system.getStatus().get(0).getCurrentFloor());
        LOGGER.info("Destination Floor: " + system.getStatus().get(0).getDestinationFloor());

    }
}
