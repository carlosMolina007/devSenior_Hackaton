package com.devSenior.hackaton.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.devSenior.hackaton.model.Paciente;
import com.devSenior.hackaton.repository.PacienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PacienteService implements IPacienteService {

    private final PacienteRepository pacienteRepositorio;
    private final AtomicLong contador = new AtomicLong(0);

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepositorio.obtenerTodos();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepositorio.obtenerPorId(id);
    }

    @Override
    public Paciente agregar(Paciente paciente) {
        paciente.setId(contador.getAndIncrement());
        return pacienteRepositorio.agregarPaciente(paciente);
    }

    @Override
    public Paciente actualizar(long id, Paciente paciente) {
        return pacienteRepositorio.actualizarActor(id, paciente);
    }

    @Override
    public boolean eliminar(long id) {
        return pacienteRepositorio.eliminarPorId(id);
    }
}