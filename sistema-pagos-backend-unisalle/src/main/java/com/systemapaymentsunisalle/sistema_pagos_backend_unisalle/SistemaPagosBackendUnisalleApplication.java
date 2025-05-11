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

    @Bean
    CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository) {
        return args -> {
            // Guarda un estudiante en la base de datos al iniciar la aplicación
            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Melissa")
                    .apellido("Gordillo")
                    .codigo("1234")
                    .programaId("ISI123")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Carlos")
                    .apellido("Martínez")
                    .codigo("1235")
                    .programaId("ISI123")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Ana")
                    .apellido("Rodríguez")
                    .codigo("1236")
                    .programaId("ISI123")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Juan")
                    .apellido("Pérez")
                    .codigo("1237")
                    .programaId("ISI123")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("María")
                    .apellido("García")
                    .codigo("1238")
                    .programaId("ISI123")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Luis")
                    .apellido("Sánchez")
                    .codigo("1239")
                    .programaId("ISI123")
                    .build());

            // Obtiene todos los valores posibles del enumerador TypePago (tipos de pago)
            TypePago tiposPago[] = TypePago.values();
            // Crea un objeto Random para seleccionar valores aleatorios
            Random random = new Random();

            // Itera sobre todos los estudiantes en el repositorio
            estudianteRepository.findAll().forEach(estudiante -> {
                // Crea 10 pagos para cada estudiante
                for (int i = 0; i < 10; i++) {
                    // Genera un índice aleatorio para seleccionar un tipo de pago
                    int index = random.nextInt(tiposPago.length);

                    // Construye un objeto Pago con valores aleatorios
                    Pago pago = Pago.builder()
                            .cantidad(1000 + (int) (Math.random() * 20000)) // Genera un monto entre 1000 y 21000
                            .type(tiposPago[index]) // Asigna un tipo de pago aleatorio
                            .status(PagoStatus.CREADO) // Estado inicial del pago (CREADO)
                            .fecha(LocalDate.now()) // Fecha actual del pago
                            .estudiante(estudiante) // Asigna el pago al estudiante actual
                            .build();

                    // Guarda el pago en la base de datos
                    pagoRepository.save(pago);
                }
            });

        };
    }

}
