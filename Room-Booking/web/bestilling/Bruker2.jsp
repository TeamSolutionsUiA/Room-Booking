<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styleRegister.css">
        <link rel="stylesheet" href="CSS/intlTelInput.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script type="text/javascript" src="regFunk.js"></script>
        <script type="text/javascript" src="regStyle.js"></script>
        <script src="js/intlTelInput.js"></script>

    </head>
    <body>


        <p><div class="inn">Registrer kundeinfo</div><br>

        Vennligst fyll inn kundeinfo. </p>
</div>




<form action="bekreft" method="post">
    <div class="col-login"> 
        <div class="form-group">
            <input type="text" name="Navn" value="${after.Navn}" placeholder="Fornavn" id="input-fornavn" class="form-control" />
            <span name="NavnError">${errors.Navn}</span>
        </div>
        <div class="form-group">
            <input type="text" name="Etternavn" value="${after.Etternavn}" placeholder="Etternavn" id="input-etternavn" class="form-control" />
            <span name="EtterNavnError">${errors.Etternavn}</span>
        </div>
        <div class="form-group">
            <p><label class="control-label" for="input-Fodselsdato" id="fodsldato">Fødselsdato*</label>
            <br><input type="text" name="Fodselsdato" value="${after.Fodselsdato}" id="input-foselsdato" class="form-control" placeholder="ÅÅÅÅ-MM-DD" required 
                   pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                   title="Enter a date in this format YYYY-MM-DD"/>
            <span name="DOBError">${errors.Fodselsdato}</span></br></p>

        </div>
        <div class="form-group">
            <input type="number" name="Mobilnummer" value="${after.Mobilnummer}" placeholder="Mobilnummer" id="input-Mobilnummer" class="form-control" />
            <span name="MobilError">${errors.Mobilnummer}</span>
        </div>
        <div class="form-group">
            <input type="email" value="${after.Epost}" name="Epost" placeholder="Epost-adresse" id="input-email" class="form-control"/>
            <span name="epostError">${errors.Epost}</span>
        </div>

        <input type = "hidden" name = "boenhetstypeid" value ="<%= request.getParameter("id")%>">
        <input type = "hidden" name = "fraDato" value ="<%= request.getParameter("fradato")%>">
        <input type = "hidden" name = "tilDato" value ="<%= request.getParameter("tildato")%>">

        <div class="buttons">
            <div class="pull-right">
                <p><button  id="button" type="submit" name="lagreKundeBruker"   class="btn " data-loading-text="<span>Lagre kundeinfo</span>">Bekreft
                        <a style="color:white; text-decoration: none;"</a></button></p>
            </div>
        </div>
</form>  
</div>
</body>
</html>