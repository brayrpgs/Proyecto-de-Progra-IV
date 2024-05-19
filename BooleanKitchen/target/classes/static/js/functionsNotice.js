function changeMessageToCheckBox() {
    var checkbox = document.getElementById("state");
    var label = document.getElementById("stateLabel");
  
    if (checkbox.checked) {
      label.textContent = "Disponible";
    } else {
      label.textContent = "No disponible";
    }
  }
  
  function addNotPrefix(inputField) {
    if (!inputField.value.startsWith("NOT-")) {
      inputField.value = "NOT-";
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
    // Eliminar todos los espacios en blanco al final del valor
    inputField.value = inputField.value.replace(/\s*$/g, "");
  }
  
  function deleteBlankSpace(inputField) {
    inputField.value = inputField.value.replace(/\s*$/g, "");
  }
  
  function validateData() {
    document
      .getElementById("myForm")
      .addEventListener("submit", function (event) {
        // Evitar que se envíe el formulario
        event.preventDefault();
      });
  
    var identifier = document.getElementById("identifier");
    var tittle = document.getElementById("tittle");
    var summary = document.getElementById("summary");
    var date = document.getElementById("date");
    var author = document.getElementById("author");
    var state = document.getElementById("state");
    var url = document.getElementById("url");
    var image = document.getElementById("file");
    var inputFile = document.getElementById("file").files[0];
  
    if (
      identifier.value === "NOT-" ||
      tittle.value.trim() === "" ||
      summary.value.trim() === "" ||
      !date.value ||
      url.value.trim() === "" ||
      image.value.trim() === ""
    ) {
      alert(
        "Datos ingresados erroneamente. !Por favor revisa que los campos esten llenos!" +
          " Si necesitas ayuda, enfoca sobre los campos de entrada para ver más información"
      );
  
    } else if (validateDate(date.value)) {
  
      alert("¡La fecha ingresada no puede ser mayor que la fecha actual!");
      
    } else {
      addNotice(
        identifier.value,
        tittle.value,
        summary.value,
        date.value,
        author.value,
        state.checked,
        url.value,
        inputFile
      );
    }
  }
  
  function addNotice(
    identifier,
    tittle,
    summary,
    date,
    author,
    state,
    url,
    image
  ) {
    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
  
    formData.append("IDENTIFICADOR", identifier);
    formData.append("TITULO", tittle);
    formData.append("RESUMEN", summary);
    formData.append("FECHA", date);
    formData.append("AUTHOR", author);
    formData.append("ESTADO", state);
    formData.append("URL", url);
    formData.append("image", image);
  
    xmlhttp.open("POST", "/c_noticia/getNotice", true);
    xmlhttp.send(formData);
  
    xmlhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        var modal = document.querySelector("#modal");
        var div = document.getElementById("aux");
  
        div.innerHTML = "";
  
        aux.innerHTML = this.responseText;
        modal.showModal();
      }
  
      if (
        this.responseText.includes(
          "La noticia ingresada se insertó correctamente"
        )
      ) {
        clearFields();
      }
    };
  }
  
  function update() {
    var identifier = document.getElementById("identifier");
    var tittle = document.getElementById("tittle");
    var summary = document.getElementById("summary");
    var date = document.getElementById("date");
    var author = document.getElementById("author");
    var state = document.getElementById("state");
    var url = document.getElementById("url");
    var image = document.getElementById("file");
    var inputFile = document.getElementById("file").files[0];
  
    if (
      tittle.value.trim() === "" ||
      summary.value.trim() === "" ||
      !date.value ||
      url.value.trim() === "" ||
      image.value.trim() === ""
    ) {
      alert(
        "Datos ingresados erroneamente. !Por favor revisa que los campos esten llenos!" +
          " Si necesitas ayuda, enfoca sobre los campos de entrada para ver más información"
      );
  
    } else if (validateDate(date.value)) {
  
      alert("¡La fecha ingresada no puede ser mayor que la fecha actual!");
  
    } else {
      updateIngredient(
        identifier.value,
        tittle.value,
        summary.value,
        date.value,
        author.value,
        state.checked,
        url.value,
        inputFile
      );
    }
  }
  
  function updateIngredient(
    identifier,
    tittle,
    summary,
    date,
    author,
    state,
    url,
    image
  ) {
    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
  
    formData.append("IDENTIFICADOR", identifier);
    formData.append("TITULO", tittle);
    formData.append("RESUMEN", summary);
    formData.append("FECHA", date);
    formData.append("AUTHOR", author);
    formData.append("ESTADO", state);
    formData.append("URL", url);
    formData.append("image", image);
  
    xmlhttp.open("POST", "/c_noticia/updateNotice", true);
    xmlhttp.send(formData);
  
    xmlhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        var modal = document.querySelector("#modal");
        var div = document.getElementById("aux");
  
        div.innerHTML = "";
  
        aux.innerHTML = this.responseText;
        modal.showModal();
      }
    };
  }
  
  function deleteNotice(button){
  
    var ID = button.getAttribute("item-ID");
    var RUTA_IMG = button.getAttribute("item-RUTA_IMG");
    var TITULO = button.getAttribute("item-TITULO");
    var currentPage = button.getAttribute("data-pageCurrent");
    var pageElements = button.getAttribute("data-pageElements");
  
    var confirmation = confirm(
      "¿Estás seguro que deseas eliminar la noticia de " +
        '"' +
        TITULO +
        '"' +
        "?"
    );
  
    if(confirmation){
      var xmlhttp = new XMLHttpRequest();
      var formData = new FormData();
  
      formData.append("ID", ID);
      formData.append("RUTA_IMG", RUTA_IMG);
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
  
          var div = document.getElementById("tableContainer");
  
          if(pageElements > 1){
            window.location.href = "?page=" + currentPage + "&size=" + 4;
  
          } else if(currentPage != 0){
            
            currentPage = currentPage - 1;
            window.location.href = "?page=" + currentPage + "&size=" + 4;
          }
  
          div.innerHTML = xmlhttp.responseText;
          
        }
      };
  
      xmlhttp.open("POST", "/c_noticia/deleteNotice", true);
      xmlhttp.send(formData);
  
    }
  
  }
  
  function closeDialog() {
    var modal = document.querySelector("#modal");
    modal.close();
  }
  
  function clearFields() {
    document.getElementById("identifier").value = "NOT-";
    document.getElementById("tittle").value = "";
    document.getElementById("summary").value = "";
    document.getElementById("date").value = "2000-01-01";
    document.getElementById("author").value = "";
    document.getElementById("state").checked = false;
    document.getElementById("stateLabel").textContent = "No disponible";
    document.getElementById("url").value = "";
  
    var header = document.querySelector(".headerP");
    var img = document.getElementById("imagen-usuario");
  
    // Si existe una imagen cargada, la eliminamos
    if (img && !img.hidden) {
      cargarSvg(); // Cargamos el SVG nuevamente
    }
  }
  
  function validateDate(date) {
    var actualDate = new Date();
    var dateToValidate = new Date(date);
  
    return dateToValidate > actualDate;
  }
  
  
  