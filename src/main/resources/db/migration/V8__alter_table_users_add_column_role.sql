ALTER TABLE users ADD COLUMN role VARCHAR(20) NOT NULL;

ALTER TABLE users ADD CONSTRAINT email UNIQUE (email);