package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Michal Polovka
 */
public class ShipManagerImpl implements ShipManager {
    @Override
    public void createShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Ship getShip(Long id) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCrewman(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteCrewman(Ship ship) throws ServiceFailureException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Ship> findAllShips() throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }
}
