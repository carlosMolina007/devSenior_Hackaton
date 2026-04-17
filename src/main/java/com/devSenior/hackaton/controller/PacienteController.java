package com.devSenior.hackaton.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devSenior.hackaton.model.Paciente;
import com.devSenior.hackaton.service.IPacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
@Tag(name = "Pacientes", description = "Operaciones CRUD para pacientes")
public class PacienteController {

    private final IPacienteService pacienteServicio;

    @GetMapping
    @Operation(summary = "Listar todos los pacientes", description = "Retorna una lista de todos los pacientes registrados. Si no hay, devuelve 204.")
    public ResponseEntity<List<Paciente>> listarTodos() {
        try {
            List<Paciente> pacientes = pacienteServicio.listarPacientes();
            if (pacientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description = "Obtiene los detalles de un paciente mediante ID")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo paciente", description = "Crea y añade un nuevo paciente a nuestra base de datos (lista)")
    public ResponseEntity<String> agregar(@Valid @RequestBody Paciente paciente) {
        try {
            pacienteServicio.agregar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("paciente agregado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el paciente");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente", description = "Modifica los datos de un paciente existente. Si el ID no existe, devuelve 404.")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id,
            @Valid @RequestBody Paciente paciente) {
        try {
            Paciente actualizado = pacienteServicio.actualizar(id, paciente);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente", description = "Borra un paciente de la base de datos (lista) por su ID.")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = pacienteServicio.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún paciente con id " + id);
            }
            return ResponseEntity.ok("paciente con id " + id + " eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el paciente");
        }
    }

}
