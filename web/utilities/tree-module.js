/* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
function openNav() {
    document.getElementById("openNav").style.display = "block";
    document.getElementById("closeNav").style.display = "block";
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("openNav").style.display = "block";
    document.getElementById("closeNav").style.display = "none";
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}
function myAccFunc() {
    var x = document.getElementById("demoAcc");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-black";
    } else {
        x.className = x.className.replace(" w3-show", "");
        x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-black", "");
    }
}

function myDropFunc() {
    var x = document.getElementById("demoDrop");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-black";
    } else {
        x.className = x.className.replace(" w3-show", "");
        x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-black", "");
    }
}