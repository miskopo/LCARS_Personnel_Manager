package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.Rank;
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
public class CrewmanManagerImplTest {
    Crewman crewman1 = new Crewman(1L, "Arthur Dent", Rank.CAPTAIN);
    Crewman crewman2 = new Crewman(2L, "Anakin Skywalker", Rank.COMMANDER);
    Crewman crewman3 = new Crewman(3L, "Doctor Who", Rank.LIEUTENANT);
    Crewman crewman4 = new Crewman(4L, "Gaius Julius Caesar", Rank.ENSIGN);

    private CrewmanManagerImpl crewmanManager;
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
        crewmanManager = new CrewmanManagerImpl(ds);
        crewmanManager.createCrewman(crewman1);
        crewmanManager.createCrewman(crewman2);
        crewmanManager.createCrewman(crewman3);
        crewmanManager.createCrewman(crewman4);
    }

    @After
    public void tearDown() throws SQLException, IOException {
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/dropTables.sql"));
    }

    @Test(expected = IllegalEntityException.class)
    public void createEmptyCrewman() {
        crewmanManager.createCrewman(null);
    }

    @Test
    public void createCrewman() {
        crewmanManager.createCrewman(crewman1);
        crewmanManager.createCrewman(crewman2);
        crewmanManager.createCrewman(crewman3);
        crewmanManager.createCrewman(crewman4);
    }

    @Test
    public void getCrewman() {
        assertEquals(crewmanManager.getCrewman(1L), crewman1);
        assertEquals(crewmanManager.getCrewman(2L), crewman2);
        assertEquals(crewmanManager.getCrewman(3L), crewman3);
        assertEquals(crewmanManager.getCrewman(4L), crewman4);
        assertNotEquals(crewmanManager.getCrewman(4L), crewman1);
    }

    @Test
    public void updateCrewman() {
        crewman1.setName("Arthur Dent_modified");
        crewmanManager.updateCrewman(crewman1);
        assertEquals("Arthur Dent_modified", crewmanManager.getCrewman(crewman1.getId()).getName());
    }

    @Test
    public void deleteCrewman() {
        crewmanManager.deleteCrewman(crewman1);
        assertTrue(crewmanManager.getCrewman(1L) == null);
    }

    @Test
    public void findAllCrewmen() {
        assertArrayEquals(crewmanManager.findAllCrewmen().toArray(), new Crewman[]{crewman1, crewman2, crewman3, crewman4});

    }
}