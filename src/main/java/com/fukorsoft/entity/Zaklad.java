/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukorsoft.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "zaklad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaklad.findAll", query = "SELECT z FROM Zaklad z")
    , @NamedQuery(name = "Zaklad.findById", query = "SELECT z FROM Zaklad z WHERE z.id = :id")
    , @NamedQuery(name = "Zaklad.findByNazwa", query = "SELECT z FROM Zaklad z WHERE z.nazwa = :nazwa")})
public class Zaklad implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zaklad", fetch = FetchType.EAGER)
    private Set<Naprawa> naprawaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zaklad", fetch = FetchType.EAGER)
    private Set<Sprzedaz> sprzedazSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nazwa", nullable = false, length = 100)
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zaklad", fetch = FetchType.EAGER)
    private Set<Klient> klientSet;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zaklad", fetch = FetchType.EAGER)
    private Set<Uzytkownik> uzytkownikSet;

    public Zaklad() {
    }

    public Zaklad(Integer id) {
        this.id = id;
    }

    public Zaklad(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Set<Klient> getKlientSet() {
        return klientSet;
    }

    public void setKlientSet(Set<Klient> klientSet) {
        this.klientSet = klientSet;
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
        if (!(object instanceof Zaklad)) {
            return false;
        }
        Zaklad other = (Zaklad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.klient.entity.Zaklad[ id=" + id + " ]";
    }

    public Set<Sprzedaz> getSprzedazSet() {
        return sprzedazSet;
    }

    public void setSprzedazSet(Set<Sprzedaz> sprzedazSet) {
        this.sprzedazSet = sprzedazSet;
    }
    
    @XmlTransient
    public Set<Uzytkownik> getUzytkownikSet() {
        return uzytkownikSet;
    }

    public void setUzytkownikSet(Set<Uzytkownik> uzytkownikSet) {
        this.uzytkownikSet = uzytkownikSet;
    }

    public Set<Naprawa> getNaprawaSet() {
        return naprawaSet;
    }

    public void setNaprawaSet(Set<Naprawa> naprawaSet) {
        this.naprawaSet = naprawaSet;
    }
    
}
