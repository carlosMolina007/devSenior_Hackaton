package com.devSenior.hackaton.service;

import java.util.List;
import java.util.Optional;

import com.devSenior.hackaton.model.Medico;

public interface IMedicoService {

    List<Medico> listDoctors();

    Optional<Medico> findById(Long id);

    Medico addDoctor (Medico newMedic);

    Medico updateDoctor (Long id, Medico doctor);

    Boolean deleteDoctor (Long id);

}
