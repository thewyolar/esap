package ru.javavlsu.kb.esap.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "lab_tests")
@Deprecated
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String result;

//    @ManyToOne
//    @JoinColumn(name = "medical_record_id")
//    private MedicalCard medicalCard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabTest labTest = (LabTest) o;
        return Objects.equals(id, labTest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}