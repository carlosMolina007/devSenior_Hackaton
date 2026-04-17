package com.devSenior.hackaton.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Medico {

    private Long idMedic;

    @NotBlank(message = "El nombre no puede ir vacío")
    @Size(min = 2, max = 30, message = "El nombre debe contener al menos 2 caracteres y máximo 30")
    private String medicName;

    @NotBlank(message = "El médico debe llevar una especialidad")
    private String specialty;

    @NotBlank(message = "El teléfono no puede ir vacío")
    @Size(min = 10, max = 10, message = "El teléfono debe contener 10 dígitos")
    private String phoneNumber;

    @Email(message = "Debe contener un formato válido")
    private String email;

    @Min(value = 0, message = "La experiencia no puede ser negativa")
    @Max(value = 30, message = "La experiencia parece demasiado alta")
    private int workExperience;

    public Medico() {
    }

    public Medico(Long idMedic, String medicName, String specialty, String phoneNumber, String email,
            int workExperience) {
        this.idMedic = idMedic;
        this.medicName = medicName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workExperience = workExperience;
    }

    public Long getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(Long idMedic) {
        this.idMedic = idMedic;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

}
