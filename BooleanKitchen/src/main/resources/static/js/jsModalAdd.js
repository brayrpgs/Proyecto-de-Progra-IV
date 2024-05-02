
document.addEventListener("DOMContentLoaded", function() {
    // Modal
    const openBtn = document.querySelector("[data-open]");
    const modal = document.querySelector("#modal-add-recipe");

    // Open the modal when the button is clicked
    openBtn.addEventListener("click", () => {
        //Cierra el menu
        const navigationMenu = document.querySelector('.navigation__menu');
        navigationMenu.classList.add('hide');
        // bloquea el scroll
        document.body.style.overflow = 'hidden';

        deleteComponents();
        agregarInputsItems();

      modal.showModal();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const closeBtn = document.querySelector("[data-close]");
    const modal = document.querySelector("#modal-add-recipe");
// Close the modal when the close button is clicked
    closeBtn.addEventListener("click", () => {
        // Restaurar el scroll
        document.body.style.overflow = 'auto';

      modal.close();
    });
});


document.addEventListener("DOMContentLoaded", function() {
//Carga el svg si no existe ya que se elimina la imagen
    document.getElementById("deleteBtn").addEventListener("click", function() {
        var header = document.querySelector('.header');
        var img = document.getElementById("imagen-usuario");

        // Si existe una imagen cargada, la eliminamos
        if (img && !img.hidden) {
            cargarSvg(); // Cargamos el SVG nuevamente
        } 
    });
});

//Se muestra la imagen y se elimina el svg
function showImagePreview(file) {
    var reader = new FileReader();
            
    //Creamos una img y lo agregamos
    var img = document.createElement("img");
    img.setAttribute("id", "imagen-usuario");
    var header = document.querySelector('.header');
    header.appendChild(img);

    reader.onload = function() {
        var container = document.querySelector(".container");
        var header = document.querySelector(".header");
        var footer = document.querySelector(".footer");

        // Eliminar el svg
        var svg = document.querySelector('.header svg');
        var p = document.querySelector('.header p');
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

//Carga nuevamente el svg
function cargarSvg() {
    var header = document.querySelector('.header');
    
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



function deleteComponents() {
    var componentes = document.querySelectorAll(".inputs-items");
    componentes.forEach(function(componente) {
        componente.parentNode.removeChild(componente);
    });
}

function agregarInputsItems() {
    var dialogo = document.querySelector('.inputs-modal'); // Obtener referencia al contenedor

    // Crear el primer elemento con input de texto
    var primerElemento = document.createElement('div');
    primerElemento.classList.add('inputs-items');
    primerElemento.innerHTML = `
        <div class="wave-group">
            <input required="" type="text" class="input">
            <span class="bar"></span>
            <label class="label">
                <span class="label-char" style="--index: 0">N</span>
                <span class="label-char" style="--index: 1">o</span>
                <span class="label-char" style="--index: 2">m</span>
                <span class="label-char" style="--index: 3">b</span>
                <span class="label-char" style="--index: 4">r</span>
                <span class="label-char" style="--index: 5">e</span>
            </label>
        </div>
    `;

    // Crear el segundo elemento con select
    var segundoElemento = document.createElement('div');
    segundoElemento.classList.add('inputs-items');
    segundoElemento.innerHTML = `
        <div class="wave-group">
            <select required="" class="input">
                <option value="" disabled selected hidden></option>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
            </select>
            <span class="bar"></span>
            <label class="label">
                <span class="label-char" style="--index: 0">O</span>
                <span class="label-char" style="--index: 1">r</span>
                <span class="label-char" style="--index: 2">i</span>
                <span class="label-char" style="--index: 3">g</span>
                <span class="label-char" style="--index: 4">e</span>
                <span class="label-char" style="--index: 5">n</span>
            </label>
        </div>
    `;

    // Crear el tercer elemento con otro select
    var tercerElemento = document.createElement('div');
    tercerElemento.classList.add('inputs-items');
    tercerElemento.innerHTML = `
        <div class="wave-group">
            <select required="" class="input">
                <option value="" disabled selected hidden></option>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
            </select>
            <span class="bar"></span>
            <label class="label">
                <span class="label-char" style="--index: 0">C</span>
                <span class="label-char" style="--index: 1">a</span>
                <span class="label-char" style="--index: 2">t</span>
                <span class="label-char" style="--index: 3">e</span>
                <span class="label-char" style="--index: 4">g</span>
                <span class="label-char" style="--index: 5">o</span>
                <span class="label-char" style="--index: 6">r</span>
                <span class="label-char" style="--index: 7">i</span>
                <span class="label-char" style="--index: 8">a</span>
            </label>
        </div>
    `;

    // Crear el cuarto elemento con carga de imagen
    var cuartoElemento = document.createElement('div');
    cuartoElemento.classList.add('inputs-items');
    cuartoElemento.innerHTML = `
            <div class="container-upload-img"> 
                <div class="header" id="header"> 
                    <svg viewBox="0 0 24 24" fill="" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path fill-rule="evenodd" clip-rule="evenodd" d="M10 1C9.73478 1 9.48043 1.10536 9.29289 1.29289L3.29289 7.29289C3.10536 7.48043 3 7.73478 3 8V20C3 21.6569 4.34315 23 6 23H7C7.55228 23 8 22.5523 8 22C8 21.4477 7.55228 21 7 21H6C5.44772 21 5 20.5523 5 20V9H10C10.5523 9 11 8.55228 11 8V3H18C18.5523 3 19 3.44772 19 4V9C19 9.55228 19.4477 10 20 10C20.5523 10 21 9.55228 21 9V4C21 2.34315 19.6569 1 18 1H10ZM9 7H6.41421L9 4.41421V7ZM14 15.5C14 14.1193 15.1193 13 16.5 13C17.8807 13 19 14.1193 19 15.5V16V17H20C21.1046 17 22 17.8954 22 19C22 20.1046 21.1046 21 20 21H13C11.8954 21 11 20.1046 11 19C11 17.8954 11.8954 17 13 17H14V16V15.5ZM16.5 11C14.142 11 12.2076 12.8136 12.0156 15.122C10.2825 15.5606 9 17.1305 9 19C9 21.2091 10.7909 23 13 23H20C22.2091 23 24 21.2091 24 19C24 17.1305 22.7175 15.5606 20.9844 15.122C20.7924 12.8136 18.858 11 16.5 11Z" fill=""></path> </g></svg>
                    <p>Cargar imagen</p>
                </div> 
                <label for="file" class="footer" id="deleteBtn"> 
                <svg fill="#000000" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M15.331 6H8.5v20h15V14.154h-8.169z"></path><path d="M18.153 6h-.009v5.342H23.5v-.002z"></path></g></svg> 
                <p id="sms-imagen">No hay imagen</p> 
                <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M5.16565 10.1534C5.07629 8.99181 5.99473 8 7.15975 8H16.8402C18.0053 8 18.9237 8.9918 18.8344 10.1534L18.142 19.1534C18.0619 20.1954 17.193 21 16.1479 21H7.85206C6.80699 21 5.93811 20.1954 5.85795 19.1534L5.16565 10.1534Z" stroke="#000000" stroke-width="2"></path> <path d="M19.5 5H4.5" stroke="#000000" stroke-width="2" stroke-linecap="round"></path> <path d="M10 3C10 2.44772 10.4477 2 11 2H13C13.5523 2 14 2.44772 14 3V5H10V3Z" stroke="#000000" stroke-width="2"></path> </g></svg>
                </label> 
                <input id="file" type="file"> 
            </div>
    `;

    // Agregar los elementos al contenedor
    dialogo.appendChild(primerElemento);
    dialogo.appendChild(segundoElemento);
    dialogo.appendChild(tercerElemento);
    dialogo.appendChild(cuartoElemento);

    document.getElementById("file").addEventListener("change", function(event) {
        var fileInput = this;
        var fileName = fileInput.value.split("\\").pop(); 
        var labelText = document.querySelector("#sms-imagen");
        if (fileName) {
            labelText.innerHTML = fileName;
            showImagePreview(event.target.files[0]);
        }
    });

    document.getElementById("deleteBtn").addEventListener("click", function() {
        var header = document.querySelector('.header');
        var img = document.getElementById("imagen-usuario");
    
        // Si existe una imagen cargada, la eliminamos
        if (img && !img.hidden) {
            cargarSvg(); // Cargamos el SVG nuevamente
        } 
    });
}

