function validarNumero(event) {
    var input = event.target;
    var valor = parseInt(input.value, 10);
    if (valor < parseInt(input.min, 10)) {
        input.value = input.min;
    } else if (valor > parseInt(input.max, 10)) {
        input.value = input.max;
    }
}




function enviarComentario(event) {
    event.preventDefault();
    var identificador = document.getElementById("identificadorReceta").value;

    var calificacion = document.querySelector('input[name="rate"]:checked');
    var tituloComentario = document.getElementById('commentTitle');
    var contenidoComentario = document.getElementById('commentContent');
    var dificultad = document.getElementById("dificultad");
    var claridadInstrucciones = document.getElementById('clarity_instructions');
    var sugerencias = document.getElementById('suggestion');
    var modificacionReceta = document.getElementById("modification-yes").checked ? true : false;
    var recomendacionReceta = document.getElementById("recommendation-yes").checked ? true : false;

    var pageSize;
    var currentPage;

    try {
        pageSize = document.getElementById("pageSize").value;
        currentPage = document.querySelector(".pagination .active a").innerText;
    } catch (error) {

    }


    // Construir la URL con todos los parámetros
    var url = "/c_comentarios/comentar?";
    url += "id=" + identificador;
    url += "&tCalificacion=" + calificacion.value;
    url += "&tTitulo=" + encodeURIComponent(tituloComentario.value);
    url += "&tComentario=" + encodeURIComponent(contenidoComentario.value);
    url += "&tDificultad=" + encodeURIComponent(dificultad.value);
    url += "&tClaridadDeInstrucciones=" + claridadInstrucciones.value;
    url += "&tModificoLaReceta=" + modificacionReceta;
    url += "&tSugerencias=" + encodeURIComponent(sugerencias.value);
    url += "&tRecomendaria=" + recomendacionReceta;

    if (currentPage) {
        url += "&page=" + currentPage;
    }
    if (pageSize) {
        url += "&size=" + pageSize;
    }

    // Crear la solicitud XMLHttpRequest
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, true);

    // Enviar la solicitud
    xmlhttp.send();

    // Manejar la respuesta
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                // Si la respuesta es exitosa, verificar si contiene elementos de paginación
                var responseText = this.responseText;
                var containsPagination = responseText.includes("pagination-container-pageSizeOpt");

                // Verificar si contiene comentarios
                var containsComments = responseText.includes("class=\"container-comment-order\"");

                if (containsPagination && containsComments) {
                    // Si contiene tanto elementos de paginación como comentarios, actualizar el contenedor de comentarios
                    var contenedorComentarios = document.getElementById("comments");
                    contenedorComentarios.innerHTML = responseText;
                } else if (containsPagination) {
                    // Si solo contiene elementos de paginación, agregarlos al contenedor de comentarios
                    var contenedorComentarios = document.getElementById("comments");
                    contenedorComentarios.innerHTML = responseText;
                } else {
                    // Si no contiene ni elementos de paginación ni comentarios, es probable que sea una notificación
                    var contenedorNotificaciones = document.getElementById("container-notification");
                    contenedorNotificaciones.innerHTML = responseText;
                }
                setTimeout(eliminarElementos, 8000);

                document.querySelector('input[name="rate"]:checked').checked = false;
                document.getElementById('commentTitle').value = "";
                document.getElementById('commentContent').value = "";
                document.getElementById("dificultad").value = "";
                document.getElementById('clarity_instructions').value = "";
                document.getElementById('suggestion').value = "";
                document.getElementById("modification-yes").checked = true;
                document.getElementById("recommendation-yes").checked = true;

                document.getElementById("commentForm").style.display = "none";

            }
        }
    };

}



function like_dislike(cbox) {
    var numero_likes = cbox.parentElement.querySelector('#numero-likes');
    var idComentario = cbox.parentElement.querySelector("#idComentario").value;
    var xmlhttp = new XMLHttpRequest();
    if (cbox.checked) {
        // Si el checkbox está marcado, incrementa el número de likes
        numero_likes.innerHTML = parseInt(numero_likes.innerHTML) + 1;
        xmlhttp.open("GET", "/c_comentarios/like?identificadorComentario=" + idComentario + "&aumentar=1", true);
    } else {
        // Si el checkbox no está marcado, decrementa el número de likes
        numero_likes.innerHTML = parseInt(numero_likes.innerHTML) - 1;
        xmlhttp.open("GET", "/c_comentarios/like?identificadorComentario=" + idComentario + "&aumentar=0", true);
    }


    // Enviar la solicitud
    xmlhttp.send();

    // Manejar la respuesta
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4) {

            if (this.status === 200) {

                // Si la respuesta es exitosa, actualizar el contenido del contenedor de comentarios
                var contenedor = document.getElementById("container-notification");
                contenedor.innerHTML = this.responseText;
                setTimeout(eliminarElementos, 8000);
            }
        }
    };

}


