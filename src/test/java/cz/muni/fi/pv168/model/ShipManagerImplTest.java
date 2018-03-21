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

    @Test
    public void getShip() {
    }

    @Test
    public void updateCrewman() {
    }

    @Test
    public void deleteCrewman() {
    }

    @Test
    public void findAllShips() {
    }
}