package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.StarDateUtils;
import cz.muni.fi.pv168.common.ValidationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michal Polovka
 */
public class AssignmentManagerImpl implements AssignmentManager {
    private DataSource dataSource;

    @SuppressWarnings("WeakerAccess")
    public AssignmentManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

        if (assignment == null) throw new IllegalArgumentException("assignment is null");
        if (assignment.getId() < 0) throw new IllegalEntityException("assignment id is not valid");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "DELETE FROM Assignment WHERE id = ?")) {
            st.setLong(1, assignment.getId());
            int count = st.executeUpdate();
            if (count != 1) throw new IllegalEntityException("updated " + count + " instead of 1 assignment");
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when deleting assignment from the db", ex);
        }
    }

    private List<Assignment> findAssignmentByShip(Ship ship) throws ServiceFailureException {

        if (ship == null) throw new IllegalArgumentException("ship is null");
        if (ship.getId() == null) throw new IllegalEntityException("ship id is null");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT Assignment.id, ship, crewman, starDate, endDate " +
                             "FROM Assignment JOIN Ship ON Assignment.ship.Id = Ship.Id" +
                             "WHERE Assignment.ship = ?")) {
            st.setLong(1, ship.getId());
            return AssignmentManagerImpl.executeQueryForMultipleAssignments(st);
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when trying to find assignment with ship " + ship, ex);
        }
    }

    private List<Assignment> findAssignmentByCrewman(Crewman crewman) throws ServiceFailureException {

        if (crewman == null) throw new IllegalArgumentException("crewman is null");
        if (crewman.getId() == null) throw new IllegalEntityException("crewman id is null");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT Body.id, name, gender, born, died, vampire " +
                             "FROM Body JOIN Assignment ON Assignment.ship = Crewman.graveId " +
                             "WHERE Grave.id = ?")) {
            st.setLong(1, crewman.getId());
            return AssignmentManagerImpl.executeQueryForMultipleAssignments(st);
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when trying to find assignments by crewman " + crewman,
                    ex);
        }
    }

    static List<Assignment> executeQueryForMultipleAssignments(PreparedStatement st) throws SQLException {
        try (ResultSet rs = st.executeQuery()) {
            List<Assignment> result = new ArrayList<>();
            while (rs.next()) {
//                result.add(rowToAssignment(rs));
            }
            return result;
        }
    }


    static private Assignment rowToAssignment(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        long shipId = rs.getLong("Ship");
        long crewmanID = rs.getLong("Crewman");
        LocalDate startDate = rs.getDate("StartDate").toLocalDate();
        LocalDate endDate = rs.getDate("EndDate").toLocalDate();
        return new Assignment(id, shipId, crewmanID, new StarDateUtils(StarDateUtils.dateToStarDate(startDate)),
                new StarDateUtils(StarDateUtils.dateToStarDate(endDate)));
    }

    private void validate(Assignment assignment) {
        if (assignment == null) {
            throw new IllegalArgumentException("assignment is null.");
        }
        if (assignment.getShipId() < 0) {
            throw new ValidationException("Ship ID is invalid.");
        }
        if (assignment.getStartDate() == null) {
            throw new ValidationException("Start date is null.");
        }
        if (assignment.getEndDate() == null) {
            throw new ValidationException("Ende date is null.");
        }
    }

    @Override
    public List<Assignment> findAssignmentByShip(long shipId) throws ServiceFailureException {
        Ship ship = new Ship();
        ship.setId(shipId);
        return findAssignmentByShip(ship);
    }

    @Override
    public List<Assignment> findAssignmentByCrewman(long crewmanId) throws ServiceFailureException {
        Crewman crewman = new Crewman();
        crewman.setId(crewmanId);
        return findAssignmentByCrewman(crewman);
    }
}
