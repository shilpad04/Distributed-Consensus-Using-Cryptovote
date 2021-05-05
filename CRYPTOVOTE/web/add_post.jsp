
<%@page import="Logic.info"%>
<%@page import="Database.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 
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
        <title>JSP Page</title>
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
        <li ><a href="adminHome.jsp">Home</a></li>
        <li><a href="add_user.jsp">Add User</a></li>
        <li class="active"><a href="add_post.jsp">Add Post</a></li>
        <li ><a href="admin_view_post.jsp">View Post</a></li>
        <li ><a href="admin_view_result.jsp">View Result</a></li>
        <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">

       <table>
           <form action="./add_post" method="post" >
               <tr>
                   <td><label>Question</label></td>
                   <td><textarea name="qus" id="qus" ></textarea></td>
               </tr>
               
             
              
              
               <tr>
                   <td></td>
                   <td><input type="submit" name="submit" value="Add" </td>
               </tr>
           </form>
           
       </table>   
  
  </div>
</body>
</html>
