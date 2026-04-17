package com.devSenior.hackaton.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devSenior.hackaton.model.Medico;
import com.devSenior.hackaton.repository.MedicoRepository;

@Service
public class MedicoService implements IMedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository){this.medicoRepository = medicoRepository;}

    @Override
    public List<Medico> listDoctors() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> findById(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public Medico addDoctor(Medico newMedic) {
        return medicoRepository.saveDoctor(newMedic);
    }

    @Override
    public Medico updateDoctor(Long id, Medico doctor) {
        return medicoRepository.updateDoctor(id, doctor);
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        return medicoRepository.deleteById(id);
    }




}
