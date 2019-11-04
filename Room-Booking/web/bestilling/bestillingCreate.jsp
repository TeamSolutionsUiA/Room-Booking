<%-- 
    Document   : bestillingCreate
    Created on : 31-Oct-2019, 16:22:01
    Author     : mohamedjabokji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="bestillingCreate" method="post">
            <p> startDato<input type="text" name="Bestilling-start" value="" placeholder="Fra dato:" id="input-bestilling" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p> sluttDato<input type="text" name="Bestilling-slutt" value="" placeholder="Til dato:" id="input-bestilling" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p> antallPersoner<input type="number" name="Bestilling-antall" value="" placeholder="Antall personer:" id="input-bestilling" > </input> </p>
            <p> <input type="hidden" name="Bestilling-kategori" value="${brukerId}" placeholder="Kategori:" id="input-bestilling" > </input> </p>
            <p><input type="submit" value="bestill"></p>
            
        </form>
    </body>
</html>
