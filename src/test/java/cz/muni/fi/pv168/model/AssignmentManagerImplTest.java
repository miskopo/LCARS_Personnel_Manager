package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.StarDateUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImplTest {
    AssignmentManager assignmentManager = new AssignmentManagerImpl();
    Ship ship1 = new Ship();
    Ship ship2 = new Ship();
    Ship ship3 = new Ship();
    Ship ship4 = new Ship();
    Crewman crewman1 = new Crewman();
    Crewman crewman2 = new Crewman();
    Crewman crewman3 = new Crewman();
    Crewman crewman4 = new Crewman();
    StarDateUtils beginning = new StarDateUtils(12345.4);
    StarDateUtils end = new StarDateUtils(12345.5);
    Assignment assignment1 = new Assignment(1L, ship1, crewman1, beginning, end);
    Assignment assignment2 = new Assignment(2L, ship2, crewman2, beginning, end);
    Assignment assignment3 = new Assignment(3L, ship3, crewman3, beginning, end);
    Assignment assignment4 = new Assignment(4L, ship4, crewman4, beginning, end);
    Assignment faultyAssignment = new Assignment(4L, ship1, crewman2, beginning, end);

    @Test(expected = IllegalEntityException.class)
    public void createEmptyAssignment() {
        assignmentManager.createAssignment(null);
    }

    @Test
    public void createAssignment() {
        assignmentManager.createAssignment(assignment1);
        assignmentManager.createAssignment(assignment2);
        assignmentManager.createAssignment(assignment3);
        assignmentManager.createAssignment(assignment4);
    }

    @Test(expected = IllegalEntityException.class)
    public void createFaultyAssignment() {
        assignmentManager.createAssignment(faultyAssignment);
    }

    @Test
    public void getAssignmentById() {
        assertTrue(assignmentManager.getAssignmentById(1L).equals(assignment1));
        assertTrue(assignmentManager.getAssignmentById(2L).equals(assignment2));
        assertTrue(assignmentManager.getAssignmentById(3L).equals(assignment3));
        assertTrue(assignmentManager.getAssignmentById(4L).equals(assignment4));
        assertFalse(assignmentManager.getAssignmentById(4L).equals(assignment1));
    }

    @Test
    public void findAllAssignments() {
        assertArrayEquals(assignmentManager.findAllAssignments().toArray(), new Assignment[]{assignment1,
                assignment2, assignment3, assignment4});

    }

    @Test
    public void updateAssignment() {
        assignment1.setCrewman(crewman2);
        assignmentManager.updateAssignment(assignment1);
        assertTrue(assignmentManager.getAssignmentById(1L).getCrewman().equals(crewman2));
        assignment1.setCrewman(crewman1);
    }

    @Test
    public void deleteAssignment() {
    }

    @Test
    public void findAssignmentByShip() {
        assertTrue(assignmentManager.findAssignmentByShip(ship1).equals(assignment1));
        assertTrue(assignmentManager.findAssignmentByShip(ship2).equals(assignment2));
        assertTrue(assignmentManager.findAssignmentByShip(ship3).equals(assignment3));
        assertTrue(assignmentManager.findAssignmentByShip(ship4).equals(assignment4));
        assertFalse(assignmentManager.findAssignmentByShip(ship4).equals(assignment1));
    }

    @Test
    public void findAssignmentByCrewman() {
        assertTrue(assignmentManager.findAssignmentByCrewman(crewman1).equals(assignment1));
        assertTrue(assignmentManager.findAssignmentByCrewman(crewman2).equals(assignment2));
        assertTrue(assignmentManager.findAssignmentByCrewman(crewman3).equals(assignment3));
        assertTrue(assignmentManager.findAssignmentByCrewman(crewman4).equals(assignment4));
        assertFalse(assignmentManager.findAssignmentByCrewman(crewman4).equals(assignment1));
    }
}