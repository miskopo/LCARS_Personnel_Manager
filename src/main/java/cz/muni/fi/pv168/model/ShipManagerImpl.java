package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ValidationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Michal Polovka
 */
public class ShipManagerImpl implements ShipManager {

    private DataSource dataSource;

    @SuppressWarnings("WeakerAccess")
    public ShipManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("WeakerAccess")
    public ShipManagerImpl() {
    }

    @Override
    public void createShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        validate(ship);
        if (ship.getId() != null) throw new IllegalEntityException("Ship id is already set");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO Ship (name, designation,type,warp) VALUES (?,?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, ship.getName());
            st.setString(2, ship.getDesignation());
            st.setString(3, ship.getType().name());
            st.setString(4, Double.toString(ship.getWarpCapabilities()));

            st.executeUpdate();
            ship.setId(DBUtils.getId(st.getGeneratedKeys()));

        } catch (SQLException e) {
            throw new ServiceFailureException("Error inserting ship into db", e);
        }
    }

    @Override
    public Ship getShip(Long id) throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteShip(Ship ship) throws ServiceFailureException, IllegalEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Ship> findAllShips() throws ServiceFailureException {
        throw new UnsupportedOperationException();
    }

    private void validate(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException("Ship is null");
        }
        if (ship.getName() == null) {
            throw new ValidationException("Ship name is null");
        }
        if (ship.getDesignation() == null) {
            throw new ValidationException("Ship designation is null");
        }
        if (ship.getType() == null) {
            throw new ValidationException("Ship type is null");
        }
        if (ship.getId() == null) {
            throw new ValidationException("Ship id is null");
        }
    }
}
