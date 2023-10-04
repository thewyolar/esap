INSERT INTO role (name) VALUES ('ROLE_PATIENT');
alter table patients add constraint patients_fk foreign key (id) references users(id);