/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import entities.Post;
import java.io.IOException;
import java.io.PrintWriter;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/BlogDetail":
                getBlogDetail(request, response);
                break;
            case "/PostsByCate":
                getBlogPaginationByCate(request, response);
                break;
            default:
                getBlogPagination(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void getBlogPagination(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCat = blogService.getHmCat();
        HashMap<String, Post> latestPost = blogService.getLatestPost();

        int numberOfPost = blogService.getTotalPosts();
        String curPage = request.getParameter("curPage");
        if (curPage == null) {
            curPage = 1 + "";
        }
        int curPages = Integer.parseInt(curPage);
        int postPerPage;
        if (numberOfPost > 500) {
            postPerPage = 20;
        } else {
            postPerPage = 4;
        }
        int nOfPages = numberOfPost / postPerPage;
        if (nOfPages % postPerPage > 0) {
            nOfPages++;
        }
        ArrayList<Post> hmPost = blogService.getPostsList(curPages, postPerPage);
        request.setAttribute("nOfPage", nOfPages);
        request.setAttribute("curPage", curPage);
        request.setAttribute("hmCat", hmCat);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);

        request.getRequestDispatcher("blog/blogList.jsp").forward(request, response);
    }

    private void getBlogDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        HashMap<String, String> hmCat = blogService.getHmCat();
        HashMap<String, String> hmUser = blogService.getUser();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        Post postDetail = blogService.getPostDetail(id);
        request.setAttribute("hmCat", hmCat);
        request.setAttribute("post", postDetail);
        request.setAttribute("latest", latestPost);

        request.getRequestDispatcher("blog/blogDetail.jsp").forward(request, response);
    }

    private void getBlogPaginationByCate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, String> hmCat = blogService.getHmCat();
        HashMap<String, Post> latestPost = blogService.getLatestPost();
        String cateId = request.getParameter("cateId");
        if (cateId == null) {
        }
        // Pagination

        int totalPosts = blogService.getTotalPostsByCate(Integer.parseInt(cateId));
        String currentPage = request.getParameter("curPage");
        if (currentPage == null) {
            currentPage = 1 + "";
        }
        int curPages = Integer.parseInt(currentPage);
        int postsPerPage;
        if (totalPosts > 500) {
            postsPerPage = 20;
        } else {
            postsPerPage = 4;
        }
        int nOfPages = totalPosts / postsPerPage;
        if (nOfPages % postsPerPage > 0) {
            nOfPages++;
        }
        ArrayList<Post> hmPost = blogService.getPostsByCate(Integer.parseInt(cateId), curPages, postsPerPage);

        request.setAttribute("nOfPage", nOfPages);
        request.setAttribute("curPage", currentPage);
        request.setAttribute("hmCat", hmCat);
        request.setAttribute("hmPost", hmPost);
        request.setAttribute("latest", latestPost);

        request.getRequestDispatcher("blog/blogList.jsp").forward(request, response);
    }
}
