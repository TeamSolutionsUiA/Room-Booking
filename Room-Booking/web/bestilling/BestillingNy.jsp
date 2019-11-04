<%-- 
    Document   : BestillingNy
    Created on : 30.okt.2019, 12:43:27
    Author     : altee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
     <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styleRegister.css">
        <link rel="stylesheet" href="CSS/intlTelInput.css">
        <script type="text/javascript" src="regFunk.js"></script>
        <script type="text/javascript" src="regStyle.js"></script>
        <script src="js/intlTelInput.js"></script>

    </head>
    </head>
    <body>
        <form action="visBoenheter" method="post" enctype="multipart/form-data">
            <p><input type="text" name="Bestilling-start" value="" placeholder="Fra dato:" id="input-bestilling" required
                        pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                        title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p><input type="text" name="Bestilling-slutt" value="" placeholder="Til dato:" id="input-bestilling" required
                        pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                        title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p><input type="number" name="Bestilling-antall" value="" placeholder="Antall personer:" id="input-bestilling" required> </input> </p>
            <p>
                <select name="Bestilling-kategori" required>
                    <option value="Alle">Alle</option>
                    <option value="Hytte">Hytte</option>
                    <option value="Leilighet">Leilighet</option>
                </select>
            </p>    
            <p><button  id="button" type="submit" name="Submit"   class="btn " data-loading-text="<span>Vis boenheter</span>">Søk</a></button></p>
            <p><span name="error">${error}</span>
                </div>
            
        </form>
    </body>
</html>
