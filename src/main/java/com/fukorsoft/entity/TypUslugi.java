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

@Entity
@Table(name = "typ_uslugi")
@NamedQueries({
    @NamedQuery(name = "TypUslugi.findAll", query = "SELECT t FROM TypUslugi t")
    , @NamedQuery(name = "TypUslugi.findById", query = "SELECT t FROM TypUslugi t WHERE t.id = :id")
    , @NamedQuery(name = "TypUslugi.findByNazwa", query = "SELECT t FROM TypUslugi t WHERE t.nazwa = :nazwa")})
public class TypUslugi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "nazwa", nullable = false, length = 1000)
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typUslugi", fetch = FetchType.EAGER)
    private Set<Naprawa> naprawaSet;

    public TypUslugi() {
    }

    public TypUslugi(Integer id) {
        this.id = id;
    }

    public TypUslugi(Integer id, String nazwa) {
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

    public Set<Naprawa> getNaprawaSet() {
        return naprawaSet;
    }

    public void setNaprawaSet(Set<Naprawa> naprawaSet) {
        this.naprawaSet = naprawaSet;
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
        if (!(object instanceof TypUslugi)) {
            return false;
        }
        TypUslugi other = (TypUslugi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.TypUslugi[ id=" + id + " ]";
    }
    
}
