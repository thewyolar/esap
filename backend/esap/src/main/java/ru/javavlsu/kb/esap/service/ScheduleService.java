package ru.javavlsu.kb.esap.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void create(Schedule schedule) throws NotCreateException{
        long minutesBetweenStartAndEnd = schedule.getStartDoctorAppointment().until(schedule.getEndDoctorAppointment(), ChronoUnit.MINUTES);
        if(minutesBetweenStartAndEnd <= 0 && minutesBetweenStartAndEnd % 30 != 0){
            throw new NotCreateException("Invalid schedule time");
        }
        schedule.setMaxPatientPerDay((int)minutesBetweenStartAndEnd / 30);
        scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllByDoctorId(Long doctorId) {
        return scheduleRepository.findAllByDoctorId(doctorId);
    }

    public Schedule getByIdAndDoctor(long id, Doctor doctor){
        Optional<Schedule> schedule = scheduleRepository.findByIdAndDoctor(id, doctor);
        return schedule.orElseThrow(() -> new NotFoundException("Schedule not found"));
    }

    public Schedule getByDateAndDoctor(LocalDate date, Doctor doctor) throws NotCreateException{
        return scheduleRepository.findByDateAndDoctor(date, doctor)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
    }

}
