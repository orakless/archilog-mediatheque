CREATE SEQUENCE utilisateurs_seq;
CREATE SEQUENCE documents_seq;

CREATE TABLE Utilisateurs (
    id              INTEGER PRIMARY KEY,
    name            VARCHAR(32) NOT NULL,
    birthdate       DATE NOT NULL
);

CREATE TABLE Documents (
    id              INTEGER PRIMARY KEY,
    title           VARCHAR(64) NOT NULL,
    type            VARCHAR(32) NOT NULL,
    adult           BOOL
);