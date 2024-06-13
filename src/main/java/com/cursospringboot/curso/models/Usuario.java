package com.cursospringboot.curso.models;

import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Entity = va a hacer referencia a la base de datos
@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {
    // Indicar que esta va a ser la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;

}
