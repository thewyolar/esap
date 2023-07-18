delete from role_doctor;
delete from medical_card;
delete from patients;
delete from doctors;
delete from clinics;

alter sequence doctors_id_seq restart with 1;
alter sequence clinics_id_seq restart with 1;
alter sequence patients_id_seq restart with 1;
alter sequence medical_card_id_seq restart with 1;