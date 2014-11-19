--
-- Populate database with sample data.
--
-- author: Lev Himmelfarb
--

INSERT INTO account (version, created_on, created_by, modified_on, modified_by,
	email, secret, pwddigest, is_admin, fname, lname, bill_name, bill_street,
	bill_city, bill_state, bill_zip)
VALUES (1, CURRENT_TIMESTAMP, 'init', CURRENT_TIMESTAMP, 'init',
	'admin@example.com', '1234567890abcdef1234567890abcdef',
	'5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', TRUE, 'Maria', 'Zimmer',
	'Maria Zimmer', '25 Schuster St.', 'Reville', 'LA', '12301');
INSERT INTO ccard (account_id, type, lastdigits, expdate)
SELECT id, 'VISA', '1111', '2020-01'
FROM account WHERE email = 'admin@example.com';

INSERT INTO account (version, created_on, created_by, modified_on, modified_by,
	email, secret, pwddigest, is_admin, fname, lname, bill_name, bill_street,
	bill_city, bill_state, bill_zip)
VALUES (1, CURRENT_TIMESTAMP, 'init', CURRENT_TIMESTAMP, 'init',
	'customer@example.com', '1234567890abcdef1234567890abcdef',
	'5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', FALSE, 'Carl', 'Becker',
	'Carl Becker', '101 Fishermann St.', 'Mytown', 'NY', '10011');
INSERT INTO bankaccount (account_id, type, lastdigits)
SELECT id, 'CHECKING', '1234'
FROM account WHERE email = 'customer@example.com';

INSERT INTO product (version, created_on, created_by, modified_on, modified_by,
	title, price)
VALUES (1, CURRENT_TIMESTAMP, 'init', CURRENT_TIMESTAMP, 'init',
	'Buttons', 10.99);

INSERT INTO product (version, created_on, created_by, modified_on, modified_by,
	title, price)
VALUES (1, CURRENT_TIMESTAMP, 'init', CURRENT_TIMESTAMP, 'init',
	'Needles', 4.50);

INSERT INTO product (version, created_on, created_by, modified_on, modified_by,
	title, price)
VALUES (1, CURRENT_TIMESTAMP, 'init', CURRENT_TIMESTAMP, 'init',
	'Thread', 2.95);
