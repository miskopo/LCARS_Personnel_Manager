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
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImplTest {
    private LocalDate beginning = LocalDate.of(2018, 4, 4);
    private LocalDate end = LocalDate.of(1970, 1, 1);
    private Assignment assignment1 = new Assignment(1L, 1L, 1L, beginning, end);
    private Assignment assignment2 = new Assignment(2L, 2L, 2L, beginning, end);
    private Assignment assignment3 = new Assignment(3L, 3L, 3L, beginning, end);
    private Assignment assignment4 = new Assignment(4L, 4L, 4L, beginning, end);
//    private Assignment faultyAssignment = new Assignment(4L, 5L, 5L, beginning, end);

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

    }

    @Test
    public void findAssignmentByShip() {
        assertEquals(assignmentManager.findAssignmentByShip(assignment1.getShipId()), assignment1);
        assertEquals(assignmentManager.findAssignmentByShip(assignment2.getShipId()), assignment2);
        assertEquals(assignmentManager.findAssignmentByShip(assignment3.getShipId()), assignment3);
        assertEquals(assignmentManager.findAssignmentByShip(assignment4.getShipId()), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByShip(assignment4.getShipId()), assignment1);
    }

    @Test
    public void findAssignmentByCrewman() {
        assertEquals(assignmentManager.findAssignmentByCrewman(1L), assignment1);
        assertEquals(assignmentManager.findAssignmentByCrewman(2L), assignment2);
        assertEquals(assignmentManager.findAssignmentByCrewman(3L), assignment3);
        assertEquals(assignmentManager.findAssignmentByCrewman(4L), assignment4);
        assertNotEquals(assignmentManager.findAssignmentByCrewman(4L), assignment1);
    }
}