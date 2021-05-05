/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import Logic.MailSend;
import Logic.generate_otp;
import Logic.info;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumit
 */
public class request_otp extends HttpServlet {

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
            String mode=request.getParameter("mode");
            HttpSession session=request.getSession();
            RequestDispatcher rd;
            String uname=session.getAttribute("userid").toString();
            DBQuery db=new DBQuery();
            generate_otp go=new generate_otp();
            String otp=go.get_otp();
            
            if(mode.equals("SMS"))
            {
            String mob=db.get_mob(uname);
            info.hm_mob.put(mob, otp);
            info.hm.put(uname, otp);
            FileWriter fw_new=new FileWriter("E:/sms.txt");
            fw_new.write(mob+"#Cryptovote OTP is"+otp);
            fw_new.close();
            }
            else{
            MailSend ms=new MailSend();
            ms.emailUtility(uname, "OTP for login into Cryptovote is "+otp);
            }
            
             
        
            rd=request.getRequestDispatcher("otp_verification.jsp");
            rd.forward(request, response);
        }catch(Exception e)
        {
        e.printStackTrace();
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
