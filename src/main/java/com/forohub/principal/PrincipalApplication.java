package com.forohub.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrincipalApplication implements CommandLineRunner {

	@Autowired
	CrearUsuario crearUsuario ;
	public static void main(String[] args) {
		SpringApplication.run(PrincipalApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		crearUsuario.crearAdmin();
		crearUsuario.crearUser();
	}
}
