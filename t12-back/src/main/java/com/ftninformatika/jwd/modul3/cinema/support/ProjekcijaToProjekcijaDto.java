package com.ftninformatika.jwd.modul3.cinema.support;

import com.ftninformatika.jwd.modul3.cinema.model.Projekcija;
import com.ftninformatika.jwd.modul3.cinema.web.dto.ProjekcijaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjekcijaToProjekcijaDto implements Converter<Projekcija, ProjekcijaDTO> {

    @Autowired
    private FilmToFilmDto filmToFilmDto;

    @Override
    public ProjekcijaDTO convert(Projekcija projekcija) {
        ProjekcijaDTO projekcijaDTO = new ProjekcijaDTO();
        projekcijaDTO.setId(projekcija.getId());
        projekcijaDTO.setDatumIVreme(projekcija.getDatumIVreme().toString());
        projekcijaDTO.setFilm(filmToFilmDto.convert(projekcija.getFilm()));
        projekcijaDTO.setSala(projekcija.getSala());
        projekcijaDTO.setTip(projekcija.getTip());
        projekcijaDTO.setCenaKarte(projekcija.getCenaKarte());

        return projekcijaDTO;
    }

    public List<ProjekcijaDTO> convert(List<Projekcija> projekcije){
        List<ProjekcijaDTO> projekcijeDto = new ArrayList<>();

        for(Projekcija projekcija : projekcije) {
            projekcijeDto.add(convert(projekcija));
        }

        return projekcijeDto;
    }
}
