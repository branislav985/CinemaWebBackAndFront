package com.ftninformatika.jwd.modul3.cinema.support;

import com.ftninformatika.jwd.modul3.cinema.model.Film;
import com.ftninformatika.jwd.modul3.cinema.model.Zanr;
import com.ftninformatika.jwd.modul3.cinema.web.dto.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class FilmToFilmDto implements Converter<Film, FilmDTO> {

    @Autowired
    private ZanrToZanrDto zanrToZanrDto;

    @Override
    public FilmDTO convert(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setId(film.getId());
        dto.setNaziv(film.getNaziv());
        dto.setTrajanje(film.getTrajanje());
        List<Zanr> zanrovi = new ArrayList<>(film.getZanrovi());
        dto.setZanrovi(new HashSet<>(zanrToZanrDto.convert(zanrovi)));
        return dto;
    }

    public List<FilmDTO> convert(List<Film> filmovi){
        List<FilmDTO> filmoviDto = new ArrayList<>();

        for(Film film : filmovi) {
            filmoviDto.add(convert(film));
        }

        return filmoviDto;
    }

}

