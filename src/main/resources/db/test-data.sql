DELETE FROM contact;
ALTER SEQUENCE contact_seq RESTART WITH 1;

INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Chris', 'Schaefer', '1981-05-03');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Scott', 'Tiger', '1990-11-02');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('John', 'Smith', '1964-02-28');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Peter', 'Jackson', '1944-1-10');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Jacky', 'Chan', '1955-10-31');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Susan', 'Boyle', '1970-05-06');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Tinner', 'Turner', '1967-04-30');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Lotus', 'Notes', '1990-02-28');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Henry', 'Dickson', '1997-06-30');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Sam', 'Davis', '2001-01-31');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Max', 'Beckham', '2002-02-01');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Paul', 'Simon', '2002-02-28');
