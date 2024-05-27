document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
    toggle.addEventListener('click', () => {
      const menu = toggle.nextElementSibling;
  
      // Cerrar todos los menús excepto el actual
      document.querySelectorAll('.menu').forEach(m => {
        if (m !== menu) {
          m.classList.remove('open');
        }
      });
  
      // Cerrar todos los toggles excepto el actual
      document.querySelectorAll('.dropdown-toggle').forEach(btn => {
        if (btn !== toggle) {
          btn.classList.remove('open');
        }
      });
  
      // Alternar el menú actual
      menu.classList.toggle('open');
      toggle.classList.toggle('open');
  
      // Ajustar margen inferior de .otherItems
      adjustOtherItemsMargin();
    });
  });
  
  
  function adjustOtherItemsMargin() {
    const otherItems = document.querySelector('.otherItems');
    const openMenu = document.querySelector('.menu.open');
    if (openMenu) {
      const menuHeight = openMenu.offsetHeight;
      otherItems.style.marginBottom = `${menuHeight + 20}px`; // 20px como margen adicional
    } else {
      otherItems.style.marginBottom = '0';
    }
  }
  
  // Ajustar margen inferior al cargar la página si algún menú está abierto
  document.addEventListener('DOMContentLoaded', adjustOtherItemsMargin);
  