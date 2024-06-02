INSERT INTO Utilisateurs (id, name, birthdate)
            VALUES (NEXTVAL('utilisateurs_seq'), 'Foo', '10-10-2003');

INSERT INTO Utilisateurs (id, name, birthdate)
            VALUES (NEXTVAL('utilisateurs_seq'), 'Bar', '10-10-2012');

INSERT INTO Utilisateurs (id, name, birthdate)
            VALUES (NEXTVAL('utilisateurs_seq'), 'John', '10-03-1994');

INSERT INTO Utilisateurs (id, name, birthdate)
            VALUES (NEXTVAL('utilisateurs_seq'), 'Doe', '04-11-2019');

INSERT INTO Documents (id, title, type, adult)
            VALUES (NEXTVAL('documents_seq'), 'DVD1', 'dvd', false);

INSERT INTO Documents (id, title, type, adult)
VALUES (NEXTVAL('documents_seq'), 'DVD2', 'dvd', false);

INSERT INTO Documents (id, title, type, adult)
VALUES (NEXTVAL('documents_seq'), 'DVD3', 'dvd', false);

INSERT INTO Documents (id, title, type, adult)
VALUES (NEXTVAL('documents_seq'), 'DVD4', 'dvd', false);

INSERT INTO Documents (id, title, type, adult)
VALUES (NEXTVAL('documents_seq'), 'DVD5', 'dvd', false);

INSERT INTO Documents (id, title, type, adult)
VALUES (NEXTVAL('documents_seq'), 'DVD6', 'dvd', false);
