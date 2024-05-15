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
    var identificador = document.getElementsByName("identificadorReceta")[0].value;

    var calificacion = document.querySelector('input[name="rate"]:checked');
    var tituloComentario = document.getElementById('commentTitle');
    var contenidoComentario = document.getElementById('commentContent');
    var dificultad = document.getElementById("dificultad");
    var claridadInstrucciones = document.getElementById('clarity_instructions');
    var sugerencias = document.getElementById('suggestion');
    var modificacionReceta = document.getElementById("modification-yes").checked ? true : false;
    var recomendacionReceta = document.getElementById("recommendation-yes").checked ? true : false;

    if (!calificacion || !tituloComentario.value || !contenidoComentario.value || !dificultad.value || !claridadInstrucciones.value) {
        // Mostrar un mensaje de error si algún campo está incompleto
        alert('Por favor complete todos los campos requeridos.');
        return false;
    }

    alert(identificador);
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

    // Crear la solicitud XMLHttpRequest
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
            } else {
                // Si hay un error, mostrar un mensaje
                alert('Error al enviar el comentario. Por favor, inténtelo de nuevo.');
            }
        }
    };

    // Evitar que se envíe el formulario
    return false;
}



function like_dislike(cbox) {
    var numero_likes = cbox.parentElement.querySelector('#numero-likes');

    if (cbox.checked) {
        // Si el checkbox está marcado, incrementa el número de likes
        numero_likes.innerHTML = parseInt(numero_likes.innerHTML) + 1;
    } else {
        // Si el checkbox no está marcado, decrementa el número de likes
        numero_likes.innerHTML = parseInt(numero_likes.innerHTML) - 1;
    }

    // Aquí puedes agregar la lógica para enviar a la base de datos y actualizar los likes
}


function editComment(button) {
    // Aquí puedes agregar la lógica para editar el comentario
    var idComentario = button.parentElement.querySelector("#idCategoria").value;
    
}

function deleteComment(button) {
    // Aquí puedes agregar la lógica para eliminar el comentario
    var idComentario = button.parentElement.querySelector("#idCategoria").value;
    
}




