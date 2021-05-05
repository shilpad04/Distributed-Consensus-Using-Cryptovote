

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
        <li ><a href="adminHome.jsp">Home</a></li>
        <li><a href="add_user.jsp">Add User</a></li>
        <li ><a href="add_post.jsp">Add Post</a></li>
        <li ><a href="admin_view_post.jsp">View Post</a></li>
        <li class="active"><a href="admin_view_result.jsp">View Result</a></li>
        <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">
       <%
           String arr[]=msg.split("@@");
           
int yes=Integer.parseInt(arr[3]);
int no=Integer.parseInt(arr[5]);

           
       %>
       <table>
           <tr><td>ID</td><td><%=arr[0]%></td></tr>
           <tr><td>Question</td><td><%=arr[1]%></td></tr>
           <tr><td><%=arr[2]%></td><td><%=arr[3]%></td></tr>
           <tr><td><%=arr[4]%></td><td><%=arr[5]%></td></tr>

           <%
               if(yes==no)
               {
               %>
                       <tr><td>Its a tie. Please Announce The Result</td>
                           <td>
                   
                                <form action="./announce_result" method="post">
                                <input type="hidden" name="id" value="<%=arr[0]%>"/>
               
               
                                <input type="radio" id="op" name="op" value="Yes"/>Yes
                                <input type="radio" id="op" name="op" value="No"/>No
                     
                           <input type="submit" name="submit" value="Announce"/></td></tr>
           </form>
                               
                               
                   </td></tr>
               <%
               }
else{
String res="";
if(yes>no){
res="yes";
}
else{
res="no";
}
               %>
                       <tr><td>Please Announce The Result</td>
                           <td>
                   
                                <form action="./announce_result" method="post">
                                <input type="hidden" name="id" value="<%=arr[0]%>"/>
                                <input type="hidden" name="op" value="<%=res%>"/>
               
               
                               
                     
                           <input type="submit" name="submit" value="Announce"/></td></tr>
               <%}%>
       </table>
  </div>
</body>
</html>
