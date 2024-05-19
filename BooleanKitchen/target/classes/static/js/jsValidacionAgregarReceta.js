
var currentTab = 0; // Variable para rastrear el paso actual del formulario
showTab(currentTab); // Mostrar el paso actual

function showTab(n) {
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block"; // Mostrar el paso actual
    if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Agregar receta";
    } else {
        document.getElementById("nextBtn").innerHTML = "Siguiente";
    }
    fixStepIndicator(n); // Actualizar los indicadores de los pasos
}

function nextPrev(n, event) {
    var x = document.getElementsByClassName("tab");
    if (n == 1 && !validateForm())
        return false; // Validar el formulario antes de avanzar

    currentTab = currentTab + n; // Actualizar el paso actual
    if (currentTab >= x.length) {
        currentTab = currentTab - n;
        prepararEnvioFormulario(event);
        return false; //-----------------------------------------saasdasdasd----------------
    }
    x[currentTab - n].style.display = "none"; // Ocultar el paso actual
    showTab(currentTab); // Mostrar el siguiente paso
}


function validateForm() {
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].querySelectorAll("#input, select, #file, #file2, #hora, #input_paso"); //, #file3

    for (i = 0; i < y.length; i++) {
        // Si el campo es requerido y está vacío
        if (y[i].hasAttribute('required') && isEmpty(y[i])) {

            y[i].classList.add("invalid");
            valid = false;
//            if (y[i].type === 'file') {
//                y[i].parentNode.querySelector('.header').classList.add("invalid");
//            }

            if (!tableIsEmpty(currentTab) && (currentTab === 1 || currentTab === 2 || currentTab === 4)) {
                y[i].classList.remove('invalid');
                valid = true;
            }
        }
    }

    if (valid) {
        document.getElementsByClassName("step")[currentTab].classList.add("finish");
    }
    return valid;
}

// Función para verificar si un campo está vacío
function isEmpty(element) {
    if (element.type === 'file') {
        if (!element.files || element.files.length === 0) {
            // Si el input de tipo file está vacío, agrega la clase "invalid" al header
            console.log("Poner");
            element.classList.add('invalid');
            return true;
        } else {
            // Si el input de tipo file no está vacío, quita la clase "invalid" del header
            console.log("Quitar");
            element.classList.remove('invalid');
            return false;
        }

    } else if (element.tagName === 'SELECT') {
        // Para los selectores, comprobamos si la opción seleccionada es la predeterminada
        return element.selectedIndex === 0;
    } else {
        // Para los campos de entrada, comprobamos si el valor está vacío
        return element.value.trim() === '';
    }
}

function tableIsEmpty(n) {
    var tablaUtensilios = document.getElementById('table-utensilios').getElementsByTagName("tbody")[0];
    var tablaIngredientes = document.getElementById('table-ingredients').getElementsByTagName("tbody")[0];
    var tablaPasos = document.getElementById('table-pasos').getElementsByTagName("tbody")[0];
    if (tablaUtensilios.childElementCount === 0 && n === 1) {
        console.log(tablaUtensilios.childElementCount);
        return true;
    } else if (tablaIngredientes.childElementCount === 0 && n === 2) {
        return true;
    } else if (tablaPasos.childElementCount === 0 && n === 4) {
        return true;
    }
    return false;
}

function fixStepIndicator(n) {
    // Función para actualizar los indicadores de los pasos
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", ""); // Eliminar la clase "active" de todos los indicadores
    }
    x[n].className += " active"; // Agregar la clase "active" al indicador del paso actual
}


// Para darle formato al input de tiempo
var inputDuracion = document.getElementById('hora');
inputDuracion.addEventListener('input', function () {
    var value = this.value;
    var cleanedValue = value.replace(/\D/g, '');
    if (cleanedValue.length > 2) {
        cleanedValue = cleanedValue.slice(0, 2) + ':' + cleanedValue.slice(2);
    }
    this.value = cleanedValue;
});


