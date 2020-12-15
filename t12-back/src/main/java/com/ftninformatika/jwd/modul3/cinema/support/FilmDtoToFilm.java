package com.ftninformatika.jwd.modul3.cinema.support;

import com.ftninformatika.jwd.modul3.cinema.model.Film;
import com.ftninformatika.jwd.modul3.cinema.model.Zanr;
import com.ftninformatika.jwd.modul3.cinema.service.FilmService;
import com.ftninformatika.jwd.modul3.cinema.service.ZanrService;
import com.ftninformatika.jwd.modul3.cinema.web.dto.FilmDTO;
import com.ftninformatika.jwd.modul3.cinema.web.dto.ZanrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmDtoToFilm implements Converter<FilmDTO,Film> {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ZanrService zanrService;

    @Override
    public Film convert(FilmDTO dto) {

        Film entity;

        if(dto.getId() == null) {
            entity = new Film();
        }else {
            entity = filmService.findOne(dto.getId());
        }

        if(entity != null) {
            entity.setNaziv(dto.getNaziv());
            entity.setTrajanje(dto.getTrajanje());
            
            if(dto.getZanrovi() != null) {
            	List<Long> idZanrova = dto.getZanrovi().stream().map(ZanrDTO::getId).collect(Collectors.toList());
                List<Zanr> zanrovi = zanrService.find(idZanrova);
                entity.setZanrovi(new HashSet<>(zanrovi));
            }
        }

        return entity;
    }
}
