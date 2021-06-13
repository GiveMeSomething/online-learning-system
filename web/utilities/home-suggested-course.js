/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Add active class to the current button (highlight it)
var header = document.getElementById("myDIV");
var btns = header.getElementsByClassName("btn1");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active1");
        current[0].className = current[0].className.replace(" active1", "");
        this.className += " active1";
    });
}

//display and none
function itOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'block';
    business.style.display = 'none';
    marketing.style.display = 'none';
    ai.style.display = 'none';
    ia.style.display = 'none';
    language.style.display = 'none';
}
function businessOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'none';
    business.style.display = 'block';
    marketing.style.display = 'none';
    ai.style.display = 'none';
    ia.style.display = 'none';
    language.style.display = 'none';
}

function marketingOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'none';
    business.style.display = 'none';
    marketing.style.display = 'block';
    ai.style.display = 'none';
    ia.style.display = 'none';
    language.style.display = 'none';
}

function aiOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'none';
    business.style.display = 'none';
    marketing.style.display = 'none';
    ai.style.display = 'block';
    ia.style.display = 'none';
    language.style.display = 'none';
}

function iaOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'none';
    business.style.display = 'none';
    marketing.style.display = 'none';
    ai.style.display = 'none';
    ia.style.display = 'block';
    language.style.display = 'none';
}

function languageOn() {
    var it = document.getElementById('it');
    var business = document.getElementById('business');
    var marketing = document.getElementById('marketing');
    var ai = document.getElementById('ai');
    var ia = document.getElementById('ia');
    var language = document.getElementById('language');

    it.style.display = 'none';
    business.style.display = 'none';
    marketing.style.display = 'none';
    ai.style.display = 'none';
    ia.style.display = 'none';
    language.style.display = 'block';
}