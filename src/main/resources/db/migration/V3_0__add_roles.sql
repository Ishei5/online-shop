CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    role VARCHAR(255) NOT NULL
);

INSERT INTO role (role) values ('admin');
INSERT INTO role (role) values ('user');
INSERT INTO role (role) values ('guest');
