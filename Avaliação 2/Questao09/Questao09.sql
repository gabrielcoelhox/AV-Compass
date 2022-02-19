use testeavaliacao;
select * from produtos;

CREATE TABLE produtos (
	Id int(11) NOT NULL AUTO_INCREMENT,
	Nome varchar(60) NOT NULL,
	descricao varchar(100) NOT NULL,
	valor DOUBLE,
	desconto double ,
	DataInicio date NOT NULL,
	PRIMARY KEY (Id)
);

INSERT INTO produtos (Nome, descricao, valor, desconto, DataInicio) VALUES 
  ('Iphone 13','Iphone 13 128GB - Azul', '5500', '8', '2022-02-16'),
  ('Notebook Dell','Dell Inspiron 15 i5 256GB', '4000', '5', '2022-01-18'),
  ('Teclado Logitech','Logitech K380 Bluetooth', '199', '10', '2022-01-19'),
  ('Suporte para notebook','Suporte dobrável de alumínio ajustável', '50', '3','2021-11-30'),
  ('Bola de futsal','Bola Topper Slik II', '60','5', '2022-02-03'),
  ('Mochila Dell', 'Mochila Pro Transporta Notebook 15.6"', '200', '0', '2022-02-19'),
  ('Placa Mãe', 'Gigabyte Z390 Gaming', '1049', '0', '2022-02-15'),
  ('Case notebook pocket', 'Notebook até 14 polegadas', '55', '0', '2022-02-18');
