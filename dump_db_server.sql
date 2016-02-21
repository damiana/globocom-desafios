create database db_server;
use db_server

CREATE TABLE server (
id_server INTEGER UNSIGNED NOT NULL AUTO_INCREMENT ,
name_server VARCHAR(20) NULL ,
username VARCHAR(20) NULL ,
host VARCHAR(20) NULL ,
password VARCHAR(20) NULL ,
PRIMARY KEY(id_server));