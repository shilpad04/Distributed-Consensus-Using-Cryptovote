

<%@page import="java.util.ArrayList"%>
<%@page import="Database.DBQuery"%>
<%@page import="Logic.info"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 


String msg="";

if(session.getAttribute("msg")!=null)
       {

msg=session.getAttribute("msg").toString();

}
String userid="";

if(session.getAttribute("userid")!=null)
       {

userid=session.getAttribute("userid").toString();

}

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="<%= projPath%>/styles/layout.css" type="text/css">

</head>
<body id="top">
<div class="wrapper col1">
  <div id="header">
    
        <h1><%=info.proj_name%></a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
        <li class="active"><a href="user_home.jsp">Home</a></li>
       
       
       
       
        <li ><a href="view_post.jsp">View Post</a></li>
         
      
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">
       <table>
           <tr><td>ID</td><td>Question</td><td>Posted Date</td></tr>
       <%
           
           DBQuery db=new DBQuery();
           ArrayList al=db.get_post("on");
           
           for(int i=0;i<al.size();i++)
           {
               
           String det=al.get(i).toString();
           String arr[]=det.split("@@");
           

           %>
           <form action="./submit_vote" method="post">
               <input type="hidden" name="id" value="<%=arr[0]%>"/>
               <tr><td><%=arr[0]%></td><td><%=arr[1]%></td><td><%=arr[2]%></td></tr>
               <tr>
                   <td></td>
                   
               
                   <td>
                       <input type="radio" id="op" name="op" value="Yes"/>Yes
                       <input type="radio" id="op" name="op" value="No"/>No
<!--                       <input type="radio" id="op" name="op" value="May Be"/>May Be-->
                       </td>
                   
               
                   <td><input type="submit" name="submit" value="Post"/></td></tr>
           </form>
           
           <%}%>
       </table>
  </div>
</body>
</html>
