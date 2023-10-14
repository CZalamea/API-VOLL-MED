
ALTER TABLE pacientes
CHANGE COLUMN documentoIdentidad documento VARCHAR(6) NOT NULL UNIQUE;