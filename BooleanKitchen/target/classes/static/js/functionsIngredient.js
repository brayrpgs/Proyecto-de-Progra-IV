function showIngredient(button){

    var code = button.getAttribute("data-code");
  
    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
  
    formData.append("code", code); 
  
    xmlhttp.open("POST", "/c_ingrediente/show", true);
    xmlhttp.send(formData);
  
    xmlhttp.onreadystatechange = function () {
  
      if (this.readyState === 4 && this.status === 200) {
        var modal = document.querySelector("#modalIndex");
        var div = document.getElementById("auxIndex");
  
        div.innerHTML = "";
        
        div.innerHTML = this.responseText;
        modal.showModal();
      }
  
    }
  }
  
  function validateData() {
    document
      .getElementById("myForm")
      .addEventListener("submit", function (event) {
        // Evitar que se envíe el formulario
        event.preventDefault();
      });
  
    var code = document.getElementById("code");
    var name = document.getElementById("name");
    var quantity = document.getElementById("quantity");
    var weight = document.getElementById("weight");
    var category = document.getElementById("category");
    var calories = document.getElementById("calories");
    var description = document.getElementById("description");
    var image = document.getElementById("file");
    var inputFile = document.getElementById("file").files[0];
  
    if (
      code.value === "ING-" ||
      name.value.trim() === "" ||
      parseFloat(quantity.value) < 0 ||
      parseFloat(weight.value) < 0 ||
      category.value.trim() === "" ||
      parseFloat(calories.value) < 0 ||
      image.value.trim() === ""
    ) {
      alert(
        "Datos ingresados erroneamente. !Por favor revisa que los campos esten llenos!" +
          " Si necesitas ayuda, enfoca sobre los campos de entrada para ver más información"
      );
    } else {
      addIngredient(
        code.value,
        name.value,
        quantity.value,
        weight.value,
        category.value,
        calories.value,
        description.value,
        inputFile
      );
    }
  }
  
  
  function addIngredient(
    code,
    name,
    quantity,
    weight,
    category,
    calories,
    description,
    image
  ) {
    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
  
    formData.append("code", code); 
    formData.append("name", name);
    formData.append("quantity", quantity);
    formData.append("weight", weight);
    formData.append("category", category);
    formData.append("calories", calories);
    formData.append("description", description);
    formData.append("imageInput", image);
  
    xmlhttp.open("POST", "/c_ingrediente/Get", true);
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
          "El ingrediente ingresado se insertó correctamente"
        )
      ) {
        clearFields();
      }
    };
  }
  
  function closeDialog() {
    var modal = document.querySelector("#modal");
    modal.close();
  }
  
  function closeDialogAux() {
    var modal = document.querySelector("#modalIndex");
    modal.close();
  }
  
  function deleteIngredient(button) {
    var code = button.getAttribute("data-code");
    var image = button.getAttribute("data-image");
    var currentPage = button.getAttribute("data-pageCurrent");
    var pageElements = button.getAttribute("data-pageElements");
  
    //alert(currentPage + " y " + pageElements);
    
    var confirmation = confirm(
      "¿Estás seguro que deseas eliminar el ingrediente con el código " +
        '"' +
        code +
        '"' +
        "?"
    );
  
    if (confirmation) {
  
      var xmlhttp = new XMLHttpRequest();
      var formData = new FormData();
  
      formData.append("code", code);
      formData.append("image", image);
  
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
          var div = document.getElementById("tabla-container");
          
          if(pageElements > 1){
            window.location.href = "?page=" + currentPage + "&size=" + 4;
  
          } else if(currentPage != 0){
            
            currentPage = currentPage - 1;
            window.location.href = "?page=" + currentPage + "&size=" + 4;
          }
          
          div.innerHTML = xmlhttp.responseText;
        }
      };
  
      xmlhttp.open("POST", "/c_ingrediente/delete", true);
      xmlhttp.send(formData);
  
    }
    
   
  }
  
  function update(button) {
    
    var code = document.getElementById("code");
    var name = document.getElementById("name");
    var quantity = document.getElementById("quantity");
    var weight = document.getElementById("weight");
    var category = document.getElementById("category");
    var calories = document.getElementById("calories");
    var description = document.getElementById("description");
    var image = document.getElementById("file");
    var inputFile = document.getElementById("file").files[0];
  
    if (
      name.value.trim() === "" ||
      parseFloat(quantity.value) < 0 ||
      parseFloat(weight.value) < 0 ||
      category.value.trim() === "" ||
      parseFloat(calories.value) < 0 ||
      image.value.trim() === "" ||
      !/^[a-zA-Z\s]*$/.test(name.value.trim())
    ) {
      alert(
        "Datos ingresados erroneamente. !Por favor revisa que los campos esten llenos" +
          " y revisa campo por campo las indicaciones!"
      );
    } else {
      updateIngredient(
        code.value,
        name.value,
        quantity.value,
        weight.value,
        category.value,
        calories.value,
        description.value,
        inputFile
      );
    }
  }
  
  function updateIngredient(
    code,
    name,
    quantity,
    weight,
    category,
    calories,
    description,
    image
  ) {
    var xmlhttp = new XMLHttpRequest();
    var formData = new FormData();
  
    formData.append("code", code);
    formData.append("name", name);
    formData.append("quantity", quantity);
    formData.append("weight", weight);
    formData.append("category", category);
    formData.append("calories", calories);
    formData.append("description", description);
    formData.append("imageInput", image);
  
    xmlhttp.open("POST", "/c_ingrediente/updateIngredient", true);
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
  
  function addIngPrefix(inputField) {
    if (!inputField.value.startsWith("ING-")) {
      inputField.value = "ING-";
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
  
  function validateLetter(inputField) {
    var regex = /^[a-zA-Z\s]+$/; 
    var inputValue = inputField.value;
  
    if (!regex.test(inputValue)) {
      inputField.value = inputValue.replace(/[^a-zA-Z\s]/g, ""); // Elimina todos los caracteres que no sean letras o espacios
    }
  }
  
  function back(event){
    event.preventDefault(); 
    window.location.href = "/c_ingrediente/ingredientes";
  }
  
  function clearFields() {
    document.getElementById("code").value = "ING-";
    document.getElementById("name").value = "";
    document.getElementById("quantity").value = "0.1";
    document.getElementById("weight").value = "0.1";
    document.getElementById("category").value = "";
    document.getElementById("calories").value = "0.1";
    document.getElementById("description").value = "";
  
    var header = document.querySelector(".headerP");
    var img = document.getElementById("imagen-usuario");
  
    // Si existe una imagen cargada, la eliminamos
    if (img && !img.hidden) {
      cargarSvg(); // Cargamos el SVG nuevamente
    }
  }
  
  