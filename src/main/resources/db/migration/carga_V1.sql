INSERT INTO paciente (cpf, data_cadastro, nome, data_nascimento, ativo, senha, sexo, email, telefone, celular)
VALUES ('05667442108', '2021-10-02', 'Arthur', '1999-12-29', true, 'senha', 'M', 'arthur.correa@sempreceub.com', null,
        '61996108588');

INSERT INTO endereco_paciente (ativo, cep, uf, cidade, logradouro, paciente_cpf)
VALUES (true, '72630354', 'df', 'recanto', 'casa x', '05667442108');

INSERT INTO cuidador (cpf, data_cadastro, nome, data_nascimento, pontuacao, ativo, senha, sexo, email, telefone,
                      celular)
VALUES ('05553719135', '20211002', 'Franklin', '19931120', 4, true, 'senha', 'M', 'franklin@sempreceub.com', null,
        '6199999999');

INSERT INTO endereco_cuidador (ativo, cep, uf, cidade, logradouro, cuidador_cpf)
VALUES (true, '72600100', 'df', 'ceilandia', 'casa 2', '05553719135');