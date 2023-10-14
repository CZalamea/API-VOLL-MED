
CREATE TABLE consultas(

    id bigint not null AUTO_INCREMENT,
    medico_id bigint not null,
    paciente_id bigint NOT NULL,
    data datetime NOT NULL,

        primary key(id),

        constraint fk_consultas_medico_id foreign key (medico_id) references medicos(id),
        constraint fk_consultas_paciente_if FOREIGN KEY (paciente_id) references pacientes(id)
);