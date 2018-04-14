package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ShipType;
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
public class ShipManagerImplTest {
    Ship ship1 = new Ship(1L, "Enterprise", "NYC-72", ShipType.NEBULA, 9.7);
    Ship ship2 = new Ship(2L, "Voyager", "NYC-94", ShipType.WARSHIP, 8.4);
    Ship ship3 = new Ship(3L, "Pegasus", "NYC-12", ShipType.TRANSPORT, 6.5);
    Ship ship4 = new Ship(4L, "Discovery", "NYC-42", ShipType.SCIENCE, 9.2);

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
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/createTables.sql"));
        shipManager = new ShipManagerImpl(ds);
        shipManager.createShip(ship1);
        shipManager.createShip(ship2);
        shipManager.createShip(ship3);
        shipManager.createShip(ship4);
    }

    @After
    public void tearDown() throws SQLException, IOException {
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/dropTables.sql"));
    }

    @Test(expected = IllegalEntityException.class)
    public void createEmptyShip() {
        shipManager.createShip(null);
    }

    @Test
    public void createShip(){
        shipManager.createShip(ship1);
        shipManager.createShip(ship2);
        shipManager.createShip(ship3);
        shipManager.createShip(ship4);
    }

    @Test
    public void getShip() {
        assertEquals(shipManager.getShip(ship1.getId()), ship1);
        assertEquals(shipManager.getShip(ship2.getId()), ship2);
        assertEquals(shipManager.getShip(ship3.getId()), ship3);
        assertEquals(shipManager.getShip(ship4.getId()), ship4);
        assertNotEquals(shipManager.getShip(ship4.getId()), ship1);
     }

    @Test(expected = IllegalEntityException.class)
    public void updateEmptyShip() {
        shipManager.updateShip(null);
    }

    @Test
    public void updateShip() {
        ship1.setName("Enterprise_modified");
        shipManager.updateShip(ship1);
        assertEquals("Enterprise_modified", shipManager.getShip(ship1.getId()).getName());
    }

    @Test(expected = IllegalEntityException.class)
    public void deleteEmptyShip(){
        shipManager.deleteShip(null);
    }

    @Test
    public void deleteShip() {
        shipManager.deleteShip(ship1);
        assertNull(shipManager.getShip(ship1.getId()));
    }

    @Test
    public void findAllShips() {
        assertArrayEquals(shipManager.findAllShips().toArray(), new Ship[]{ship1, ship2, ship3, ship4});
    }
}