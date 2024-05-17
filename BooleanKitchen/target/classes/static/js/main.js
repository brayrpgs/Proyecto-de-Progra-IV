/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

/*Para funcionamiento del inpu img*/

function showImagePreview(file) {
    var reader = new FileReader();
            
    //Creamos una img y lo agregamos
    var img = document.createElement("img");
    img.setAttribute("id", "imagen-usuario");
    var header = document.querySelector('.headImgInput');
    header.appendChild(img);

    reader.onload = function() {
        var container = document.querySelector(".container");
        var header = document.querySelector(".header");
        var footer = document.querySelector(".footer");

        // Eliminar el svg
        var svg = document.querySelector('.headImgInput svg');
        var p = document.querySelector('.headImgInput p');
        if (svg && p) {
            svg.parentNode.removeChild(svg);
            p.parentNode.removeChild(p);
        }

        // Asignar la imagen cargada al elemento img
        img.src = reader.result;
        img.style.display = "block"; // Mostrar la imagen

    }

    reader.readAsDataURL(file);

}

document.addEventListener("DOMContentLoaded", function() {
//Se actualiza el p con el nombre de la imagen y se manda a cargar la imagen
    document.getElementById("file").addEventListener("change", function(event) {
        var fileInput = this;
        var fileName = fileInput.value.split("\\").pop(); 
        var labelText = document.querySelector("#sms-imagen");
        if (fileName) {
            labelText.innerHTML = fileName;
            showImagePreview(event.target.files[0]);
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    //Carga el svg si no existe ya que se elimina la imagen
        document.getElementById("deleteBtn").addEventListener("click", function() {
            var header = document.querySelector('.headImgInput');
            var img = document.getElementById("imagen-usuario");
    
            // Si existe una imagen cargada, la eliminamos
            if (img && !img.hidden) {
                cargarSvg(); // Cargamos el SVG nuevamente
            } 
        });
});


function cargarSvg() {
    var header = document.querySelector('.headImgInput');
    
    // Eliminar la imagen si existe
    var img = document.getElementById("imagen-usuario");
    if (img) {
        img.parentNode.removeChild(img);
    }
    
    // Crear el nuevo SVG
    var nuevoSvg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    nuevoSvg.setAttribute("viewBox", "0 0 24 24");
    nuevoSvg.setAttribute("fill", "");
    nuevoSvg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    
    // Crear el path dentro del SVG
    var path = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path.setAttribute("fill-rule", "evenodd");
    path.setAttribute("clip-rule", "evenodd");
    path.setAttribute("d", "M10 1C9.73478 1 9.48043 1.10536 9.29289 1.29289L3.29289 7.29289C3.10536 7.48043 3 7.73478 3 8V20C3 21.6569 4.34315 23 6 23H7C7.55228 23 8 22.5523 8 22C8 21.4477 7.55228 21 7 21H6C5.44772 21 5 20.5523 5 20V9H10C10.5523 9 11 8.55228 11 8V3H18C18.5523 3 19 3.44772 19 4V9C19 9.55228 19.4477 10 20 10C20.5523 10 21 9.55228 21 9V4C21 2.34315 19.6569 1 18 1H10ZM9 7H6.41421L9 4.41421V7ZM14 15.5C14 14.1193 15.1193 13 16.5 13C17.8807 13 19 14.1193 19 15.5V16V17H20C21.1046 17 22 17.8954 22 19C22 20.1046 21.1046 21 20 21H13C11.8954 21 11 20.1046 11 19C11 17.8954 11.8954 17 13 17H14V16V15.5ZM16.5 11C14.142 11 12.2076 12.8136 12.0156 15.122C10.2825 15.5606 9 17.1305 9 19C9 21.2091 10.7909 23 13 23H20C22.2091 23 24 21.2091 24 19C24 17.1305 22.7175 15.5606 20.9844 15.122C20.7924 12.8136 18.858 11 16.5 11Z");
    
    // Agregar el path al SVG
    nuevoSvg.appendChild(path);
    
    // Agregar el nuevo SVG y el párrafo al header
    header.appendChild(nuevoSvg);
    
    var p = document.createElement("p");
    var textNode = document.createTextNode("Cargar imagen");
    p.appendChild(textNode);
    header.appendChild(p);
    
    var labelText = document.querySelector("#sms-imagen");
    labelText.innerHTML = "No hay imagen";
}

function eventAct(){
   document.getElementById('file').addEventListener('change', handleFileSelect); 
}
   

    function handleFileSelect(event) {
        const fileInput = event.target;
        const file = fileInput.files[0];
        const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];

        if (file && allowedTypes.includes(file.type)) {
            // El archivo seleccionado es una imagen válida
           
        } else {
            // El archivo no es una imagen válida, mostrar mensaje de error
         
            // Eliminar el archivo seleccionado para prevenir su envío
            fileInput.value = '';
        }
    }
