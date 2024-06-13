// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
});
//GET
async function cargarUsuarios(){
        const response = await fetch("api/usuarios", {
            method: "GET",
            headers: getHeaders()
        });

        const usuarios = await response.json();

        let usersList = "";
        let telefono = usuarios.telefono == null ? "-" : usuarios.telefono;
        for (let usuario of usuarios){
            let botonEliminar = `<a href="#" class="btn btn-danger btn-circle" onClick="eliminarUsuario(${usuario.id})"><i class="fas fa-trash"></i></a>`;
            let users = `<tr><td>${usuario.id}</td><td>${usuario.nombre} ${usuario.apellido}</td><td>${usuario.email}</td><td>${telefono}</td><td>${botonEliminar}</td></tr>`;
            usersList += users;
        }

        let tablesUsers = document.querySelector("#usuarios tbody");
        tablesUsers.innerHTML = usersList;
}

function getHeaders(){
    return {
        "Accept": "application/json",
        "Content-Type": "application/json",
        "Authorization": localStorage.toke
    }
}

async function eliminarUsuario(id){
    if (!confirm(`Desea eliminar el usuario ${id}`)){
        return null
    }
    else {

    const request = await fetch(`api/usuarios/${id}`, {
            method: "DELETE",
            headers: getHeaders()
        });

    location.reload()
    }
}

cargarUsuarios();