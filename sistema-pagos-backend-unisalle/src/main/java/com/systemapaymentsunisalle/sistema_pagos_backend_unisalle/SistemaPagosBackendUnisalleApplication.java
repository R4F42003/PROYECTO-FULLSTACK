// Paquete principal de la aplicación, donde se encuentran todas las clases relacionadas con el backend del sistema de pagos
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle;

import java.time.LocalDate;
import java.util.Random;
// Importación de la clase UUID para generar identificadores únicos
import java.util.UUID;

// Importaciones de Spring Boot para ejecutar la aplicación y configurar la inicialización
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Importaciones de las entidades y repositorios de la aplicación
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Estudiante;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Pago;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.PagoStatus;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.EstudianteRepository;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.PagoRepository;

// Anotación que indica que esta clase es la aplicación principal de Spring Boot
@SpringBootApplication
public class SistemaPagosBackendUnisalleApplication {

    // Método principal que inicia la aplicación de Spring Boot
    public static void main(String[] args) {
        // `SpringApplication.run()` inicia la aplicación y carga el contexto de Spring
        SpringApplication.run(SistemaPagosBackendUnisalleApplication.class, args);
    }


}
