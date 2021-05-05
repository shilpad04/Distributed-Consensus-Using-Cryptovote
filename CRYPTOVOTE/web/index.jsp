<% 
String projPath = request.getContextPath(); 

String msg="";

if(session.getAttribute("msg")!=null)
       {

msg=session.getAttribute("msg").toString();

}
%>

<%@page import="Logic.info"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="Login.jsp">Login</a></li>
        <li><a href="register.jsp">Registration</a></li>
        
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">

        <%=msg%> 
  
  </div>
</body>
</html>
