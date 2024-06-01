package repositories;

import entities.Abonne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AbonneRepository {
    Optional<Abonne> findAbonne(int id) {
        String requestString = "SELECT * FROM Abonne WHERE id = ?";
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(requestString);
            ps.setInt(1, id);
            if (ps.execute()) {
                ResultSet set = ps.getResultSet();
                Abonne abonne = new Abonne(
                        set.getInt("id"),
                        set.getString("nom"),
                        set.getDate("date_naissance"));
                return Optional.of(abonne);
            } else return Optional.empty();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
