package ru.javavlsu.kb.esap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

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
    @JsonIgnore
    private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    @JsonIgnore
    private Clinic clinic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(login, doctor.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}

