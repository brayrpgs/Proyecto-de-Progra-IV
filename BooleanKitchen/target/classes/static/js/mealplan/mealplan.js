let list = null;
function insertCard(){
    if(list === null){
        let http = new XMLHttpRequest();
        let url = "/mealplan/insert";
        http.open("GET", url, true);
        http.send();
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                list = document.getElementById("listCards").innerHTML;
                document.getElementById("listCards").innerHTML = this.responseText+list;
                
            }
        };
    }
    else{
        document.getElementById("listCards").innerHTML = list;
        list = null;
    }
}

function saveCard(eleParent,token){
    const quest = confirm("¿Estás seguro de que deseas guardar este elemento?");
    if (!quest) {
        return;
    }
    let id;
    if(token === undefined){
        id = null;
    }
    card = document.getElementById(eleParent).parentElement.parentElement.children;
    //datos de la tarjeta
    name = card["up"].children["name"].value;
    dateInit = card["up"].children["contentDates"].children["dateInit"].value;
    dateEnd = card["up"].children["contentDates"].children["dateEnd"].value;
    typeDiet = card["up"].children["typeDiet"].value;
    description = card["center"].children["description"].value;
    numPerson = card["center"].children["numberPerson"].value;
    price = card["down"].children["cost"].value;
    state = card["down"].children[4].children["state"].checked;
    let datas = [name, dateInit, dateEnd, typeDiet, description, numPerson, price, state];
    a = card["up"].children["name"];
    b = card["up"].children["contentDates"].children["dateInit"];
    c = card["up"].children["contentDates"].children["dateEnd"];
    d = card["up"].children["typeDiet"];
    e = card["center"].children["description"];
    f = card["center"].children["numberPerson"];
    g = card["down"].children["cost"];
    h = card["down"].children[4].children["state"];
    let inputs = [a, b, c, d, f, g, h];
    for (var i = 0; i < datas.length; i++) {
        if (datas[i].toString() === "" | typeof (datas[i]) === undefined) {
            alert("Datos Incorrectos");
            inputs[i].style.color="black";
            inputs[i].style.backgroundColor="red";
            return;
        }
    }
        
    //undefined
    // validadciones
    let http = new XMLHttpRequest();
    let url = "/mealplan/insertMealplan";
    
    let dataUser = {
        "id": token,
        "name": name,
        "description": description,
        "dateInit": dateInit,
        "dateEnd": dateEnd,
        "numPerson": numPerson,
        "price": price,
        "typeDiet": typeDiet,
        "state": state,
        "idUser": 1
    };
    
    http.open("POST", url, true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify(dataUser));
    
    alert("Guardado con exito");
    window.location.href = '/mealplan/pane';
}

function deleteShoplist(token) {
    
    let http = new XMLHttpRequest();
    let url = "/mealplan/deleteMealplan?Id=";
    const quest = confirm("¿Estás seguro de que deseas eliminar este elemento?");
    const form = document.getElementById("deleteForm");
    if (quest) {
        http.open("POST", url+token, true);
        http.send();
        alert("Eliminado con exito");
        window.location.href = '/mealplan/pane';
    } else {

    }
    
}


function update(token){
    card = document.getElementById(token).children;
    //datos de la tarjeta
    name = card["up"].children["name"].disabled= false;
    dateInit = card["up"].children["contentDates"].children["dateInit"].disabled= false;
    dateEnd = card["up"].children["contentDates"].children["dateEnd"].disabled= false;
    typeDiet = card["up"].children["typeDiet"].disabled= false;
    description = card["center"].children["description"].disabled= false;
    numPerson = card["center"].children["numberPerson"].disabled= false;
    price = card["down"].children["cost"].disabled= false;
    state = card["down"].children[4].children["state"].disabled= false;
    state = card["down"].children[1].disabled= false;
    state = card["down"].children[3].disabled= false;
}


function search() {
    //ajax
    let http = new XMLHttpRequest();
    let url = "/mealplan/find?data="+document.getElementById("findInput").value;
    http.open("GET", url, true );
    http.send();
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("addNewCard").innerHTML = this.responseText;
        }
    };
    return;
}