
function goTo(value) {
    window.location.href = value;
    return;
}

function showComponentInsert() {
    if (document.getElementById("insert").getAttribute("value") === "0") {
        document.getElementById("insert").setAttribute("value", "1");
        document.getElementById("insert").innerHTML = "Cancelar Compra";
        document.getElementById("insert").style.color = "Orange";
        //ajax
        let http = new XMLHttpRequest();
        let url = "/shoplist/update";
        http.open("GET", url, true);
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("formUpdate").innerHTML = this.responseText;
                document.getElementById("update").type = "submit";
                document.getElementById("update").value = "Guardar Compra";
                document.getElementById("update").setAttribute("onclick","");
                
            }
        };
        
        return;
    } else {
        document.getElementById("insert").setAttribute("value", "0");
        document.getElementById("formUpdate").innerHTML = "";
        document.getElementById("insert").innerHTML = "Agregar Compra";
        document.getElementById("insert").style.color = "Blue";
        return;
    }
}

function deleteShoplist() {

    const quest = confirm("¿Estás seguro de que deseas eliminar este elemento?");
    const form = document.getElementById("deleteForm");
    if (quest) {
        form.submit();
    } else {

    }
}

function showComponentUpdate(idElement, token) {
    if (document.getElementById("insert").getAttribute("value") == "0") {
        document.getElementById("insert").setAttribute("value", "1");
        document.getElementById("insert").innerHTML = "Cancelar Actualizacion";
        document.getElementById("insert").style.color = "Orange";
        //ajax
        let http = new XMLHttpRequest();
        let url = "/shoplist/update";
        http.open("GET", url, true );
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("formUpdate").innerHTML = this.responseText;
                updateShopList(idElement,token);
            }
        };
        return;
    } else {
        document.getElementById("insert").setAttribute("value", "0");
        document.getElementById("formUpdate").innerHTML = "";
        document.getElementById("insert").innerHTML = "Agregar Compra";
        document.getElementById("insert").style.color = "Blue";
        return;
    }
}


function updateShopList(idElement, token) {
    //optengo el conjunto de datos que deseo utilizar
    let dest =  document.getElementById("formUpdate").children;
    let src =  document.getElementById(idElement).parentElement.parentElement.children;
    //asigno los valores
    dest[0].id = token;//primero el token
    dest[0].value = token;
    dest[1].value = src[0].innerText;
    dest[2].value = src[1].innerText;
    dest[3].value = src[2].innerText;
    dest[4].value = src[3].innerText;
    dest[5].checked = src[4].children[0].checked;
    dest[6].value = src[5].innerText;
}

function updateShoplist() {

    const quest = confirm("¿Estás seguro de que deseas actualizar este elemento?");
    const form = document.getElementById("formUpdate");
    if (quest) {
        form.submit();
    } else {

    }
}

function search() {
        //ajax
        let http = new XMLHttpRequest();
        let url = "/shoplist/find?data="+document.getElementById("findInput").value;
        http.open("GET", url, true );
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("TableDataUser").innerHTML = this.responseText;
            }
        };
        return;
}
