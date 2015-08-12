/*Create Data Base*/
CREATE DATABASE db_BalletPaperInMobile;
/*usar base de datos*/
USE db_BalletPaperInMobile;
#SET SQL_SAFE_UPDATES = 0;
#SET SQL_SAFE_UPDATES = 0;

CREATE TABLE tb_User(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, /*Id de Usuario*/
	email VARCHAR(60),
	passwordUser VARCHAR(60),
	typeUser INT,
	namesUser varchar(500),
	lastNameUser varchar(500),
	dniUser varchar(15),
	recordingDevice varchar(100),
	nameDistrict varchar(100),
	status INT,
	date_created TIMESTAMP,
	date_updated TIMESTAMP,
	user_created INT,
	user_updated INT
);
insert into tb_User(id,email,passwordUser,typeUser,namesUser,lastNameUser,status,recordingDevice,nameDistrict)
values(0,'admin@user.com','3132333435363738',99,'Adminsitrador','De Todo',1,'DATA_BASE','');

CREATE TABLE tb_Request_Response(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	typeOperation VARCHAR(100),
	idUser INT,
	request LONGTEXT,
	response LONGTEXT,
	status INT,
	date_created TIMESTAMP DEFAULT NOW(),
	user_created INT
);

CREATE TABLE tb_Complient(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUser int,
	status int,
	longitude varchar(20),
	latitude varchar(20),
	completeAddress varchar(100),
	comments varchar(200),
	number_plate varchar(20),
	distrinctName varchar(50),
	typeComplaint int,
	date_created TIMESTAMP DEFAULT NOW(),
	user_created INT
);

ALTER TABLE tb_Complient
ADD FOREIGN KEY (idUser)
REFERENCES tb_User(id);

/*Table Image*/
CREATE TABLE tb_Image(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	categoryImage VARCHAR(200),
	img LONGBLOB NOT NULL,
	status int,
	date_created TIMESTAMP DEFAULT NOW(),
	date_updated TIMESTAMP DEFAULT NOW(),
	user_created INT,
	user_updated INT
);

CREATE TABLE tb_Complaint_Image(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idComplaint int,
	idUser int,
	idImage int,
	date_created TIMESTAMP DEFAULT NOW(),
	user_created INT
);

ALTER TABLE tb_Complaint_Image
ADD FOREIGN KEY (idComplaint)
REFERENCES tb_Complient(id);

ALTER TABLE tb_Complaint_Image
ADD FOREIGN KEY (idImage)
REFERENCES tb_Image(id);

CREATE TABLE tb_TypeComplaint(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	categoryComplaint VARCHAR(200),
	status int,
	date_created TIMESTAMP DEFAULT NOW(),
	user_created INT
);

insert into tb_TypeComplaint (id,categoryComplaint,status,user_created) values(0,'Sin Asignacion',1,1);

ALTER TABLE tb_Complient
ADD FOREIGN KEY (typeComplaint)
REFERENCES tb_TypeComplaint(id);

CREATE TABLE tb_country(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	countryName VARCHAR(100),
	countryId VARCHAR(20)
); 
CREATE TABLE tb_departments(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	departmentName VARCHAR(100),
	countryId int
); 
ALTER TABLE tb_departments
ADD FOREIGN KEY (countryId)
REFERENCES tb_country(id);

CREATE TABLE tb_province(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	provinceName VARCHAR(200),
	departmentId int
);
ALTER TABLE tb_province
ADD FOREIGN KEY (departmentId)
REFERENCES tb_departments(id);

CREATE TABLE tb_district(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	districtName VARCHAR(200),
	provinceId int
);
ALTER TABLE tb_district
ADD FOREIGN KEY (provinceId)
REFERENCES tb_province(id);

/*
	New Script ---> 11-08-2015
*/
CREATE TABLE tb_system_param(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	generalParam VARCHAR(200),
	nameParam VARCHAR(200),
	reasonParam VARCHAR(200),
	valueParam VARCHAR(300),
	status int,
	date_created TIMESTAMP DEFAULT NOW()
);

insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_FROM','Email for send mail','ojovial@inmobile.pe',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','PASSWORD_FROM','Password for send mail','ojo1234viale',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','SUBJECT_CREATION_USER','Subject Creation User for send mail','InMobile - Creation User',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','SUBJECT_COMPLETE_COMPLAINT','Sbject Complete Complaint for send mail','InMobile - Registro Denuncia',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_TRUE','Configuration Email ','true',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_SMTP_GMAIL','Configuration Value for Send Email SMTP','smtp.gmail.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_PORT_GMAIL','Configuration Value for Send Email PORT','587',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','BODY_EMAIL_CREATION_USER','Texto para enviar en el correo','<html><body><p><b>InMobile Bienvenido - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicación</p><p>Su cuenta ha sido creada con exito</p><p><b>Gracias</b></p></body></html>',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','BODY_EMAIL_COMPLETE_COMPLAINT','Texto para enviar en el correo de Denuncia Registrada','<html><body><p><b>InMobile Generacion de Denuncia - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicación</p><p>Su denuncia fue registrada con exito, aqui el detalle : </p><p><b>Id Denuncia : </b>+idComplaint+</p><p><b>Lugar de la Infracción : </b>+address+</p><p><b>Placa del Vehiculo : </b>+numberPlate+</p><p><b>Estados de Denuncia : ABIERTA</b></p><p>Le estaremos informando del proceso de esta denuncia</p><p><b>Gracias</b></p></body></html>;',1);
select * from tb_system_param;