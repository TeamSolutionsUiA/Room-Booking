<!DOCTYPE html>

<html>
    <head>
        <title>Opprett konto</title>
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
        
        <nav>
            <div class="log">
                <div class="loggin">
                    <a class='nav-link1' href="/Room-Booking/Logg_inn/login.html" data-toggle="tooltip" title="logg inn her"> logg inn</a>
                    <a class='nav-link1' href="/Room-Booking/bruker/register.html" data-toggle="tooltip" title="register nytt konto"> registrer</a>
                </div>
            </div>

            <div class="container " id="124">
                <div class='logo'>
                    <a href="/Room-Booking/HomePage/index.html"> Brokke</a>
                </div>

                <div id="navbar">

                    <ul>

                        <li><a href="about.html" data-toggle="tooltip" title="bli kjent med oss">Om Brokke</a></li>
                        
                        <li><a href="Room-Booking/boenhetstype/read.java" data-toggle="tooltip" title=" Se leilightene">Boenheter</a></li>
                        <li><a href="" data-toggle="tooltip" title="Se Aktiveter">Aktiveter</a></li>
                        <li><a href="news.html" data-toggle="tooltip" title="Nyheter">Nyheter</a></li>
                        <li><a href="egenskaper.html" data-toggle="tooltip" title="Egenskaper">Kontakt oss</a></li>
          

                    </ul>
                      

                </div>
                <div class="nav-bar">
                    <ul class="">
                        <li class="nav-item"><a href="about.html" class="nav-link" data-toggle="tooltip" title="bli kjent med oss">Om Brokke</a></li>
                    

                        <li class="nav-item"><a href="Room-Booking/boenhetstype/read.java" class="nav-link" data-toggle="tooltip" title=" Se leilightene">Boenheter</a></li>
                        <li class="nav-item"><a href="" class="nav-link" data-toggle="tooltip" title="Se Aktiveter">Aktiviteter</a></li>
                        <li class="nav-item"><a href="news.html" class="nav-link" data-toggle="tooltip" title="Nyheter">Nyheter</a></li>
                           <li class="nav-item"><a href="egenskaper.html" class="nav-link" data-toggle="tooltip" title="Kontant oss">Kontakt oss</a></li>
                    </ul>
                </div>

                <div class="bestilling">
                    
                    <i class="fas fa-search , icons"   onclick="myFunction()" data-toggle="tooltip" title="search" ></i>
                    <i class="fas fa-phone , icons" data-toggle="tooltip" title="00043434"></i>

                </div>
            </div>
            <div id="search">
                <p>
                    <input type="text" name="Navn" id="rr" class="search" placeholder="search: ">
                </p>

            </div>
            
            <div class="info">
                
                <p><div class="inn">Registrer Konto</div><br>
                    
                   Hvis du allerede har en konto hos oss, kan du logge inn på innloggingssiden </p>
            </div>
     
            
            
            
        <form action="register" method="post" enctype="multipart/form-data">
              <div class="col-login"> 
                <div class="form-group">
                <label class="control-label" for="input-fornavn" id="fornavn">Fornavn*</label>
                <input type="text" name="Navn" value="${after.Navn}" placeholder="Fornavn" id="input-fornavn" class="form-control" />
                </div>
                <div class="form-group">
                <label class="control-label" for="input-etternavn" id="etternavn">Etternavn*</label>
                <input type="text" name="Etternavn" value="${after.Etternavn}" placeholder="Etternavn" id="input-etternavn" class="form-control" />
                
                </div>
                   <div class="form-group">
                <label class="control-label" for="input-fodselsdato" id="fodsldato">Fødselsdato*</label>
                <input type="text" name="Fodselsdato" value="${after.Fodselsdato}" id="input-foselsdato" class="form-control" placeholder="ÅÅÅÅ-MM-DD" required 
                    pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"/>
                
                </div>
                  <div class="form-group">
                <label class="control-label" for="input-Mobilnummer" id="mobil">Mobilnummer*</label>
                <input type="number" name="Mobilnummer" value="${after.Mobilnummer}" placeholder="Mobilnummer" id="input-Mobilnummer" class="form-control" />
                
                </div>
                   <div class="form-group">
                <label class="control-label" for="input-email" id="email">E-postadresse*</label>
                <input type="email" value="${after.Epost}" name="Epost" placeholder="Epost-adresse" id="input-email" class="form-control"/>
                <span name="epostError">${errors.Epost}</span>
                </div>
             <fieldset>
                
                 <div class="form-group">
                <label class="control-label" for="input-password" id="passord">Passord*</label>
                <input type="password" name="Passord" value=""  id="pass1" placeholder="Passord" id="input-password" required
                          pattern=".{6,}" title="Minimum 6 karakterer" class="form-control" />
                   <p><span id='message'></span></p>
                <input type="checkbox" onclick="visPassord1()">Vis passord 
                
                
                </div>
                <div class="form-group">
                <label class="control-label" for="input-password" id="passord2">Gjenta Passord*</label>
                <input type="password" name="Re-passord" id ="pass2" required
                          pattern=".{6,}" title="Minimum 6 karakterer"
                          value="" placeholder="Gjenta Passord" id="input-Gjenta Passord" class="form-control" />
                 <input type="checkbox" onclick="visPassord2()">Vis passord
                <input type="button" value="Bekreft" onclick="sjekkPassord()">
               
                </div>
               
             </fieldset>
            </form>         
              <div class="buttons">
                   <div class="pull-right">
                  <button  id="button" type="submit"   class="btn " data-loading-text="<span>Opprett Konto</span>">
                      <a style="color:white; text-decoration: none;">Opprett Konto</a></button>
                </div>
              </div>
             </div>
               <script>
        var input = document.querySelector("#input-Mobilnummer");
        window.intlTelInput(input, {
        utilsScript: "js/utils.js"
                        });
         </script>
                    
    </body>
</html>
