package com.devSenior.hackaton.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.devSenior.hackaton.model.Paciente;

@Repository
public class PacienteRepository {

    private final List<Paciente> pacientes = new ArrayList<>();

    public Paciente agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        return paciente;
    }

    public List<Paciente> obtenerTodos() {
        return pacientes;
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return pacientes.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    public boolean eliminarPorId(long id) {
        return pacientes.removeIf(l -> l.getId().equals(id));
    }

    public Paciente actualizarActor(Long id, Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = obtenerPorId(id);
        if (pacienteEncontrado.isPresent()) {
            Paciente pacienteActual = pacienteEncontrado.get();
            pacienteActual.setNombre(paciente.getNombre());
            pacienteActual.setApellido(paciente.getApellido());
            pacienteActual.setEdad(paciente.getEdad());
            pacienteActual.setGenero(paciente.getGenero());
            pacienteActual.setDireccion(paciente.getDireccion());
            pacienteActual.setTelefono(paciente.getTelefono());
            pacienteActual.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteActual.setHistorialMedico(paciente.getHistorialMedico());
            pacienteActual.setMedicoAsignado(paciente.getMedicoAsignado());
            return pacienteActual;
        }
        return null;
    }
}