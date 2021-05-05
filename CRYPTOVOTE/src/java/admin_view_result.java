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


public class admin_view_result extends HttpServlet {

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
            String qus=request.getParameter("qus");
            String op1=request.getParameter("op1");
            String op2=request.getParameter("op2");
           // String op3=request.getParameter("op3");
          
            HttpSession session=request.getSession();  
            RequestDispatcher rd=null;
            DBQuery db=new DBQuery();
            try {
                File fnew=new File(info.py_path+"fid.txt");
                FileWriter fw_new=new FileWriter(fnew);
                fw_new.write(id+"");
                fw_new.close();
                String fhash=db.get_ipfs_hash(id);
                System.out.println("fhash="+fhash);
                File fh2=new File(info.py_path+id+"_hash.txt");
                FileWriter fwh2=new FileWriter(fh2);
                fwh2.write(fhash);
                fwh2.close();
                File fh1=new File(info.py_path+"status.txt");
                FileWriter fwh1=new FileWriter(fh1);
                fwh1.write("get");
                fwh1.close();
                Thread.sleep(10000);
                String res="";
                File file1 = new File(info.py_path+"result.txt"); 

                BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

                String st1; 
                while ((st1 = br1.readLine()) != null) {
                System.out.println(st1); 
                res=st1;
                }
                String arr[]=res.split("#");
                
                arr[0]=arr[0].trim();
                arr[1]=arr[1].trim();
                
                
                
                
                
                int op1_count=Integer.parseInt(arr[0]);//db.get_result(id, op1);
                int op2_count=Integer.parseInt(arr[1]);//db.get_result(id, op2);
                System.out.println("op1_count="+op1_count);
                System.out.println("op2_count="+op2_count);
                
              //op2_count=3;
               // int op3_count=Integer.parseInt(arr[2]);
                String msg=""+id+"@@"+qus+"@@"+op1+"@@"+op1_count+"@@"+op2+"@@"+op2_count;//+"@@ "+op3+"@@ "+op3_count;
                  System.out.println("msg="+msg);
                session.setAttribute("msg", msg);
                rd=request.getRequestDispatcher("admin_view_result1.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(admin_view_result.class.getName()).log(Level.SEVERE, null, ex);
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
