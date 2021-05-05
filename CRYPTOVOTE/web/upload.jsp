<%@page import="Database.DBQuery"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="Logic.path"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Image"%>
<%@page import="Logic.ImageResizer"%>
<%@page import="Logic.ImageResizer"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>    
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>  
    <%               
        
                        Connection con;
                        Statement st;
                        ResultSet rs;
        try
        {
         
            
            //String  mp=request.getParameter("mposter");
            MultipartRequest m = new MultipartRequest(request,path.p);
            File f1=m.getFile("file");
            String fnm=f1.getName();
           System.out.println("name"+fnm);
        RequestDispatcher rd = null;         
             String itemid = m.getParameter("itemid");
        String itname = m.getParameter("itname");
        String itdesc = m.getParameter("itdesc");
        String itmanu = m.getParameter("itmanu");
        String itprice = m.getParameter("itprice");
        String ittype = m.getParameter("ittype");
        String userid = session.getAttribute("userid").toString();
        DBQuery db = new DBQuery();
        int i = db.addStoreItem(userid, itemid, itname, itdesc, itmanu, itprice, ittype);

       
            
             try {
                        ImageResizer ir=new ImageResizer();
                        Image img = null;
                        img = ImageIO.read(new File(path.p+""+fnm));
                        BufferedImage tempPNG = null;
                        tempPNG = ir.resizeImage(img, 200, 200);
                        File newFilePNG = null;
                        System.out.println("**"+path.p+userid+"_"+itemid+".png");
                        newFilePNG = new File(path.p+userid+"_"+itemid+".png");
                        ImageIO.write(tempPNG, "png", newFilePNG);
                        
                        
                       
                    } catch (IOException ex) {
                       ex.printStackTrace();
                    }
            
            

                 File f=new File(path.p+""+fnm);
        f.delete();
                             
                                       
                                       
         if (i == 1) {
            session.setAttribute("msg", "Registration Successfull");
            rd = request.getRequestDispatcher("storeHome.jsp");
            rd.forward(request, response);
        } else {
            session.setAttribute("msg", "Failed");
            rd = request.getRequestDispatcher("out.jsp");
            rd.forward(request, response);
        }
        
       
        }
        catch(Exception x)
        {
          response.sendRedirect("storeHome.jsp");
        }
    %>  