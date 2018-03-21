package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class ShipManagerImplTest {
    Ship ship1 = new Ship(1L, "Enterprise", "NYC-72", "Nebula", 9.7);
    Ship ship2 = new Ship(2L, "Voyager", "NYC-94", "Warship", 8.4);
    Ship ship3 = new Ship(3L, "Pegasus", "NYC-12", "Science", 6.5);
    Ship ship4 = new Ship(4L, "Discovery", "NYC-42", "Science", 9.2);
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
        assert shipManager.getShip(1L).getName().equals("Enterprise");
        assert shipManager.getShip(1L).getDesignation().equals("NYC-72");
        assert shipManager.getShip(1L).getType().equals("Nebula");
        assert shipManager.getShip(1L).getWarpCapabilities() == 9.7;

        assert shipManager.getShip(2L).getName().equals("Voyager");
        assert shipManager.getShip(2L).getDesignation().equals("NYC-94");
        assert shipManager.getShip(2L).getType().equals("Warship");
        assert shipManager.getShip(2L).getWarpCapabilities() == 8.4;

        assert shipManager.getShip(3L).getName().equals("Pegasus");
        assert shipManager.getShip(3L).getDesignation().equals("NYC-12");
        assert shipManager.getShip(3L).getType().equals("Science");
        assert shipManager.getShip(3L).getWarpCapabilities() == 6.5;

        assert shipManager.getShip(4L).getName().equals("Discovery");
        assert shipManager.getShip(4L).getDesignation().equals("NYC-42");
        assert shipManager.getShip(4L).getType().equals("Science");
        assert shipManager.getShip(4L).getWarpCapabilities() == 9.2;
    }

    @Test(expected = IllegalEntityException.class)
    public void updateEmptyShip() {
        shipManager.updateShip(null);
    }

    @Test
    public void updateShip() {
        ship1.setDesignation("EnterpriseModified");
        shipManager.updateShip(ship1);
        assert shipManager.getShip(ship1.getId()).getName().equals("EnterpriseModified");
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