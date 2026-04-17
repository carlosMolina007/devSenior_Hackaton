package com.devSenior.hackaton.service;

import java.util.List;
import java.util.Optional;

import com.devSenior.hackaton.model.Paciente;

public interface IPacienteService {

    List<Paciente> listarPacientes();

    Optional<Paciente> buscarPorId(Long id);

    Paciente agregar(Paciente autor);

    Paciente actualizar(long id, Paciente paciente);

    boolean eliminar(long id);
}
