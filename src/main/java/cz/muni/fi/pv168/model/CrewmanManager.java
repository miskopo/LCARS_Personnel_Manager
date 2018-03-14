package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Katarina Bulkova
 */
public interface CrewmanManager {

    /**
     * Stores new crewman into the database. Id for the crewman is automatically
     * generated and stored into id attribute.
     *
     * @param crewman new crewman to be created
     * @throws IllegalArgumentException when crewman is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when crewman breaks validation rules
     * (name, currentAssignment or rank is null)
     * @throws IllegalEntityException when crewman  has already assigned id
     */
    void createCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Returns crewman according to given id
     *
     * @param id primary key of requested crewman
     * @return crewman with given id or null if id doesnt match any crewman
     * @throws IllegalArgumentException when given id is null
     * @throws ServiceFailureException when db operation fails
     */
    Crewman getCrewman(Long id) throws ServiceFailureException;

    /**
     * Updates crewman in the database.
     *
     * @param crewman updated crewman to be stored into the database
     * @throws IllegalArgumentException when body is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when crewman breaks validation rules
     * (name, currentAssignment or rank is null)
     * @throws IllegalEntityException when crewman has null id or does not exist in the databse
     */
    void updateCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Deletes crewman from the database
     *
     * @param crewman crewman to be deleted from db
     * @throws IllegalArgumentException when crewman is null
     * @throws ServiceFailureException when db operation fails
     * @throws IllegalEntityException when crewman has null id or does not exist in the database
     */
    void deleteCrewman(Crewman crewman) throws ServiceFailureException, IllegalEntityException;

    /**
     * Returns list of all crewmen in the database
     *
     * @return list of all crewmen in the database
     * @throws ServiceFailureException when db operation fails
     */
    List<Crewman> findAllCrewmen() throws ServiceFailureException;
}

