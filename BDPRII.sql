create table Cliente (
	codCliente int primary key not null,

	nome varChar(50) not null
);

create table Banco (
	codBanco int primary key not null,

	nome			varChar(30) not null,
	jurosPoupanca	 real		not null,
	jurosEmprestimo  real		not null,
	mesesEmprestimo  int 		not null,
	emprestimoMinimo money		not null,
	emprestimoMaximo money		not null
);

create table ContaBancaria (
	codContaBancaria int primary key not null,
	codCliente		 int		  not null,
	codBanco		 int	      not null,
	constraint fk_CodCliente	   foreign key (codCliente)       references Cliente(codCliente),
	constraint fk_CodBanco		   foreign key (codBanco)		  references Banco  (codBanco),

	senha char(10) not null,
	saldo money    not null
);

create table Saque (
	codSaque		 int primary key not null,
	codContaBancaria int		  not null,
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	valor money not null
);

create table Deposito (
	codDeposito		 int primary key not null,
	codContaBancaria int          not null,
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	valor money not null
);

create table Emprestimo (
	codEmprestimo    int primary key not null,
	codContaBancaria int, --Deixará de ser nulo assim que for aceito e será sugerido pelo cliente
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	mesesParaPagar  int   not null,			
	valorParaQuitar money not null,			
	valorOriginal   money not null
);

create table TipoCobranca ( --Tabela de hard data para ser usada na tabela "Cobranca"
	codTipoCobranca int primary key not null,

	nome varChar(30) not null
);

create table Cobranca (
	codCobranca      int primary key not null,
	codContaBancaria int		  not null,
	codTipoCobranca  int		  not null,
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),
	constraint fk_codTipoCobranca  foreign key (codTipoCobranca)  references TipoCobranca (codTipoCobranca) ,

	foiPaga		     bit   not null,
	dataDeVencimento date  not null,
	valor		     money not null	
);

create table Boleto  ( --Criado assim que "foiPaga" de "Cobranca" virar 1 (true)
	codBoleto   int primary key not null,
	codCobranca int          not null,
	constraint fk_CodCobranca foreign key (codCobranca) references Cobranca(codCobranca) ,

	operacao        varChar(20) not null,
	dataDeGeracao   timestamp    not null,
	valor	        money       not null,
	codAutenticacao int         not null
);

--Dados para a tabela "TipoCobranca", que é de hard data
insert into TipoCobranca values (1,
	'Conta de luz'
);

insert into TipoCobranca values (2,
	'Conta de água'
);

insert into TipoCobranca values (3,
	'Conta de internet / telefone'
);

insert into TipoCobranca values (4,
	'Parcela de empréstimo'
);
