package ru.javavlsu.kb.esap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.model.Schedule;


@Component
public class ScheduleMapper {

    private final ModelMapper modelMapper;

    public ScheduleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public ScheduleResponseDTO toScheduleResponseDTO(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
    }

}
