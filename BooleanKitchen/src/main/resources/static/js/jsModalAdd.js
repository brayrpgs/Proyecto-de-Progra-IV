
document.addEventListener("DOMContentLoaded", function() {
    // Modal
    const openBtn = document.querySelector("[data-open]");
    const modal = document.querySelector("#modal-add-recipe");

    // Open the modal when the button is clicked
    openBtn.addEventListener("click", () => {
        //Cierra el menu
        const navigationMenu = document.querySelector('.navigation__menu');
        navigationMenu.classList.add('hide');
        // bloquea el scroll
        document.body.style.overflow = 'hidden';

        //agregar cerrar

      modal.showModal();
    });
});


function closeModal() {
    var modal = document.getElementById('modal-add-recipe');
    document.body.style.overflow = 'visible'; //Desbloque el scroll
    modal.close();
}

