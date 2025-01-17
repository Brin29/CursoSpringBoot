package com.cursospringboot.curso.dao;

import com.cursospringboot.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Conexion con la base de datos
// Transasctional Armar las consultas a la base de datos
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    //Conexion con la base de datos
    EntityManager entityManager;

    // Override remplazar
    @Transactional
    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        List<Usuario> resultList = entityManager.createQuery(query).getResultList();
        return resultList;
    }

    @Override
    public void eliminar(Long id) {
        //Buscar el usuario
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario){
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuariosPorCredenciales(Usuario usuario){
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        // Si la lista esta vacia
        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }

        return null;
    }
}
