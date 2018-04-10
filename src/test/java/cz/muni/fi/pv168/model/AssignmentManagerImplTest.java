package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.StarDateUtils;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImplTest {
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

    private AssignmentManagerImpl assignmentManager;
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
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/createTables.sql"));
        assignmentManager = new AssignmentManagerImpl(ds);
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

    @Test(expected = IllegalEntityException.class)
    public void createFaultyAssignment() {
        assignmentManager.createAssignment(faultyAssignment);
    }

    @Test
    public void getAssignmentById() {
        assertEquals(assignmentManager.getAssignmentById(1L), assignment1);
        assertEquals(assignmentManager.getAssignmentById(2L), assignment2);
        assertEquals(assignmentManager.getAssignmentById(3L), assignment3);
        assertEquals(assignmentManager.getAssignmentById(4L), assignment4);
        assertNotEquals(assignmentManager.getAssignmentById(4L), assignment1);
    }

    @Test
    public void findAllAssignments() {
        assertArrayEquals(assignmentManager.findAllAssignments().toArray(), new Assignment[]{assignment1,
                assignment2, assignment3, assignment4});

    }

    @Test
    public void updateAssignment() {
        assignment1.setCrewmanId(crewman2);
        assignmentManager.updateAssignment(assignment1);
        assertEquals(assignmentManager.getAssignmentById(1L).getCrewmanId(), crewman2);
        assignment1.setCrewmanId(crewman1);
    }

    @Test
    public void deleteAssignment() {
    }

    @Test
    public void findAssignmentByShip() {
        assertEquals(assignmentManager.findAssignmentByShip(ship1), assignment1);
        assertEquals(assignmentManager.findAssignmentByShip(ship2), assignment2);
        assertEquals(assignmentManager.findAssignmentByShip(ship3), assignment3);
        assertEquals(assignmentManager.findAssignmentByShip(ship4), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByShip(ship4), assignment1);
    }

    @Test
    public void findAssignmentByCrewman() {
        assertEquals(assignmentManager.findAssignmentByCrewman(crewman1), assignment1);
        assertEquals(assignmentManager.findAssignmentByCrewman(crewman2), assignment2);
        assertEquals(assignmentManager.findAssignmentByCrewman(crewman3), assignment3);
        assertEquals(assignmentManager.findAssignmentByCrewman(crewman4), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByCrewman(crewman4), assignment1);
    }
}