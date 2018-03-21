package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import java.util.List;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImpl implements AssignmentManager {
    @Override
    public void createAssignment(Assignment assignment) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Assignment getAssignmentById(Long id) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Assignment> findAllAssignments() throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAssignment(Assignment assignment) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAssignment(Assignment assignment) throws ServiceFailureException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Assignment> findAssignmentByShip(Ship ship) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Assignment> findAssignmentByCrewman(Crewman crewman) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }
}
