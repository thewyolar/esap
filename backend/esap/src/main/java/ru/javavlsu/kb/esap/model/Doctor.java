package ru.javavlsu.kb.esap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class Doctor extends User {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 100)
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @Max(value = 2, message = "Не верно указан пол")
    @Min(value = 1, message = "Не верно указан пол")
    private int gender;

    //TODO    @ToString.Include(name = "password")
    //    private String maskPassword(){
    //        return "*******";
    //    } потестить


    public String getFio(){
        return this.lastName + " " + this.firstName + " " + this.patronymic;
    }
}

