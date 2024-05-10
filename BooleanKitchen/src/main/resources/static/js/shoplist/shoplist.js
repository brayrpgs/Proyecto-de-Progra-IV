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
        alert("cambiando value");
        document.getElementById("insert").setAttribute("value", "1");
        //ajax
        let http = new XMLHttpRequest();
        let url = "/shoplist/insert";
        http.open("GET", url, true);
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("insertData").innerHTML = this.responseText;
            }
        };
        return;
    }
    else {
        alert("cambiando value");
        document.getElementById("insert").setAttribute("value", "0");
        document.getElementById("insertData").innerHTML = "";
        return;
    }
}