CREATE SEQUENCE utilisateurs_seq;
CREATE SEQUENCE documents_seq;

CREATE TABLE Utilisateurs (
    id              INTEGER PRIMARY KEY,
    nom             VARCHAR(32) NOT NULL,
    date_naissance  DATE NOT NULL
)

CREATE TABLE Documents (
    id          INTEGER PRIMARY KEY,
)

-- Hérite de Document
CREATE TABLE Dvds (
    document_id INTEGER PRIMARY KEY,
    adulte      BOOLEAN DEFAULT FALSE,
    CONSTRAINT  FK_Document 
                FOREIGN KEY document_id REFERENCES Documents(id)
)

CREATE TABLE Reservations (
    document_id     INTEGER NOT NULL,
    utilisateur_id  INTEGER NOT NULL,
    CONSTRAINT      PK_Reservation  
                    PRIMARY KEY (document_id, utilisateur_id),
    CONSTRAINT      FK_Document
                    FOREIGN KEY document_id REFERENCES Documents(id),
    CONSTRAINT      FK_Utilisateur
                    FOREIGN KEY utilsateur_id REFERENCES Utilisateurs(id)
)

CREATE TABLE Emprunts (
    document_id     INTEGER NOT NULL,
    utilisateur_id  INTEGER NOT NULL,
    fin_emprunt     DATE NOT NULL,
    CONSTRAINT      PK_Reservation  
                    PRIMARY KEY (document_id, utilisateur_id),
    CONSTRAINT      FK_Document
                    FOREIGN KEY document_id REFERENCES Documents(id),
    CONSTRAINT      FK_Utilisateur
                    FOREIGN KEY utilsateur_id REFERENCES Utilisateurs(id)
)


