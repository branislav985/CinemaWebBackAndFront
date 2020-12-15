package com.ftninformatika.jwd.modul3.cinema.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.cinema.model.Film;
/*
 * Primer repozitorijuma u kojem su navedene metode koje po
 * odredjenoj konstrukciji naziva prave upit u bazu.
 * Metode za pretragu pocinju sa find*By* dok u nastavku sadrze
 * nazive atributa iz modela, konkatenirane sa And, Or, Between, LessThan, GreaterThan, Like, itd.
 * uz dodavanje pomocnih uslova poput Containing, AllIgnoringCase, itd.
 */
/*
 * Pri startovanju Spring kontejnera trigerovace se Spring Data
 * infrastruktura koja ce kreirati binove za repozitorijume.
 * Proci se kroz metode navedene u svakom repozitorijumu i
 * pokusati da konstruise upite koji ce se pozivati pri
 * pozivu metoda.
 */


@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    Film findOneById(Long id);

    List<Film> findByZanroviId(Long zanrId);

    List<Film> findByNazivIgnoreCaseContainsAndTrajanjeBetweenAndZanroviId(String naziv,Integer trajanjeOd,Integer trajanjeDo,Long zanrId);

    List<Film> findByNazivIgnoreCaseContainsAndTrajanjeBetween(String naziv,Integer trajanjeOd,Integer trajanjeDo);

    /*
     * Pronalazi sve objekte tipa Film i vraca onoliko objekata koliko je
     * specificirano kroz Pageable objekat. Npr. ako se prosledi objekat: new
     * PageRequest(0, 10) vratice se nulta stranica sa prvih 10 objekata tipa Film.
     */
//    Page<Film> findAll(Pageable pageable);

}

