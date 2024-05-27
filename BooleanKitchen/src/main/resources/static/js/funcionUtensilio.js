function eliminarUtensilio(button){
    var id= button.getAttribute("data-identificador");
    var url=button.getAttribute("url");

    var xmlhttp = new XMLHttpRequest();

    var TablaOrigen = document.getElementById("TablaUtensilio");

    xmlhttp.open("GET", "/c_utensilio/BorrarUtensilio/" + id+"/"+url);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200){
            TablaOrigen.innerHTML="";
            TablaOrigen.innerHTML = this.responseText;
            
        }

    };

};



function cambiarForm(accion) {
    document.getElementById('FormU').action = accion;
}