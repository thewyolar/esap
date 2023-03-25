package ru.javavlsu.kb.esap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import ru.javavlsu.kb.esap.model.Doctor;

import java.util.List;

@Getter
@Setter
public class ClinicDTO {

    private String name;

    private String address;

}
