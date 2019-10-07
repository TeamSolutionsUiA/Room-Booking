



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
window.onscroll = function() {
    scrollFunction();
};

var i = 0;
var col = new Array("rgba(241, 89, 34, 1)", "#9e009e");


function changebar() {

    document.getElementById("124").style.backgroundColor = col[i];
    document.getElementById("navbar").style.backgroundColor = col[i];
    document.getElementById("fornavn").style.color = col[i];
    document.getElementById("etternavn").style.color = col[i];
    document.getElementById("fodsldato").style.color = col[i];
    document.getElementById("mobil").style.color = col[i];
    document.getElementById("email").style.color = col[i];
    document.getElementById("passord").style.color = col[i];
    document.getElementById("passord2").style.color = col[i];
    document.getElementById("button").style.backgroundColor = col[i];
    document.getElementById("button").style.backgroundColor = col[i];

    i++;
    if (i > col.length) {
        i = 0;
    }
    window.setTimeout("changebar()", 3000);

}

window.onload = function(){changebar();};