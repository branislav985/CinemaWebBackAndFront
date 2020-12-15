package com.ftninformatika.jwd.modul3.cinema.service;

import com.ftninformatika.jwd.modul3.cinema.model.Projekcija;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

public interface ProjekcijaService {

    Projekcija findOne(Long id);

    List<Projekcija> findAll();

    Projekcija save(Projekcija projekcija);

    Projekcija update(Projekcija projekcija);

    Projekcija delete(Long id);

    List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip, Integer sala, Double cenaKarteOd, Double cenaKarteDo);
    
    List<Projekcija> findByFilmId(Long filmId);
    
    Page<Projekcija> pretraga(Long filmId, LocalDateTime datumIVremeOd, String tip, int pageNum);
}
