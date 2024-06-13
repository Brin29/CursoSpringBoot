package com.cursospringboot.curso.controllers;

import com.cursospringboot.curso.dao.UsuarioDao;
import com.cursospringboot.curso.models.Usuario;
import com.cursospringboot.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// Los controllers sirven para manejar las URL'S
@RestController
public class UsuarioController {

    //Inyeccion de dependencias
    //Hace que la clase UsuarioDao cree en un objeto y la guarda dentro de la variable
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    // RequestMapping nombre de la direccion
    // Acceder a un usuario en especifico: "usuario/{id}"
    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        String usuarioId = jwtUtil.getKey(token);

        if (usuarioId == null){
            return new ArrayList<>();
        }
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    //Convierte el JSON a un usuario
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }
}
