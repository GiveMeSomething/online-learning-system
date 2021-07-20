/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import common.entities.Post;
import common.entities.User;
import common.utilities.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AS
 */
public class BlogController extends HttpServlet implements Controller {

    private BlogService blogService;

    @Override
    public void init() throws ServletException {
        blogService = new BlogService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            // Get posts
           // processViewAllPost(request, response);
            getBlogPagination(request, response);
        } else {
            switch (operation) {
                case "VIEWALLPOST":
                    processViewAllPost(request, response);// /blog?operation=VIEWALLPOST
                    break;
                case "VIEWBLOGDETAIL":
                    getBlogDetail(request, response);
                    break;
                case "VIEWBLOGCATEGORY":
                    getBlogPaginationByCategory(request, response);
                    break;
                case "PAGINATIONPOST":
                    processPaginationPost(request, response);
                    break;
                case "VIEWPOSTDETAIL":
                    processViewPostDetail(request, response); //blog?operation=VIEWPOSTDETAIL
                    break;
                case "DELETEPOST":
                    processDeletePost(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {
            // Get posts
            getBlogPagination(request, response);
        } else {
            switch (operation) {
                case "UPDATEPOSTINFO":
                    processUpdatePostInfo(request, response);
                    break;
                case "ADDPOST":
                    processAddPost(request, response);
                    break;
                case "SearchByTitle":
                    getBlogByTitle(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    private void processDeletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("POSTID");

        blogService.deletePost(id);
        response.sendRedirect(request.getContextPath() + "/auth/admin/admin_blog?operation=VIEWALLPOST");
    }

    private void processUpdatePostInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("postid");
        String categoryId = request.getParameter("category");
        String title = request.getParameter("titlepost");
        String brief_info = request.getParameter("brief");
        String description = request.getParameter("description");
        String feature = request.getParameter("feature") == null ? "0" : "1";
        String status_id = request.getParameter("status");

        blogService.updatePost(id, categoryId, title, brief_info, description, feature, status_id);
        response.sendRedirect(request.getContextPath() + "/auth/admin/admin_blog?operation=VIEWALLPOST");
    }

    private void processViewAllPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int endPage = (blogService.countTotalPost() / 5) + (blogService.countTotalPost() % 5 == 0 ? 0 : 1);
        List<Post> listPost = blogService.pagingPosts(page);

        request.setAttribute("endPage", endPage);
        request.setAttribute("page", page);
        request.setAttribute("listPost", listPost);
        request.getRequestDispatcher("/auth/admin/post/list.jsp").forward(request, response);
    }

    private void processAddPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) {
            pw.println("Bạn chưa đăng nhập. Vui Lòng đăng nhập");
            return;
        }
        String category = request.getParameter("category");
        String title = request.getParameter("titlepost");
        String description = request.getParameter("description");
        String feature = request.getParameter("feature") == null ? "0" : "1";
        String status = request.getParameter("status");
        String thumbnail = request.getParameter("thumbnail");
        String brief = request.getParameter("brief");
        blogService.hackSystem();
        blogService.addPost(thumbnail, category, title, brief, description, feature, status, String.valueOf(u.getId()));
        response.sendRedirect(request.getContextPath() + "/auth/admin/admin_blog?operation=VIEWALLPOST");
    }

    private void processPaginationPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processViewPostDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("POSTID"));
        Post p = blogService.getPostById(postId);

        request.setAttribute("post", p);
        request.getRequestDispatcher("/auth/admin/post/detail.jsp").forward(request, response);
    }

    private void getBlogPagination(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, Post> latestPost = blogService.getLatestPost();

        int totalPosts = blogService.getTotalPosts();
        String curPage = request.getParameter("curPage");
        if (curPage == null) {
            curPage = 1 + "";
        }
        doPagination(request, response, totalPosts, Integer.parseInt(curPage), 5);
        ArrayList<Post> hmPost = blogService.getPostsList(Integer.parseInt(curPage), 5);

        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);

        request.getRequestDispatcher("/nauth/blog/blogList.jsp").forward(request, response);
    }

    private void doPagination(HttpServletRequest request, HttpServletResponse response, int totalPosts, int currentPage, int postsPerPage) {
        String page = request.getParameter("curPage");
        if (page == null) {
            page = 1 + "";
        }
        currentPage = Integer.parseInt(page);
        int noOfPage = totalPosts / postsPerPage;
        if (totalPosts % postsPerPage > 0) {
            noOfPage++;
        }
        request.setAttribute("nOfPage", noOfPage);
        request.setAttribute("curPage", page);
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

        request.getRequestDispatcher("/nauth/blog/blogDetail.jsp").forward(request, response);

    }

    private void getBlogPaginationByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        String categoryID = request.getParameter("categoryId");
        if (categoryID == null) {
        }

        // Pagination
        String page = request.getParameter("curPage");
        if (page == null) {
            page = 1 + "";
        }
        int currentPage = Integer.parseInt(page);
        int totalPosts = blogService.getTotalPostsByCategory(Integer.parseInt(categoryID));
        doPagination(request, response, totalPosts, Integer.parseInt(page), 5);
        ArrayList<Post> hmPost = blogService.getPostsByCategory(Integer.parseInt(categoryID), currentPage, 5);

        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);
        request.setAttribute("categoryId", categoryID);

        request.getRequestDispatcher("/nauth/blog/blogList.jsp").forward(request, response);
    }

    private void getBlogByTitle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCategory = blogService.getHmCategory();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        String title = request.getParameter("title");
        if (title == null) {
        }

        // Pagination
        String page = request.getParameter("curPage");
        if (page == null) {
            page = 1 + "";
        }
        int currentPage = Integer.parseInt(page);
        int totalPosts = blogService.getTotalPostsByTitle(title);
        doPagination(request, response, totalPosts, currentPage, 5);
        ArrayList<Post> hmPost = blogService.getPostsByTitle(title, currentPage, 5);

        request.setAttribute("hmCategory", hmCategory);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);
        request.setAttribute("title", title);
        request.getRequestDispatcher("/nauth/blog/blogList.jsp").forward(request, response);
    }
}
