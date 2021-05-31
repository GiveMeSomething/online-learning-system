/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.blog;

import entities.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import utilities.Repository;

/**
 *
 * @author AS
 */
public class BlogRepository extends Repository {

    private final HashMap<String, Post> hmPost = new HashMap<>();
    private final HashMap<String, String> hmCat = new HashMap<>();

    public HashMap<String, Post> gethmPost() throws Exception {
        this.connectDatabase();
        String sql = "select * from post";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmPost.put(rs.getString(1), new Post(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10)));
            }
        }
        return hmPost;
    }

    public HashMap<String, Post> getLatestPost() throws Exception {
        this.connectDatabase();
        String sql = "select * from post order by updated_date desc limit 3;";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmPost.put(rs.getString(1), new Post(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10)));
            }
        }
        return hmPost;
    }

    public HashMap<String, String> gethmCat() throws Exception {
        this.connectDatabase();
        String sql = "select * from category";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmCat.put(rs.getString(1), rs.getString(2));
            }
        }
        return hmCat;
    }

    public static void main(String[] args) throws Exception {
        BlogRepository blog = new BlogRepository();
        HashMap<String, Post> hmCat = (HashMap<String, Post>) blog.getLatestPost();
        for (String key : blog.getLatestPost().keySet()) {
            System.out.println(blog.gethmPost().get(key).toString());
        }
    }
}
