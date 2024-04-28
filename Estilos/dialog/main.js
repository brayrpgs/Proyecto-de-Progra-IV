document.addEventListener("DOMContentLoaded", function() {
    //Abrir dialog
    const openBtn = document.querySelector("[data-open]");
    const modal = document.querySelector("#modal");
    openBtn.addEventListener("click", () => {

      modal.showModal();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    // Cerrar dialog
    const closeBtn = document.querySelector("[data-close]");
    const modal = document.querySelector("#modal");
    closeBtn.addEventListener("click", () => {

      modal.close();
    });
});



function enviarDatos(){
  // Solo simulación
  alert("Enviar datos");
}