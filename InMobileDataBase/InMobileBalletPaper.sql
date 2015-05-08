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