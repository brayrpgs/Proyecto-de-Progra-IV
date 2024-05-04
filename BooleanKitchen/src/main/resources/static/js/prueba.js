function subir(){

    var formulario = document.getElementById('form');

        // Agregamos un evento de escucha para el evento "submit"
        formulario.addEventListener('submit', function(event) {
            // Evitamos el comportamiento predeterminado del formulario
            event.preventDefault();
        });

    // Obtener el archivo de entrada
    var fileInput = document.getElementById('image');
    var file = fileInput.files[0];

    if (!file) {
        alert('Por favor, selecciona una imagen.');
        return;
    }

    // Crear un objeto FormData
    var formData = new FormData();

    // Agregar la imagen al FormData
    formData.append('image', file);

    // Crear una solicitud HTTP (ejemplo con XMLHttpRequest)
    var xhr = new XMLHttpRequest();

    // Configurar la solicitud
    xhr.open('POST', '/upload', true); 
    xhr.send(formData);
    // Manejar el resultado de la solicitud
    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log('Imagen subida exitosamente');
        } else {
            console.error('Error al subir la imagen');
        }
    };


}