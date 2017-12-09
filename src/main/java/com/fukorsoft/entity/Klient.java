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

@Entity
@Table(name = "klient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k")
    , @NamedQuery(name = "Klient.findById", query = "SELECT k FROM Klient k WHERE k.id = :id")
    , @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie")
    , @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko")
    , @NamedQuery(name = "Klient.findByDataWprowadzenia", query = "SELECT k FROM Klient k WHERE k.dataWprowadzenia = :dataWprowadzenia")
    , @NamedQuery(name = "Klient.findByZakladId", query = "SELECT k FROM Klient k WHERE k.zaklad.id = :zaklad_id")})
public class Klient implements Serializable {

    @OneToMany(mappedBy = "klient", fetch = FetchType.EAGER)
    private Set<Naprawa> naprawaSet;

    @OneToMany(mappedBy = "klient", fetch = FetchType.EAGER)
    private Set<Sprzedaz> sprzedazSet;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klient", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Adres> adresSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
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
    @Basic(optional = true)
    //@NotNull
    @Column(name = "data_wprowadzenia", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWprowadzenia;
    @JoinColumn(name = "wprowadzil_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Uzytkownik uzytkownik;
    @JoinColumn(name = "zaklad_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zaklad zaklad;
    
    @Size(max = 100)
    @Column(name = "email", nullable = true, length = 200)
    private String email;
    
    @Size(max = 20)
    @Column(name = "telefon", nullable = true, length = 20)
    private String telefon;

    public Klient() {
    }

    public Klient(Integer id) {
        this.id = id;
    }

    //public Klient(Integer id, String imie, String nazwisko, Date dataWprowadzenia) {
    public Klient(Integer id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        //this.dataWprowadzenia = dataWprowadzenia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDataWprowadzenia() {
        return dataWprowadzenia;
    }

    public void setDataWprowadzenia(Date dataWprowadzenia) {
        this.dataWprowadzenia = dataWprowadzenia;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public Zaklad getZaklad() {
        return zaklad;
    }

    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
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
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.klient.entity.Klient[ id=" + id + " ]";
    }

    public Set<Adres> getAdresSet() {
        return adresSet;
    }

    public void setAdresSet(Set<Adres> adresSet) {
        this.adresSet = adresSet;
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