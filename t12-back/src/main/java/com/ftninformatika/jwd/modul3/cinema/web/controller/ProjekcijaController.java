package com.ftninformatika.jwd.modul3.cinema.web.controller;

import com.ftninformatika.jwd.modul3.cinema.model.Projekcija;
import com.ftninformatika.jwd.modul3.cinema.service.ProjekcijaService;
import com.ftninformatika.jwd.modul3.cinema.support.ProjekcijaDtoToProjekcija;
import com.ftninformatika.jwd.modul3.cinema.support.ProjekcijaToProjekcijaDto;
import com.ftninformatika.jwd.modul3.cinema.web.dto.ProjekcijaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/projekcije", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjekcijaController {

    @Autowired
    private ProjekcijaService projekcijaService;

    @Autowired
    private ProjekcijaDtoToProjekcija toProjekcija;

    @Autowired
    private ProjekcijaToProjekcijaDto toProjekcijaDto;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjekcijaDTO> create(@Valid @RequestBody ProjekcijaDTO projekcijaDTO){
        Projekcija projekcija = toProjekcija.convert(projekcijaDTO);

        if(projekcija.getFilm() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Projekcija sacuvanProjekcija = projekcijaService.save(projekcija);

        return new ResponseEntity<>(toProjekcijaDto.convert(sacuvanProjekcija), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjekcijaDTO> update(@PathVariable Long id, @Valid @RequestBody ProjekcijaDTO projekcijaDTO){

        if(!id.equals(projekcijaDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Projekcija projekcija = toProjekcija.convert(projekcijaDTO);

        if(projekcija.getFilm() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Projekcija sacuvanProjekcija = projekcijaService.update(projekcija);

        return new ResponseEntity<>(toProjekcijaDto.convert(sacuvanProjekcija),HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Projekcija obrisanProjekcija = projekcijaService.delete(id);

        if(obrisanProjekcija != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjekcijaDTO> getOne(@PathVariable Long id){
        Projekcija projekcija = projekcijaService.findOne(id);

        if(projekcija != null) {
            return new ResponseEntity<>(toProjekcijaDto.convert(projekcija), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    // TODO: doraditi pretragu i za ostale parametre
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProjekcijaDTO>> getAll(
    		@RequestParam(required=false) String datumIVremeOdParametar,
            @RequestParam(required=false) String datumIVremeDoParametar,
            @RequestParam(required=false) Long filmId,
            @RequestParam(required=false) String tip,
            @RequestParam(required=false) Integer sala,
            @RequestParam(required=false) Double cenaKarteOd,
            @RequestParam(required=false) Double cenaKarteDo,
            @RequestParam(defaultValue = "0") int pageNum){

        
        LocalDateTime datumIVremeOd = null;
        if(datumIVremeOdParametar != null) {
        	datumIVremeOd = getLocalDateTime(datumIVremeOdParametar);
        }
        
        Page<Projekcija> projekcijePage = projekcijaService.pretraga(filmId, datumIVremeOd, tip, pageNum);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Total-Pages", projekcijePage.getTotalPages() + "");
        
        return new ResponseEntity<>(toProjekcijaDto.convert(projekcijePage.getContent()), responseHeaders, HttpStatus.OK);
    }

    private LocalDateTime getLocalDateTime(String datumIVreme) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datum = LocalDate.parse(datumIVreme.substring(0, 10), formatter);
        LocalTime vreme = LocalTime.parse(datumIVreme.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(datum, vreme);
    }
}
