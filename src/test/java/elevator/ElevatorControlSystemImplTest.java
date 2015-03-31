package elevator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class ElevatorControlSystemImplTest {

    ElevatorControlSystemImpl system;

    @Before
    public void creatingControlSystem() {
        List<Elevator> elevators = newArrayList();
        elevators.add(new Elevator(0, 0, 1));
        elevators.add(new Elevator(1, 1, 2));
        elevators.add(new Elevator(2, 1, 1));
        elevators.add(new Elevator(3, 1, 1));

        system = new ElevatorControlSystemImpl(elevators);
    }

    @Test
    public void systemCanHandlePickUpRequests() {
        system.pickup(3, 0, -1);
        system.pickup(3, 4, 1);
        system.pickup(3, 2, 1);

        system.step();
        ElevatorStatus status = system.getStatus().get(3);

        assertThat(status.getCurrentFloor(), is(2));
        assertThat(status.getDestinationFloor(), is(4));

        system.step();

        ElevatorStatus latestStatus = system.getStatus().get(3);

        assertThat(latestStatus.getCurrentFloor(), is(4));
        assertThat(latestStatus.getDestinationFloor(), is(0));

    }

    @Test
    public void systemCanHandlePickUpRequest() {
        system.pickup(2, 2, 1);
        system.step();

        ElevatorStatus status = system.getStatus().get(2);

        assertThat(status.getElevatorId(), is(2));
        assertThat(status.getCurrentFloor(), is(2));
        assertThat(status.getDestinationFloor(), is(2));
    }

    @Test
    public void systemCanJumpToNextStep() {
        system.step();

        ElevatorStatus firstElevatorStatus = system.getStatus().get(0);

        assertThat(firstElevatorStatus.getElevatorId(), is(0));
        assertThat(firstElevatorStatus.getCurrentFloor(), is(1));
        assertThat(firstElevatorStatus.getDestinationFloor(), is(1));

        ElevatorStatus secondElevatorStatus = system.getStatus().get(1);

        assertThat(secondElevatorStatus.getCurrentFloor(), is(2));
        assertThat(secondElevatorStatus.getDestinationFloor(), is(2));
    }

    @Test
    public void elevatorSystemCanUpdateElevator() {
        ElevatorStatus initialStatus = system.getStatus().get(0);

        assertThat(initialStatus.getElevatorId(), is(0));
        assertThat(initialStatus.getCurrentFloor(), is(0));
        assertThat(initialStatus.getDestinationFloor(), is(1));

        system.update(0, 1, 2);
        ElevatorStatus updatedStatus = system.getStatus().get(0);

        assertThat(updatedStatus.getCurrentFloor(), is(1));
        assertThat(updatedStatus.getDestinationFloor(), is(2));

    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionForWrongPickUpRequest() {
        system.pickup(1, 0, 0);
    }
}