package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ShipType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class ShipManagerImplTest {
    Ship ship1 = new Ship(1L, "Enterprise", "NYC-72", ShipType.NEBULA, 9.7);
    Ship ship2 = new Ship(2L, "Voyager", "NYC-94", ShipType.WARSHIP, 8.4);
    Ship ship3 = new Ship(3L, "Pegasus", "NYC-12", ShipType.TRANSPORT, 6.5);
    Ship ship4 = new Ship(4L, "Discovery", "NYC-42", ShipType.SCIENCE, 9.2);
    ShipManager shipManager = new ShipManagerImpl();


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
        assertEquals(shipManager.getShip(1L), ship1);
        assertEquals(shipManager.getShip(2L), ship2);
        assertEquals(shipManager.getShip(3L), ship3);
        assertEquals(shipManager.getShip(4L), ship4);
        assertNotEquals(shipManager.getShip(4L), ship1);
     }

    @Test(expected = IllegalEntityException.class)
    public void updateEmptyShip() {
        shipManager.updateShip(null);
    }

    @Test
    public void updateShip() {
        ship1.setDesignation("EnterpriseModified");
        shipManager.updateShip(ship1);
        assertEquals("EnterpriseModified", shipManager.getShip(ship1.getId()).getName());
        ship1.setDesignation("Enterprise");
    }

    @Test(expected = IllegalEntityException.class)
    public void deleteEmptyShip(){
        shipManager.deleteShip(null);
    }

    @Test
    public void deleteShip() {
    }

    @Test
    public void findAllShips() {
        assertArrayEquals(shipManager.findAllShips().toArray(), new Ship[]{ship1, ship2, ship3, ship4});
    }
}