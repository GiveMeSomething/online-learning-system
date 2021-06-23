/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package price_package;

import common.entities.PricePack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class PricePackageController extends HttpServlet {

  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            listPackagePrice(request, response);
        } else {
            
                switch (operation) {
                    case "addPackage":
                        addPackage(request, response);
                        break;
                    case "deletePackage":
                        deletePackage(request, response);
                        break;
                    case "editPackage":
                        editPackage(request, response);
                        break;
                    default:
                        listPackagePrice(request, response);
                    break;
                }    
        }
    }
    
    private void listPackagePrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PricePackageService service = new PricePackageService();
        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        List<PricePack> listPackage = service.getPricePackage(Integer.parseInt(index));
        int endPage = (service.countTotalPricePackage() / 5) + (service.countTotalPricePackage() % 5 == 0 ? 0 : 1);
        request.setAttribute("index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listP", listPackage);
        request.setAttribute("activeId", 3);
        request.setAttribute("totalPackage", service.countTotalPricePackage());
        request.setAttribute("totalPackageOfPage", listPackage.size());
        request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
    }

    private void addPackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("namepackage");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String descriptions = request.getParameter("descriptions");

        PricePackageService service = new PricePackageService();
        service.addPackage(duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/price-package");  
    }

    private void deletePackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        PricePackageService service = new PricePackageService();
        service.deletePackage(id);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/price-package");
    }

    private void editPackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("eId"));
        String name = request.getParameter("editName");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String descriptions = request.getParameter("descriptions");

        PricePackageService service = new PricePackageService();
        service.editPackage(id, duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/price-package");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGet(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
