/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukorsoft.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "inny_towar")
@NamedQueries({
    @NamedQuery(name = "InnyTowar.findAll", query = "SELECT i FROM InnyTowar i")
    , @NamedQuery(name = "InnyTowar.findById", query = "SELECT i FROM InnyTowar i WHERE i.id = :id")
    , @NamedQuery(name = "InnyTowar.findByNazwa", query = "SELECT i FROM InnyTowar i WHERE i.nazwa = :nazwa")})
public class InnyTowar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "nazwa", length = 100)
    private String nazwa;
    @OneToMany(mappedBy = "innyTowar", fetch = FetchType.EAGER)
    private Set<Sprzedaz> sprzedazSet;

    public InnyTowar() {
    }

    public InnyTowar(Integer id) {
        this.id = id;
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
        if (!(object instanceof InnyTowar)) {
            return false;
        }
        InnyTowar other = (InnyTowar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.InnyTowar[ id=" + id + " ]";
    }
    
}
