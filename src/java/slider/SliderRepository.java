/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import common.entities.Slider;
import common.entities.Status;
import common.utilities.Repository;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SliderRepository extends Repository {

    public List<Slider> getSlidersList(String keyword, Status status) throws SQLException {
        this.connectDatabase();
        String condition = "where 1=1 ";

        if (keyword != null && !keyword.equals("")) {
            keyword = "%" + keyword + "%";
            condition += "AND title LIKE '" + keyword + "' ";
        }

        if (status != null) {
            condition += "AND status_id = " + Status.valueOf(status) + " ";

        }

        String getSliderList = "SELECT * FROM db_ite1.slider " + condition;

        List<Slider> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getSliderList)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Slider(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("title"),
                        result.getString("backlink"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("notes")
                ));

            }
            return list;
        }
    }

    public List<Slider> displaySliders() throws SQLException {
        this.connectDatabase();

        String displaySliders = "SELECT * FROM db_ite1.slider where status_id = 1";

        List<Slider> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(displaySliders)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Slider(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("title"),
                        result.getString("backlink"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("notes")
                ));

            }
            return list;
        }
    }

    public boolean deleteSliderById(int sliderId) throws SQLException {
        this.connectDatabase();
        String deleteSliderById = "DELETE FROM db_ite1.slider "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(deleteSliderById)) {
            statement.setInt(1, sliderId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addNewSlider(Slider slider, InputStream inputStream) throws SQLException {
        this.connectDatabase();

        String addNewSubject = "INSERT INTO db_ite1.slider(image, title, status_id, notes) "
                + "VALUES(?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addNewSubject)) {

            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
            }
            statement.setString(2, slider.getTitle());
            statement.setInt(3, Status.valueOf(slider.getStatus()));
            statement.setString(4, slider.getNote());

            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public static void main(String[] args) throws Exception {

        try {
            SliderRepository repo = new SliderRepository();
            List<Slider> list = repo.displaySliders();
            for (Slider slider : list) {
                System.out.println(slider);
            }
        } catch (Exception e) {
        }
    }

}
