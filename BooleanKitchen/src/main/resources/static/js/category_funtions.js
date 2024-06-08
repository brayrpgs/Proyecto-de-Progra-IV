/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


 

function openDialog(event) {
    // Obtenemos el botón que ha sido clicado
    if (event.target){
       var openBtn = event.target;   
    }
    // Buscamos el modal relacionado al botón clicado
    var modal = openBtn.closest("td").querySelector("#view-category");
    var closeBtn = modal.querySelector("[data-close]");
    
    openBtn.addEventListener("click", () => {
      modal.showModal();
    });
    closeBtn.addEventListener("click", () => {
      modal.close();
    });
  }
  
  
  //agregar categoria
  
  function sendCategoryForm(){
    
    document.getElementById("formulario-dinamico").addEventListener("submit", function(event) {
        // Evitar que se envíe el formulario
        event.preventDefault();
    });

    
        var idSerial = document.getElementById("idSerial").value.trim();
        var name = document.getElementById("name").value;
        var description = document.getElementById("description").value;
        var img=document.getElementById("file").files[0];
        var label = document.getElementById("label").value;
        var visibility = document.querySelector('input[name="Visibilidad"]:checked').value;
        var notaAdmin = document.getElementById("notaAdmin").value;
        
        if (!idSerial || !name.trim() || !description.trim() || !label.trim()) {
            var classType='adMessage';
            var type='pen-to-square-regular';
            var message='Faltan datos por llenar';
           // alert('Por favor, complete todos los campos requeridos.');
              document.getElementById("view-message").className = classType;
              document.getElementById("notificacion").className = 'warning ' + classType;
              document.querySelector("#notificacion img").src = '/icono/' + type + '.svg';
              document.querySelector("#notificacion p b").textContent = message;
              var dialog = document.getElementById("view-message");
              dialog.showModal();
            return; // Evita el envío del formulario
        }
        
        if(!/^CAT-\d{3,}$/.test(idSerial)){
             var classType='adMessage';
            var type='triangle-exclamation-solid';
            var message='El identificador tiene que contener al menos 3 numeros como mínimo.';
           
              document.getElementById("view-message").className = classType;
              document.getElementById("notificacion").className = 'warning ' + classType;
              document.querySelector("#notificacion img").src = '/icono/' + type + '.svg';
              document.querySelector("#notificacion p b").textContent = message;
              var dialog = document.getElementById("view-message");
              dialog.showModal();
            return; // Evita el envío del formulario
        }
        
        if(!img){
           // alert('Entrando');
             var classType='adMessage';
            var type='file-regular';
            var message='La imagen se encuentra vacia, por favor, llenar';
             document.getElementById("view-message").className = classType;
              document.getElementById("notificacion").className = 'warning ' + classType;
              document.querySelector("#notificacion img").src = '/icono/' + type + '.svg';
              document.querySelector("#notificacion p b").textContent = message;
              var dialog = document.getElementById("view-message");
              dialog.showModal();
              return;
        }
        
        
    //alert(description);
    
    var modal = document.getElementById("container");

    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
    
    formData.append('idSerial', idSerial);
    formData.append('name', name);
    formData.append('img', img);
    formData.append('label', label);
    formData.append('description', description);
    formData.append('Visibilidad', visibility);
    formData.append('notaAdmin', notaAdmin);

    xmlhttp.open("POST", "/categoriaIndex/agregarCat", true);
    xmlhttp.send(formData);

    xmlhttp.onreadystatechange= function (){

        if(this.readyState === 4 && this.status === 200){
            document.getElementById("idSerial").value = "CAT-";
            document.getElementById("name").value = "";
            document.getElementById("description").value = "";
            document.getElementById("file").value = ""; // Para limpiar el campo de archivo, establece su valor en ""
            document.getElementById("label").index = 0;
            document.getElementById("notaAdmin").value = "";
            modal.innerHTML = this.responseText;
           
         
            


        }
  
    };
};


