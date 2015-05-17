/*Create Data Base*/
CREATE DATABASE db_BalletPaperInMobile;
/*usar base de datos*/
USE db_BalletPaperInMobile;
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
	status INT,
	date_created TIMESTAMP DEFAULT NOW(),
	date_updated TIMESTAMP DEFAULT NOW(),
	user_created INT,
	user_updated INT
);
 

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