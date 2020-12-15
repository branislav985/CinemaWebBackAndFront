INSERT INTO adresa (id, ulica, broj) VALUES (1,'Bulevar Cara Lazara', 5);
INSERT INTO adresa (id, ulica, broj) VALUES (2, 'Dalmatinska', 7);

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga, adresa_id)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN',1);
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga, adresa_id)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK',2);
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga, adresa_id)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK',2);

INSERT INTO film (id, naziv, trajanje) VALUES (1, 'Avengers: Endgame', 182);
INSERT INTO film (id, naziv, trajanje) VALUES (2, 'Life', 110);
INSERT INTO film (id, naziv, trajanje) VALUES (3, 'It: Chapter 2', 170);
INSERT INTO film (id, naziv, trajanje) VALUES (4, 'Pirates of the Caribbean: Dead Men Tell No Tales', 153);

INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (1, '2020-06-21 20:00', 1, 1, '2D', 380.00);
INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (2, '2020-07-22 21:00', 2, 2, '3D', 580.00);
INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (3, '2020-06-22 20:00', 1, 3, '4D', 780.00);
INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (4, '2020-08-10 18:00', 3, 1, '2D', 350.00);
INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (5, '2020-08-12 19:00', 4, 3, '4D', 680.00);
INSERT INTO projekcija (id, datum_vreme, film_id, sala, tip, cena_karte) VALUES (6, '2020-11-26 19:00', 4, 3, '4D', 680.00);

INSERT INTO zanr (id, naziv) VALUES (1, 'naučna fantastika');
INSERT INTO zanr (id, naziv) VALUES (2, 'akcija');
INSERT INTO zanr (id, naziv) VALUES (3, 'komedija');
INSERT INTO zanr (id, naziv) VALUES (4, 'horor');
INSERT INTO zanr (id, naziv) VALUES (5, 'avantura');

INSERT INTO film_zanr (film_id, zanr_id) VALUES (1, 1);
INSERT INTO film_zanr (film_id, zanr_id) VALUES (1, 2);
INSERT INTO film_zanr (film_id, zanr_id) VALUES (1, 5);

INSERT INTO film_zanr (film_id, zanr_id) VALUES (2, 1);
INSERT INTO film_zanr (film_id, zanr_id) VALUES (2, 4);

INSERT INTO film_zanr (film_id, zanr_id) VALUES (3, 4);

INSERT INTO film_zanr (film_id, zanr_id) VALUES (4, 2);
INSERT INTO film_zanr (film_id, zanr_id) VALUES (4, 3);
INSERT INTO film_zanr (film_id, zanr_id) VALUES (4, 5);