function ModifyCategoryForm(){
    
    document.getElementById("formulario-dinamico").addEventListener("submit", function(event) {
        // Evitar que se envíe el formulario
        event.preventDefault();
    });

    
        var idSerial = document.getElementById("idSerial").value.trim();
        var name = document.getElementById("name").value;
        var description = document.getElementById("description").value;
        var img=document.getElementById("file").files[0];
        var label = document.getElementById("label").value;
        var visibility = document.querySelector('input[name="Visibilidad"]:checked').value;
        var notaAdmin = document.getElementById("notaAdmin").value;
        
        if (!idSerial || !name.trim() || !description.trim() || !label.trim()) {
            var classType='adMessage';
            var type='pen-to-square-regular';
            var message='Faltan datos por llenar';
           // alert('Por favor, complete todos los campos requeridos.');
              document.getElementById("view-message").className = classType;
              document.getElementById("notificacion").className = 'warning ' + classType;
              document.querySelector("#notificacion img").src = '/icono/' + type + '.svg';
              document.querySelector("#notificacion p b").textContent = message;
              var dialog = document.getElementById("view-message");
              dialog.showModal();
            return; // Evita el envío del formulario
        }
        
        if(!/^CAT-\d{3,}$/.test(idSerial)){
             var classType='adMessage';
            var type='triangle-exclamation-solid';
            var message='El identificador tiene que contener al menos 3 numeros como mínimo.';
           
              document.getElementById("view-message").className = classType;
              document.getElementById("notificacion").className = 'warning ' + classType;
              document.querySelector("#notificacion img").src = '/icono/' + type + '.svg';
              document.querySelector("#notificacion p b").textContent = message;
              var dialog = document.getElementById("view-message");
              dialog.showModal();
            return; // Evita el envío del formulario
        }
        
    
    //alert(description);
    
    var modal = document.getElementById("container");

    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
    
    formData.append('idSerial', idSerial);
    formData.append('name', name);
    formData.append('img', img);
    formData.append('label', label);
    formData.append('description', description);
    formData.append('Visibilidad', visibility);
    formData.append('notaAdmin', notaAdmin);

    xmlhttp.open("POST", "/categoriaIndex/TerminarModificaciones", true);
    xmlhttp.send(formData);

    xmlhttp.onreadystatechange= function (){

        if(this.readyState === 4 && this.status === 200){
            document.getElementById("idSerial").value = "CAT-";
            document.getElementById("name").value = "";
            document.getElementById("description").value = "";
            document.getElementById("file").value = ""; // Para limpiar el campo de archivo, establece su valor en ""
            document.getElementById("label").index = 0;
            document.getElementById("notaAdmin").value = "";
            modal.innerHTML = this.responseText;
           
            var dialog = document.getElementById("view-message");
              dialog.showModal();
 
        }
  
    };
};

  
  
   function cambiarAccion(accion) {
      document.getElementById('formulario-dinamico').action = accion;
  }
  
  function addCatPrefix(inputField) {
      if (!inputField.value.startsWith('CAT-')) {
          inputField.value = 'CAT-';
      }
      
      // Si la longitud es mayor a 4, comprobar el último carácter ingresado
      if (inputField.value.length > 4) {
          // Obtener el último carácter ingresado
          var lastChar = inputField.value.slice(-1);
          // Verificar si el último carácter no es un número
          if (isNaN(lastChar)) {
              // Si no es un número, eliminar el último carácter
              inputField.value = inputField.value.slice(0, -1);
          }
      }
      
  }
  
  function validateDates(input) {
    var regex = /^[a-zA-Z\s\n]+$/;
    var inputValue = input.value;
  
    if (!regex.test(inputValue)) {
      // Eliminar caracteres no permitidos excepto espacios
      input.value = inputValue.replace(/[^a-zA-Z\s\n]/g, ''); 
    }
  }
  
  function SearchCategory(){

     var search = document.getElementById("search").value;
      
    //alert(description);
    
    if(search.trim()===''){
        search='NS';
    }
    // alert(search);
    var modal = document.getElementById("container-table");
   

    var xmlhttp = new XMLHttpRequest();
      xmlhttp.open("GET", "/categoriaIndex/searchCategory/"+search, true);
      xmlhttp.send();

    xmlhttp.onreadystatechange= function (){

        if(this.readyState === 4 && this.status === 200){
            modal.innerHTML = this.responseText;
           document.getElementById("search").value='';
        }
  
    };
};
  
  
   function closeNotification(){
  
     var notificaciones = document.getElementById("notificacion");
     var buttons = document.getElementById("cerrar");
   
  
     buttons.addEventListener("click", function(){
      notificaciones.style.display = "none";
     });
  
     }
  
  function closeDialogNotification(){
  
    
      var modal = document.querySelector("#view-message");
     var closeBtn = modal.querySelector("[data-close]");
     
  
      closeBtn.addEventListener("click", () => {
      modal.close();
      });
    
     }
     
     
     function confirmAction(button) {
      var modal = document.querySelector("#Confirm-Dialog");
      modal.showModal();
  
      var confirmar = document.getElementById("confirmar");
      var cancelar = document.getElementById("cancelar");
  
      confirmar.addEventListener("click", function() {
          DeleteValidation(button);
          modal.close();
      });
  
      cancelar.addEventListener("click", function() {
          modal.close();
      });
  }
  
  
  
     
  
  
  function DeleteValidation(button) {
      var contenedor = document.getElementById("container-table");
      var dates = button.getAttribute("idSerial"); // Obtiene el valor del atributo data-code del botón
      var url= button.getAttribute("url");
       var currentPage = button.getAttribute("data-pageCurrent");
    var pageElements = button.getAttribute("data-pageElements");
      
      //alert(url+""+dates)
    
      // Realiza tu XMLHttpRequest con el código obtenido
      var xmlhttp = new XMLHttpRequest();
      xmlhttp.open("GET", "/categoriaIndex/DeleteCategory/" + dates+"/"+url, true);
      xmlhttp.send();
  
      xmlhttp.onreadystatechange = function() {
          if (this.readyState === 4 && this.status === 200) {
               contenedor.innerHTML = this.responseText;
              setTimeout(function () {
                  getRedirectPage(currentPage, pageElements);
              }, 1000); 
              
             
          }
      };
  }
  
  
  
  function getRedirectPage(currentPage,pageElements){
     
    
  if(pageElements > 1){
            window.location.href = "?page=" + currentPage + "&size=" + 4;
  
          } else if(currentPage != 0){
  
            currentPage = currentPage - 1;
            window.location.href = "?page=" + currentPage + "&size=" + 4;
          }
  }
  
   