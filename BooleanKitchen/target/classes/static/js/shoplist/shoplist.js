/**
 * Function to navigate to the insert page from the backend.
 *
 * @param {string} value - The URL to navigate to.
 */
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
        let url = "/shoplist/insert";
        http.open("GET", url, true);
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("insertData").innerHTML = this.responseText;
            }
        };
        return;
    }
    else {
        document.getElementById("insert").setAttribute("value", "0");
        document.getElementById("insertData").innerHTML = "";
        document.getElementById("insert").innerHTML = "Agregar Compra";
        document.getElementById("insert").style.color = "Blue";
        return;
    }
    
    
    function getRegisters(path, numPage){
        let http = new XMLHttpRequest();
        let url = path;
        http.open("GET", url, true);
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("insertData").innerHTML = this.responseText;
            }
        };
    }
}
