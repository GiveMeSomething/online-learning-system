/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.blog;

import entities.Post;
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

    public HashMap<String, String> gethmCat() {
        try {
            HashMap<String, String> hmCat = blogRepository.gethmCat();
            return hmCat;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, Post> gethmPost() {
        try {
            HashMap<String, Post> hmPost = blogRepository.gethmPost();
            return hmPost;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, Post> getLatestPost() {
        try {
            HashMap<String, Post> hmLatestPost = blogRepository.getLatestPost();
            return hmLatestPost;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Post getPostDetail(String id) {
        try {
            Post postDetail = blogRepository.getPostDetail(id);
            return postDetail;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, String> getUser() {
        try {
            HashMap<String, String> getUser = blogRepository.getUser();
            return getUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public int getNumberOfPosts(){
        try {
            return blogRepository.getNumberOfPosts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public ArrayList<Post> getPost(int curPage, int postsPerPage){
        try {
            return blogRepository.getPost(curPage, postsPerPage);
        } catch (Exception e) {
        }
        return null;
    }
}
