package com.ftninformatika.jwd.modul3.cinema.repository;

import com.ftninformatika.jwd.modul3.cinema.model.Projekcija;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProjekcijaRepository extends JpaRepository<Projekcija,Long> {

    Projekcija findOneById(Long id);

    List<Projekcija> findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLike(LocalDateTime datumIVremeOd,LocalDateTime datumIVremeDo,
                                                                           Double cenaKarteOd,Double cenaKarteDo,String tip);

    List<Projekcija> findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndFilmId(LocalDateTime datumIVremeOd,LocalDateTime datumIVremeDo,
                                                                           Double cenaKarteOd,Double cenaKarteDo,String tip,Long filmId);

    List<Projekcija> findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndSala(LocalDateTime datumIVremeOd,LocalDateTime datumIVremeDo,
                                                                                    Double cenaKarteOd,Double cenaKarteDo,String tip,Integer sala);

    List<Projekcija> findByDatumIVremeBetweenAndCenaKarteBetweenAndTipLikeAndFilmIdAndSala(LocalDateTime datumIVremeOd,LocalDateTime datumIVremeDo,
                                                                                    Double cenaKarteOd,Double cenaKarteDo,String tip,Long filmId,Integer sala);
    List<Projekcija> findByFilmId(Long filmId);
    
    @Query("SELECT p FROM Projekcija p WHERE "
    		+ "(:filmId IS NULL OR p.film.id = :filmId) AND "
    		+ "(:datumIVremeOd IS NULL OR p.datumIVreme >= :datumIVremeOd) AND "
    		+ "(:tip IS NULL OR p.tip like %:tip% )")
    Page<Projekcija> pretraga(@Param("filmId") Long filmId, @Param("datumIVremeOd") LocalDateTime datumIVremeOd, @Param("tip") String tip, Pageable pageable);
}
