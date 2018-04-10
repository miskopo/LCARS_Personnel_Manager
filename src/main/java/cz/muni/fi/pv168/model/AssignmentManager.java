package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Katarina Bulkova
 * @author Michal Polovka
 */
public interface AssignmentManager {

    /**
     * Stores new assignment into the database.
     *
     * @param assignment assignment to be added to the database
     * @throws IllegalArgumentException when assignment is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when crewman breaks validation rules
     * (ship or crewman is null)
     * @throws IllegalEntityException ???
     */
    void createAssignment(Assignment assignment) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Returns assignment by given Id
     *
     * @param id primary key of requested assignment
     * @return assignment by given Id
     * @throws IllegalArgumentException when given id is null
     * @throws ServiceFailureException when db operation fails
     */
    Assignment getAssignmentById(Long id) throws ServiceFailureException;

    /**
     * Returns list of all assignments
     * @return list of all assignments
     * @throws ServiceFailureException when db operation fails
     */
    List<Assignment> findAllAssignments() throws ServiceFailureException;

    /**
     * Updates assignment in the database.
     *
     * @param assignment updated assignment to be stored into the database
     * @throws IllegalArgumentException when assignment is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when assignment breaks validation rules
     * (ship, crewman or startdate is null)
     * @throws IllegalEntityException when assignment does not exist in the databse
     */
    void updateAssignment(Assignment assignment) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Deletes assignment form the database
     *
     * @param assignment assignment to be deleted from the db
     * @throws IllegalArgumentException when assignment is null
     * @throws ServiceFailureException when db operation fails
     * @throws IllegalEntityException when assignment does not exist in the database
     */
    void deleteAssignment(Assignment assignment) throws ServiceFailureException, IllegalEntityException;

    /**
     * Returns list of all assigments of given ship
     * @param shipId key of requested assignment
     * @throws ServiceFailureException when db operation fails
     * @throws IllegalArgumentException when ship is null
     * @return list of all assignments of given ship
     */
    List<Assignment> findAssignmentByShip(long shipId) throws ServiceFailureException;

    /**
     * Returns list of all assignemnts of given crewman
     *
     * @param crewmanId key of requested assignment
     * @throws ServiceFailureException when db operation fails
     *@throws IllegalArgumentException when crewman is null
     * @return list of all assignemnts of gien crewman
     */
    List<Assignment> findAssignmentByCrewman(long crewmanId) throws ServiceFailureException;
}
