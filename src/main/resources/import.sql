INSERT INTO pessoa (nome, data_de_nascimento) values ('Jose','1965-08-16');
INSERT INTO pessoa (nome, data_de_nascimento) values ('Maria','1995-08-16');

INSERT INTO endereco(logradouro,cep,numero,cidade,principal,pessoa_id) values ('Rua Evaldo Martins','57302-623','265','Arapiraca',false,1);
INSERT INTO endereco(logradouro,cep,numero,cidade,principal,pessoa_id) values ('Rua Santa Dorotéia','64040-320','327','Teresina',false,1);
INSERT INTO endereco(logradouro,cep,numero,cidade,principal,pessoa_id) values ('Rua Severino Fernandes de Oliveira','58411-058','246','Campina Grande',true,1);

INSERT INTO endereco(logradouro,cep,numero,cidade,principal,pessoa_id) values ('Rua Itacolomi','27977-340','735','Macaé',true,2);
INSERT INTO endereco(logradouro,cep,numero,cidade,principal,pessoa_id) values ('Travessa Vitória Gama','45986-020','207','Teixeira de Freitas',false,2);