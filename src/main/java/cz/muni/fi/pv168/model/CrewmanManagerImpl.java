package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Michal Polovka
 */
public class CrewmanManagerImpl implements CrewmanManager {
    @Override
    public void createCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException {
        if (crewman == null){
            throw new IllegalEntityException("Provided crewman is null");
        }
        //TODO: Add db check for unique ID
        throw new UnsupportedOperationException();
    }

    @Override
    public Crewman getCrewman(Long id) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteCrewman(Crewman crewman) throws ServiceFailureException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Crewman> findAllCrewmen() throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }
}
