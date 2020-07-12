package com.diprotiv.encuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class EncuestaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncuestaApplication.class, args);
    }
}
