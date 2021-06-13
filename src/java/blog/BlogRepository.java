/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import common.entities.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import common.utilities.Repository;

/**
 *
 * @author AS
 */
public class BlogRepository extends Repository {

    private final HashMap<String, Post> hmPost = new HashMap<>();
    private final HashMap<String, String> hmCategory = new HashMap<>();
    private final HashMap<String, Post> hmLatestPost = new HashMap<>();
    private final HashMap<String, String> hmUser = new HashMap<>();

    // Get all posts from database
    public HashMap<String, Post> getHmPost() throws SQLException {
        this.connectDatabase();

        String getPosts = "SELECT * FROM post WHERE status_id = 1";
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

    // Get all users
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

    // Get 3 latest posts
    public HashMap<String, Post> getLatestPost() throws SQLException {
        this.connectDatabase();

        String getLatestPosts = "SELECT * FROM post WHERE status_id = 1 ORDER BY updated_date desc LIMIT 3";
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

    // Get all categories
    public HashMap<String, String> getHmCategory() throws SQLException {
        this.connectDatabase();

        String getHmCategory = "SELECT * FROM category";
        try (PreparedStatement statement = this.connection.prepareStatement(getHmCategory)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hmCategory.put(result.getString("id"), result.getString("category_name"));
            }

            return hmCategory;
        } finally {
            this.disconnectDatabase();
        }

    }

    // Get 1 post
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

    // Get total of posts
    public int getTotalPosts() throws SQLException {
        this.connectDatabase();

        String getTotalPosts = "SELECT count(*) AS total FROM post WHERE status_id = 1";
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

    // Get list of posts and limit number of posts per page
    public ArrayList<Post> getPostsList(int currentPage, int postsPerPage) throws SQLException {
        this.connectDatabase();

        ArrayList<Post> posts = new ArrayList<>();
        String getPostsList = "SELECT * FROM post WHERE status_id = 1 LIMIT ?,?";
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

    // Get total number of posts by category
    public int getTotalPostsByCategory(int id) throws SQLException {
        this.connectDatabase();

        String getTotalPostsByCategory = "SELECT count(*) AS total FROM post WHERE category_id = ? and status_id = 1";
        try (PreparedStatement statment = this.connection.prepareStatement(getTotalPostsByCategory)) {
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

    public int getTotalPostsByTitle(String title) throws SQLException {
        this.connectDatabase();

        String getTotalPostsByCategory = "SELECT count(*) AS total FROM post WHERE title LIKE ? and status_id = 1";
        try (PreparedStatement statment = this.connection.prepareStatement(getTotalPostsByCategory)) {
            statment.setString(1, "%" + title + "%");
            ResultSet result = statment.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }

            return 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    // Get post pagination by category
    public ArrayList<Post> getPostsByCategory(int cateId, int currentPage, int postsPerPage) throws SQLException {
        this.connectDatabase();

        ArrayList<Post> posts = new ArrayList<>();
        String getPostsList = "SELECT * FROM post WHERE category_id = ? and status_id = 1 LIMIT ?,?";
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

    // Get posts by searching
    public ArrayList<Post> getPostsByTitle(String title, int currentPage, int postsPerPage) throws SQLException {
        this.connectDatabase();

        ArrayList<Post> posts = new ArrayList<>();
        String getPostsList = "SELECT * FROM post WHERE title LIKE ? and status_id = 1 LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setString(1, "%" + title + "%");
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
//        HashMap<String, Post> hmCat = (HashMap<String, Post>) blog.getLatestPost();
        for (String key : blog.getHmCategory().keySet()) {
            System.out.println(blog.getHmCategory());
        }
//        ArrayList<Post> posts = blog.getPostsByTitle("tech");
//        for (Post post : posts) {
//            System.out.println(post);
//        }
//        System.out.println(blog.getTotalPostsByTitle("tec"));
    }
}
