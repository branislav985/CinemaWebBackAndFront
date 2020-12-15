package com.ftninformatika.jwd.modul3.cinema.support;

import com.ftninformatika.jwd.modul3.cinema.model.Zanr;
import com.ftninformatika.jwd.modul3.cinema.service.ZanrService;
import com.ftninformatika.jwd.modul3.cinema.web.dto.ZanrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class ZanrDtoToZanr implements Converter<ZanrDTO, Zanr> {

    @Autowired
    private ZanrService zanrService;

    @Override
    public Zanr convert(ZanrDTO zanrDto) {
        Zanr zanr;

        if(zanrDto.getId() == null){
            zanr = new Zanr();
        }else {
            zanr = zanrService.findOne(zanrDto.getId());
        }

        if(zanr != null){
            zanr.setNaziv(zanrDto.getNaziv());
        }
        return zanr;
    }
}
