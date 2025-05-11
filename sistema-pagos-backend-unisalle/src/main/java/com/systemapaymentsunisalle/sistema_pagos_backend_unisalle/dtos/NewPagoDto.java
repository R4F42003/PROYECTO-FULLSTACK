package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.dtos;

// Importación de la clase LocalDate para manejar fechas
import java.time.LocalDate;

import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago;

// Importación de la enumeración TypePago, que define los tipos de pago permitidos

// Importaciones de Lombok para reducir código repetitivo (boilerplate)
import lombok.AllArgsConstructor; // Genera un constructor con todos los atributos
import lombok.Data; // Genera automáticamente getters, setters, toString(), equals() y hashCode()
import lombok.NoArgsConstructor; // Genera un constructor vacío

// Anotación Lombok: Genera automáticamente los métodos getter, setter, toString(), equals() y hashCode()
@Data
// Anotación Lombok: Genera un constructor con todos los argumentos de la clase
@AllArgsConstructor
// Anotación Lombok: Genera un constructor sin argumentos
@NoArgsConstructor
public class NewPagoDto { // Clase DTO (Data Transfer Object) para representar datos de pago

    private double cantidad; // Monto del pago

    private TypePago typePago; // Tipo de pago (EFECTIVO, CHEQUE, TRANSFERENCIA, DEPOSITO)

    private LocalDate date; // Fecha en la que se realizó el pago

    private String codigoEstudiante; // Código único del estudiante que realizó el pago
}
