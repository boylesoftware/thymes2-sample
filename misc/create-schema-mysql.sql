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
	lname VARCHAR(50) NOT NULL,
	bill_name VARCHAR(50) NOT NULL,
	bill_street VARCHAR(50) NOT NULL,
	bill_unit VARCHAR(10),
	bill_city VARCHAR(30) NOT NULL,
	bill_state CHAR(2) NOT NULL,
	bill_zip CHAR(5) NOT NULL,
	ship_name VARCHAR(50),
	ship_street VARCHAR(50),
	ship_unit VARCHAR(10),
	ship_city VARCHAR(30),
	ship_state CHAR(2),
	ship_zip CHAR(5)
);

CREATE TABLE ccard (
	account_id INT NOT NULL,
	type VARCHAR(15) NOT NULL,
	lastdigits CHAR(4) NOT NULL,
	expdate CHAR(7) NOT NULL,
	FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE bankaccount (
	account_id INT NOT NULL,
	type VARCHAR(10) NOT NULL,
	lastdigits CHAR(4) NOT NULL,
	FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE ord (
	id INT PRIMARY KEY AUTO_INCREMENT,
	version INT NOT NULL,
	created_on TIMESTAMP NOT NULL,
	created_by VARCHAR(50) NOT NULL,
	modified_on TIMESTAMP NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	account_id INT NOT NULL,
	FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE ord_item (
	id INT PRIMARY KEY AUTO_INCREMENT,
	ord_id INT NOT NULL,
	product_id INT NOT NULL,
	qty INT NOT NULL,
	FOREIGN KEY (ord_id) REFERENCES ord (id),
	FOREIGN KEY (product_id) REFERENCES product (id),
	UNIQUE (ord_id, product_id)
);
