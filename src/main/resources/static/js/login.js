$(document).ready(function() {
    // On ready
});

//GET
async function iniciarSesion(){
       const inputEmail = document.getElementById("exampleInputEmail").value,
            inputPassword = document.getElementById("exampleInputPassword").value;

        let datos = {
            email: inputEmail,
            password: inputPassword
        };

        const response = await fetch("api/login", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos)
        });

        const res = await response.text();
        if (res != "FAIL"){
            localStorage.token = res;
            localStorage.email = datos.email;
             window.location.href = "usuarios.html";
        }
        else{
            alert("Contraseña incorrecta");
        }
  }