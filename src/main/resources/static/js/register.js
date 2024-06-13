$(document).ready(function() {
    // On ready
});

//GET
async function registrarUsuarios(){
        //exampleFristName, exampleLastName,
        //exampleInputEmail, exampleInputPassword, exampleRepeatPassword
        const firstName = document.getElementById("exampleFirstName").value,
            lastName = document.getElementById("exampleLastName").value,
            inputEmail = document.getElementById("exampleInputEmail").value,
            inputPassword = document.getElementById("exampleInputPassword").value,
            RepeatPassword = document.getElementById("exampleRepeatPassword").value;

        if (!RepeatPassword === inputPassword){
            alert("Las contraseñas no coinciden")
            return null;
        }

        let datos = {
            nombre: firstName,
            apellido: lastName,
            email: inputEmail,
            password: inputPassword
        };

        const response = await fetch("api/usuarios", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos)
        });
        alert("Usuario registrado de manera exitoras")
        window.location.href = "login.html"
}