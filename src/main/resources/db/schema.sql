DROP TABLE IF EXISTS contact;
DROP SEQUENCE IF EXISTS contact_seq;

CREATE SEQUENCE contact_seq START 1;

CREATE TABLE contact (
	id          INT         NOT NULL PRIMARY KEY DEFAULT nextval('contact_seq'),
	first_name  VARCHAR(60) NOT NULL,
	last_name   VARCHAR(40) NOT NULL,
	birth_date  DATE,
	description VARCHAR(2000),
	photo       BYTEA,
	version     INT         NOT NULL             DEFAULT 0
);

CREATE UNIQUE INDEX uq_contact_1
	ON contact (first_name, last_name);
