/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    const button = document.getElementById('avatar-navbar');
    const closeButton = document.querySelector('.close-button');
    const navigationMenu = document.querySelector('.navigation__menu');

    button.addEventListener('click', () => {
        navigationMenu.classList.remove('none');
        navigationMenu.classList.remove('hide');
    });

    closeButton.addEventListener('click', () => {
        navigationMenu.classList.add('hide');
    });
});
