package Database;


import Logic.info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author sai
 */
public class DBQuery {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

  public int add_result(String id,String op) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
		String query1="insert into result values('"+id+"','"+op+"')";
		
		st=con.createStatement();
		int i=st.executeUpdate(query1);
                
		return i;
	}
    public int add_user(String name,String email,String mobile,String password,String age) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
		String query1="insert into user_registration values('"+name+"','"+email+"','"+mobile+"','"+age+"')";
		
		st=con.createStatement();
		int i=st.executeUpdate(query1);
                String query2="insert into login values('"+email+"','"+password+"','user')";
		int j=st.executeUpdate(query2);

		return i;
	}
 public int add_post(String qus) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String date = simpleDateFormat.format(new Date());
                System.out.println(date);
		String query1="insert into post set qus='"+qus+"' , post_date='"+date+"',status='on'";
		
		st=con.createStatement();
		int i=st.executeUpdate(query1);
                String query2="select MAX(id) from post";
		rs=st.executeQuery(query2);
                if(rs.next())
                {
                i=rs.getInt(1);
                }
		return i;
	}

  
   
  
    public int insertDetails(String email, String mobile, String name, String pass) throws ClassNotFoundException, SQLException {
        int i = 0;
        try {
            con = DBConnection.getConnection();
            String q = "insert into user_details values ('" + email + "','" + mobile + "','" + name + "','" + pass + "')";
            System.out.println(">>" + q);
            st = con.createStatement();

            System.out.println("" + q);
            i = st.executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
            i = 2;
        }
        return i;
    }

    public int add_file_ipfs(String fid, String hash) throws ClassNotFoundException, SQLException {
        int i = 0;
        try {
            con = DBConnection.getConnection();
            String q = "delete from file_ipfs where fid='" + fid + "'";
            System.out.println(">>" + q);
            st = con.createStatement();

            System.out.println("" + q);
            i = st.executeUpdate(q);
            
            String q1="insert into file_ipfs values('" + fid + "','" + hash + "')";
            System.out.println(q1);
            i = st.executeUpdate(q1);
        } catch (Exception e) {
            e.printStackTrace();
            i = 2;
        }
        return i;
    }
    public String updatePass(String user, String op, String np) throws ClassNotFoundException, SQLException {

        int i = 0;
        String dbpass = "", status = "";
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select * from login where user_id='" + user + "'";

        rs = st.executeQuery(q);
        if (rs.next()) {
            dbpass = rs.getString("password");

        }
        if (dbpass.equals(op)) {
            String qq = "update login set password='" + np + "' where user_id='" + user + "'";
            String qq1 = "update user_reg set password='" + np + "' where user_id='" + user + "'";
            st.executeUpdate(qq);
            st.executeUpdate(qq1);
            status = "ok";
        } else {
            status = "notok";

        }


        return status;
    }

  
 
   

    public String loginCheck(String name, String pass) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        st = con.createStatement();
        String utype = "";
        String query = "select * from login where user_id='" + name + "' and password='" + pass + "'";
        System.out.println(query);
        rs = st.executeQuery(query);

        if (rs.next()) {
            utype = rs.getString("utype");
        }
        System.out.println(utype);
        return utype;
    }
 public String get_mob(String email) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        st = con.createStatement();
        String mobile = "";
        String query = "select * from user_registration where email='" + email + "' ";
        System.out.println(query);
        rs = st.executeQuery(query);

        if (rs.next()) {
            mobile = rs.getString("mobile");
        }
        System.out.println(mobile);
        return mobile;
    }
   
   
  public ArrayList get_post(String status) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        st = con.createStatement();
        ArrayList al=new ArrayList();
        String query = "";
                
        if(status.equals(""))    
        {
        query="select * from post";
        }
        else{
        query="select * from post where status='" + status + "' ";
        }
        System.out.println(query);
        rs = st.executeQuery(query);

        while (rs.next()) {
            al.add(rs.getInt("id")+"@@"+rs.getString("qus")+"@@"+rs.getString("post_date")+"@@"+rs.getString("status"));
        }
       
        return al;
    }
   

 
  



      public int add_post(String email,String id,String ans) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
                st=con.createStatement();
                int i=0;
                String query = "select * from user_post where userid='" + email + "' and id='" + id + "' ";
                System.out.println(query);
                rs = st.executeQuery(query);

                if (rs.next()) {
                   i=1;
                }
                
                if(i==0)
                {
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String date = simpleDateFormat.format(new Date());
                System.out.println(date);
                String query1="insert into user_post values('"+email+"','"+id+"','"+date+"')";//'"+ans+"',
		
		
		st.executeUpdate(query1);
                }
                
		
                
		return i;
	}
    public String get_ipfs_hash(String fid) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
                st=con.createStatement();
                String fhash="";
                String query = "select * from file_ipfs where fid='" + fid + "' ";
                System.out.println(query);
                rs = st.executeQuery(query);

                if (rs.next()) {
                   fhash=rs.getString("hash");
                }
                
                
                
		
                
		return fhash;
	}
  public int update_post_status(String id,String status) throws ClassNotFoundException, SQLException
	{
		con=DBConnection.getConnection();
                st=con.createStatement();
                int i=0;
                String query = "update  post set status='" + status + "' where id='" + id + "'";
                System.out.println(query);
                i=st.executeUpdate(query);

                
                
		
                
		return i;
	}
    
    public int get_result(String id,String ans) throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
        st = con.createStatement();
        int i=0;
        String query = "";
                
        
        query="select count(*) from user_post where id='" + id + "' and ans='" + ans + "' ";
        
        System.out.println(query);
        rs = st.executeQuery(query);

        if (rs.next()) {
           i=rs.getInt(1);
        }
       
        return i;
    }
    
 
    
}
