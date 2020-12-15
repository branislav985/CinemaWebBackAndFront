package com.ftninformatika.jwd.modul3.cinema.repository;

import com.ftninformatika.jwd.modul3.cinema.model.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZanrRepository extends JpaRepository<Zanr,Long> {

    Zanr findOneById(Long id);

    List<Zanr> findByNazivIgnoreCaseContains(String naziv);

    List<Zanr> findByIdIn(List<Long> ids);
}
