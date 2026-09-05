package com.proyecto_final.Pizza4You;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.proyecto_final.Pizza4You.repositorio.ProductoRepository;

@SpringBootApplication
public class Pizza4YouApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Pizza4YouApplication.class, args);
		 var repo = context.getBean(ProductoRepository.class);
		
	}

}
