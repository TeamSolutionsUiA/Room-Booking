

<html>
    <head>
        <title>Logg Inn</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
         <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
             integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
          crossorigin="anonymous"></script>
        <script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
    </head>
    <body>
         
        
        
         <nav>
            <div class="log">
                <div class="loggin">
                    <a class='nav-link1' href='' data-toggle="tooltip" title="logg inn her"> logg inn</a>
                    <a class='nav-link1' href="../bruker/register.jsp" data-toggle="tooltip" title="register nytt konto"> register</a>
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
                   
                    <i class="fas fa-search , icons"   onclick="myFunction()" data-toggle="tooltip" title="search" ></i>
                    <i class="fas fa-phone , icons" data-toggle="tooltip" title="00043434"></i>

                </div>
            </div>
            <div id="search">
                <p>
                    <input type="text" name="Navn" id="rr" class="search" placeholder="search: ">
                </p>

            </div>
             
        <div class="login">
            
            <form action="login" method="post">
              <div class="col-login"> 
                <div class="form-group">
                <label class="control-label" for="input-email" id="email">E-postadresse</label>
                <input type="text" name="epost" value="" placeholder="E-postadresse" id="input-email" class="form-control" />
                </div>
                <div class="form-group">
                <label class="control-label" for="input-password" id="passord">Passord</label>
                <input type="passord" name="passord" value="" placeholder="Passord" id="input-password" class="form-control" />
                <span name="error">${message}</span>
                <br><br>  
                <div><a  target="_top" href="glemt_passord.html">Glemt Passord</a>
                     
                </div>
                </div>
                   
            
              <div class="buttons">
                   <div class="pull-right">
                  <button value="" id="button" type="submit" class="btn " data-loading-text="<span>Login</span>">
                      <a style="color:white; text-decoration: none;">Login</a></button>
                </div>
              </div>
             </div>
            </form>
                <div class="buttons-register">
                <div class="pull-right">    
                    <span style="color :black; text-decoration: underline;">Har du ikke en bruker? <span>
                     <button   id="button1" type="submit" class="btn" data-loading-text="<span>opprett</span>">
                      <a href="/Room-Booking/bruker/register.jsp" style="color:white; text-decoration: none;" >Opprett konto</a></button>
                </div>
      
                </div>  
           
           <!-- <p>Har du ikke en bruker? <button type="button"><a href="register.html" style="text-decoration: none; color: black;">Opprett konto</a></button></p>
            
            <p>Request en sikker adminside <a href="/adminOmraade/adminSide.html">here!</a></p>
            <p>Request en sikker ansattside <a href="/ansattOmraade/ansattSide.html" >here!</a></p>
            <p>Request en sikker brukerside <a href="/brukerOmraade/brukerSide.html" >here!</a></p>**/-->
            
        
             
             
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