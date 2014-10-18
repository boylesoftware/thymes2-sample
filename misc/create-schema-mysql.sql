--
-- Schema creation script for MySQL.
--
-- author: Lev Himmelfarb
--

CREATE TABLE product (
	id INT PRIMARY KEY AUTO_INCREMENT,
	version INT NOT NULL,
	created_on TIMESTAMP NOT NULL,
	created_by VARCHAR(50) NOT NULL,
	modified_on TIMESTAMP NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL UNIQUE,
	price DECIMAL(5,2) NOT NULL
);

CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
	version INT NOT NULL,
	created_on TIMESTAMP NOT NULL,
	created_by VARCHAR(50) NOT NULL,
	modified_on TIMESTAMP NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	secret CHAR(32) NOT NULL,
	pwddigest CHAR(40) NOT NULL,
	is_admin BOOLEAN NOT NULL,
	fname VARCHAR(50) NOT NULL,
	lname VARCHAR(50) NOT NULL
);
