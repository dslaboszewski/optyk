/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukorsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "uzytkownik")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u")
    , @NamedQuery(name = "Uzytkownik.findById", query = "SELECT u FROM Uzytkownik u WHERE u.id = :id")
    , @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login")
    , @NamedQuery(name = "Uzytkownik.findByHaslo", query = "SELECT u FROM Uzytkownik u WHERE u.haslo = :haslo")
    , @NamedQuery(name = "Uzytkownik.findByImie", query = "SELECT u FROM Uzytkownik u WHERE u.imie = :imie")
    , @NamedQuery(name = "Uzytkownik.findByNazwisko", query = "SELECT u FROM Uzytkownik u WHERE u.nazwisko = :nazwisko")
    , @NamedQuery(name = "Uzytkownik.findByDataUtworzenia", query = "SELECT u FROM Uzytkownik u WHERE u.dataUtworzenia = :dataUtworzenia")
    , @NamedQuery(name = "Uzytkownik.findHasloUzytkownika", query = "SELECT u.haslo FROM Uzytkownik u WHERE u.login = :login")
    , @NamedQuery(name = "Uzytkownik.findSprzedawcy", query = "SELECT u FROM Uzytkownik u WHERE u.id NOT IN (1, 9) ORDER BY u.nazwisko")
})

public class Uzytkownik implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "naprawiajacy", fetch = FetchType.EAGER)
    private Set<Naprawa> naprawaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprzedawca", fetch = FetchType.EAGER)
    private Set<Sprzedaz> sprzedazSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "login", nullable = false, length = 30)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "haslo", nullable = false, length = 4000)
    private String haslo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "imie", nullable = false, length = 100)
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nazwisko", nullable = false, length = 200)
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_utworzenia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uzytkownik", fetch = FetchType.EAGER)
    private Set<Klient> klientSet;
    @JoinColumn(name = "zaklad_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zaklad zaklad;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer id) {
        this.id = id;
    }

    public Uzytkownik(Integer id, String login, String haslo, String imie, String nazwisko, Date dataUtworzenia) {
        this.id = id;
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUtworzenia = dataUtworzenia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Date dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    @XmlTransient
    public Set<Klient> getKlientSet() {
        return klientSet;
    }

    public void setKlientSet(Set<Klient> klientSet) {
        this.klientSet = klientSet;
    }
    
    public Zaklad getZaklad() {
        return zaklad;
    }

    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.klient.entity.Uzytkownik[ id=" + id + " ]";
    }

    public Set<Sprzedaz> getSprzedazSet() {
        return sprzedazSet;
    }

    public void setSprzedazSet(Set<Sprzedaz> sprzedazSet) {
        this.sprzedazSet = sprzedazSet;
    }
    
    public String getEtykieta() {
        return this.imie + " " + this.nazwisko;
    }

    public Set<Naprawa> getNaprawaSet() {
        return naprawaSet;
    }

    public void setNaprawaSet(Set<Naprawa> naprawaSet) {
        this.naprawaSet = naprawaSet;
    }
}