function agregarUtensilios(select) {
    var selectedOption = select.options[select.selectedIndex];
    var utensilio = selectedOption.text;
    var utensilioValue = selectedOption.value;

    var table = document.querySelector('#table-utensilios tbody');

    if (utensilioValue.trim() === '') {
        return false;
    }

    if (noExisteValorEnTabla(table, utensilioValue)) {
        var newRow = document.createElement('tr');
        newRow.setAttribute('value', utensilioValue);

        var cellUtensilios = document.createElement('td');
        cellUtensilios.textContent = utensilio;

        var cellActions = document.createElement('td');
        cellActions.className = 'acciones';
        var deleteButton = document.createElement('button');
        deleteButton.className = 'deleteBtn';
        deleteButton.title = 'Eliminar utensilio';
        deleteButton.onclick = function () {
            eliminarUtensilios(this);
        };

        var deleteTopSvg = '<svg class="delete-top" viewBox="0 0 39 7" fill="none" xmlns="http://www.w3.org/2000/svg"><line y1="5" x2="39" y2="5" stroke="white" stroke-width="4"></line><line x1="12" y1="1.5" x2="26.0357" y2="1.5" stroke="white" stroke-width="3"></line></svg>';

        var deleteBottomSvg = '<svg class="delete-bottom" viewBox="0 0 33 39" fill="none" xmlns="http://www.w3.org/2000/svg"><mask id="path-1-inside-1_8_19" fill="white"><path d="M0 0H33V35C33 37.2091 31.2091 39 29 39H4C1.79086 39 0 37.2091 0 35V0Z"></path></mask><path d="M0 0H33H0ZM37 35C37 39.4183 33.4183 43 29 43H4C-0.418278 43 -4 39.4183 -4 35H4H29H37ZM4 43C-0.418278 43 -4 39.4183 -4 35V0H4V35V43ZM37 0V35C37 39.4183 33.4183 43 29 43V35V0H37Z" fill="white" mask="url(#path-1-inside-1_8_19)"></path><path d="M12 6L12 29" stroke="white" stroke-width="4"></path><path d="M21 6V29" stroke="white" stroke-width="4"></path></svg>';

        deleteButton.innerHTML = deleteTopSvg + deleteBottomSvg;

        cellActions.appendChild(deleteButton);

        newRow.appendChild(cellUtensilios);
        newRow.appendChild(cellActions);

        table.appendChild(newRow);
        select.selectedIndex = 0;
        select.blur();
    } else {
        select.selectedIndex = 0;
        select.blur();
        alert("Ya existe");
    }


}

function agregarIngrediente(select) {
    var selectedOption = select.options[select.selectedIndex]; // Obtener la opción seleccionada
    var ingredient = selectedOption.text; // Obtener el texto de la opción seleccionada
    var ingredientValue = selectedOption.value;

    var table = document.querySelector('#table-ingredients tbody');

    if (noExisteValorEnTabla(table, ingredientValue)) {
        var newRow = document.createElement('tr');
        newRow.setAttribute('value', ingredientValue);

        var cellIngredient = document.createElement('td');
        cellIngredient.textContent = ingredient;

        var cellActions = document.createElement('td');
        cellActions.className = 'acciones';
        var deleteButton = document.createElement('button');
        deleteButton.className = 'deleteBtn';
        deleteButton.title = 'Eliminar ingrediente';
        deleteButton.onclick = function () {
            eliminarIngredientes(this);
        };

        var deleteTopSvg = '<svg class="delete-top" viewBox="0 0 39 7" fill="none" xmlns="http://www.w3.org/2000/svg"><line y1="5" x2="39" y2="5" stroke="white" stroke-width="4"></line><line x1="12" y1="1.5" x2="26.0357" y2="1.5" stroke="white" stroke-width="3"></line></svg>';

        var deleteBottomSvg = '<svg class="delete-bottom" viewBox="0 0 33 39" fill="none" xmlns="http://www.w3.org/2000/svg"><mask id="path-1-inside-1_8_19" fill="white"><path d="M0 0H33V35C33 37.2091 31.2091 39 29 39H4C1.79086 39 0 37.2091 0 35V0Z"></path></mask><path d="M0 0H33H0ZM37 35C37 39.4183 33.4183 43 29 43H4C-0.418278 43 -4 39.4183 -4 35H4H29H37ZM4 43C-0.418278 43 -4 39.4183 -4 35V0H4V35V43ZM37 0V35C37 39.4183 33.4183 43 29 43V35V0H37Z" fill="white" mask="url(#path-1-inside-1_8_19)"></path><path d="M12 6L12 29" stroke="white" stroke-width="4"></path><path d="M21 6V29" stroke="white" stroke-width="4"></path></svg>';

        deleteButton.innerHTML = deleteTopSvg + deleteBottomSvg;

        cellActions.appendChild(deleteButton);

        newRow.appendChild(cellIngredient);
        newRow.appendChild(cellActions);

        table.appendChild(newRow);
        select.selectedIndex = 0;
        select.blur();
    } else {
        select.selectedIndex = 0;
        select.blur();
        alert("Ya existe");
    }


}

