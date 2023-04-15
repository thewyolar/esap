INSERT INTO clinics (address, name) VALUES ('Владимир, ул. Горького, д. 12', 'Поликлиника №1');

INSERT INTO doctors (first_name, last_name, login, password, patronymic, specialization, clinic_id) VALUES ('Иван', 'Иванов', 'admin', 'admin', 'Иванович', 'Терапевт', 1);

INSERT INTO patients (address, birth_date, email, first_name, gender, last_name, patronymic, phone_number, clinic_id) VALUES ('ул. Пушкина, д. 10, кв. 5', '1990-05-15', 'ivanov@mail.ru', 'Иван', 1, 'Иванов', 'Иванович', '+7(999)123-45-67', 1);

INSERT INTO medical_card (patient_id) VALUES (1);

INSERT INTO schedules (date, end_doctor_appointment, max_patient_per_day, start_doctor_appointment, doctor_id) VALUES ('2023-03-26', '18:30:00', 20, '08:30:00', 1);

INSERT INTO appointments (date, end_time, start_time, doctor_id, patient_id, schedule_id) VALUES ('2023-03-26', '11:00:00', '10:30:00', 1, 1, 1);