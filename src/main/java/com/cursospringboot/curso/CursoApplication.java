package com.cursospringboot.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @inidican funcionalidad a una clase
// que se utilizara el framework de SpringBoot
@SpringBootApplication(scanBasePackages={"com.cursospringboot.curso.utils", "com.cursospringboot.curso.controllers", "com.cursospringboot.curso.dao"})
public class CursoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}
}