/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import common.entities.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AS
 */
public class BlogController extends HttpServlet {

    private BlogService blogService;

    @Override
    public void init() throws ServletException {
        blogService = new BlogService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        String action = path.substring(path.lastIndexOf("/") + 1);

        String flag = request.getParameter("flag");
        if (flag == null) {
        } else {
            getBlogPaginationByCategory(request, response);
        }
        switch (action) {
            case "BlogDetail":
                getBlogDetail(request, response);
                break;
            case "PostsByCate":
                getBlogPaginationByCategory(request, response);
                break;
            default:
                getBlogPagination(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void getBlogPagination(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, Post> latestPost = blogService.getLatestPost();

        int totalPosts = blogService.getTotalPosts();
        String curPage = request.getParameter("curPage");
        if (curPage == null) {
            curPage = 1 + "";
        }
        int currentPage = Integer.parseInt(curPage);
        int postsPerPage;
        if (totalPosts > 500) {
            postsPerPage = 20;
        } else {
            postsPerPage = 4;
        }
        int noOfPages = totalPosts / postsPerPage;
        if (noOfPages % postsPerPage > 0) {
            noOfPages++;
        }
        ArrayList<Post> hmPost = blogService.getPostsList(currentPage, postsPerPage);
        request.setAttribute("nOfPage", noOfPages);
        request.setAttribute("curPage", curPage);
        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);
        request.setAttribute("flag", 0);

        request.getRequestDispatcher("nauth/blogList.jsp").forward(request, response);
    }

    private void getBlogDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, String> hmUser = blogService.getUser();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        Post postDetail = blogService.getPostDetail(id);

        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("post", postDetail);
        request.setAttribute("latest", latestPost);

        request.getRequestDispatcher("/nauth/blogDetail.jsp").forward(request, response);
    }

    private void getBlogPaginationByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        String cateId = request.getParameter("cateId");
        if (cateId == null) {
        }

        // Pagination
        int totalPosts = blogService.getTotalPostsByCategory(Integer.parseInt(cateId));
        String page = request.getParameter("curPage");
        if (page == null) {
            page = 1 + "";
        }
        int currentPage = Integer.parseInt(page);
        int postsPerPage;
        if (totalPosts > 500) {
            postsPerPage = 20;
        } else {
            postsPerPage = 2;
        }
        int noOfPage = totalPosts / postsPerPage;
        if (totalPosts % postsPerPage > 0) {
            noOfPage++;
        }
        ArrayList<Post> hmPost = blogService.getPostsByCategory(Integer.parseInt(cateId), currentPage, postsPerPage);

        request.setAttribute("nOfPage", noOfPage);
        request.setAttribute("curPage", page);
        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);
        request.setAttribute("flag", 1);
        request.setAttribute("cateId", cateId);

        request.getRequestDispatcher("nauth/blogList.jsp").forward(request, response);
    }
}
