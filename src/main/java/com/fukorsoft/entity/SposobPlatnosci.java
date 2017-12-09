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

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "sposob_platnosci")
@NamedQueries({
    @NamedQuery(name = "SposobPlatnosci.findAll", query = "SELECT s FROM SposobPlatnosci s")
    , @NamedQuery(name = "SposobPlatnosci.findById", query = "SELECT s FROM SposobPlatnosci s WHERE s.id = :id")
    , @NamedQuery(name = "SposobPlatnosci.findByNazwa", query = "SELECT s FROM SposobPlatnosci s WHERE s.nazwa = :nazwa")})
public class SposobPlatnosci implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sposobPlatnosci", fetch = FetchType.EAGER)
    private Set<Naprawa> naprawaSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nazwa", nullable = false, length = 20)
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sposobPlatnosci", fetch = FetchType.EAGER)
    private Set<Sprzedaz> sprzedazSet;

    public SposobPlatnosci() {
    }

    public SposobPlatnosci(Integer id) {
        this.id = id;
    }

    public SposobPlatnosci(Integer id, String nazwa) {
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

    public Set<Sprzedaz> getSprzedazSet() {
        return sprzedazSet;
    }

    public void setSprzedazSet(Set<Sprzedaz> sprzedazSet) {
        this.sprzedazSet = sprzedazSet;
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
        if (!(object instanceof SposobPlatnosci)) {
            return false;
        }
        SposobPlatnosci other = (SposobPlatnosci) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.SposobPlatnosci[ id=" + id + " ]";
    }

    public Set<Naprawa> getNaprawaSet() {
        return naprawaSet;
    }

    public void setNaprawaSet(Set<Naprawa> naprawaSet) {
        this.naprawaSet = naprawaSet;
    }
    
}
