package com.ftninformatika.jwd.modul3.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//specifikacija naziva tabele u bazi
//@Table(name= "filmovi")
public class Film {

    //@Id - oznaka da je atribut surogatni kljuc
    //@GeneratedValue - podesavanje strategije generisanja kljuceva
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column - atribut klase se mapira na kolonu tabele
    //nullable i unique su ogranicenja na moguce vrednosti kolone
    @Column(nullable = false)
    private String naziv;

    @Column
    private int trajanje;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Projekcija> projekcije = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "film_zanr", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zanr_id", referencedColumnName = "id"))
    private Set<Zanr> zanrovi = new HashSet<>();

    //Svaki entity mora imati konstruktor bez parametara
    public Film() {
        super();
    }

    public Film(String naziv, int trajanje) {
        this.naziv = naziv;
        this.trajanje = trajanje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

    public void dodajProjekciju(Projekcija projekcija){
        this.projekcije.add(projekcija);
        if(!equals(projekcija.getFilm())){
            projekcija.setFilm(this);
        }
    }

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(Set<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", naziv=" + naziv + ", trajanje=" + trajanje + "]";
    }

}
