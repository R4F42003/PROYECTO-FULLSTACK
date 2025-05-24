// Paquete donde se encuentra la entidad Estudiante
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities;

// Importaciones necesarias para el uso de anotaciones de JPA y Lombok
import jakarta.persistence.Column; // Permite definir características de las columnas en la base de datos
import jakarta.persistence.Entity; // Marca la clase como una entidad JPA
import jakarta.persistence.Id; // Indica que un atributo es la clave primaria

// Importaciones de Lombok para reducir el código repetitivo (boilerplate)
import lombok.AllArgsConstructor; // Genera un constructor con todos los atributos
import lombok.Builder; // Implementa el patrón de diseño Builder para crear objetos de la clase
import lombok.Data; // Genera automáticamente getters, setters, toString(), equals() y hashCode()
import lombok.NoArgsConstructor; // Genera un constructor sin argumentos

// Anotación que marca esta clase como una entidad que será gestionada por JPA
@Entity
// Lombok: Permite la construcción de objetos de esta clase con el patrón Builder
@Builder
// Lombok: Genera automáticamente los métodos getters, setters, toString(), equals() y hashCode()
@Data
// Lombok: Genera un constructor sin argumentos
@NoArgsConstructor
// Lombok: Genera un constructor con todos los atributos
@AllArgsConstructor
public class Estudiante {

    // Indica que este campo es la clave primaria en la base de datos
    @Id
    private String id;

    // Campo que almacena el nombre del estudiante
    private String nombre;

    // Campo que almacena el apellido del estudiante
    private String apellido;

    // Indica que la columna 'codigo' debe ser única en la base de datos (no se pueden repetir valores)
    @Column(unique = true)
    private String codigo;

    // Identificador del programa académico al que pertenece el estudiante
    private String programaId;

    // Almacena la URL o la ruta de la foto del estudiante
    private String foto;
}
