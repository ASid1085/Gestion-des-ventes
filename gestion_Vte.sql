create schema gestionVte;

use gestionVte;

create table USER (
	idUser		int AUTO_INCREMENT 	not null,
    userName 	varchar(100)		not null,
    mdp			varchar(255)		not null,
    acces		varchar(20)			not null,
    constraint pk_User primary key (idUser));
    
create table PRODUCT (
	idProduct	int AUTO_INCREMENT	not null,
    nameProduct	varchar(255)		not null,
    price		decimal(8,2)		not null,
    constraint	pk_Product primary key (idProduct));

create table COMMAND (
	idCde		int				 	not null,
    totalPrice	decimal(8,2)		not null,
    dateCde		date				not null,
    constraint pk_Command primary key (idCde));
    
create table COMMANDLINE (
	idCdeLine	int AUTO_INCREMENT	not null,
    idProduct	int					not null,
    quantite	int					not null,
    idCde		int					not null,
    constraint pk_CdeLine primary key (idCdeLine),
	constraint fk_COMMAND foreign key (idCde) references COMMAND(idCde),
    constraint fk_PRODUCT foreign key (idProduct) references PRODUCT(idProduct));

insert into PRODUCT (nameProduct, price) values 
	( 'Une brève histoire du temps', 6.80),
    ( 'Une histoire de tout ou presque', 10.65),
    ( 'Poussières d\'étoiles', 10.10),
    ( 'L\'Origine des espèces', 12),
    ( 'Discours sur l\'origine de l\'univers', 17.30),
    ( 'L\'Univers expliqué à mes petits enfants', 7.10),
    ( 'Prenez le temps d\'e-penser, tome 1', 5.49),
    ( 'Le Charme discret de l\'intestin', 21.80),
    ( 'L\'univers élégant', 12.30),
    ( 'Le Gène égoïste', 12.50),
    ( 'Sapiens', 24),
    ( 'La Relativité', 7.70);

insert into USER(userName, mdp, acces) values 
	( 'sid', 'sid1', 'admin');
    
select * from USER;
update USER set userName ='patrick', mdp ='pat1', acces ='employé' where userName = 'pat';

select * from COMMANDLINE;
delete from COMMANDLINE;

select * from COMMAND;
delete from COMMAND;

SELECT mdp FROM USER WHERE userName ='sid';

SELECT COUNT(*) FROM USER;

select
	pr.nameProduct as 'Produit',
	pr.price as 'Prix unitaire',
	cdeLi.quantite as 'Qté',
	(pr.price * cdeLi.quantite) as 'Prix total'
from COMMANDLINE as cdeLi	INNER JOIN COMMAND as cde ON cdeLi.idCde = cde.idCde
							INNER JOIN PRODUCT as pr ON cdeLi.idProduct = pr.idProduct
and cdeLi.idCde = '1';
