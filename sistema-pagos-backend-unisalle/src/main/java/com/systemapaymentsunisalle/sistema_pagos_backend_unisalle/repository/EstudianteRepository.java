// Define el paquete en el que se encuentra la interfaz EstudianteRepository
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository;

// Importaciones necesarias para el uso de List y JPA Repository
import java.util.List; // Importa la clase List para manejar listas de objetos

import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para la gestión de bases de datos
import org.springframework.stereotype.Repository;

// Importa la entidad Estudiante, que será gestionada por este repositorio
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Estudiante;

// Definición de la interfaz EstudianteRepository
// Extiende JpaRepository, lo que proporciona métodos CRUD predefinidos para la entidad Estudiante

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // Método personalizado que busca un estudiante por su código único
    Estudiante findByCodigo(String codigo);

    // Método personalizado que busca una lista de estudiantes que pertenecen a un
    // programa específico
    List<Estudiante> findByProgramaId(String programaId);
}
