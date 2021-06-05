/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import entities.Post;
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

    public HashMap<String, String> getHmCat() {
        try {
            return blogRepository.getHmCat();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, Post> getHmPost() {
        try {
            return blogRepository.getHmPost();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, Post> getLatestPost() {
        try {
            return blogRepository.getLatestPost();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Post getPostDetail(String id) {
        try {
            return blogRepository.getPostDetail(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, String> getUser() {
        try {
            return blogRepository.getUser();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTotalPosts() {
        try {
            return blogRepository.getTotalPosts();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int getTotalPostsByCate(int id) {
        try {
            return blogRepository.getTotalPostsByCate(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    
    public ArrayList<Post> getPostsByCate(int cateId, int currentPage, int postsPerPage) {
        try {
            return blogRepository.getPostsByCate(cateId, currentPage, postsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
