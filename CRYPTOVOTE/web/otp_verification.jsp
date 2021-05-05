

<%@page import="Logic.info"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 

String msg="";

if(session.getAttribute("msg")!=null)
       {

msg=session.getAttribute("msg").toString();

}
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
        
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">

      <%=msg%>
           <form action="./verify_otp" method="post">
                <table>
               <tr>
                   <td><label>Enter OTP</label></td>
                   <td><input type="text" name="otp" id="otp"/></td>
               </tr>
              
               <tr>
                   <td></td>  <td><input type="submit" name="submit" value="Verify"</td>
               </tr>
        
       </table>   
                  </form>
  
  </div>
</body>
</html>
