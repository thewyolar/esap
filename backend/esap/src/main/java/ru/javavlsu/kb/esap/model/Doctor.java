package ru.javavlsu.kb.esap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    @NotBlank
    @NotNull
    private String password;

    @Column(name = "login")
    @Size(min = 3, max = 255, message = "login должен быть от 3 до 255 символов")
    @NotBlank
    @NotNull
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
    private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_doctor",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> role;

    @Max(value = 2, message = "Не верно указан пол")
    @Min(value = 1, message = "Не верно указан пол")
    private int gender;

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

    public String getFio(){
        return this.lastName + " " + this.firstName + " " + this.patronymic;
    }
}

