package com.ftninformatika.jwd.modul3.cinema.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Projekcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="datum_vreme", nullable = false)
    private LocalDateTime datumIVreme;

    @ManyToOne
    private Film film;

    @Column
    private int sala;

    @Column
    private String tip;

    @Column
    private double cenaKarte;

    public Projekcija() {
        super();
    }

    public Projekcija(LocalDateTime datumIVreme, Film film, int sala, String tip, double cenaKarte) {
        this.datumIVreme = datumIVreme;
        this.film = film;
        this.sala = sala;
        this.tip = tip;
        this.cenaKarte = cenaKarte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(LocalDateTime datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        if(film !=null && !film.getProjekcije().contains(this)){
            film.getProjekcije().add(this);
        }
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getCenaKarte() {
        return cenaKarte;
    }

    public void setCenaKarte(double cenaKarte) {
        this.cenaKarte = cenaKarte;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Projekcija other = (Projekcija) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String nazivFilma = film == null ? " - " : film.getNaziv();
        return "Projekcija [id=" + id + ", film=" + nazivFilma +", datum=" + datumIVreme+ ", tip=" + tip +
                ", sala=" + sala + ", cena karte=" + cenaKarte + "]";
    }

}
