--
-- Schema creation script for MySQL.
--
-- author: Lev Himmelfarb
--

CREATE TABLE product (
	id INT PRIMARY KEY AUTO_INCREMENT,
	version INT NOT NULL,
	title VARCHAR(50) NOT NULL UNIQUE,
	price DECIMAL(5,2) NOT NULL
);
