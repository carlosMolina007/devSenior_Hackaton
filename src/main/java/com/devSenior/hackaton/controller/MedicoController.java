package com.devSenior.hackaton.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devSenior.hackaton.model.Medico;
import com.devSenior.hackaton.service.IMedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/medicos")
// Agrupación de los endpoints en Swagger
@Tag(name = "Médicos", description = "Operaciones CRUD para médicos")
public class MedicoController {

    private final IMedicoService medicoService;

    public MedicoController(IMedicoService medicoService){
        this.medicoService = medicoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los médicos", description = "Retorna una lista de todos los médicos registrados. Si no hay, devuelve 204.")
    public ResponseEntity<List<Medico>> listDoctors() {
        try {
            List<Medico> doctors = medicoService.listDoctors();
            if (doctors.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204
            }
            return ResponseEntity.ok(doctors); // 200
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar médico por ID", description = "Obtiene los detalles de un médico mediante ID")
    public ResponseEntity<Medico> findDoctorById(@PathVariable Long id) {
        try {
            return medicoService.findById(id)
                    .map(ResponseEntity::ok)                    
                    .orElse(ResponseEntity.notFound().build()); // 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo médico", description = "Crea y añade un nuevo médico a nuestra base de datos (lista)")
    public ResponseEntity<String> addDoctor(@Valid @RequestBody Medico doctor) {
        try {
            medicoService.addDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("El médico fue agregado con éxito"); // 201
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error agregando al médico"); 
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar médico", description = "Modifica los datos de un médico existente. Si el ID no existe, devuelve 404.")
    public ResponseEntity<Medico> updateDoctor(@PathVariable Long id,
                                             @Valid @RequestBody Medico doctor) {
        try {
            Medico actualizado = medicoService.updateDoctor(id, doctor);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar médico", description = "Borra un médico de la base de datos (lista) por su ID.")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long id) {
        try {
            boolean eliminado = medicoService.deleteDoctor(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún médico con id " + id);
            }
            return ResponseEntity.ok("Médico con identificación " + id + " fue eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error eliminando al médico con id " + id);
        }
    }
}
