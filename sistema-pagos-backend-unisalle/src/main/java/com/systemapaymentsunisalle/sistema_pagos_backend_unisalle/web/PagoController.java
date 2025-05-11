// Define el paquete en el que se encuentra esta clase
package com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.web;

// Importaciones necesarias
import java.io.IOException; // Manejo de excepciones de entrada/salida
import java.time.LocalDate; // Manejo de fechas
import java.util.List; // Uso de listas

// Importaciones de Spring Framework
import org.springframework.beans.factory.annotation.Autowired; // Inyección de dependencias
import org.springframework.http.MediaType; // Manejo de tipos de contenido en respuestas HTTP
import org.springframework.web.bind.annotation.CrossOrigin; // Permite solicitudes desde diferentes dominios (CORS)
import org.springframework.web.bind.annotation.GetMapping; // Define endpoints GET
import org.springframework.web.bind.annotation.PathVariable; // Permite extraer variables de la URL
import org.springframework.web.bind.annotation.PostMapping; // Define endpoints POST
import org.springframework.web.bind.annotation.PutMapping; // Define endpoints PUT
import org.springframework.web.bind.annotation.RequestParam; // Captura parámetros de la solicitud
import org.springframework.web.bind.annotation.RestController; // Indica que esta clase es un controlador REST
import org.springframework.web.multipart.MultipartFile; // Permite manejar archivos enviados en solicitudes

// Importación de clases del proyecto
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Estudiante; // Entidad Estudiante
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.entities.Pago; // Entidad Pago
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.PagoStatus; // Enum para estado del pago
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.enums.TypePago; // Enum para tipo de pago
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.EstudianteRepository; // Repositorio de estudiantes
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.repository.PagoRepository; // Repositorio de pagos
import com.systemapaymentsunisalle.sistema_pagos_backend_unisalle.services.PagoService; // Servicio para manejar pagos

// Define esta clase como un controlador REST
@RestController
@CrossOrigin("*") // Permite que esta API sea accesible desde cualquier dominio
public class PagoController {

    // Inyección de dependencias para acceder a la base de datos de estudiantes
    @Autowired
    private EstudianteRepository estudianteRepository;

    // Inyección de dependencias para acceder a la base de datos de pagos
    @Autowired
    private PagoRepository pagoRepository;

    // Inyección del servicio que contiene la lógica de pagos
    @Autowired
    private PagoService pagoService;

    // ===================================
    // MÉTODOS PARA MANEJO DE ESTUDIANTES
    // ===================================

    // Método que devuelve una lista con todos los estudiantes
    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll(); // Retorna todos los estudiantes desde la base de datos
    }

    // Método que devuelve un estudiante específico según su código
    @GetMapping("/estudiantes/{codigo}")
    public Estudiante listarEstudiantePorCodigo(@PathVariable String codigo) {
        return estudianteRepository.findByCodigo(codigo); // Busca un estudiante por su código
    }

    // Método que lista estudiantes según el programa académico al que pertenecen
    @GetMapping("/estudiantesPorPrograma")
    public List<Estudiante> listarEstudiantesPorPrograma(@RequestParam String programaId) {
        return estudianteRepository.findByProgramaId(programaId); // Busca estudiantes por ID del programa
    }

    // ===================================
    // MÉTODOS PARA MANEJO DE PAGOS
    // ===================================

    // Método que devuelve una lista con todos los pagos registrados
    @GetMapping("/pagos")
    public List<Pago> listarPagos() {
        return pagoRepository.findAll(); // Recupera todos los pagos de la base de datos
    }

    // Método que devuelve un pago específico según su ID
    @GetMapping("/pagos/{id}")
    public Pago listarPagoPorId(@PathVariable Long id) {
        return pagoRepository.findById(id).get(); // Busca un pago por su ID
    }

    // Método que lista los pagos hechos por un estudiante según su código
    @GetMapping("/estudiantes/{codigo}/pagos")
    public List<Pago> listarPagosPorCodigoEstudiante(@PathVariable String codigo) {
        return pagoRepository.findByEstudianteCodigo(codigo); // Busca los pagos hechos por un estudiante
    }

    // Método que lista los pagos según su estado (CREADO, PENDIENTE, APROBADO, etc.)
    @GetMapping("/pagosPorStatus")
    public List<Pago> listarPagosPorStatus(@RequestParam PagoStatus status) {
        return pagoRepository.findByStatus(status); // Busca pagos según su estado
    }

    // Método que lista los pagos según su tipo (EFECTIVO, CHEQUE, TRANSFERENCIA, etc.)
    @GetMapping("/pagos/porTipo")
    public List<Pago> listarPagosPorType(@RequestParam TypePago type) {
        return pagoRepository.findByType(type); // Busca pagos según su tipo
    }

    // ===================================
    // MÉTODO PARA ACTUALIZAR ESTADO DE UN PAGO
    // ===================================

    // Método que permite actualizar el estado de un pago existente
    @PutMapping("/pagos/{pagoId}/actualizarPago")
    public Pago actualizarStatusDePago(@RequestParam PagoStatus status, @PathVariable Long pagoId) {
        return pagoService.actualizarPagoPorStatus(status, pagoId); // Llama al servicio para actualizar el estado del pago
    }

    // ===================================
    // MÉTODO PARA GUARDAR UN PAGO NUEVO
    // ===================================

    // Método para registrar un pago con archivo adjunto (como un comprobante)
    @PostMapping(path = "/pagos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pago guardarPago(
            @RequestParam("file") MultipartFile file, // Archivo adjunto
            double cantidad, // Monto del pago
            TypePago type, // Tipo de pago
            LocalDate date, // Fecha del pago
            String codigoEstudiante // Código del estudiante que realiza el pago
    ) throws IOException {
        return pagoService.savePago(file, cantidad, type, date, codigoEstudiante); // Guarda el pago en la base de datos
    }

    // ===================================
    // MÉTODO PARA DESCARGAR ARCHIVO DE UN PAGO
    // ===================================

    // Método que devuelve el archivo asociado a un pago en formato PDF
    @GetMapping(value = "/pagoFile/{pagoId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] listarArchivoPorId(@PathVariable Long pagoId) throws IOException {
        return pagoService.getArchivoPorId(pagoId); // Obtiene el archivo de pago en formato binario
    }
}
