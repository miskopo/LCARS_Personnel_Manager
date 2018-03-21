package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.Rank;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Michal Polovka
 */
public class CrewmanManagerImplTest {
    CrewmanManager crewmanManager = new CrewmanManagerImpl();
    Crewman crewman1 = new Crewman(1L, "Arthur Dent", Rank.CAPTAIN);
    Crewman crewman2 = new Crewman(2L, "Anakin Skywalker", Rank.COMMANDER);
    Crewman crewman3 = new Crewman(3L, "Doctor Who", Rank.LIEUTENANT);
    Crewman crewman4 = new Crewman(4L, "Gaius Julius Caesar", Rank.ENSIGN);

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
        assertTrue(crewmanManager.getCrewman(1L).equals(crewman1));
        assertTrue(crewmanManager.getCrewman(2L).equals(crewman2));
        assertTrue(crewmanManager.getCrewman(3L).equals(crewman3));
        assertTrue(crewmanManager.getCrewman(4L).equals(crewman4));
        assertFalse(crewmanManager.getCrewman(4L).equals(crewman1));
    }

    @Test
    public void updateCrewman() {
        crewman1.setName("Arthur Dent_modified");
        crewmanManager.updateCrewman(crewman1);
        assertTrue(crewmanManager.getCrewman(1L).getName().equals("Arthur Dent_modified"));
        crewman1.setName("Arthur Dent");
    }

    @Test
    public void deleteCrewman() {
    }

    @Test
    public void findAllCrewmen() {
        assertArrayEquals(crewmanManager.findAllCrewmen().toArray(), new Crewman[]{crewman1, crewman2, crewman3, crewman4});

    }
}