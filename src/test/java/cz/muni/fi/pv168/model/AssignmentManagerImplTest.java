package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.Rank;
import cz.muni.fi.pv168.common.ShipType;
import cz.muni.fi.pv168.common.StarDateUtils;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImplTest {
    private LocalDate beginning = LocalDate.of(2018, 4, 4);
    private LocalDate end = LocalDate.of(1970, 1, 1);
    Crewman crewman1 = new Crewman(1L, "Arthur Dent", Rank.CAPTAIN);
    Crewman crewman2 = new Crewman(2L, "Anakin Skywalker", Rank.COMMANDER);
    Crewman crewman3 = new Crewman(3L, "Doctor Who", Rank.LIEUTENANT);
    Crewman crewman4 = new Crewman(4L, "Gaius Julius Caesar", Rank.ENSIGN);

    Ship ship1 = new Ship(1L, "Enterprise", "NYC-72", ShipType.NEBULA, 9.7);
    Ship ship2 = new Ship(2L, "Voyager", "NYC-94", ShipType.WARSHIP, 8.4);
    Ship ship3 = new Ship(3L, "Pegasus", "NYC-12", ShipType.TRANSPORT, 6.5);
    Ship ship4 = new Ship(4L, "Discovery", "NYC-42", ShipType.SCIENCE, 9.2);

    private Assignment assignment1 = new Assignment(crewman1.getId(), ship1.getId(), 1L, beginning, end);
    private Assignment assignment2 = new Assignment(crewman2.getId(), ship2.getId(), 2L, beginning, end);
    private Assignment assignment3 = new Assignment(crewman3.getId(), ship3.getId(), 3L, beginning, end);
    private Assignment assignment4 = new Assignment(crewman4.getId(), ship4.getId(), 4L, beginning, end);
//    private Assignment faultyAssignment = new Assignment(4L, 5L, 5L, beginning, end);

    private AssignmentManagerImpl assignmentManager;
    private CrewmanManagerImpl crewmanManager;
    private ShipManagerImpl shipManager;
    private DataSource ds;


    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("memory:shipmanager-test");
        ds.setCreateDatabase("create");
        return ds;
    }


    @Before
    public void setUp() throws SQLException, IOException {
        ds = prepareDataSource();
        DBUtils.executeSqlScript(ds, AssignmentManager.class.getResourceAsStream("/createTables.sql"));
        assignmentManager = new AssignmentManagerImpl(ds);
        crewmanManager = new CrewmanManagerImpl(ds);
        shipManager = new ShipManagerImpl(ds);
        assignmentManager.createAssignment(assignment1);
        assignmentManager.createAssignment(assignment2);
        assignmentManager.createAssignment(assignment3);
        assignmentManager.createAssignment(assignment4);
    }

    @After
    public void tearDown() throws SQLException, IOException {
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/dropTables.sql"));
    }

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

    @Test
    public void getAssignmentById() {
        assertEquals(assignmentManager.getAssignmentById(assignment1.getId()), assignment1);
        assertEquals(assignmentManager.getAssignmentById(assignment2.getId()), assignment2);
        assertEquals(assignmentManager.getAssignmentById(assignment3.getId()), assignment3);
        assertEquals(assignmentManager.getAssignmentById(assignment4.getId()), assignment4);
        assertNotEquals(assignmentManager.getAssignmentById(assignment4.getId()), assignment1);
    }

    @Test
    public void findAllAssignments() {
        assertArrayEquals(assignmentManager.findAllAssignments().toArray(), new Assignment[]{assignment1,
                assignment2, assignment3, assignment4});

    }

    @Test
    public void updateAssignment() {
        assignment1.setCrewmanId(4L);
        assignmentManager.updateAssignment(assignment1);
        assertEquals(assignmentManager.getAssignmentById(1L).getCrewmanId(), 4L);
        assignment1.setCrewmanId(2L);
    }

    @Test
    public void deleteAssignment() {
        assignmentManager.deleteAssignment(assignment1);
        assertNull(assignmentManager.getAssignmentById(assignment1.getId()));
    }

    @Test
    public void findAssignmentByShip() {
        shipManager.createShip(ship1);
        shipManager.createShip(ship2);
        shipManager.createShip(ship3);
        shipManager.createShip(ship4);
        assertEquals(assignmentManager.findAssignmentByShip(assignment1.getShipId()), assignment1);
        assertEquals(assignmentManager.findAssignmentByShip(assignment2.getShipId()), assignment2);
        assertEquals(assignmentManager.findAssignmentByShip(assignment3.getShipId()), assignment3);
        assertEquals(assignmentManager.findAssignmentByShip(assignment4.getShipId()), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByShip(assignment4.getShipId()), assignment1);
    }

    @Test
    public void findAssignmentByCrewman() {
        crewmanManager.createCrewman(crewman1);
        crewmanManager.createCrewman(crewman2);
        crewmanManager.createCrewman(crewman3);
        crewmanManager.createCrewman(crewman4);
        assertEquals(assignmentManager.findAssignmentByCrewman(1L), assignment1);
        assertEquals(assignmentManager.findAssignmentByCrewman(2L), assignment2);
        assertEquals(assignmentManager.findAssignmentByCrewman(3L), assignment3);
        assertEquals(assignmentManager.findAssignmentByCrewman(4L), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByCrewman(4L), assignment1);
    }
}