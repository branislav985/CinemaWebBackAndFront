package com.ftninformatika.jwd.modul3.cinema.service;

import com.ftninformatika.jwd.modul3.cinema.model.Zanr;

import java.util.List;

public interface ZanrService {

    Zanr findOne(Long id);

    List<Zanr> findAll();

    List<Zanr> find(List<Long> ids);

    Zanr save(Zanr zanr);

    Zanr update(Zanr zanr);

    Zanr delete(Long id);

    List<Zanr> find(String naziv);
}
