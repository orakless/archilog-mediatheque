INSERT INTO Utilisateurs (id, nom, date_naissance)
            VALUES (NEXTVAL('utilisateurs_seq'), "Foo", `10-10-2003`);

INSERT INTO Utilisateurs (id, nom, date_naissance)
            VALUES (NEXTVAL('utilisateurs_seq'), "Bar", `10-10-2012`);

INSERT INTO Utilisateurs (id, nom, date_naissance)
            VALUES (NEXTVAL('utilisateurs_seq'), "John", `10-03-1994`);

INSERT INTO Utilisateurs (id, nom, date_naissance)
            VALUES (NEXTVAL('utilisateurs_seq'), "Doe", `04-11-2019`);

INSERT INTO Documents (id, titre)
            VALUES (NEXTVAL('documents_seq'), "Titre1");

INSERT INTO Dvds (document_id)
            VALUES (CURRVAL('documents_seq'));

INSERT INTO Documents (id, titre)
            VALUES (NEXTVAL('documents_seq'), "TitreAdulte");

INSERT INTO Dvds (document_id, adulte)
            VALUES (CURRVAL('documents_seq'), true);

INSERT INTO Documents (id, titre)
            VALUES (NEXTVAL('documents_seq'), "Titre2");

INSERT INTO Dvds (document_id)
            VALUES (CURRVAL('documents_seq'));

INSERT INTO Documents (id, titre)
            VALUES (NEXTVAL('documents_seq'), "Titre3");

INSERT INTO Dvds (document_id)
            VALUES (CURRVAL('documents_seq'));
