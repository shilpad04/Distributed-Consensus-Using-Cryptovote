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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumit
 */
public class submit_vote extends HttpServlet {

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
            String id=request.getParameter("id");
            String ans=request.getParameter("op");
            HttpSession session=request.getSession();  
            RequestDispatcher rd=null;
            DBQuery db=new DBQuery();
            String user=session.getAttribute("userid").toString();
            try {
                int i=db.add_post(user, id, ans);
                String fhash=db.get_ipfs_hash(id);
                System.out.println("fhash="+fhash);
                
                
                File fnew=new File(info.py_path+"fid.txt");
                FileWriter fw_new=new FileWriter(fnew);
                fw_new.write(id+"");
                fw_new.close();
                
                
                File fh2=new File(info.py_path+id+"_hash.txt");
                FileWriter fwh2=new FileWriter(fh2);
                fwh2.write(fhash);
                fwh2.close();
                
                
                File fh=new File(info.py_path+"ans.txt");
                FileWriter fwh=new FileWriter(fh);
                fwh.write(ans);
                fwh.close();
                
                File fh1=new File(info.py_path+"task.txt");
                FileWriter fwh1=new FileWriter(fh1);
                fwh1.write("update");
                fwh1.close();
                String msg="";
                if(i==0)
                {
                msg="Successfully Submited";
                }
                else{
                msg="You already posted for this question";
                }
                Thread.sleep(5000);
                
                File file1 = new File(info.py_path+id+"_hash.txt"); 

                BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

                String st1; 
                while ((st1 = br1.readLine()) != null) {
                System.out.println(st1); 
                fhash=st1;
                }
                
                db.add_file_ipfs(id+"", fhash);
                session.setAttribute("msg", msg);
                rd=request.getRequestDispatcher("user_home.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(submit_vote.class.getName()).log(Level.SEVERE, null, ex);
            }
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
