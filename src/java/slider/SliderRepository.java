/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */

package slider;

import common.entities.Slider;
import common.entities.Status;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SliderRepository extends Repository {
    public Slider getSliderDetail(int sliderId) throws SQLException {
        this.connectDatabase();
        String getSliderDetail = "SELECT * FROM db_ite1.slider WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getSliderDetail)) {
            statement.setInt(1, sliderId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Slider(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("title"),
                        result.getString("backlink"),
                        result.getInt("status_id") == 1 ? Status.ACTIVE : Status.INACTIVE,
                        result.getString("notes")
                );
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public boolean updateSliderDetail(String image, String title, int status_id, String notes, int sliderId) throws SQLException {
        this.connectDatabase();
        String updateSliderDetail = "UPDATE db_ite1.slider SET "
                + "image = ?, "
                + "title = ?, "
                + "status_id = ?, "
                + "notes = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateSliderDetail)) {
            statement.setString(1, image);
            statement.setString(2, title);
            statement.setInt(3, status_id);
            statement.setString(4, notes);
            statement.setInt(5, sliderId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public static void main(String[] args) throws Exception {
        SliderRepository repo = new SliderRepository();
        try {
            Slider slider = repo.getSliderDetail(1);
            System.out.println(slider);
        } catch (Exception e) {
        }
    }
}
