/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.blog;

import entities.Post;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AS
 */
@WebServlet(name = "BlogDetailController", urlPatterns = {"/BlogDetail"})
public class BlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogService blogService = new BlogService();
        String id = request.getParameter("id");
        HashMap<String, String> hmCat = blogService.gethmCat();
        HashMap<String, String> hmUser = blogService.getUser();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        Post postDetail = blogService.getPostDetail(id);
        request.setAttribute("hmCat", hmCat);
        request.setAttribute("post", postDetail);
        request.setAttribute("latest", latestPost);
        request.getRequestDispatcher("blog/blogDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