function agregarPaso() {
    event.preventDefault();
    // Obtener el valor del campo de texto
    var paso = document.getElementById("input_paso").value;
    var titulo = document.getElementById("Titulo").value;
    if (paso.trim() === '' || titulo.trim() === '') {
        return false;
    }
    // Obtener el archivo de imagen seleccionado por el usuario
    var fileInput = document.getElementById("file3");
    var imagenFile = fileInput.files[0];


    var formData = new FormData();
    formData.append("img", imagenFile);
    formData.append("texto", titulo);
    formData.append("paso", paso);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/PreparacionIndex/addStep", true);
    xmlhttp.setRequestHeader("enctype", "multipart/form-data");
    xmlhttp.send(formData);

    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            // Obtener el elemento de la tabla donde se agregarán los datos
            var tbody = document.getElementById("tbody-table3");

            // Crear una nueva fila para la tabla
            var newRow = document.createElement("tr");

            // Crear las celdas para la nueva fila
            var tituloCell = document.createElement("td");
            var pasoCell = document.createElement("td");
            var imagenCell = document.createElement("td");
            var accionesCell = document.createElement("td");

            // Agregar el texto del paso a la celda correspondiente
            tituloCell.textContent = titulo;
            pasoCell.textContent = paso;

            // Crear un elemento de imagen y establecer una imagen por defecto si no se ha cargado ninguna
            var imagen = document.createElement("img");
            imagen.alt = "Imagen";
            imagen.style.width = "50px";
            imagen.style.height = "50px";

            // Función de devolución de llamada que se ejecuta cuando se carga la imagen
            var reader = new FileReader();
            reader.onload = function (event) {
                // Obtener la URL de la imagen cargada
                var imageUrl = event.target.result;
                // Establecer la URL de la imagen cargada en el elemento de imagen
                imagen.src = imageUrl;
            };

            // Leer el archivo de imagen como una URL de datos
            if (imagenFile) {
                reader.readAsDataURL(imagenFile);
            } else {
                // Si no se ha seleccionado ninguna imagen, cargar una imagen por defecto
                imagen.src = "/assets/no_img.png"; // Ruta de la imagen por defecto
            }

            // Agregar la imagen a la celda correspondiente
            imagenCell.appendChild(imagen);

            // Agregar botones de acciones a la celda correspondiente (editar y eliminar)
            accionesCell.innerHTML = `
        <div style="width: 100%; display: flex; justify-content: center;">
            <button class="editBtn" alt="Editar" onclick="editarPaso(this)">
                <svg height="1em" viewBox="0 0 512 512">
                                                          <path
                                                            d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"
                                                          ></path>
                                                        </svg>
            </button>
            <button class="deleteBtn" alt="Eliminar" onclick="eliminarPaso(this)">
                <svg
                                                          class="delete-top"
                                                          viewBox="0 0 39 7"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <line y1="5" x2="39" y2="5" stroke="white" stroke-width="4"></line>
                                                          <line
                                                            x1="12"
                                                            y1="1.5"
                                                            x2="26.0357"
                                                            y2="1.5"
                                                            stroke="white"
                                                            stroke-width="3"
                                                          ></line>
                                                        </svg>
                                                        <svg
                                                          class="delete-bottom"
                                                          viewBox="0 0 33 39"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <mask id="path-1-inside-1_8_19" fill="white">
                                                            <path
                                                              d="M0 0H33V35C33 37.2091 31.2091 39 29 39H4C1.79086 39 0 37.2091 0 35V0Z"
                                                            ></path>
                                                          </mask>
                                                          <path
                                                            d="M0 0H33H0ZM37 35C37 39.4183 33.4183 43 29 43H4C-0.418278 43 -4 39.4183 -4 35H4H29H37ZM4 43C-0.418278 43 -4 39.4183 -4 35V0H4V35V43ZM37 0V35C37 39.4183 33.4183 43 29 43V35V0H37Z"
                                                            fill="white"
                                                            mask="url(#path-1-inside-1_8_19)"
                                                          ></path>
                                                          <path d="M12 6L12 29" stroke="white" stroke-width="4"></path>
                                                          <path d="M21 6V29" stroke="white" stroke-width="4"></path>
                                                        </svg>
            </button>
        </div>
    `;

            // Agregar las celdas a la nueva fila
            newRow.appendChild(tituloCell);
            newRow.appendChild(pasoCell);
            newRow.appendChild(imagenCell);
            newRow.appendChild(accionesCell);

            // Agregar la nueva fila a la tabla
            tbody.appendChild(newRow);

            // Limpiar el campo de texto
            document.getElementById("input_paso").value = "";
            fileInput.value = "";
        }
    }


}

