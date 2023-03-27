package ru.javavlsu.kb.esap.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "registries")
public class Registry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "clinic")
    private Clinic clinic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry registry = (Registry) o;
        return Objects.equals(id, registry.id) && Objects.equals(phoneNumber, registry.phoneNumber) && Objects.equals(email, registry.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, email);
    }
}
