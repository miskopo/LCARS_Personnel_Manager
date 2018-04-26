package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.common.IllegalEntityException;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ShipType;
import cz.muni.fi.pv168.common.ValidationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public void createShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        validate(ship);
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
        if (id <= 0) throw new IllegalArgumentException("ID is invalid.");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT id, name, designation, type, warp FROM " +
                     "SHIP " +
                     "WHERE id = ?")) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                return (rs.next()) ? rowToShip(rs) : null;
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when getting ship with id = " + id + " from DB.", ex);
        }
    }

    @Override
    public void updateShip(Ship ship) throws ServiceFailureException, ValidationException, IllegalEntityException {
        validate(ship);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "UPDATE Ship SET Name = ?, Designation  = ?, Type = ?, Warp = ? WHERE ID = ?")) {
            st.setString(1, ship.getName());
            st.setString(2, ship.getDesignation());
            st.setString(3, ship.getType().name());
            st.setDouble(4, ship.getWarpCapabilities());
            st.setLong(5, ship.getId());

            st.executeUpdate();
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting assignment to db.", ex);
        }
    }

    @Override
    public void deleteShip(Ship ship) throws ServiceFailureException, IllegalEntityException {
        if (ship == null) throw new IllegalArgumentException("Ship is null.");
        if (ship.getId() <= 0) throw new IllegalEntityException("Ship id is invalid.");        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "DELETE FROM Ship WHERE id = ?")) {
            st.setLong(1, ship.getId());
            int count = st.executeUpdate();
            if (count != 1) throw new IllegalEntityException("Updated " + count + " instead of 1 assignment");
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when deleting ship from DB.", ex);
        }
    }

    @Override
    public void deleteShipByID(long id) {
        Ship ship = new Ship();
        ship.setId(id);
        deleteShip(ship);
    }

    @Override
    public List<Ship> findAllShips() throws ServiceFailureException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT * FROM Ship")) {
            return ShipManagerImpl.executeQueryForMultipleShips(st);
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when getting ships from DB.", ex);
        }    }

    private void validate(Ship ship) {
        if (ship == null) {
            throw new IllegalEntityException("Ship is null");
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
        if (ship.getId() <= 0) {
            throw new ValidationException("Ship id is invalid.");
        }
    }

    static private Ship rowToShip(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String designation = rs.getString("designation");
        String type = rs.getString("type");
        double warp = rs.getDouble("warp");
        return new Ship(id, name, designation, ShipType.valueOf(type), warp);
    }

    private static List<Ship> executeQueryForMultipleShips(PreparedStatement st) throws SQLException {
        try (ResultSet rs = st.executeQuery()) {
            List<Ship> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rowToShip(rs));
            }
            return result;
        }
    }
}
