<%-- 
    Document   : welcom
    Created on : 08.okt.2019, 20:59:34
    Author     : altee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <% 
        if (session.getAttribute("epost")== null)
        {
           response.sendRedirect("login.jsp");
        }
        
        
     %>
       welcome ${epost}
    </body>
</html>
