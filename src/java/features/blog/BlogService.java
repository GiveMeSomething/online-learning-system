/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.blog;

import entities.Post;
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
    public HashMap<String, String> gethmCat(){
        try {
            HashMap<String, String> hmCat = blogRepository.gethmCat();
            return hmCat;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public HashMap<String, Post> gethmPost(){
        try {
            HashMap<String, Post> hmPost = blogRepository.gethmPost();
            return hmPost;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public HashMap<String, Post> getLatestPost(){
        try {
            HashMap<String, Post> hmPost = blogRepository.getLatestPost();
            return hmPost;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
