package com.devSenior.hackaton.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.devSenior.hackaton.model.Medico;

@Repository
public class MedicoRepository {

    private final List<Medico> doctors = new ArrayList<>();

    // Inyectamos datos a nuestra lista médicos
    public MedicoRepository() {
        doctors.add(new Medico(1L, "Federico Gonzales", "Neurocirujano", "3108889977", "fedecirujano@email.com", 5));
        doctors.add(new Medico(2L, "María Fernández", "Pediatra", "3124455667", "maria.pediatra@email.com", 8));
        doctors.add(new Medico(3L, "Carlos Ramírez", "Cardiólogo", "3001234567", "cramirez.cardio@email.com", 12));
        doctors.add(new Medico(4L, "Laura Gómez", "Dermatóloga", "3159876543", "lauragomez.derma@email.com", 3));
        doctors.add(new Medico(5L, "Andrés Vargas", "Traumatólogo", "3205554433", "avargas.trauma@email.com", 10));
        doctors.add(new Medico(6L, "Sofía Castro", "Oftalmóloga", "3112223344", "sofiacastro.ojos@email.com", 6));
        doctors.add(new Medico(7L, "Javier López", "Psiquiatra", "3187778899", "javierlopez.psiq@email.com", 15));
    }

    // Retorna la lista de médicos
    public List<Medico> findAll() {
        return doctors;
    }

    // Busca a un médico por ID
    public Optional<Medico> findById(Long idMedico) {
        return doctors.stream()
                .filter(m -> m.getIdMedic().equals(idMedico))
                .findFirst();
    }

    // Elimina un médico mediante ID
    public boolean deleteById(Long medicoId) {
        return doctors.removeIf(m -> m.getIdMedic().equals(medicoId));
    }

    // Guarda un médico en la lista doctors
    public Medico saveDoctor(Medico newDoctor) {
        doctors.add(newDoctor);
        return newDoctor;
    }

    // Actualiza el médico mediante la busqueda de ID
    public Medico updateDoctor(Long idMedico, Medico newMedico) {
        Optional<Medico> medicoEncontrado = findById(idMedico);
        if (medicoEncontrado.isPresent()) {
            Medico medicoExistente = medicoEncontrado.get();
            medicoExistente.setMedicName(newMedico.getMedicName());
            medicoExistente.setSpecialty(newMedico.getSpecialty());
            medicoExistente.setPhoneNumber(newMedico.getPhoneNumber());
            medicoExistente.setWorkExperience(newMedico.getWorkExperience());

            return medicoExistente;
        }

        return null;
    }

}
