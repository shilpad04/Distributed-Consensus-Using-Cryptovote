/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import Logic.info;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class add_post extends HttpServlet {

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
           String qus=request.getParameter("qus");
//           String op1=request.getParameter("op1");
//           String op2=request.getParameter("op2");
//           String op3=request.getParameter("op3");
//           String op4=request.getParameter("op4");
            DBQuery db=new DBQuery();
            try {
                int id=db.add_post(qus);
                
                File f=new File(info.py_path+id+".txt");
                FileWriter fw=new FileWriter(f);
                fw.write("0#0");//#0
                fw.close();
                
                File fnew=new File(info.py_path+"fid.txt");
                FileWriter fw_new=new FileWriter(fnew);
                fw_new.write(id+"");
                fw_new.close();
                
                
                File fnew1=new File(info.py_path+"status.txt");
                FileWriter fw_new1=new FileWriter(fnew1);
                fw_new1.write("create");
                fw_new1.close();
                Thread.sleep(10000);
                String fhash="";
                File file1 = new File(info.py_path+id+"_hash.txt"); 

                BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

                String st1; 
                while ((st1 = br1.readLine()) != null) {
                System.out.println(st1); 
                fhash=st1;
                }
                
                db.add_file_ipfs(id+"", fhash);
                
            } catch (Exception ex) {
                Logger.getLogger(add_post.class.getName()).log(Level.SEVERE, null, ex);
            }
             RequestDispatcher rd=null;
             rd=request.getRequestDispatcher("adminHome.jsp");
             rd.forward(request, response);
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
