package cz.muni.fi.pv168.model;

import java.util.List;

/**
 * @author Katarina Bulkova
 */
public interface AssignmentManager {

    void createAssignment(Assignment assignment);

    Assignment getAssignmentById(Long id);

    List<Assignment> findAllAssignments();

    void updateAssignment(Assignment assignment);

    void deleteAssignment(Assignment assignment);

    List<Assignment> findAssignmentByShip(Ship ship);

    List<Assignment> findAssignmentByCrewman(Crewman crewman);
}
