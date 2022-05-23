CREATE TABLE IF NOT EXISTS kategorija (
                                          id SERIAL PRIMARY KEY,
                                          naziv varchar NOT NULL UNIQUE,
                                          kratica varchar
);
CREATE INDEX IF NOT EXISTS index_kategorija ON kategorija(id);

CREATE TABLE IF NOT EXISTS odjel (
                                     id SERIAL PRIMARY KEY,
                                     naziv varchar NOT NULL UNIQUE,
                                     kratica varchar,
                                     opis varchar
);

CREATE INDEX IF NOT EXISTS  index_odjel ON odjel(id);

CREATE TABLE IF NOT EXISTS posao (
                                     id SERIAL PRIMARY KEY,
                                     naziv varchar NOT NULL,
                                     opis varchar
);

CREATE INDEX IF NOT EXISTS  index_posao ON posao(id);

CREATE TABLE IF NOT EXISTS zaposlenik (
                                          id SERIAL PRIMARY KEY,
                                          sef_id int REFERENCES zaposlenik(id),
                                          odjel_id int REFERENCES odjel(id),
                                          posao_id int REFERENCES posao(id),
                                          ime varchar NOT NULL UNIQUE,
                                          prezime varchar NOT NULL,
                                          OIB varchar NOT NULL UNIQUE,
                                          email varchar NOT NULL,
                                          adresa varchar,
                                          mobitel varchar
);

CREATE INDEX IF NOT EXISTS index_zaposlenik ON zaposlenik(id);

CREATE TABLE IF NOT EXISTS sadrzaj (
                                       id SERIAL PRIMARY KEY,
                                       kategorija_id int REFERENCES kategorija(id),
                                       zaposlenik_id int REFERENCES zaposlenik(id),
                                       naziv varchar NOT NULL,
                                       tekst varchar NOT NULL,
                                       slika varchar,
                                       datum date
);

CREATE INDEX IF NOT EXISTS  index_sadrzaj ON sadrzaj(id);

CREATE TABLE IF NOT EXISTS clanak (
                                      id int REFERENCES sadrzaj(id),
                                      kljucneRijeci varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS prodavac (
                                        id SERIAL PRIMARY KEY,
                                        naziv varchar NOT NULL,
                                        email varchar NOT NULL
);

CREATE INDEX IF NOT EXISTS index_prodavac ON prodavac(id);

CREATE TABLE IF NOT EXISTS oglas (
                                     id int REFERENCES sadrzaj(id),
                                     prodavac_id int REFERENCES prodavac(id),
                                     cijena numeric NOT NULL
);