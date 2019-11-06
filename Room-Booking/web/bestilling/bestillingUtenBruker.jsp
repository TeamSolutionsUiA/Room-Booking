<%-- 
    Document   : bestillingUtenBruker
    Created on : 05.nov.2019, 12:58:34
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
         <form action="bestillUtenId" method="post">
           <p> fornavn  <input type="text" name="Navn" value="" placeholder="Fornavn" id="input-fornavn" class="form-control" /></p>
           <p> etternavn  <input type="text" name="Etternavn" value="" placeholder="Etternavn" id="input-etternavn" class="form-control" /></p>
            <p> fødselsdato  <input type="text" name="Fodselsdato" value="" id="input-foselsdato" class="form-control" placeholder="ÅÅÅÅ-MM-DD" required 
                    pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"/></p>
                        <p> telefon   <input type="number" name="Mobilnummer" value="" placeholder="Mobilnummer" id="input-Mobilnummer" class="form-control" /></p>
               <p> e-post <input type="email" value="" name="Epost" placeholder="Epost-adresse" id="input-email" class="form-control"/></p>

            <p> startDato<input type="text" name="Bestilling-start" value="" placeholder="Fra dato:" id="input-bestilling" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p> sluttDato<input type="text" name="Bestilling-slutt" value="" placeholder="Til dato:" id="input-bestilling" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"</input></p>
            <p> antallPersoner<input type="number" name="Bestilling-antall" value="" placeholder="Antall personer:" id="input-bestilling" > </input> </p>
           
                <p><button  id="button" type="submit" name="Submit"   class="btn " data-loading-text="<span>Vis boenheter</span>">bestill</a></button></p>

        </form>
    </body>
</html>
