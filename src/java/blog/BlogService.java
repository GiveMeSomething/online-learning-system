/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import common.entities.Post;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author AS
 */
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService() {
        this.blogRepository = new BlogRepository();
    }

    public HashMap<String, String> getHmCategory() {
        try {
            return blogRepository.getHmCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Post> getHmPost() {
        try {
            return blogRepository.getHmPost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Post> getLatestPost() {
        try {
            return blogRepository.getLatestPost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPostDetail(String id) {
        try {
            return blogRepository.getPostDetail(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> getUser() {
        try {
            return blogRepository.getUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPosts() {
        try {
            return blogRepository.getTotalPosts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalPostsByCategory(int id) {
        try {
            return blogRepository.getTotalPostsByCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Post> getPostsList(int currentPage, int postsPerPage) {
        try {
            return blogRepository.getPostsList(currentPage, postsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Post> getPostsByCategory(int cateId, int currentPage, int postsPerPage) {
        try {
            return blogRepository.getPostsByCategory(cateId, currentPage, postsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Post> getPostsByTitle(String title, int currentPage, int PostsPerPage){
        try {
            return blogRepository.getPostsByTitle(title, currentPage, PostsPerPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getTotalPostsByTitle(String title){
        try {
            return blogRepository.getTotalPostsByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
