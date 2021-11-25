CREATE TABLE alergia
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    alergia  VARCHAR(255)                            NOT NULL,
    grau     INTEGER                                 NOT NULL,
    paciente VARCHAR(11),
    CONSTRAINT pk_alergia PRIMARY KEY (id)
);

CREATE TABLE certificacao
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    instituicao   VARCHAR(255)                            NOT NULL,
    titulo        VARCHAR(100)                            NOT NULL,
    carga_horaria INTEGER,
    cuidador_cpf  VARCHAR(11),
    CONSTRAINT pk_certificacao PRIMARY KEY (id)
);

CREATE TABLE combinacao
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    qtd_dias     INTEGER                                 NOT NULL,
    dias         VARCHAR(255)                            NOT NULL,
    preco        DECIMAL(8, 2)                           NOT NULL,
    hora_inicio  TIME WITHOUT TIME ZONE                  NOT NULL,
    hora_fim     TIME WITHOUT TIME ZONE                  NOT NULL,
    avaliacao    INTEGER,
    comentario   VARCHAR(255),
    cuidador_cpf VARCHAR(11)                             NOT NULL,
    paciente_cpf VARCHAR(11)                             NOT NULL,
    CONSTRAINT pk_combinacao PRIMARY KEY (id)
);

CREATE TABLE comorbidade
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    comorbidade  VARCHAR(255)                            NOT NULL,
    cuidados     VARCHAR(255),
    paciente_cpf VARCHAR(11),
    CONSTRAINT pk_comorbidade PRIMARY KEY (id)
);

CREATE TABLE cuidador
(
    cpf             VARCHAR(11)  NOT NULL,
    data_cadastro   date         NOT NULL,
    nome            VARCHAR(255) NOT NULL,
    data_nascimento date         NOT NULL,
    ativo           BOOLEAN      NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    pontuacao       INTEGER,
    sexo            VARCHAR(1)   NOT NULL,
    email           VARCHAR(150),
    celular         VARCHAR(12),
    cep             VARCHAR(8)   NOT NULL,
    uf              VARCHAR(2)   NOT NULL,
    cidade          VARCHAR(100) NOT NULL,
    logradouro      VARCHAR(255) NOT NULL,
    latitude        VARCHAR(255) NOT NULL,
    longitude       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cuidador PRIMARY KEY (cpf)
);

CREATE TABLE dispositivo
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    dispositivo  VARCHAR(255)                            NOT NULL,
    cuidados     VARCHAR(255),
    paciente_cpf VARCHAR(11),
    CONSTRAINT pk_dispositivo PRIMARY KEY (id)
);

CREATE TABLE experiencia
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    empresa      VARCHAR(255)                            NOT NULL,
    cargo        VARCHAR(255)                            NOT NULL,
    descricao    VARCHAR(255)                            NOT NULL,
    data_inicio  date                                    NOT NULL,
    data_fim     date,
    atual        BOOLEAN                                 NOT NULL,
    cuidador_cpf VARCHAR(11),
    CONSTRAINT pk_experiencia PRIMARY KEY (id)
);

CREATE TABLE formacao
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    instituicao   VARCHAR(255)                            NOT NULL,
    curso         VARCHAR(255)                            NOT NULL,
    periodo       VARCHAR(1)                              NOT NULL,
    data_inicio   date                                    NOT NULL,
    data_formacao date,
    em_andamento  BOOLEAN                                 NOT NULL,
    cuidador_cpf  VARCHAR(11),
    CONSTRAINT pk_formacao PRIMARY KEY (id)
);

CREATE TABLE paciente
(
    cpf             VARCHAR(11)  NOT NULL,
    data_cadastro   date         NOT NULL,
    nome            VARCHAR(255) NOT NULL,
    data_nascimento date         NOT NULL,
    ativo           BOOLEAN      NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    sexo            VARCHAR(1)   NOT NULL,
    email           VARCHAR(150),
    celular         VARCHAR(12),
    observacoes     VARCHAR(255),
    cep             VARCHAR(8)   NOT NULL,
    uf              VARCHAR(2)   NOT NULL,
    cidade          VARCHAR(100) NOT NULL,
    logradouro      VARCHAR(255) NOT NULL,
    latitude        VARCHAR(255) NOT NULL,
    longitude       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_paciente PRIMARY KEY (cpf)
);

CREATE TABLE prescricao
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    remedio      VARCHAR(200)                            NOT NULL,
    horarios     VARCHAR(255)                            NOT NULL,
    via          VARCHAR(1)                              NOT NULL,
    paciente_cpf VARCHAR(11),
    CONSTRAINT pk_prescricao PRIMARY KEY (id)
);

ALTER TABLE cuidador
    ADD CONSTRAINT uc_cuidador_celular UNIQUE (celular);

ALTER TABLE cuidador
    ADD CONSTRAINT uc_cuidador_email UNIQUE (email);

ALTER TABLE paciente
    ADD CONSTRAINT uc_paciente_celular UNIQUE (celular);

ALTER TABLE paciente
    ADD CONSTRAINT uc_paciente_email UNIQUE (email);

ALTER TABLE alergia
    ADD CONSTRAINT FK_ALERGIA_ON_PACIENTE FOREIGN KEY (paciente) REFERENCES paciente (cpf);

ALTER TABLE certificacao
    ADD CONSTRAINT FK_CERTIFICACAO_ON_CUIDADOR_CPF FOREIGN KEY (cuidador_cpf) REFERENCES cuidador (cpf);

ALTER TABLE combinacao
    ADD CONSTRAINT FK_COMBINACAO_ON_CUIDADOR_CPF FOREIGN KEY (cuidador_cpf) REFERENCES cuidador (cpf);

ALTER TABLE combinacao
    ADD CONSTRAINT FK_COMBINACAO_ON_PACIENTE_CPF FOREIGN KEY (paciente_cpf) REFERENCES paciente (cpf);

ALTER TABLE comorbidade
    ADD CONSTRAINT FK_COMORBIDADE_ON_PACIENTE_CPF FOREIGN KEY (paciente_cpf) REFERENCES paciente (cpf);

ALTER TABLE dispositivo
    ADD CONSTRAINT FK_DISPOSITIVO_ON_PACIENTE_CPF FOREIGN KEY (paciente_cpf) REFERENCES paciente (cpf);

ALTER TABLE experiencia
    ADD CONSTRAINT FK_EXPERIENCIA_ON_CUIDADOR_CPF FOREIGN KEY (cuidador_cpf) REFERENCES cuidador (cpf);

ALTER TABLE formacao
    ADD CONSTRAINT FK_FORMACAO_ON_CUIDADOR_CPF FOREIGN KEY (cuidador_cpf) REFERENCES cuidador (cpf);

ALTER TABLE prescricao
    ADD CONSTRAINT FK_PRESCRICAO_ON_PACIENTE_CPF FOREIGN KEY (paciente_cpf) REFERENCES paciente (cpf);