CREATE DATABASE IF NOT EXISTS svcars_db;

USE svcars_db;

CREATE TABLE IF NOT EXISTS UtenteGuest 
(
    codice_utente INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS UtenteIscritto 
(
    e_mail VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    tipo_utente VARCHAR(30),
    password VARCHAR(100),
    città VARCHAR(50),
    cap VARCHAR(10),
    via VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Carrello 
(
    codice_carrello INT PRIMARY KEY,
    utente VARCHAR(20),
    totale DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS Fattura
(
    numero_fattura INT PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS Ordine 
(
    codice_ordine INT PRIMARY KEY,
    indirizzo_origine VARCHAR(100),
    indirizzo_destinazione VARCHAR(100),
    costo_prodotti DECIMAL(10, 2),
    costo_spedizione DECIMAL(10, 2),
    totale DECIMAL(10, 2),
    tempo_spedizione VARCHAR(50),
    numero_fattura INT,
    FOREIGN KEY (numero_fattura) REFERENCES Fattura(numero_fattura)
);

CREATE TABLE IF NOT EXISTS Annuncio 
(
    targa VARCHAR(10) PRIMARY KEY,
    titolo VARCHAR(100),
    descrizione TEXT,
    prezzo DECIMAL(10, 2),
    tipologia VARCHAR(30),
    km INT,
    anno INT,
    carburante VARCHAR(20),
    marca VARCHAR(50),
    modello VARCHAR(50),
    cilindrata INT,
    n_porte INT,
    città VARCHAR(50),
    e_mail VARCHAR(100),
    FOREIGN KEY (e_mail) REFERENCES UtenteIscritto(e_mail)
);

CREATE TABLE IF NOT EXISTS Colore (
    nome VARCHAR(30) PRIMARY KEY
);

-- RELAZIONI N:N

CREATE TABLE IF NOT EXISTS Contiene (
    codice_carrello INT,
    targa VARCHAR(10),
    PRIMARY KEY (codice_carrello, targa),
    FOREIGN KEY (codice_carrello) REFERENCES Carrello(codice_carrello),
    FOREIGN KEY (targa) REFERENCES Annuncio(targa)
);

CREATE TABLE IF NOT EXISTS Acquista (
    codice_ordine INT,
    targa VARCHAR(10),
    PRIMARY KEY (codice_ordine, targa),
    FOREIGN KEY (codice_ordine) REFERENCES Ordine(codice_ordine),
    FOREIGN KEY (targa) REFERENCES Annuncio(targa)
);

CREATE TABLE IF NOT EXISTS InteragisceGuest (
    codice_utente INT,
    codice_carrello INT,
    PRIMARY KEY (codice_utente, codice_carrello),
    FOREIGN KEY (codice_utente) REFERENCES UtenteGuest(codice_utente),
    FOREIGN KEY (codice_carrello) REFERENCES Carrello(codice_carrello)
);

CREATE TABLE IF NOT EXISTS InteragisceIscritto (
    e_mail VARCHAR(100),
    codice_carrello INT,
    PRIMARY KEY (e_mail, codice_carrello),
    FOREIGN KEY (e_mail) REFERENCES UtenteIscritto(e_mail),
    FOREIGN KEY (codice_carrello) REFERENCES Carrello(codice_carrello)
);

CREATE TABLE IF NOT EXISTS Effettua (
    e_mail VARCHAR(100),
    codice_ordine INT,
    PRIMARY KEY (e_mail, codice_ordine),
    FOREIGN KEY (e_mail) REFERENCES UtenteIscritto(e_mail),
    FOREIGN KEY (codice_ordine) REFERENCES Ordine(codice_ordine)
);

CREATE TABLE IF NOT EXISTS CreaModificaRimuove (
    e_mail VARCHAR(100),
    targa VARCHAR(10),
    azione ENUM('crea', 'modifica', 'rimuove'),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (e_mail, targa, timestamp),
    FOREIGN KEY (e_mail) REFERENCES UtenteIscritto(e_mail),
    FOREIGN KEY (targa) REFERENCES Annuncio(targa)
);
