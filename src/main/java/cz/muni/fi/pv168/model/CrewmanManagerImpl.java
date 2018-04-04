package cz.muni.fi.pv168.model;

import cz.muni.fi.pv168.common.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Polovka
 */
public class CrewmanManagerImpl implements CrewmanManager {
    private DataSource dataSource;

    @SuppressWarnings("WeakerAccess")
    public CrewmanManagerImpl() {
    }

    @SuppressWarnings("WeakerAccess")
    public CrewmanManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException {
        if (crewman == null){
            throw new IllegalEntityException("Provided crewman is null");
        }
        //TODO: Add db check for unique ID

        validate(crewman);
        if (crewman.getId() != null) throw new IllegalEntityException("body id is already set");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "INSERT INTO Crewman (id, name,rank) VALUES (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, crewman.getId());
            st.setString(2, crewman.getName());

            // This is the proper way, how to handle LocalDate, however it is not
            // supported by Derby yet - see https://issues.apache.org/jira/browse/DERBY-6445
            //st.setObject(3, body.getBorn());
            //st.setObject(4, body.getDied());

            st.setString(3, crewman.getRank().name());

            st.executeUpdate();
            crewman.setId(DBUtils.getId(st.getGeneratedKeys()));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting crewman into db", ex);
        }
    }

    @Override
    public Crewman getCrewman(Long id) throws ServiceFailureException {

        if (id == null) throw new IllegalArgumentException("id is null");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT id, name, rank FROM Crewman WHERE id = ?")) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rowToCrewman(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when getting crewman with id = " + id + " from DB", ex);
        }
    }

    @Override
    public void updateCrewman(Crewman crewman) throws ServiceFailureException, ValidationException, IllegalEntityException {

        validate(crewman);

        if (crewman.getId() == null) throw new IllegalEntityException("crewman id is null");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement("UPDATE Crewman SET name = ?, rank = ? WHERE id = ?")) {
            st.setString(1, crewman.getName());
            st.setString(2, crewman.getRank().name());

            int count = st.executeUpdate();
            if (count != 1) throw new IllegalEntityException("updated " + count + " crewman records instead of 1");
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when updating crewman in the db", ex);
        }
    }

    @Override
    public void deleteCrewman(Crewman crewman) throws ServiceFailureException, IllegalEntityException {
        if (crewman == null) throw new IllegalArgumentException("crewman is null");
        if (crewman.getId() == null) throw new IllegalEntityException("crewman id is null");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM Crewman WHERE id = ?")) {
            st.setLong(1, crewman.getId());
            int count = st.executeUpdate();
            if (count != 1) throw new IllegalEntityException("deleted " + count + " instead of 1 crewman");
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when deleting crewman from the db", ex);
        }
    }

    @Override
    public List<Crewman> findAllCrewmen() throws ServiceFailureException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT id, name, rank FROM Crewman")) {
            return executeQueryForMultipleCrewmen(st);
        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when getting all crewmen from DB", ex);
        }
    }

    static List<Crewman> executeQueryForMultipleCrewmen(PreparedStatement st) throws SQLException {
        try (ResultSet rs = st.executeQuery()) {
            List<Crewman> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rowToCrewman(rs));
            }
            return result;
        }
    }

    static private Crewman rowToCrewman(ResultSet rs) throws SQLException , IllegalArgumentException{
        Crewman crewman = new Crewman();
        crewman.setId(rs.getLong("id"));
        crewman.setName(rs.getString("name"));
        crewman.setRank(Rank.valueOf(rs.getString("rank")));
        return crewman;
    }

    private void validate(Crewman crewman) {
        if (crewman == null) {
            throw new IllegalArgumentException("crewman is null");
        }
        if (crewman.getId() == null) {
            throw new ValidationException("id is null");
        }
        if (crewman.getName() == null) {
            throw new ValidationException("name is null");
        }
        if (crewman.getRank() == null) {
            throw new ValidationException("rank is null");
        }
    }
}
