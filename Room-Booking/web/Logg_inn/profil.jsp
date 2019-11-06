<%-- 
    Document   : profil
    Created on : 04.nov.2019, 17:39:39
    Author     : altee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>JSP Page</title>
    </head>
    <body>
         <nav>
          
            <div class="log">
                
                <div class="loggin">
                    <a class='nav-link1' href='/Room-Booking/Logg_inn//welcom.jsp' data-toggle="tooltip" title="logg inn her"> ${fornavn1} ${Etternavn}</a>
                    <a class='nav-link1' href="../HomePage/index.html" data-toggle="tooltip" title="loo ut konto"> logg ut</a>
                   <a class='nav-link1' href='profil.jsp' data-toggle="tooltip" title=""> Profil</a>

                </div>
            </div>

            <div class="container " id="124">
                <div class='logo'>
                    <a href="/Room-Booking/HomePage/index.html"> Brokke</a>
                </div>

                <div id="navbar">

                    <ul>

                        <li><a href="about.html" data-toggle="tooltip" title="bli kjent med oss">Hva er Brokke</a></li>
                        
                        <li><a href="Room-Booking/boenhetstype/read.java" data-toggle="tooltip" title=" Se leilightene">leilighter</a></li>
                        <li><a href="" data-toggle="tooltip" title="Se Aktiveter">Aktiveter</a></li>
                        <li><a href="news.html" data-toggle="tooltip" title="Nyheter">Nyheter</a></li>
                        <li><a href="egenskaper.html" data-toggle="tooltip" title="Egenskaper">Kontakt oss</a></li>
                     

                    </ul>
                      

                </div>
               
                <div class="nav-bar">
                    <ul class="">
                        <li class="nav-item"><a href="about.html" class="nav-link" data-toggle="tooltip" title="bli kjent med oss">Hva er Brokke</a></li>
                    

                        <li class="nav-item"><a href="Room-Booking/boenhetstype/read.java" class="nav-link" data-toggle="tooltip" title=" Se leilightene">leilighter</a></li>
                        <li class="nav-item"><a href="" class="nav-link" data-toggle="tooltip" title="Se Aktiveter">Aktiveter</a></li>
                        <li class="nav-item"><a href="news.html" class="nav-link" data-toggle="tooltip" title="Nyheter">Nyheter</a></li>
                           <li class="nav-item"><a href="egenskaper.html" class="nav-link" data-toggle="tooltip" title="Kontant oss">Kontakt oss</a></li>
                    </ul>
                </div>

                <div class="bestilling">
                    <a class="" data-toggle="tooltip" title="Bestill Nå" href="/Room-Booking/bestilling/bestillingCreate.jsp"> Bestill Nå</a>
                    <i class="fas fa-search , icons"   onclick="myFunction()" data-toggle="tooltip" title="search" ></i>
                    <i class="fas fa-phone , icons" data-toggle="tooltip" title="00043434"></i>

                </div>
            </div>
            <div id="search">
                <p>
                    <input type="text" name="Navn" id="rr" class="search" placeholder="search: ">
                </p>

            </div>
      
         <form action="" method="post" enctype="multipart/form-data">
              <div class="col-login"> 
                <div class="form-group">
                <label class="control-label" for="input-Navn" id="fornavn">Fornavn*</label>
                <input type="text" name="Navn" value="  ${fornavn1}" placeholder="Fornavn" id="input-fornavn" class="form-control" />
                
                </div>
                <div class="form-group">
                <label class="control-label" for="input-Etternavn" id="etternavn">Etternavn*</label>
                <input type="text" name="Etternavn" value="${Etternavn}" placeholder="Etternavn" id="input-etternavn" class="form-control" />
              
                </div>
                   <div class="form-group">
                <label class="control-label" for="input-Fodselsdato" id="fodsldato">Fødselsdato*</label>
                <input type="text" name="Fodselsdato" value="${DOB}" id="input-foselsdato" class="form-control" placeholder="ÅÅÅÅ-MM-DD" required 
                    pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                    title="Enter a date in this format YYYY-MM-DD"/>
            
                
                </div>
                  <div class="form-group">
                <label class="control-label" for="input-Mobilnummer" id="mobil">Mobilnummer*</label>
                <input type="number" name="Mobilnummer" value="${tele}" placeholder="Mobilnummer" id="input-Mobilnummer" class="form-control" />
               
                </div>
                   <div class="form-group">
                <label class="control-label" for="input-Epost" id="email">E-postadresse*</label>
                <input type="email" value="${epost}" name="Epost" placeholder="Epost-adresse" id="input-email" class="form-control"/>
                
                </div>
         
    </body>
</html>
