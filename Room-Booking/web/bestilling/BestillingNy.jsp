<%-- 
    Document   : BestillingNy
    Created on : 30.okt.2019, 12:43:27
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
        <form action="BestillingNy" method="post">
            <p> startDato<input type="text" name="Bestilling-start" value="" placeholder="startDato" id="input-bestilling" > </input> </p>
            <p> sluttDato<input type="text" name="Bestilling-slutt" value="" placeholder="sluttDato" id="input-bestilling" > </input> </p>
            <p> antallPerson<input type="number" name="Bestilling-antall" value="" placeholder="antallPers" id="input-bestilling" > </input> </p>
            <p><input type="submit" value="bestill"></p>
            
        </form>
    </body>
</html>
