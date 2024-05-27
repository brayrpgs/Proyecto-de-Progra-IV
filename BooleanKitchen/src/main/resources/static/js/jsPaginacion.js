function changePageSizeComentario() {
    const pageSize = document.getElementById("pageSize").value;
    const totalItems = totalElements;
    const currentItemStartIndex = currentPage * currentPageSize;
    const newPage = Math.floor(currentItemStartIndex / pageSize);

    window.location.href = "?page=" + newPage + "&size=" + pageSize + "&identificadorReceta=" + idReceta;
}



function changePageSizeReceta() {
    const pageSize = document.getElementById("pageSize").value;
    const totalItems = totalElements;
    const currentItemStartIndex = currentPage * currentPageSize;
    const newPage = Math.floor(currentItemStartIndex / pageSize);

    window.location.href = "?page=" + newPage + "&size=" + pageSize + "&buscarReceta=" + buscar;
}