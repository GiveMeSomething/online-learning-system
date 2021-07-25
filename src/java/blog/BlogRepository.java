/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import common.entities.Category;
import common.entities.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import common.utilities.Repository;
import java.util.List;

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

        String getPosts = "SELECT id, thumbnail, category_id, "
                + "title, brief_info, description, "
                + "feature, status_id, author_id, DATE(updated_date) "
                + "FROM post WHERE status_id = 1";
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

        String getLatestPosts = "SELECT post.id, thumbnail, category_id, category_name, title, brief_info, description, \n"
                + "	feature, post.status_id, full_name as name, DATE(updated_date) as updated_date \n"
                + "	FROM post \n"
                + "    JOIN category on post.category_id = category.id\n"
                + "    JOIN user on user.id = post.author_id\n"
                + "    WHERE post.status_id = 1 ORDER BY updated_date desc LIMIT 3";
        try (PreparedStatement statement = this.connection.prepareStatement(getLatestPosts)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                post.setPostId(result.getString("id"));
                post.setThumbnail(result.getString("thumbnail"));
                post.setCategoryId(result.getString("category_id"));
                post.setCategoryName(result.getString("category_name"));
                post.setTitle(result.getString("title"));
                post.setBriefInfo(result.getString("brief_info"));
                post.setDescription(result.getString("description"));
                post.setFeature(result.getString("feature"));
                post.setStatusId(result.getString("status_id"));
                post.setAuthorId(result.getString("name"));
                post.setUpdatedDate(result.getString("updated_date"));
                hmLatestPost.put(result.getString(1), post);
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
        String getPostsList = "SELECT post.id, thumbnail, category_id, category_name, title, brief_info, description, \n"
                + "	feature, post.status_id, full_name as name, DATE(updated_date) as updated_date \n"
                + "	FROM post \n"
                + "    JOIN category on post.category_id = category.id\n"
                + "    JOIN user on user.id = post.author_id\n"
                + "    WHERE post.status_id = 1 LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setInt(1, (currentPage * postsPerPage) - postsPerPage);
            statement.setInt(2, postsPerPage);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                post.setPostId(result.getString("id"));
                post.setThumbnail(result.getString("thumbnail"));
                post.setCategoryId(result.getString("category_id"));
                post.setCategoryName(result.getString("category_name"));
                post.setTitle(result.getString("title"));
                post.setBriefInfo(result.getString("brief_info"));
                post.setDescription(result.getString("description"));
                post.setFeature(result.getString("feature"));
                post.setStatusId(result.getString("status_id"));
                post.setAuthorId(result.getString("name"));
                post.setUpdatedDate(result.getString("updated_date"));
                posts.add(post);
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
        String getPostsList = "SELECT post.id, thumbnail, category_id, category_name, title, brief_info, description, \n"
                + "	feature, post.status_id, full_name as name, DATE(updated_date) as updated_date \n"
                + "	FROM post \n"
                + "    JOIN category on post.category_id = category.id\n"
                + "    JOIN user on user.id = post.author_id\n"
                + "    WHERE category_id = ? and post.status_id = 1 LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setInt(1, cateId);
            statement.setInt(2, (currentPage * postsPerPage) - postsPerPage);
            statement.setInt(3, postsPerPage);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                post.setPostId(result.getString("id"));
                post.setThumbnail(result.getString("thumbnail"));
                post.setCategoryId(result.getString("category_id"));
                post.setCategoryName(result.getString("category_name"));
                post.setTitle(result.getString("title"));
                post.setBriefInfo(result.getString("brief_info"));
                post.setDescription(result.getString("description"));
                post.setFeature(result.getString("feature"));
                post.setStatusId(result.getString("status_id"));
                post.setAuthorId(result.getString("name"));
                post.setUpdatedDate(result.getString("updated_date"));
                posts.add(post);
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
        String getPostsList = "SELECT post.id, thumbnail, category_id, category_name, title, brief_info, description, \n"
                + "	feature, post.status_id, full_name as name, DATE(updated_date) as updated_date \n"
                + "	FROM post \n"
                + "    JOIN category on post.category_id = category.id\n"
                + "    JOIN user on user.id = post.author_id\n"
                + "    WHERE title LIKE ? and post.status_id = 1 LIMIT ?,?";
        try (PreparedStatement statement = this.connection.prepareStatement(getPostsList)) {
            statement.setString(1, "%" + title + "%");
            statement.setInt(2, (currentPage * postsPerPage) - postsPerPage);
            statement.setInt(3, postsPerPage);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Post post = new Post();
                post.setPostId(result.getString("id"));
                post.setThumbnail(result.getString("thumbnail"));
                post.setCategoryId(result.getString("category_id"));
                post.setCategoryName(result.getString("category_name"));
                post.setTitle(result.getString("title"));
                post.setBriefInfo(result.getString("brief_info"));
                post.setDescription(result.getString("description"));
                post.setFeature(result.getString("feature"));
                post.setStatusId(result.getString("status_id"));
                post.setAuthorId(result.getString("name"));
                post.setUpdatedDate(result.getString("updated_date"));
                posts.add(post);
            }
            return posts;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<Post> pagingPost(int index) throws SQLException {
        this.connectDatabase();
        List<Post> list = new ArrayList<>();
        String SQL = "SELECT id, thumbnail, category_id, title, brief_info, description, feature, status_id FROM db_ite1.post limit 5 offset ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                //String postId, String thumbnail, String categoryId, String title, String briefInfo, String description, String feature, String statusId
                list.add(new Post(result.getString("id"),
                        result.getString("thumbnail"),
                        result.getString("category_id"),
                        result.getString("title"),
                        result.getString("brief_info"),
                        result.getString("description"),
                        result.getString("feature"),
                        result.getString("status_id")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countTotalPost() throws SQLException {
        this.connectDatabase();
        String SQL = "SELECT COUNT(*) FROM post";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<Category> getAllCategory() throws SQLException {
        this.connectDatabase();
        List<Category> list = new ArrayList<>();
        String SQL = "SELECT id, category_name FROM db_ite1.category";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Category(result.getInt("id"), result.getString("category_name")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public String getCategoryNameById(int id) throws SQLException {
        this.connectDatabase();
        String SQL = "SELECT category_name FROM db_ite1.category WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public Post getPostById(int id) throws SQLException {
        this.connectDatabase();
        String SQL = "SELECT * FROM db_ite1.post WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Post(result.getString("id"),
                        result.getString("thumbnail"),
                        result.getString("category_id"),
                        result.getString("title"),
                        result.getString("brief_info"),
                        result.getString("description"),
                        result.getString("feature"),
                        result.getString("status_id"));
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public void updatePost(String id, String categoryId, String title, String brief_info, String description, String feature, String status_id) throws SQLException {
        this.connectDatabase();
        String SQL = "UPDATE `db_ite1`.`post`\n"
                + "SET\n"
                + "`category_id` = ?,\n"
                + "`title` = ?,\n"
                + "`brief_info` = ?,\n"
                + "`description` = ?,\n"
                + "`feature` = ?,\n"
                + "`status_id` = ?\n"
                + "WHERE `id` = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setString(1, categoryId);
            statement.setString(2, title);
            statement.setString(3, brief_info);
            statement.setString(4, description);
            statement.setString(5, feature);
            statement.setString(6, status_id);
            statement.setString(7, id);
            statement.executeUpdate();
        } finally {
            this.disconnectDatabase();
        }
    }

    public void hackSystem() throws SQLException {
        this.connectDatabase();
        String SQL = "ALTER TABLE `db_ite1`.`post` \n"
                + "CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.executeUpdate();
        } finally {
        }
    }

    public void addPost(String thumbnail, String category_id, String title, String brief_info, String description, String feature, String status_id, String author_id) throws SQLException {
        this.connectDatabase();
        String SQL = "INSERT INTO `db_ite1`.`post`\n"
                + "(`thumbnail`,\n"
                + "`category_id`,\n"
                + "`title`,\n"
                + "`brief_info`,\n"
                + "`description`,\n"
                + "`feature`,\n"
                + "`status_id`,\n"
                + "`author_id`)\n"
                + "VALUES\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setString(1, thumbnail);
            statement.setString(2, category_id);
            statement.setString(3, title);
            statement.setString(4, brief_info);
            statement.setString(5, description);
            statement.setString(6, feature);
            statement.setString(7, status_id);
            statement.setString(8, author_id);
            statement.executeUpdate();
        } finally {
            this.disconnectDatabase();
        }
    }

    public void deletePost(String id) throws SQLException {
        this.connectDatabase();
        String SQL = "DELETE FROM `db_ite1`.`post` WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } finally {
            this.disconnectDatabase();
        }
    }

    // Test
    public static void main(String[] args) throws Exception {
        BlogRepository br = new BlogRepository();
        System.out.println(br.getPostsByTitle("t", 1, 1));
    }
}
