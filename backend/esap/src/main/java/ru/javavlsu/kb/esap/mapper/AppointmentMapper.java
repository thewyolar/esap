package ru.javavlsu.kb.esap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.model.Appointment;

@Component
public class AppointmentMapper {

    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Appointment toAppointment(AppointmentDTO appointmentDTO){
        return modelMapper.map(appointmentDTO, Appointment.class);
    }
}

