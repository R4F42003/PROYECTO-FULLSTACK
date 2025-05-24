// Define el paquete en el que se encuentra la clase PagoService
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.services;

// Importaciones necesarias para manejar archivos, fechas y generar identificadores únicos
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

// Importaciones de Spring para la inyección de dependencias y la gestión de servicios
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// Importaciones de las entidades y repositorios necesarios para la funcionalidad del servicio
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Estudiante;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Pago;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.PagoStatus;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.EstudianteRepository;
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.PagoRepository;

// Importación de la anotación para manejar transacciones en la base de datos
import jakarta.transaction.Transactional;

// Anotación @Service para definir esta clase como un servicio de Spring
@Service
// Anotación @Transactional para asegurar que los métodos de la clase se
// ejecuten dentro de una transacción
@Transactional
public class PagoService {

    // Inyección de dependencias de PagoRepository para interactuar con la base de
    // datos de pagos
    @Autowired
    private PagoRepository pagoRepository;

    // Inyección de dependencias de EstudianteRepository para obtener información de
    // los estudiantes desde la base de datos
    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * Método para guardar un pago en la base de datos y almacenar el archivo PDF
     * asociado.
     *
     * @param file             Archivo PDF que se subirá al servidor.
     * @param cantidad         Monto del pago realizado.
     * @param type             Tipo de pago (EFECTIVO, CHEQUE, TRANSFERENCIA,
     *                         DEPÓSITO).
     * @param date             Fecha en la que se realizó el pago.
     * @param codigoEstudiante Código del estudiante que realiza el pago.
     * @return Objeto Pago guardado en la base de datos.
     * @throws IOException Excepción lanzada si ocurre un error al manejar el
     *                     archivo.
     */
    public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante)
            throws IOException {

        /*
         * Construimos la ruta donde se guardará el archivo dentro del sistema.
         * - System.getProperty("user.home"): Obtiene la ruta del directorio personal
         * del usuario actual del sistema operativo.
         * - Paths.get(...): Construye una ruta dentro del directorio personal en la
         * carpeta "enset-data/pagos".
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");

        // Verificamos si la carpeta ya existe, si no, la creamos
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath); // Crea la carpeta si no existe
        }

        // Generamos un nombre único para el archivo usando UUID (identificador único
        // universal)
        String fileName = UUID.randomUUID().toString();

        // Construimos la ruta completa del archivo, añadiendo la extensión ".pdf"
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");

        // Guardamos el archivo recibido en la ubicación especificada dentro del sistema
        // de archivos
        Files.copy(file.getInputStream(), filePath);

        // Buscamos en la base de datos el estudiante que realizó el pago usando su
        // código único
        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);

        // Creamos un nuevo objeto Pago utilizando el patrón de diseño Builder
        Pago pago = Pago.builder()
                .type(type) // Tipo de pago (efectivo, cheque, transferencia, etc.)
                .status(PagoStatus.CREADO) // Estado inicial del pago (CREADO)
                .fecha(date) // Fecha en que se realizó el pago
                .estudiante(estudiante) // Relación con el estudiante que realiza el pago
                .cantidad(cantidad) // Monto del pago
                .file(filePath.toUri().toString()) // Ruta del archivo PDF almacenado
                .build(); // Construcción final del objeto Pago

        // Guarda el objeto 'pago' en la base de datos y lo devuelve con su ID generado
        // o actualizado.
        return pagoRepository.save(pago);

    }

    public byte[] getArchivoPorId(Long pagoId) throws IOException {
        // Busca un objeto Pago en la base de datos por su ID
        Pago pago = pagoRepository.findById(pagoId).get();

        /*
         * - pago.getFile(): Obtiene la URI del archivo guardado como una cadena de
         * texto.
         * - URI.create(...): Convierte la cadena en un objeto URI.
         * - Path.of(...): Convierte el URI en un Path para poder acceder al archivo.
         * - Files.readAllBytes(...): Lee el contenido del archivo y lo devuelve en un
         * array de bytes.
         * Esto permite obtener el contenido del archivo para su posterior uso (por
         * ejemplo, para su descarga).
         */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus(PagoStatus status, Long id) {
        // Busca un objeto Pago en la base de datos por su ID
        Pago pago = pagoRepository.findById(id).get();

        // Actualiza el estado del pago con el nuevo estado recibido como parámetro
        pago.setStatus(status);

        // Guarda el objeto Pago actualizado en la base de datos y lo devuelve
        return pagoRepository.save(pago);
    }

    // método para actualizar el estado de un pago
    @PutMapping("/pagos/{id}/status")
    public Pago actualizarEstadoPago(@PathVariable Long id, @RequestParam PagoStatus status) {
        return pagoService.actualizarPagoPorStatus(status, id);
    }
}