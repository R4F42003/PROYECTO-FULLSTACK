// Define el paquete donde se encuentra la clase PagoStatus dentro del proyecto.
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums;

// Define una enumeración (enum) llamada PagoStatus.
// Un enum en Java es un tipo especial de datos que representa un conjunto fijo de valores constantes.
public enum PagoStatus {

    // Se definen los posibles estados que puede tener un pago en el sistema:
    // - CREADO: Indica que el pago ha sido generado pero aún no se ha procesado.
    // - VALIDADO: Indica que el pago ha sido revisado y aprobado.
    // - RECHAZADO: Indica que el pago ha sido rechazado por algún motivo.
    CREADO, VALIDADO, RECHAZADO;
}
