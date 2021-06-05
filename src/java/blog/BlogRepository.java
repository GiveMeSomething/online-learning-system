/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import database.DBContext;
import entities.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public HashMap<String, Post> getHmPost() throws SQLException {
        this.connectDatabase();

        String getPosts = "SELECT * FROM post";
        try (PreparedStatement statement = this.connection.prepareStatement(getPosts)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hmPost.put(result.getString(1), new Post(result.getString("id"), result.getString("thumbnail"),
                        result.getString("category_id"), result.getString("title"),
                        result.getString("brief_info"), result.getString("description"),
                        result.getString("feature"), result.getString("status_id"),
                        result.getString("author_id"), result.getString("updated_date")));
            }

            return hmPost;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, String> getUser() throws SQLException {
        this.connectDatabase();

        String getUser = "SELECT id, full_name FROM user";
        try (PreparedStatement statement = this.connection.prepareStatement(getUser)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hmUser.put(result.getString("id"), result.getString("full_name"));
            }

            return hmUser;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, Post> getLatestPost() throws SQLException {
        this.connectDatabase();

        String getLatestPosts = "SELECT * FROM post ORDER BY updated_date desc LIMIT 3";
        try (PreparedStatement statement = this.connection.prepareStatement(getLatestPosts)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hmLatestPost.put(result.getString(1), new Post(result.getString("id"), result.getString("thumbnail"),
                        result.getString("category_id"), result.getString("title"),
                        result.getString("brief_info"), result.getString("description"),
                        result.getString("feature"), result.getString("status_id"),
                        result.getString("author_id"), result.getString("updated_date")));
            }

            return hmLatestPost;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<String, String> getHmCat() throws SQLException {
        this.connectDatabase();

        String getHmCategory = "SELECT * FROM category";
        try (PreparedStatement statement = this.connection.prepareStatement(getHmCategory)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hmCat.put(result.getString("id"), result.getString("category_name"));
            }

            return hmCat;
        } finally {
            this.disconnectDatabase();
        }

    }

    public Post getPostDetail(String id) throws SQLException {
        this.connectDatabase();

        String getPost = "SELECT * FROM post WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPost)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Post(result.getString("id"), result.getString("thumbnail"),
                        result.getString("category_id"), result.getString("title"),
                        result.getString("brief_info"), result.getString("description"),
                        result.getString("feature"), result.getString("status_id"),
                        hmUser.get(result.getString("author_id")),
                        result.getString("updated_date"));
            }

            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public int getTotalPosts() throws SQLException {
        this.connectDatabase();

        String getTotalPosts = "SELECT count(*) AS total FROM post";
        try (PreparedStatement statment = this.connection.prepareStatement(getTotalPosts)) {
            ResultSet result = statment.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }

            return 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Post> getPostsList(int currentPage, int postsPerPage) throws SQLException {
        this.connectDatabase();

        ArrayList<Post> posts = new ArrayList<>();
        String getPostsList = "SELECT * FROM post LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setInt(1, (currentPage * postsPerPage) - postsPerPage);
            statement.setInt(2, postsPerPage);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                posts.add(new Post(result.getString("id"), result.getString("thumbnail"),
                        result.getString("category_id"), result.getString("title"),
                        result.getString("brief_info"), result.getString("description"),
                        result.getString("feature"), result.getString("status_id"),
                        result.getString("author_id"), result.getString("updated_date")));
            }

            return posts;
        } finally {
            this.disconnectDatabase();
        }
    }

    public int getTotalPostsByCate(int id) throws SQLException {
        this.connectDatabase();

        String getTotalPostsByCate = "SELECT count(*) AS total FROM post WHERE category_id = ?";
        try (PreparedStatement statment = this.connection.prepareStatement(getTotalPostsByCate)) {
            statment.setInt(1, id);
            ResultSet result = statment.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }

            return 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Post> getPostsByCate(int cateId, int currentPage, int postsPerPage) throws SQLException {
        this.connectDatabase();

        ArrayList<Post> posts = new ArrayList<>();
        String getPostsList = "SELECT * FROM post WHERE category_id = ? LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setInt(1, cateId);
            statement.setInt(2, (currentPage * postsPerPage) - postsPerPage);
            statement.setInt(3, postsPerPage);
            
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                posts.add(new Post(result.getString("id"), result.getString("thumbnail"),
                        result.getString("category_id"), result.getString("title"),
                        result.getString("brief_info"), result.getString("description"),
                        result.getString("feature"), result.getString("status_id"),
                        result.getString("author_id"), result.getString("updated_date")));
            }

            return posts;
        } finally {
            this.disconnectDatabase();
        }
    }

    // Test
    public static void main(String[] args) throws Exception {
        BlogRepository blog = new BlogRepository();
        HashMap<String, Post> hmCat = (HashMap<String, Post>) blog.getLatestPost();
        for (String key : blog.getHmPost().keySet()) {
            System.out.println(blog.getHmPost().get(key));
        }
        ArrayList<Post> posts = blog.getPostsByCate(1, 1, 5);
        for (Post post : posts) {
            System.out.println(post);
        }
        System.out.println(blog.getTotalPostsByCate(1));
    }
}