function noExisteValorEnTabla(tabla, valor) {
    var filas = tabla.querySelectorAll('tr');

    for (var i = 0; i < filas.length; i++) {
        var valorFila = filas[i].getAttribute('value');

        if (valorFila === valor) {
            return false;
        }
    }
    return true;
}

function eliminarUtensilios(button) {
    event.preventDefault();
    var row = button.closest('tr');
    row.remove();
}

function eliminarIngredientes(button) {
    event.preventDefault();
    var row = button.closest('tr');
    row.remove();
}

function eliminarPaso(button) {
    event.preventDefault();
    var row = button.closest('tr');



    // Realiza tu XMLHttpRequest con el código obtenido
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "/PreparacionIndex/deletePas/" + row.cells[0].innerText, true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            row.remove();
        }
    }
    
    row.remove();
}
function editarPaso(button) {
    event.preventDefault();
    // Abrir un dialog para editar los datos de las filas
    alert("Editar paso");
}




function prepararEnvioFormulario(event) {
    event.preventDefault();
    var tam = 5; //Los 5 tab

    for (var i = 0; i < tam; i++) {
        var x, y, j, valid = true;
        x = document.getElementsByClassName("tab");
        y = x[i].querySelectorAll("#input, select, #file, #file2, #hora"); //, #file3

        for (j = 0; j < y.length; j++) {
            // Si el campo es requerido y está vacío
            if (y[j].hasAttribute('required') && isEmpty(y[j])) {
                y[j].classList.add("invalid");
                valid = false;
//                if (y[j].type === 'file') {
//                    y[j].parentNode.querySelector('.header').classList.add("invalid");
//                }

                if (!tableIsEmpty(i) && (i === 1 || i === 2 || i === 4)) {
                    y[j].classList.remove('invalid');
                    valid = true;
                }
            }
        }
    }

    if (tableIsEmpty(4)) {
        valid = false;
        document.getElementById("input_paso").classList.add("invalid");
    }


    if (valid) {
        //        Utensilios
        var tablaUtensilios = document.getElementById("table-utensilios");
        var filasUtensilios = tablaUtensilios.getElementsByTagName("tr");
        var datosTablaUtensilios = [];
        for (var i = 1; i < filasUtensilios.length; i++) {
            var celdas = filasUtensilios[i].getElementsByTagName("td");
            datosTablaUtensilios.push(celdas[0].innerText);
        }
        var tablaUtensiliosData = document.createElement("input");
        tablaUtensiliosData.setAttribute("type", "hidden");
        tablaUtensiliosData.setAttribute("name", "datosTablaUtensilios");
        tablaUtensiliosData.setAttribute("value", JSON.stringify(datosTablaUtensilios));
        document.getElementById("recipeForm").appendChild(tablaUtensiliosData);

        //        Ingredientes
        var tablaIngredientes = document.getElementById("table-ingredients");
        var filasIngredientes = tablaIngredientes.getElementsByTagName("tr");
        var datosTablaIngredientes = [];

        for (var i = 1; i < filasIngredientes.length; i++) {
            var celdas = filasIngredientes[i].getElementsByTagName("td");
            datosTablaIngredientes.push(celdas[0].innerText);
        }

        var tablaIngredientesData = document.createElement("input");
        tablaIngredientesData.setAttribute("type", "hidden");
        tablaIngredientesData.setAttribute("name", "datosTablaIngredientes");
        tablaIngredientesData.setAttribute("value", JSON.stringify(datosTablaIngredientes));
        // Agregar el campo oculto al formulario
        document.getElementById("recipeForm").appendChild(tablaIngredientesData);


        document.getElementById("recipeForm").submit();
        return true;
    } else {
        return false;
        //        Mostrar mensaje de que faltan datos
    }
}