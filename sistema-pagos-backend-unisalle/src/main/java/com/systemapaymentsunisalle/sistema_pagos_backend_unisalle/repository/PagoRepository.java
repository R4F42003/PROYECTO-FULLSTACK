// Define el paquete en el que se encuentra la interfaz PagoRepository
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository;

// Importa la clase List para manejar listas de objetos en las consultas
import java.util.List;

// Importaciones necesarias de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository; // Proporciona métodos CRUD y consultas automáticas
import org.springframework.stereotype.Repository; // Indica que esta interfaz es un componente de repositorio en Spring

// Importaciones de entidades y enumeraciones necesarias para definir consultas personalizadas
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Pago; // Entidad Pago
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.PagoStatus; // Enum con los estados del pago
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago; // Enum con los tipos de pago

// Anotación que marca esta interfaz como un componente de repositorio gestionado por Spring
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> { // Extiende JpaRepository para manejar la entidad Pago

    // Método personalizado para buscar pagos por el código del estudiante
    // Devuelve una lista de pagos asociados a un estudiante específico
    List<Pago> findByEstudianteCodigo(String codigo);

    // Método personalizado para buscar pagos por su estado (CREADO, VALIDADO, RECHAZADO)
    // Devuelve una lista de pagos según su estado actual
    List<Pago> findByStatus(PagoStatus status);

    // Método personalizado para buscar pagos por el tipo de pago (EFECTIVO, CHEQUE, TRANSFERENCIA, DEPÓSITO)
    // Devuelve una lista de pagos según el tipo seleccionado
    List<Pago> findByType(TypePago typePago);
}
