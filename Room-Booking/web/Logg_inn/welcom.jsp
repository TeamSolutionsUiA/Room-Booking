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
        <link rel="stylesheet" type="text/css" href="style.css">
         <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
          <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
     <% 
        if (session.getAttribute("fornavn1")== null && session.getAttribute("Etternavn")== null && 
                session.getAttribute("brukerId")== null
               
              )
        {
           response.sendRedirect("login.jsp");
        }
        
        
     %>
    
     <nav>
          
            <div class="log">
                
                <div class="loggin">
                    <a class='nav-link1' href='' data-toggle="tooltip" title="logg inn her"> ${fornavn1} ${Etternavn}</a>
                    <a class='nav-link1' href="../index.html" data-toggle="tooltip" title="loo ut konto"> logg ut</a>
                   <a class='nav-link1' href='profil.jsp' data-toggle="tooltip" title=""> Profil</a>

                </div>
            </div>

            <div class="container " id="124">
                <div class='logo'>
                    <a href="../index.html"> Brokke</a>
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
                     
                    
                    
                     
             
       
        
             
             
      <script>
                 window.onscroll = function() { scrollFunction();   };
                  function scrollFunction() {
            var soso = document.getElementById("navbar");
            var sara = document.getElementsByClassName("nav-bar");
            if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
                soso.style.top = "0";
                soso.style.display = "inline";
                sara.style.display = "none";

            } else {

                soso.style.display = "none";
                sara.style.display = "inline-block";
            }

              }
         
        var i = 0;
                var col = new Array("rgba(241, 89, 34, 1)", "#9e009e");

        function changebar() {
                
         document.getElementById("124").style.backgroundColor= col[i];
         document.getElementById("navbar").style.backgroundColor= col[i];
         document.getElementById("email").style.color= col[i];
         document.getElementById("passord").style.color= col[i];
         document.getElementById("button").style.backgroundColor= col[i];
         document.getElementById("button1").style.backgroundColor= col[i];
             i++;
            if (i > col.length) {
                i = 0;
            }
            window.setTimeout("changebar()", 3000);

           }

              window.onload = changebar();
              
              
                $(document).ready(function() {
                   $("#loginForm").validate({
                      rules: {
                            email: {
                           required: true,
                            email: true
                            },
         
                            password: "required",
                   },
             
                    messages: {
                       email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                      },
                 
                      password: "Please enter password"
                   }
             });
  
              });
        </script>
       
    </body>
</html>
