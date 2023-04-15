create table clinics
(
    id      bigserial primary key,
    address varchar(255) not null,
    name    varchar(255) not null
);

create table doctors
(
    id             bigserial primary key,
    first_name     varchar(255) not null,
    last_name      varchar(255) not null,
    login          varchar(255),
    password       varchar(255),
    patronymic     varchar(100) not null,
    specialization varchar(255) not null,
    clinic_id      bigint
);

alter table if exists doctors
    add constraint clinic_id_fk
    foreign key (clinic_id) references clinics;

create table patients
(
    id           bigserial primary key,
    address      varchar(200),
    birth_date   date not null,
    email        varchar(100),
    first_name   varchar(100),
    gender       integer not null,
    last_name    varchar(100),
    patronymic   varchar(100),
    phone_number varchar(20),
    clinic_id    bigint
);

alter table if exists patients
    add constraint clinic_id_fk
    foreign key (clinic_id) references clinics;

alter table if exists patients
    add constraint patients_gender_check
    check ((gender <= 2) AND (gender >= 1));

create table registries
(
    id           bigserial primary key,
    email        varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    clinic_id       bigint
);

alter table if exists registries
    add constraint clinic_id_fk
    foreign key (clinic_id) references clinics;

create table schedules
(
    id                       bigserial primary key,
    date                     date,
    end_doctor_appointment   time,
    max_patient_per_day      integer not null,
    start_doctor_appointment time,
    doctor_id                bigint
);

alter table if exists schedules
    add constraint doctor_id_fk
    foreign key (doctor_id) references doctors;

create table medical_records
(
    id         bigserial primary key,
    record     varchar(255),
    patient_id bigint
);

alter table if exists medical_records
    add constraint patient_id_fk
    foreign key (patient_id) references patients;

create table appointments
(
    id          bigserial primary key,
    date        date,
    end_time    time,
    start_time  time,
    doctor_id   bigint,
    patient_id  bigint,
    schedule_id bigint
);

alter table if exists appointments
    add constraint doctor_id_fk
    foreign key (doctor_id) references doctors;

alter table if exists appointments
    add constraint patient_id_fk
    foreign key (patient_id) references patients;

alter table if exists appointments
    add constraint schedule_id_fk
    foreign key (schedule_id) references schedules;

create table analyzes
(
    id                bigserial primary key,
    date              timestamp(6),
    name              varchar(255),
    result            varchar(255),
    medical_record_id bigint
);

alter table if exists analyzes
    add constraint medical_record_id_fk
    foreign key (medical_record_id) references medical_records;

create table lab_tests
(
    id                bigserial primary key,
    description       varchar(255),
    name              varchar(255),
    result            varchar(255),
    medical_record_id bigint
);

alter table if exists lab_tests
    add constraint medical_record_id_fk
    foreign key (medical_record_id) references medical_records;

create table employees
(
    id         bigserial primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    patronymic varchar(100) not null,
    position   varchar(255)
);