function editComment(event, button) {
    event.preventDefault();
    // Obtener los valores de los elementos del formulario
    var identificadorComentario = button.parentElement.querySelector("#idComentario").value;
    var identificadorReceta = document.getElementById("identificadorReceta").value;

    var calificacion = document.querySelector('input[name="rate"]:checked').value;
    var titulo = document.getElementById('commentTitle').value;
    var comentario = document.getElementById('commentContent').value;
    var dificultad = document.getElementById("dificultad").value;
    var claridadInstrucciones = document.getElementById('clarity_instructions').value;
    var sugerencias = document.getElementById('suggestion').value;
    var modifico = document.getElementById("modification-yes").checked;
    var recomendaria = document.getElementById("recommendation-yes").checked;

    var page;
    var size;

    try {
        size = document.getElementById("pageSize").value;
        page = document.querySelector(".pagination .active a").innerText;
    } catch (error) {
        // Si ocurre algún error, se mantienen los valores predeterminados
    }

    // Construir la URL con todos los parámetros
    var url = "/c_comentarios/editarComentario?";
    url += "identificadorComentario=" + identificadorComentario;
    url += "&identificadorReceta=" + identificadorReceta;
    url += "&tCalificacion=" + encodeURIComponent(calificacion);
    url += "&tTitulo=" + encodeURIComponent(titulo);
    url += "&tComentario=" + encodeURIComponent(comentario);
    url += "&tDificultad=" + encodeURIComponent(dificultad);
    url += "&tClaridadDeInstrucciones=" + encodeURIComponent(claridadInstrucciones);
    url += "&tModificoLaReceta=" + modifico;
    url += "&tSugerencias=" + encodeURIComponent(sugerencias);
    url += "&tRecomendaria=" + recomendaria;
    
    if(currentPage && pageSize){
        url += "&page=" + currentPage;
        url += "&size=" + pageSize;
    }

    // Crear la solicitud XMLHttpRequest
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, true);

    // Enviar la solicitud
    xmlhttp.send();

    // Manejar la respuesta
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                // Si la respuesta es exitosa, verificar si contiene elementos de paginación
                var responseText = this.responseText;
                var containsPagination = responseText.includes("pagination-container-pageSizeOpt");

                // Verificar si contiene comentarios
                var containsComments = responseText.includes("class=\"container-comment-order\"");

                if (containsPagination && containsComments) {
                    // Si contiene tanto elementos de paginación como comentarios, actualizar el contenedor de comentarios
                    var contenedorComentarios = document.getElementById("comments");
                    contenedorComentarios.innerHTML = responseText;
                } else if (containsPagination) {
                    // Si solo contiene elementos de paginación, agregarlos al contenedor de comentarios
                    var contenedorComentarios = document.getElementById("comments");
                    contenedorComentarios.innerHTML = responseText;
                } else {
                    // Si no contiene ni elementos de paginación ni comentarios, es probable que sea una notificación
                    var contenedorNotificaciones = document.getElementById("container-notification");
                    contenedorNotificaciones.innerHTML = responseText;
                }
                setTimeout(eliminarElementos, 8000);

                document.querySelector('input[name="rate"]:checked').checked = false;
                document.getElementById('commentTitle').value = "";
                document.getElementById('commentContent').value = "";
                document.getElementById("dificultad").value = "";
                document.getElementById('clarity_instructions').value = "";
                document.getElementById('suggestion').value = "";
                document.getElementById("modification-yes").checked = true;
                document.getElementById("recommendation-yes").checked = true;

                document.getElementById("commentFormModify").style.display = "none";
            }
        }
    };
}


function deleteComment(button) {
    // Aquí puedes agregar la lógica para eliminar el comentario
    var idComentario = button.parentElement.querySelector("#idComentario").value;
    var idReceta = document.getElementById("identificadorReceta").value;

    var pageSize;
    var currentPage;

    try {
        pageSize = document.getElementById("pageSize").value;
        currentPage = document.querySelector(".pagination .active a").innerText - 1;
    } catch (error) {

    }
    if(!idReceta){
        return false;
    }
    var url = "/c_comentarios/eliminarComentario?identificadorComentario=" + idComentario + "&idReceta=" + idReceta
    if (currentPage) {
        url += "&page=" + currentPage;
    }
    if (pageSize) {
        url += "&size=" + pageSize;
    }

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, true);

    // Enviar la solicitud
    xmlhttp.send();

    // Manejar la respuesta
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4) {

            if (this.status === 200) {

                // Si la respuesta es exitosa, actualizar el contenido del contenedor de comentarios
                var contenedor = document.getElementById("comments");
                contenedor.innerHTML = this.responseText;

                setTimeout(eliminarElementos, 8000);
            }
        }
    };
}



function cerrarFormularios(button){
    var formulario = button.parentElement.parentElement;
    formulario.remove();
}

