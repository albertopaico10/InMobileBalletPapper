insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_FROM_COMPLAINT','Email for send mail specific complaint','denuncias@ojovial.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_FROM_CREATION_USER','Email for send mail specific creation user','registro@ojovial.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','PASSWORD_FROM','Password for send mail','ojovial.2015',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','SUBJECT_CREATION_USER','Subject Creation User for send mail','InMobile - Creation User',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','SUBJECT_COMPLETE_COMPLAINT','Sbject Complete Complaint for send mail','InMobile - Registro Denuncia',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_TRUE','Configuration Email ','true',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_SMTP_GMAIL','Configuration Value for Send Email SMTP','smtp.gmail.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_SMTP_OJOVIAL','Configuration Value for Send Email OJO VIAL SMTP','mail.ojovial.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_PORT_OJOVIAL','Configuration Value for Send Email OJO VIAL PORT','465',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_PORT_GMAIL','Configuration Value for Send Email PORT','25',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','BODY_EMAIL_CREATION_USER','Texto para enviar en el correo','<html><body><p><b>InMobile Bienvenido - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicaci?n</p><p>Su cuenta ha sido creada con exito</p><p><b>Gracias</b></p></body></html>',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','BODY_EMAIL_COMPLETE_COMPLAINT','Texto para enviar en el correo de Denuncia Registrada','<html><body><p><b>InMobile Generacion de Denuncia - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicación</p><p>Su denuncia fue registrada con exito, aqui el detalle : </p><p><b>Id Denuncia : </b>${idComplaint}</p><p><b>Lugar de la Infracción : </b>${address}</p><p><b>Placa del Vehiculo : </b>${numberPlate}</p><p><b>Estados de Denuncia : ABIERTA</b></p><p>Le estaremos informando del proceso de esta denuncia</p><p><b>Gracias</b></p></body></html>',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','TRANSPORT_PROTOCOL_OJOVIAL','Configuration Value for Transport Protocol OJO VIAL','smtp',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_FROM_COMPLAINT_GMAIL','Email for send mail specific complaint','ojovial@inmobile.pe',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','EMAIL_FROM_CREATION_USER_GMAIL','Email for send mail specific creation user - GMAIL','ojovial@inmobile.pe',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('EMAIL','PASSWORD_FROM_GMAIL','Password for send mail','ojo1234vial',1);


insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('DOWNLOAD','DOWNLOAD_FILE','Root for send image','download',1);

--02/12/2015
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('SEND_EMAIL','DOMAIN_HOTMAIL','Send email to hotmail.com domain','@hotmail.com',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('SEND_EMAIL','DOMAIN_HOTMAIL_ES','Send email to hotmail.es domain','@hotmail.es',1);
insert into tb_system_param (generalParam,nameParam,reasonParam,valueParam,status) values ('SEND_EMAIL','DOMAIN_OUTLOOK','Send email to outlook.com domain','@outlook.com',1);

select * from tb_system_param where generalParam='EMAIL';