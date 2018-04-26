package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Katarina Bulkova
 */
public interface ShipManager {

    /**
     * Stores new ship into the database. Id for the ship is automatically
     * generated and stored into id attribute.
     *
     * @param ship new ship to be created
     * @throws IllegalArgumentException when ship is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when ship breaks validation rules
     * (name, designation, type or warpCapability is null)
     * @throws IllegalEntityException when ship has already assigned id
     */
    void createShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Returns ship according to given id
     *
     * @param id primary key of requested ship
     * @return ship with given id or null if id doesnt match any ship
     * @throws IllegalArgumentException when given id is null
     * @throws ServiceFailureException when db operation fails
     */
    Ship getShip(Long id) throws ServiceFailureException;

    /**
     * Updates ship in the database.
     *
     * @param ship updated ship to be stored into the database
     * @throws IllegalArgumentException when ship is null
     * @throws ServiceFailureException when db operation fails
     * @throws ValidationException when ship breaks validation rules
     * (name, designation, type or warpCapability is null)
     * @throws IllegalEntityException when ship has null id or does not exist in the databse
     */
    void updateShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException;

    /**
     * Deletes ship from the database
     *
     * @param ship to be deleted from db
     * @throws IllegalArgumentException when ship is null
     * @throws ServiceFailureException when db operation fails
     * @throws IllegalEntityException when ship has null id or does not exist in the database
     */
    void deleteShip(Ship ship) throws ServiceFailureException, IllegalEntityException;

    /**
     * Returns list of all ships in the database
     *
     * @return list of all ships in the database
     * @throws ServiceFailureException when db operation fails
     */
    List<Ship> findAllShips() throws ServiceFailureException;


    public void deleteShipByID(long id);
}
