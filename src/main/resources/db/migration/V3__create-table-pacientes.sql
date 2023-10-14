
CREATE TABLE pacientes (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           nombre VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           documentoIdentidad VARCHAR(6) NOT NULL UNIQUE,
                           telefono varchar (20) not null,
                           calle VARCHAR(100) NOT NULL,
                           numero VARCHAR(20),
                           distrito VARCHAR(100) NOT NULL,
                           complemento VARCHAR(100),
                           ciudad VARCHAR(100) NOT NULL,

                           PRIMARY KEY (id)
);