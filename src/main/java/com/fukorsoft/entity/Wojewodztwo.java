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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "wojewodztwo")
@NamedQueries({
    @NamedQuery(name = "Wojewodztwo.findAll", query = "SELECT w FROM Wojewodztwo w")
    , @NamedQuery(name = "Wojewodztwo.findById", query = "SELECT w FROM Wojewodztwo w WHERE w.id = :id")
    , @NamedQuery(name = "Wojewodztwo.findByNazwa", query = "SELECT w FROM Wojewodztwo w WHERE w.nazwa = :nazwa")})
public class Wojewodztwo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wojewodztwo", fetch = FetchType.EAGER)
    private Set<Miejscowosc> miejscowoscSet;

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
    @JoinColumn(name = "panstwo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Panstwo panstwo;

    public Wojewodztwo() {
    }

    public Wojewodztwo(Integer id) {
        this.id = id;
    }

    public Wojewodztwo(Integer id, String nazwa) {
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

    public Panstwo getPanstwo() {
        return panstwo;
    }

    public void setPanstwo(Panstwo panstwo) {
        this.panstwo = panstwo;
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
        if (!(object instanceof Wojewodztwo)) {
            return false;
        }
        Wojewodztwo other = (Wojewodztwo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.Wojewodztwo[ id=" + id + " ]";
    }

    public Set<Miejscowosc> getMiejscowoscSet() {
        return miejscowoscSet;
    }

    public void setMiejscowoscSet(Set<Miejscowosc> miejscowoscSet) {
        this.miejscowoscSet = miejscowoscSet;
    }
    
}
