function eliminarElementos() {
    var elementos = document.querySelectorAll('#unaNotificacion');
    elementos.forEach(function(elemento) {
        // Obtener el estilo calculado del elemento
        var estilo = window.getComputedStyle(elemento);
        
        // Verificar si la propiedad visibility está configurada en 'visible'
        if (estilo.visibility === 'visible') {
            // Si está visible, esperar 15 segundos antes de eliminarlo
            setTimeout(function() {
                elemento.remove();
            }, 15000); // 15 segundos en milisegundos
        } else {
            // Si está oculto, eliminar el elemento de inmediato
            elemento.remove();
        }
    });
}