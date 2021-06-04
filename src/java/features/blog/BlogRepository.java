/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.blog;

import database.DBContext;
import entities.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import utilities.Repository;

/**
 *
 * @author AS
 */
public class BlogRepository extends Repository {

    private final HashMap<String, Post> hmPost = new HashMap<>();
    private final HashMap<String, String> hmCat = new HashMap<>();
    private final HashMap<String, Post> hmLatestPost = new HashMap<>();
    private final HashMap<String, String> hmUser = new HashMap<>();

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
            return hmPost;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, String> getUser() throws Exception {
        this.connectDatabase();
        String sql = "select * from user";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmUser.put(rs.getString(1), rs.getString(3));
            }
            return hmUser;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, Post> getLatestPost() throws Exception {
        this.connectDatabase();

        String sql = "select * from post order by updated_date desc limit 3";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmLatestPost.put(rs.getString(1), new Post(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10)));
            }
            return hmLatestPost;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, String> gethmCat() throws Exception {
        this.connectDatabase();
        String sql = "select * from category";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hmCat.put(rs.getString(1), rs.getString(2));
            }
            return hmCat;
        } finally {
            this.disconnectDatabase();
        }

    }

    public Post getPostDetail(String id) throws Exception {
        this.connectDatabase();
        String sql = "select * from post where id = ?";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Post(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        hmUser.get(rs.getString(9)), rs.getString(10));

            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public int getNumberOfPosts() throws Exception {
        this.connectDatabase();
        String getNumOfPosts = "select count(*) from post";
        try (PreparedStatement ps = new DBContext().getConnection().prepareStatement(getNumOfPosts)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Post> getPost(int curPage, int postsPerPage) throws Exception {
        this.connectDatabase();
        ArrayList<Post> posts = new ArrayList<>();
        String getPagination = "SELECT * FROM post limit ?,?";
        try (PreparedStatement ps = new database.DBContext().getConnection().prepareStatement(getPagination)) {
            ps.setInt(1, (curPage * 4) - 4);
            ps.setInt(2, 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10)));
            }
            return posts;
        } finally {
            this.disconnectDatabase();
        }
    }

    public static void main(String[] args) throws Exception {
        BlogRepository blog = new BlogRepository();
        HashMap<String, Post> hmCat = (HashMap<String, Post>) blog.getLatestPost();
//        for (String key : blog.getPost(1, 4).keySet()) {
//            System.out.println(blog.getPost(1, 4).get(key));
//        }
        System.out.println(blog.getNumberOfPosts());
    }
}
