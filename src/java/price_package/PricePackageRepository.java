/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package price_package;

import common.utilities.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PricePackageRepository extends Repository {

    public List<PricePack> pagingPricePackage(int index) throws SQLException {
        this.connectDatabase();
        List<PricePack> list = new ArrayList<>();
        String getAllPricePackage = "SELECT id, duration, name, list_price, status_id, description, discount FROM db_ite1.price_package LIMIT 5 OFFSET ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getAllPricePackage)) {
            statement.setInt(1, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new PricePack(result.getInt("id"),
                        result.getInt("duration"),
                        result.getString("name"),
                        result.getDouble("list_price"),
                        result.getInt("status_id") == 1 ? Status.ACTIVE : Status.INACTIVE,
                        result.getString("description"),
                        result.getDouble("discount")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countTotalPricePackage() throws SQLException {
        this.connectDatabase();
        String query = "SELECT COUNT(*) FROM db_ite1.price_package";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public boolean addPackage(int duration, String name, double price, int status, String descriptions, double discount) throws SQLException {
        this.connectDatabase();
        String query = "INSERT INTO `db_ite1`.`price_package` "
                + "(`duration`, `name`, `list_price`, `status_id`, `description`, `discount`) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, duration);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, status);
            statement.setString(5, descriptions);
            statement.setDouble(6, discount);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public boolean deletePackage(int id) throws SQLException {
        this.connectDatabase();
        String query = "DELETE FROM `db_ite1`.`price_package` WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public boolean editPackage(int id, int duration, String name, double price, int status, String descriptions, double discount) throws SQLException {
        this.connectDatabase();
        String query = "UPDATE `db_ite1`.`price_package` SET `duration` = ?, "
                + "`name` = ?, "
                + "`list_price` = ?, "
                + "`status_id` = ?, "
                + "`description` = ?, `discount` = ? WHERE `id` = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, duration);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, status);
            statement.setString(5, descriptions);
            statement.setDouble(6, discount);
            statement.setInt(7, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            PricePackageRepository dao = new PricePackageRepository();
            System.out.println(dao.addPackage(3, "duc long", 23.3, 0, "hello", 4));
        } catch (Exception ex) {
            Logger.getLogger(PricePackageRepository.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
