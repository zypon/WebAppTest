/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CJM
 */
public class WriteDB extends HttpServlet {

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
          /*  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WriteDB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WriteDB at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            
            
            String cusID = request.getParameter("customerId");
            String cusName = request.getParameter("customerName");
            
            
            out.println(cusID);
            out.println("cusName:"+cusName);
            DBConn dbConn = new DBConn();
       
            
            Connection conn = dbConn.getConn();
           
         
            
            if (conn == null)
            {
                out.println("conn null");
            }
            
            Statement stmt = conn.createStatement();
       
            ResultSet rs = stmt.executeQuery("Select * from customer");
         
            out.println("<table border=\"2\">");
            out.println("<tr><td>ID</td><td>Name</td></tr>");
            while (rs.next())
            {
                out.println("<tr><td>");
                out.println(rs.getInt("customerID"));
                out.println("</td><td>");
                out.println(rs.getString("customerName"));              
                out.println("</td></tr>");
            }
            
            out.println("</table>");
            
            stmt.executeUpdate("INSERT INTO customer (customerID,customerName) VALUES ("+cusID +",'" + cusName +"')");
           out.println("Done");
        } catch (Exception ex) {
            Logger.getLogger(WriteDB.class.getName()).log(Level.SEVERE, null, ex);
            
            ex.printStackTrace();
            out.println(ex.getMessage());
            
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
