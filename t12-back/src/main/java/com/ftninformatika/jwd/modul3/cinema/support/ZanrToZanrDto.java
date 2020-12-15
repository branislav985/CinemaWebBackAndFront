package com.ftninformatika.jwd.modul3.cinema.support;
import com.ftninformatika.jwd.modul3.cinema.model.Zanr;
import com.ftninformatika.jwd.modul3.cinema.web.dto.ZanrDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZanrToZanrDto implements Converter<Zanr, ZanrDTO> {

    @Override
    public ZanrDTO convert(Zanr zanr) {
        ZanrDTO zanrDTO = new ZanrDTO();
        zanrDTO.setId(zanr.getId());
        zanrDTO.setNaziv(zanr.getNaziv());
        return zanrDTO;
    }

    public List<ZanrDTO> convert(List<Zanr> zanrovi){
        List<ZanrDTO> zanrDto = new ArrayList<>();

        for(Zanr zanr : zanrovi) {
            zanrDto.add(convert(zanr));
        }

        return zanrDto;
    }
}
