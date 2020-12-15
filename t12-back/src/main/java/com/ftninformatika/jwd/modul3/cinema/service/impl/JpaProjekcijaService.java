package com.ftninformatika.jwd.modul3.cinema.service.impl;

import com.ftninformatika.jwd.modul3.cinema.model.Projekcija;
import com.ftninformatika.jwd.modul3.cinema.repository.ProjekcijaRepository;
import com.ftninformatika.jwd.modul3.cinema.service.ProjekcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class JpaProjekcijaService implements ProjekcijaService {

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

    @Override
    public Projekcija findOne(Long id) {
        return projekcijaRepository.findOneById(id);
    }

    @Override
    public List<Projekcija> findAll() {
        return projekcijaRepository.findAll();
    }

    @Override
    public Projekcija save(Projekcija projekcija) {
        return projekcijaRepository.save(projekcija);
    }

    @Override
    public Projekcija update(Projekcija projekcija) {
        return projekcijaRepository.save(projekcija);
    }

    @Override
    public Projekcija delete(Long id) {
        Projekcija projekcija = findOne(id);
        if(projekcija != null){
            projekcija.getFilm().getProjekcije().remove(projekcija);
            projekcija.setFilm(null);
            projekcija = projekcijaRepository.save(projekcija);
            projekcijaRepository.delete(projekcija);
            return projekcija;
        }
        return null;
    }

    @Override
    public List<Projekcija> find(LocalDateTime datumIVremeOd, LocalDateTime datumIVremeDo, Long filmId, String tip, Integer sala,
                                 Double cenaKarteOd, Double cenaKarteDo) {
        if (cenaKarteOd == null) {
            cenaKarteOd = 0.0;
        }

        if (cenaKarteDo == null) {
            cenaKarteDo = Double.MAX_VALUE;
        }

        if(filmId == null && sala == null){
            return projekcijaRepository.findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLike(datumIVremeOd,datumIVremeDo,cenaKarteOd,cenaKarteDo,tip);
        }else if(filmId == null){
            return projekcijaRepository.findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndSala(datumIVremeOd,datumIVremeDo,cenaKarteOd,cenaKarteDo,tip,sala);
        }else if(sala == null){
            return projekcijaRepository.findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndFilmId(datumIVremeOd,datumIVremeDo,cenaKarteOd,cenaKarteDo,tip,filmId);
        }
        return projekcijaRepository.findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndFilmIdAndSala(datumIVremeOd,datumIVremeDo,cenaKarteOd,cenaKarteDo,tip,filmId,sala);
    }

    @Override
    public List<Projekcija> findByFilmId(Long filmId) {
        return projekcijaRepository.findByFilmId(filmId);
    }

    private LocalDateTime getDateConverted(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

	@Override
	public Page<Projekcija> pretraga(Long filmId, LocalDateTime datumIVremeOd, String tip, int pageNum) {
		
		return projekcijaRepository.pretraga(filmId, datumIVremeOd, tip, PageRequest.of(pageNum, 5));
	}
}
