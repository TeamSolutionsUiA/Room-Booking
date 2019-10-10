/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function visPassord1() {

    var pass1 = document.getElementById("pass1");

    if (pass1.type === "password") {
        pass1.type = "text";
    } else {
        pass1.type = "password";
    }
}

function visPassord2() {

    var pass2 = document.getElementById("pass2");

    if (pass2.type === "password") {
        pass2.type = "text";
    } else {
        pass2.type = "password";
    }
}

var sjekkPassord = function() {
    if (document.getElementById('pass1').value ===
        document.getElementById('pass2').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Passordet er verifisert';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Passordene er ikke like';
    }
};










