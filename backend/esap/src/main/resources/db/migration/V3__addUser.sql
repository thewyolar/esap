create table users
(
    id bigserial not null,
    login varchar(255),
    password varchar(255),
    primary key (id)
);
INSERT INTO users (id, login, password)
SELECT id, login, password FROM doctors;
alter table doctors drop password;
alter table doctors drop login;
alter table doctors add constraint doctors_fk foreign key (id) references users(id);
