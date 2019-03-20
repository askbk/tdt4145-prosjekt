DROP DATABASE if exists askbk_tdt4145;
CREATE DATABASE if not exists askbk_tdt4145;

USE askbk_tdt4145;

DROP TABLE if exists Notat;
DROP TABLE if exists FriOvelse;
DROP TABLE if exists ApparatOvelse;
DROP TABLE if exists Apparat;
DROP TABLE if exists OvelseIOkt;
DROP TABLE if exists OvelseIGruppe;
DROP TABLE if exists Gruppe;
DROP TABLE if exists Ovelse;
DROP TABLE if exists Okt;


CREATE TABLE Okt(
OktId int NOT NULL AUTO_INCREMENT,
OktDato date NOT NULL,
OktTidspunkt Timestamp NOT NULL,
OktVarighet int NOT NULL,
PersonligForm int NOT NULL,
PersonligPrestasjon int NOT NULL,
PRIMARY KEY (OktId));

CREATE TABLE Ovelse(
OvelseId int NOT NULL AUTO_INCREMENT,
OvelseNavn varchar(30) NOT NULL,
Ovelsetype int NOT NULL,
PRIMARY KEY (OvelseId));

CREATE TABLE Notat(
OktId int NOT NULL,
Treningsformal varchar(60) NOT NULL,
TreningsOpplevelse varchar(60) NOT NULL,
PRIMARY KEY (OktId),
FOREIGN KEY (OktId) REFERENCES Okt(OktId)
ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Gruppe(
GruppeId int NOT NULL AUTO_INCREMENT,
GruppeNavn varchar(30) NOT NULL,
PRIMARY KEY (GruppeId));

CREATE TABLE FriOvelse(
    OvelseId int NOT NULL,
FriOvelseBeskrivelse varchar(60) NOT NULL,
FOREIGN KEY (OvelseId) REFERENCES Ovelse(OvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE Apparat(
ApparatId int NOT NULL AUTO_INCREMENT,
ApparatNavn varchar(30) NOT NULL,
ApparatBeskrivelse varchar(30) NOT NULL,
PRIMARY KEY (ApparatId));

CREATE TABLE ApparatOvelse(
ApparatID int NOT NULL,
OvelseID int NOT NULL,
PRIMARY KEY (OvelseID),
FOREIGN KEY (ApparatId) REFERENCES Apparat(ApparatId)
ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (OvelseId) REFERENCES Ovelse(OvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);



CREATE TABLE OvelseIOkt(
    OktId int NOT NULL,
    OvelseId int NOT NULL,
Kilo float,
sett int NOT NULL,
FOREIGN KEY (OktId) REFERENCES Okt(OktId)
ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (OvelseId) REFERENCES Ovelse(OvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE OvelseIGruppe(
    GruppeId int NOT NULL,
    OvelseId int NOT NULL,
FOREIGN KEY (GruppeId) REFERENCES Gruppe(GruppeId),
FOREIGN KEY (OvelseId) REFERENCES Ovelse(OvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);
