package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities;
import java.time.LocalDate;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.PagoStatus;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago;

// Importaciones necesarias para el uso de anotaciones de JPA y Lombok

import jakarta.persistence.Entity; // Marca la clase como una entidad JPA
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // Indica que un atributo es la clave primaria
import jakarta.persistence.ManyToOne;
// Importaciones de Lombok para reducir el código repetitivo (boilerplate)
import lombok.AllArgsConstructor; // Genera un constructor con todos los atributos
import lombok.Builder; // Implementa el patrón de diseño Builder para crear objetos de la clase
import lombok.Data; // Genera automáticamente getters, setters, toString(), equals() y hashCode()
import lombok.NoArgsConstructor; // Genera un constructor sin argumentos

// Anotación que marca esta clase como una entidad que será gestionada por JPA
@Entity
// Lombok: Permite la construcción de objetos de esta clase con el patrón
// Builder
@Builder
// Lombok: Genera automáticamente los métodos getters, setters, toString(),
// equals() y hashCode()
@Data
// Lombok: Genera un constructor sin argumentos
@NoArgsConstructor
// Lombok: Genera un constructor con todos los atributos
@AllArgsConstructor

// Definición de la clase Pago, que representa una entidad en el sistema
public class Pago {

    // Indica que este campo es la clave primaria en la base de datos
    @Id
    // Genera automáticamente un valor único para el ID usando la estrategia IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del pago

    private LocalDate fecha; // Fecha en la que se realizó el pago

    private double cantidad; // Monto del pago

    private TypePago type; // Tipo de pago (EFECTIVO, CHEQUE, TRANSFERENCIA, DEPOSITO)

    private PagoStatus status; // Estado del pago (CREADO, VALIDADO, RECHAZADO)

    private String file; // Archivo asociado al pago (puede ser un comprobante, factura, etc.)

    // Relación ManyToOne: Muchos pagos pueden estar asociados a un solo estudiante
    @ManyToOne
    private Estudiante estudiante; // Estudiante que realizó el pago
}
