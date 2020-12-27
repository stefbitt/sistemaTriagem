/*DROP TABLE t_medico;
DROP TABLE t_enfermeiro;
DROP TABLE t_paciente;*/

CREATE TABLE IF NOT EXISTS T_Medico (
    medicoID INT NOT NULL AUTO_INCREMENT,
    userID INT NOT NULL,
    crm INT NOT NULL UNIQUE,
    PRIMARY KEY (medicoID),
    FOREIGN KEY (userID) REFERENCES T_user(userID)
);


CREATE TABLE IF NOT EXISTS T_Enfermeiro (
    enfermeiroID INT NOT NULL AUTO_INCREMENT,
    userID INT NOT NULL,
    matricula INT NOT NULL UNIQUE,
    PRIMARY KEY (EnfermeiroID),
    FOREIGN KEY (userID) REFERENCES T_user(userID)
    
);

CREATE TABLE IF NOT EXISTS T_Paciente (
    pacienteID INT NOT NULL AUTO_INCREMENT,
    userID INT NOT NULL,
    cpf INT NOT NULL UNIQUE,
    PRIMARY KEY (pacienteID),
    FOREIGN KEY (userID) REFERENCES T_user(userID)
    
);

CREATE TABLE IF NOT EXISTS T_User (
    userID INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(15),
    PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS T_Atendimento (
    atendimentoID INT NOT NULL AUTO_INCREMENT,
    tipo_atendimento VARCHAR(50) NOT NULL,
    pacienteID INT NOT NULL,
    horario_chegada DATE,
    horario_inicio_atendimento DATE,
    horario_fim_atendimento DATE,
    medicoID INT,
    PRIMARY KEY (atendimentoID),
    FOREIGN KEY (pacienteID) REFERENCES t_paciente(pacienteID),
    FOREIGN KEY (medicoID) REFERENCES t_medico(medicoID)
);



