// Define el paquete donde se encuentra la enumeración TypePago dentro del proyecto.
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums;

// Define una enumeración (enum) llamada TypePago.
// Un enum en Java es un tipo especial de datos que representa un conjunto fijo de valores constantes.
public enum TypePago {

    // Se definen los diferentes tipos de pago disponibles en el sistema:
    // - EFECTIVO: Indica que el pago se realiza con dinero en efectivo.
    // - CHEQUE: Indica que el pago se realiza mediante cheque bancario.
    // - TRANSFERENCIA: Indica que el pago se realiza a través de una transferencia bancaria.
    // - DEPOSITO: Indica que el pago se realiza mediante un depósito bancario.
    EFECTIVO, CHEQUE, TRANSFERENCIA, DEPOSITO
}
