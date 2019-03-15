CREATE DATABASE if not exists askbk_tdt4145;

USE askbk_tdt4145;

DROP TABLE if exists Notat;
DROP TABLE if exists FriØvelse;
DROP TABLE if exists ApparatØvelse;
DROP TABLE if exists Apparat;
DROP TABLE if exists ØvelseIØkt;
DROP TABLE if exists ØvelseIGruppe;
DROP TABLE if exists Gruppe;
DROP TABLE if exists Øvelse;
DROP TABLE if exists Økt;


CREATE TABLE Økt(
ØktId int NOT NULL AUTO_INCREMENT,
ØktDato date NOT NULL,
ØktTidspunkt Timestamp NOT NULL,
ØktVarighet int NOT NULL,
PersonligForm int NOT NULL,
PersonligPrestasjon int NOT NULL,
PRIMARY KEY (ØktId));

CREATE TABLE Øvelse(
ØvelseId int NOT NULL AUTO_INCREMENT,
ØvelseNavn varchar(30) NOT NULL,
Øvelsetype int NOT NULL,
PRIMARY KEY (ØvelseId));

CREATE TABLE Notat(
ØktId int NOT NULL,
Treningsformål varchar(60) NOT NULL,
TreningsOpplevelse varchar(60) NOT NULL,
PRIMARY KEY (ØktId),
FOREIGN KEY (ØktId) REFERENCES Økt(ØktId)
ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Gruppe(
GruppeId int NOT NULL AUTO_INCREMENT,
GruppeNavn varchar(30) NOT NULL,
PRIMARY KEY (GruppeId));

CREATE TABLE FriØvelse(
    ØvelseId int NOT NULL,
FriØvelseBeskrivelse varchar(60) NOT NULL,
FOREIGN KEY (ØvelseId) REFERENCES Øvelse(ØvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE Apparat(
ApparatId int NOT NULL AUTO_INCREMENT,
ApparatNavn varchar(30) NOT NULL,
ApparatBeskrivelse varchar(30) NOT NULL,
PRIMARY KEY (ApparatId));

CREATE TABLE ApparatØvelse(
ApparatID int NOT NULL,
ØvelseID int NOT NULL,
PRIMARY KEY (ØvelseID),
FOREIGN KEY (ApparatId) REFERENCES Apparat(ApparatId)
ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (ØvelseId) REFERENCES Øvelse(ØvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);



CREATE TABLE ØvelseIØkt(
    ØktId int NOT NULL,
    ØvelseId int NOT NULL,
Kilo float,
sett int NOT NULL,
FOREIGN KEY (ØktId) REFERENCES Økt(ØktId)
ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (ØvelseId) REFERENCES Øvelse(ØvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);

CREATE TABLE ØvelseIGruppe(
    GruppeId int NOT NULL,
    ØvelseId int NOT NULL,
FOREIGN KEY (GruppeId) REFERENCES Gruppe(GruppeId),
FOREIGN KEY (ØvelseId) REFERENCES Øvelse(ØvelseId)
ON DELETE NO ACTION ON UPDATE CASCADE);
