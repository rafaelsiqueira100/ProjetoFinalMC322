create table Cliente (
	codCliente int identity not null,

	constraint pk_CodCliente primary key (codCliente),

	nome varChar(50) not null
)

create table Banco (
	codBanco int identity not null,

	constraint pk_CodBanco primary key (codBanco),

	nome			varChar(30) not null,
	jurosPoupanca	 real		not null,
	jurosEmprestimo  real		not null
	mesesEmprestimo  int 		not null,
	emprestimoMinimo money		not null,
	emprestimoMaximo money		not null
)

create table ContaBancaria (
	codContaBancaria int identity not null,
	codCliente		 int		  not null,
	codBanco		 int	      not null,

	constraint pk_CodContaBancaria primary key (codContaBancaria),	
	constraint fk_CodCliente	   foreign key (codCliente)       references Cliente(codCliente),
	constraint fk_CodBanco		   foreign key (codBanco)		  references Banco  (codBanco),

	senha char(10) not null,
	saldo money    not null
)

create table Saque (
	codSaque		 int identity not null,
	codContaBancaria int		  not null,

	constraint pk_CodSaque		   primary key (codSaque),
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	valor money not null
)

create table Deposito (
	codDeposito		 int identity not null,
	codContaBancaria int          not null,

	constraint pk_CodDeposito	   primary key (codDeposito),
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	valor money not null
)

create table Emprestimo (
	codEmprestimo    int identity not null,
	codContaBancaria int, --Deixará de ser nulo assim que for aceito e será sugerido pelo cliente

	constraint pk_CodEmprestimo	   primary key (codEmprestimo),
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),

	mesesParaPagar  int   not null,			
	valorParaQuitar money not null,			
	valorOriginal   money not null
)

create table TipoCobranca ( --Tabela de hard data para ser usada na tabela "Cobranca"
	codTipoCobranca int identity not null,

	constraint pk_CodTipoCobranca primary key (codTipoCobranca),

	nome varChar(30) not null
)

create table Cobranca (
	codCobranca      int identity not null,
	codContaBancaria int		  not null,
	codTipoCobranca  int		  not null,

	constraint pk_CodCobranca	   primary key (codCobranca),
	constraint fk_CodContaBancaria foreign key (codContaBancaria) references ContaBancaria(codContaBancaria),
	constraint fk_codTipoCobranca  foreign key (codTipoCobranca)  references TipoCobranca (codTipoCobranca) 

	foiPaga		     bit   not null,
	dataDeVencimento date  not null,
	valor		     money not null	
)

create table Boleto  ( --Criado assim que "foiPaga" de "Cobranca" virar 1 (true)
	codBoleto   int identity not null,
	codCobranca int          not null,

	constraint pk_CodBoleto   primary key (codBoleto),
	constraint fk_CodCobranca foreign key (codCobranca) references Cobranca(codCobranca) 

	operacao        varChar(20) not null,
	dataDeGeracao   dateTime    not null,
	valor	        money       not null,
	codAutenticacao int         not null
)

--Dados para a tabela "TipoCobranca", que é de hard data
insert into TipoCobranca values (
	'Conta de luz'
)

insert into TipoCobranca values (
	'Conta de água'
)

insert into TipoCobranca values (
	'Conta de internet / telefone'
)

insert into TipoCobranca values (
	'Parcela de empréstimo'
)

select * from Emprestimo